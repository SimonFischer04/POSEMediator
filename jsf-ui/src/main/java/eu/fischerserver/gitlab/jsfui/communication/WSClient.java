package eu.fischerserver.gitlab.jsfui.communication;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;
import org.springframework.web.socket.sockjs.frame.Jackson2SockJsMessageCodec;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

@Named
@ApplicationScoped
public class WSClient {
    private static final String WS_URI = "ws://localhost:42000/pm-websocket";
    private static final String SEND_PREFIX = "/app";
    private static final String RECEIVE_PREFIX = "/topic";

    private final WebSocketStompClient stompClient;
    private Optional<StompSession> session = Optional.empty();

    public WSClient() {
        stompClient = new WebSocketStompClient(new StandardWebSocketClient());
        init();
    }

    private void init() {
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

        ListenableFuture<StompSession> f = connect();

        try {
            StompSession stompSession = f.get();
            logger.info("Subscribing to greeting topic using session " + stompSession);
            subscribeGreetings(stompSession);

            logger.info("Sending hello message" + stompSession);
            sendHello(stompSession);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }


    }

    private static Logger logger = Logger.getLogger(WSClient.class.getName());

    private final static WebSocketHttpHeaders headers = new WebSocketHttpHeaders();

    public ListenableFuture<StompSession> connect() {

        Transport webSocketTransport = new WebSocketTransport(new StandardWebSocketClient());
        List<Transport> transports = Collections.singletonList(webSocketTransport);

        SockJsClient sockJsClient = new SockJsClient(transports);
        sockJsClient.setMessageCodec(new Jackson2SockJsMessageCodec());

        WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);

        String url = "ws://{host}:{port}/pm-websocket";
        return stompClient.connect(url, headers, new MyHandler(), "localhost", 42000);
    }

    public void subscribeGreetings(StompSession stompSession) throws ExecutionException, InterruptedException {
        stompSession.subscribe("/topic/greetings", new StompFrameHandler() {

            public Type getPayloadType(StompHeaders stompHeaders) {
                return byte[].class;
            }

            public void handleFrame(StompHeaders stompHeaders, Object o) {
                logger.info("Received greeting " + new String((byte[]) o));
            }
        });
    }

    public void sendHello(StompSession stompSession) {
        String jsonHello = "{ \"name\" : \"Nick\" }";
        stompSession.send("/app/hello", jsonHello.getBytes());
    }

    private class MyHandler extends StompSessionHandlerAdapter {
        public void afterConnected(StompSession stompSession, StompHeaders stompHeaders) {
            logger.info("Now connected");
        }
    }

    // ----------

    public void send(String topic, Object value) {
        if (session.isEmpty()) {
            System.err.println("SEND called before websocket connected!");
            return;
        }
        session.get().send("%s/%s".formatted(SEND_PREFIX, topic), value);
    }

    public void disconnect() {
        if (session.isEmpty()) {
            System.out.println("WARNING: websocket disconnect called, but not connected");
            return;
        }

        session.get().disconnect();
    }

    private String getReceivingTopicPath(@SuppressWarnings("SameParameterValue") String topic) {
        return "%s/%s".formatted(RECEIVE_PREFIX, topic);
    }
}
