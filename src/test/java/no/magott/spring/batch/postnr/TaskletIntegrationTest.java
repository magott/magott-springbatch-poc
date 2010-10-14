package no.magott.spring.batch.postnr;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@ContextConfiguration(locations = { "classpath:postnr-taskletstep-ctx.xml",
"classpath:springbatch-common.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TaskletIntegrationTest extends JobLauncherTestUtils{

    @Test
    public void jobConfigIsParsedWithoutErrors(){
        assertThat(getJob(), is(notNullValue()));
    }
    
    @Test
    public void runJob() throws JobExecutionException{
        JobParameters jobParameters = new JobParametersBuilder().addLong("systime", System.currentTimeMillis()).toJobParameters();
        JobExecution jobExecution = getJobLauncher().run(getJob(), jobParameters);
        assertThat(jobExecution.getExitStatus().getExitCode(), equalTo(ExitStatus.COMPLETED.getExitCode()));
    }
	
}
