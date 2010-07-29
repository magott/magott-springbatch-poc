package no.magott.spring.batch.window;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations={"classpath:infiniteJob-batchwindow-completionpolicy-cutoff.xml", "classpath:springbatch-common.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class InfiniteJobBatchWindowCompletionPolicyTests {

	private static final int DELAY_FOR_BATCHWINDOW_IN_MILLIS = 1000;
	private static final int TIMEOUT_LENIENCE_IN_MILLIS = 1000;
	
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;
    
    @Test(timeout=TIMEOUT_LENIENCE_IN_MILLIS+DELAY_FOR_BATCHWINDOW_IN_MILLIS)
    public void testLaunchJob() throws Exception {

    	JobParameters params = new JobParametersBuilder().addLong("batchwindow.closingTime", getClosingTime()).toJobParameters();
    	JobExecution jobExecution = jobLauncher.run(job, params);

    	assertEquals(BatchStatus.FAILED, jobExecution.getStatus());
    	StepExecution stepExecution = jobExecution.getStepExecutions().iterator().next();
    	assertEquals(BatchStatus.FAILED, stepExecution.getStatus());
		String description = stepExecution.getExitStatus().getExitDescription();
		assertTrue("Wrong description: "+description, description.contains("java.util.concurrent.TimeoutException: Batch window has passed"));

    }
    
    private Long getClosingTime(){
    	Calendar calendar = Calendar.getInstance();
    	calendar.add(Calendar.MILLISECOND, DELAY_FOR_BATCHWINDOW_IN_MILLIS);
    	return (Long)calendar.getTime().getTime();
    }
    
}
