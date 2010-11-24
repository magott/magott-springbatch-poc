package no.magott.spring.batch.scope;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.batch.test.StepScopeTestExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@ContextConfiguration(locations={"classpath:stepscope-factorybean-test.xml"})
@TestExecutionListeners( { DependencyInjectionTestExecutionListener.class, 
    StepScopeTestExecutionListener.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class ConfigurationTest {
    
    private final String THE_STRING = "Hello World!";

    @Autowired
    private Config config;

    
    @Test
    public void configIsInjectedWithValueSuppliedFromJobParameter(){
        assertThat(config, notNullValue());
        assertThat(config.getMagicString(), notNullValue());
    }
    
    
    
    public StepExecution getStepExection() {
        JobParameters parameters = new JobParametersBuilder().addString("magicString", THE_STRING).toJobParameters(); 
        StepExecution execution = MetaDataInstanceFactory.createStepExecution(parameters);
        return execution;
    }   
}
