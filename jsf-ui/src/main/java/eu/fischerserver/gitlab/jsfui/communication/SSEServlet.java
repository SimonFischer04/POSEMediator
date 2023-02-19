package eu.fischerserver.gitlab.jsfui.communication;

import jakarta.inject.Inject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/sseServlet")
public class SSEServlet extends HttpServlet {
    @Inject
    SSEUpdateQueue updateQueue;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/event-stream");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        try {
            //noinspection InfiniteLoopStatement
            while (true) {
                String data = updateQueue.take(); // get the updated data to send
                out.write("data: " + data + "\n\n");
                out.flush();
                //noinspection BusyWait
                Thread.sleep(100); // wait for min 0.1 second before sending the next event
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
