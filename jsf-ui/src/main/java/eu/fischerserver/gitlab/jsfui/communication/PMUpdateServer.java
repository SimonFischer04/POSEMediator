package eu.fischerserver.gitlab.jsfui.communication;

import java.util.concurrent.Executors;
import java.util.concurrent.SubmissionPublisher;

public class PMUpdateServer extends SubmissionPublisher<PMData> {
    public PMUpdateServer() {
        super(Executors.newSingleThreadExecutor(), 5);
    }
}
