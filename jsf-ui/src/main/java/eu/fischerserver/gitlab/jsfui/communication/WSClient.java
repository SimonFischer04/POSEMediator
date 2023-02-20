package eu.fischerserver.gitlab.jsfui.communication;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
//import org.springframework.messaging.simp.stomp.*;
//import org.springframework.web.socket.client.standard.StandardWebSocketClient;
//import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;
import java.util.Optional;

@Named
@ApplicationScoped
public class WSClient {
//    private static final String WS_URI = "ws://localhost:42000/pm-websocket";
//    private static final String SEND_PREFIX = "/app";
//    private static final String RECEIVE_PREFIX = "/topic";
//
//    private final WebSocketStompClient stompClient;
//    private Optional<StompSession> session = Optional.empty();
//
//    public WSClient() {
//        stompClient = new WebSocketStompClient(new StandardWebSocketClient());
//        init();
//    }
//
//    private void init() {
//        StompSessionHandler sessionHandler = new StompSessionHandlerAdapter() {
//            @Override
//            public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
//                System.out.println("Connected to STOMP server");
//                session.subscribe(getReceivingTopicPath("greetings"), new StompFrameHandler() {
//                    @Override
//                    public Type getPayloadType(StompHeaders headers) {
//                        return String.class;
//                    }
//
//                    @Override
//                    public void handleFrame(StompHeaders headers, Object payload) {
//                        String message = (String) payload;
//                        System.out.println("Received message: " + message);
//                    }
//                });
//            }
//
//            @Override
//            public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
//                System.out.println("STOMP session exception: " + exception.getMessage());
//            }
//        };
//        var sessionCompletableFuture = stompClient.connectAsync(WS_URI, sessionHandler);
//        sessionCompletableFuture.thenAccept(session -> this.session = Optional.of(session));
//    }
//
//    public void send(String topic, Object value) {
//        if (session.isEmpty()) {
//            System.err.println("SEND called before websocket connected!");
//            return;
//        }
//        session.get().send("%s/%s".formatted(SEND_PREFIX, topic), value);
//    }
//
//    public void disconnect() {
//        if (session.isEmpty()) {
//            System.out.println("WARNING: websocket disconnect called, but not connected");
//            return;
//        }
//
//        session.get().disconnect();
//    }
//
//    private String getReceivingTopicPath(@SuppressWarnings("SameParameterValue") String topic) {
//        return "%s/%s".formatted(RECEIVE_PREFIX, topic);
//    }
}
