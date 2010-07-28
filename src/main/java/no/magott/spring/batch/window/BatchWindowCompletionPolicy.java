package no.magott.spring.batch.window;

import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.repeat.RepeatContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.batch.repeat.policy.SimpleCompletionPolicy;

public class BatchWindowCompletionPolicy extends SimpleCompletionPolicy {

    private static final Log log = LogFactory.getLog(BatchWindowCompletionPolicy.class);    
    
    public boolean isComplete(RepeatContext context) {
        return super.isComplete(context) || isBatchWindowClosed(context);
    }

    public boolean isComplete(RepeatContext context, RepeatStatus result) {
        return super.isComplete(context, result) || isBatchWindowClosed(context);
    }

    private boolean isBatchWindowClosed(RepeatContext context) {
        if(new Random().nextBoolean()){
            log.debug("Complete");
            //Terminate only is no different from complete only in the way the step exits.
            context.getParent().setTerminateOnly();
            return true;
        }
        log.debug("Not completed");
        return false;
    }

}
