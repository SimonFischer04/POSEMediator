package eu.fischerserver.gitlab.jsfui.communication;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Named
@ApplicationScoped
public class SSEUpdateQueue {
    private final BlockingQueue<String> updates = new LinkedBlockingQueue<>();

    public String take() {
        try {
            return updates.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void put(String data) {
        try {
            updates.put(data);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
