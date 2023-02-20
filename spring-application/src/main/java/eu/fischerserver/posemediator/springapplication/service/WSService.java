package eu.fischerserver.posemediator.springapplication.service;

import eu.fischerserver.posemediator.springapplication.model.PMData;

public interface WSService {
    void sendPMDataUpdate(PMData pmData);
}
