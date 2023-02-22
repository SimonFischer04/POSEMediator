package eu.fischerserver.gitlab.pm.jsfui.main.include.update;

import eu.fischerserver.gitlab.pm.jsfui.model.PMData;

import java.util.concurrent.Executors;
import java.util.concurrent.SubmissionPublisher;

public class PMUpdateServer extends SubmissionPublisher<PMData> {
    public PMUpdateServer() {
        super(Executors.newSingleThreadExecutor(), 5);
    }
}
