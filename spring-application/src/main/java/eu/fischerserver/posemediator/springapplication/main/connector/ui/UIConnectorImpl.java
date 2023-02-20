package eu.fischerserver.posemediator.springapplication.main.connector.ui;

import eu.fischerserver.posemediator.springapplication.model.PMData;
import eu.fischerserver.posemediator.springapplication.service.WSService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UIConnectorImpl implements UIConnector {
    private final WSService wsService;

    @Override
    public void sendPMDataUpdate(PMData pmData) {
        wsService.sendPMDataUpdate(pmData);
    }
}
