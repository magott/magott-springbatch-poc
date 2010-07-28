package no.magott.spring.batch.window;

import static org.junit.Assert.assertNotNull;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations={"classpath:infiniteJob-batchwindow-cutoff.xml", "classpath:springbatch-common.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class InfiniteJobBatchWindowTests {

	private static final int DELAY_FOR_BATCHWINDOW_IN_MILLIS = 5000;
	private static final int TIMEOUT_LENIENCE_IN_MILLIS = 2000;
	
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;
    
    @Test
    public void testSimpleProperties() throws Exception {
        assertNotNull(jobLauncher);
    }
    
    @Test(timeout=TIMEOUT_LENIENCE_IN_MILLIS+DELAY_FOR_BATCHWINDOW_IN_MILLIS)
    public void testLaunchJob() throws Exception {
        JobParameters params = new JobParametersBuilder().addLong("batchwindow.closingTime", getClosingTime()).toJobParameters();
    	jobLauncher.run(job, params);
    }
    
    private Long getClosingTime(){
    	Calendar calendar = Calendar.getInstance();
    	calendar.add(Calendar.MILLISECOND, DELAY_FOR_BATCHWINDOW_IN_MILLIS);
    	return (Long)calendar.getTime().getTime();
    }
    
}
