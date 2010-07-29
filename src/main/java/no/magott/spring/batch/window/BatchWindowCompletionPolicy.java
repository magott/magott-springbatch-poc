package no.magott.spring.batch.window;

import java.util.Date;
import java.util.concurrent.TimeoutException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.repeat.RepeatContext;
import org.springframework.batch.repeat.RepeatException;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.batch.repeat.policy.DefaultResultCompletionPolicy;
import org.springframework.util.Assert;

public class BatchWindowCompletionPolicy extends DefaultResultCompletionPolicy {

    private static final Log log = LogFactory.getLog(BatchWindowCompletionPolicy.class);   
    
    //Should probably be changed to take a cron expression
    private Date closingTime;
    
    public boolean isComplete(RepeatContext context) {
        return super.isComplete(context) || isBatchWindowClosed(context);
    }

    public boolean isComplete(RepeatContext context, RepeatStatus result) {
        return super.isComplete(context, result) || isBatchWindowClosed(context);
    }

    private boolean isBatchWindowClosed(RepeatContext context) {
        Assert.notNull(closingTime, "ClosingTime must be set");
    	if(new Date().after(closingTime)){
            String message = "Batch window has passed, batch will exit with data left to process";
            log.debug(message);
            throw new RepeatException("Planned failure on timeout", new TimeoutException(message));
        }
        log.trace("Timeout not reached");
        return false;
    }

	public void setClosingTime(long closingTimeInMillis) {
		this.closingTime = new Date(closingTimeInMillis);
	}


}
