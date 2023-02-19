package eu.fischerserver.gitlab.jsfui.communication;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.Flow;
import java.util.function.Consumer;

@RequiredArgsConstructor
public class PMUpdateClient implements Flow.Subscriber<PMData> {
    private Flow.Subscription subscription = null;
    private final Consumer<PMData> callback;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(PMData item) {
        System.out.printf("update: %s\n", item);
        callback.accept(item);
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        System.err.printf("There is an error in update processing: %s\n", throwable.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println("updates done");
    }

}
