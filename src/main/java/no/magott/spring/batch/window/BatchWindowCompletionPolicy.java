package no.magott.spring.batch.window;

import java.util.Date;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.repeat.RepeatContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.batch.repeat.policy.SimpleCompletionPolicy;
import org.springframework.util.Assert;

public class BatchWindowCompletionPolicy extends SimpleCompletionPolicy{

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
            log.debug("Batch window has passed, batch will exit with data left to process");
            //Seems to be no difference between terminateOnly and completeOnly?
            context.getParent().setTerminateOnly();
            return true;
        }
        log.trace("Timeout not reached");
        return false;
    }

	public void setClosingTime(long closingTimeInMillis) {
		this.closingTime = new Date(closingTimeInMillis);
	}


}
