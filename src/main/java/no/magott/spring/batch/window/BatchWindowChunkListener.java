package no.magott.spring.batch.window;

import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.ChunkListenerSupport;
import org.springframework.scheduling.support.CronSequenceGenerator;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public class BatchWindowChunkListener extends ChunkListenerSupport {

    private StepExecution stepExecution;

    private Date closingTime;
    private String cronDate;

    private Log log = LogFactory.getLog(BatchWindowChunkListener.class);

    public void setStepExecution(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
        setWindowClosing(cronDate);
    }

    public void setClosingTime(long closingTimeInMillis) {
        this.closingTime = new Date(closingTimeInMillis);
    }

    public void beforeChunk() {
        verifyBatchWindowStillOpen();
    }

    private void verifyBatchWindowStillOpen() {
        Assert.notNull(closingTime, "ClosingTime must be set");
        if (new Date().after(closingTime)) {
            stop();//Hammertime
        }
        log.trace("Batch window is still open");
    }

    private void stop() {
        String message = "Batch window has passed, batch will exit with data left to process. Window set to close at "+closingTime;
        stepExecution.setTerminateOnly();
        stepExecution.setExitStatus(stepExecution.getExitStatus().addExitDescription(message));
    }

    private void setWindowClosing(String cronExpression) {
        if (StringUtils.hasLength(cronExpression)) {
            closingTime = new CronSequenceGenerator(cronExpression, TimeZone.getDefault()).next(new Date());
            log.info("Closing time set by cron expression. Batch window will close at: "+closingTime);
            
        }
    }

    public void setCronDate(String cronDate) {
        this.cronDate = cronDate;
    }

}
