package eu.fischerserver.gitlab.jsfui.communication;

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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/event-stream");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        // NOTE: no, directly subscribing here does not work, because then stream is closed
        try {
            //noinspection InfiniteLoopStatement
            while (true) {
                PMData data = updateManager.waitForUpdate(); // get the updated data to send
                Jsonb jsonb = JsonbBuilder.create();
                String jsonString = jsonb.toJson(data);
                out.write("data: " + jsonString + "\n\n");
                out.flush();
                //noinspection BusyWait
                Thread.sleep(100); // wait for min 0.1 second before sending the next event
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
