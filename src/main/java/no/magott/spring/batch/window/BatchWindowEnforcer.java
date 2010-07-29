package no.magott.spring.batch.window;

import java.util.Date;
import java.util.concurrent.TimeoutException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.listener.StepListenerSupport;
import org.springframework.batch.repeat.RepeatException;
import org.springframework.util.Assert;

public class BatchWindowEnforcer extends StepListenerSupport<Object, Object> {

    private static final Log log = LogFactory.getLog(BatchWindowEnforcer.class);   
    
    //Should probably be changed to take a cron expression
    private Date closingTime;
 
    @Override
    public void afterChunk() {
        Assert.notNull(closingTime, "ClosingTime must be set");
    	if(new Date().after(closingTime)){
            String message = "Batch window has passed, batch will exit with data left to process";
            log.debug(message);
            throw new RepeatException("Planned failure on timeout", new TimeoutException(message));
        }
        log.trace("Timeout not reached");
    }

	public void setClosingTime(long closingTimeInMillis) {
		this.closingTime = new Date(closingTimeInMillis);
	}


}
