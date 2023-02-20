package eu.fischerserver.gitlab.jsfui.communication;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import org.springframework.messaging.simp.stomp.*;
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
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

@Named
@ApplicationScoped
public class WSClient {
    private static final String WS_URI = "ws://localhost:42000/pm-websocket";
    private static final String SEND_PREFIX = "/app";
    private static final String RECEIVE_PREFIX = "/topic";

    private static final Logger logger = Logger.getLogger(WSClient.class.getName());
    private Optional<StompSession> session = Optional.empty();

    public WSClient() {
        init();
    }

    private void init() {
        var sessionCompletableFuture = connect();
        sessionCompletableFuture
                .thenAccept(session -> this.session = Optional.of(session))
                .thenRun(this::subscribe)
        ;
    }

    public CompletableFuture<StompSession> connect() {
        Transport webSocketTransport = new WebSocketTransport(new StandardWebSocketClient());
        List<Transport> transports = Collections.singletonList(webSocketTransport);

        SockJsClient sockJsClient = new SockJsClient(transports);
        sockJsClient.setMessageCodec(new Jackson2SockJsMessageCodec());

        WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);

        return stompClient.connectAsync(WS_URI, new StompSessionHandlerAdapter() {
            @Override
            public void afterConnected(StompSession stompSession, StompHeaders stompHeaders) {
                logger.info("Now connected");
            }

            @Override
            public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
                System.out.println("STOMP session exception: " + exception.getMessage());
            }
        });
    }

    public void subscribe() {
        if (this.session.isEmpty()) {
            logger.severe("SUBSCRIBE CALLED, BUT SESSION DOES NOT EXIST");
            return;
        }

        var session = this.session.get();

        session.subscribe(getReceivingTopicPath("greetings"), new StompFrameHandler() {
            public Type getPayloadType(StompHeaders stompHeaders) {
                return byte[].class;
            }

            public void handleFrame(StompHeaders stompHeaders, Object o) {
                logger.info("Received greeting " + new String((byte[]) o));
            }
        });
    }

    public void send(String topic, Object data) {
        if (session.isEmpty()) {
            System.err.println("SEND called before websocket connected!");
            return;
        }
        Jsonb jsonb = JsonbBuilder.create();
        String jsonString = jsonb.toJson(data);
        session.get().send("%s/%s".formatted(SEND_PREFIX, topic), jsonString.getBytes());
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
