package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.boot.actuate.audit.listener.AuditApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AuditApplicationEventListener {

    private static final Logger LOG = LoggerFactory.getLogger(AuditApplicationEventListener.class);

    @Autowired
    CustomAuditEventRepository customAuditEventRepository;

    @EventListener
    @Async
    public void onAuditEvent(AuditApplicationEvent event) {
        AuditEvent actualAuditEvent = event.getAuditEvent();

        CustomAuditEvent customAuditEvent = new CustomAuditEvent();
        customAuditEvent.setPrincipal(actualAuditEvent.getPrincipal());
        customAuditEvent.setType(actualAuditEvent.getType());
        customAuditEvent.setTimestamp(actualAuditEvent.getTimestamp());

        customAuditEventRepository.save(customAuditEvent);

        LOG.info("On audit application event: timestamp: {}, principal: {}, type: {}, data: {}",
                actualAuditEvent.getTimestamp(),
                actualAuditEvent.getPrincipal(),
                actualAuditEvent.getType(),
                actualAuditEvent.getData()
        );
    }
}
