package no.magott.spring.batch.window;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.util.Assert;

public class BatchWindowChunkListener implements ChunkListener {

	private StepExecution stepExecution;
	
	private Date closingTime;
	
	private Log log = LogFactory.getLog(BatchWindowChunkListener.class);
	
	public void setStepExecution(StepExecution stepExecution) {
		this.stepExecution = stepExecution;
	}
	
	public void setClosingTime(long closingTimeInMillis) {
		this.closingTime = new Date(closingTimeInMillis);
	}

	public void afterChunk() {
		//NO-OP
	}

	public void beforeChunk() {
		verifyBatchWindowStillOpen();		
	}
	
    private void verifyBatchWindowStillOpen() {
        Assert.notNull(closingTime, "ClosingTime must be set");
    	if(new Date().after(closingTime)){
            String message = "Batch window has passed, batch will exit with data left to process";
            stepExecution.setTerminateOnly();
            stepExecution.setExitStatus(stepExecution.getExitStatus().addExitDescription(message));
        }
        log.trace("Batch window is still open");
    }

}
