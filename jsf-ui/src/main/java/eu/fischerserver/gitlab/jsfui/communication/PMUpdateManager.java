package eu.fischerserver.gitlab.jsfui.communication;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

@Named
@ApplicationScoped
public class PMUpdateManager {
    private final PMUpdateServer updateServer = new PMUpdateServer();

    public void subscribe(Consumer<PMData> callback) {
        updateServer.subscribe(new PMUpdateClient(callback));
    }

    public PMData waitForUpdate() {
        AtomicReference<Optional<PMData>> dataAtomicReference = new AtomicReference<>(Optional.empty());
        subscribe(pmData -> dataAtomicReference.set(Optional.of(pmData)));
        try {
            while (dataAtomicReference.get().isEmpty())
                //noinspection BusyWait
                Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return dataAtomicReference.get().get();
    }

    public void update(PMData data) {
        updateServer.offer(data, (subscriber, videoFrame) -> {
            subscriber.onError(new RuntimeException("Update#" + data.id() + " dropped because of backpressure"));
            return true;
        });
    }
}
