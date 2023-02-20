package eu.fischerserver.posemediator.springapplication.main.connector.ui;

import eu.fischerserver.posemediator.springapplication.model.PMData;

public interface UIConnector {
    void sendPMDataUpdate(PMData pmData);
}
