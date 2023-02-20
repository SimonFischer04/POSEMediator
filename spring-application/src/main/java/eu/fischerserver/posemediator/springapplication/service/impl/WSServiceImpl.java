package eu.fischerserver.posemediator.springapplication.service.impl;

import eu.fischerserver.posemediator.springapplication.config.WebSocketConfig;
import eu.fischerserver.posemediator.springapplication.model.PMData;
import eu.fischerserver.posemediator.springapplication.service.WSService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WSServiceImpl implements WSService {
    private static final String PM_DATA_SEND_PATH = WebSocketConfig.getSendTopicPath("pm-data");
    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public void sendPMDataUpdate(PMData pmData) {
        messagingTemplate.convertAndSend(PM_DATA_SEND_PATH, pmData);
    }
}
