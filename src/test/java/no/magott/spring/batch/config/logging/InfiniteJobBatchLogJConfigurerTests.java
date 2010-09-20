package no.magott.spring.batch.config.logging;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = { "classpath:infiniteJob-log4jconfigurer.xml",
		"classpath:springbatch-common.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class InfiniteJobBatchLogJConfigurerTests {

   
	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;

	@Test(timeout = 60000)
	public void testLaunchJob() throws Exception {

		JobParameters params = new JobParametersBuilder().addLong("batchwindow.closingTime", getClosingTime()).toJobParameters();
		JobExecution jobExecution = jobLauncher.run(job, params);

		assertEquals(ExitStatus.STOPPED, jobExecution.getExitStatus());

		/*
		 * Checks that the step execution contains exit description for batch
		 * window timeout
		 */
		String description = jobExecution.getStepExecutions().iterator().next().getExitStatus().getExitDescription();
		assertEquals("org.springframework.batch.core.JobInterruptedException; "
				+ "Batch window has passed, batch will exit with data left to process", description);


	}

	private Long getClosingTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MILLISECOND, 10000);
		return (Long) calendar.getTime().getTime();
	}

}
