package eu.fischerserver.gitlab.pm.jsfui.servlet;

import eu.fischerserver.gitlab.pm.jsfui.main.Mediator;
import eu.fischerserver.gitlab.pm.jsfui.main.include.update.PMUpdateManager;
import eu.fischerserver.gitlab.pm.jsfui.model.PMData;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/sseServlet")
public class SSEServlet extends HttpServlet {
    @Inject
    PMUpdateManager updateManager;

    @Inject
    Mediator mediator;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/event-stream");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        // NOTE: no, directly subscribing here does not work, because then stream is closed
        try {
            // send initial state on connect
            send(out, mediator.getCurrentState());

            //noinspection InfiniteLoopStatement
            while (true) {
                PMData data = updateManager.waitForUpdate(); // get the updated data to send
                send(out, data);
                //noinspection BusyWait
                Thread.sleep(100); // wait for min 0.1 second before sending the next event
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void send(PrintWriter out, PMData data) {
        Jsonb jsonb = JsonbBuilder.create();
        String jsonString = jsonb.toJson(data);
        out.write("data: " + jsonString + "\n\n");
        out.flush();
    }
}
