package no.magott.spring.batch.listener;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.batch.core.listener.SkipListenerSupport;

public class LoggingSkipListener extends SkipListenerSupport<Object, Object> {

    Logger log = LogManager.getLogger(LoggingSkipListener.class);
    
    @Override
    public void onSkipInRead(Throwable t) {
        log.info("Element ble skippet",t);
    }

}
