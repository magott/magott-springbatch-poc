package no.magott.spring.configuration.logging;
import static org.junit.Assert.assertTrue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import no.magott.spring.configuration.logging.Log4jDynamicConfigurer;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.lf5.LogLevel;
import org.junit.After;
import org.junit.Test;


public class Log4jConfigurationTest {
	
	private static String relativeFilename = "log4jtest2.properties";
	private static String absoluteFilename;
	
	static{
		String tempDir = System.getProperty("java.io.tmpdir");
		absoluteFilename =new File(tempDir+File.separator+relativeFilename).getAbsolutePath(); 
	}
	
	Log4jDynamicConfigurer log4jDynamicConfigurer;
	
	@Test
	public void logLevelIsChanged() throws Exception{
		writeLog4JFile(LogLevel.INFO);
		PropertyConfigurator.configure(absoluteFilename);
		Logger log = LogManager.getLogger(getClass());
		assertTrue(log.isInfoEnabled());
		log4jDynamicConfigurer = new Log4jDynamicConfigurer();
		log4jDynamicConfigurer.setLocation(absoluteFilename);
		log4jDynamicConfigurer.setRefreshInterval(1);
		log4jDynamicConfigurer.afterPropertiesSet();
		writeLog4JFile(LogLevel.DEBUG);
		Thread.sleep(100);
		assertTrue(log.isDebugEnabled());
	}
	
	
	
	private void writeLog4JFile(LogLevel logLevel) throws IOException {
		File file = new File(absoluteFilename);
		FileOutputStream log4jConfigFileWriter = new FileOutputStream(file,false);
		OutputStreamWriter out = new OutputStreamWriter(log4jConfigFileWriter);
		BufferedWriter writer = new BufferedWriter(out);
		writer.write("log4j.logger."+this.getClass().getName()+"="+logLevel.getLabel());
		writer.close();
		log4jConfigFileWriter.close();		
	}
	
	@After
	public void deleteLog4jFileAndDestroyDynamicConfigurer() throws Exception{
		File file = new File(absoluteFilename);
		assertTrue(file.delete());
		if(log4jDynamicConfigurer!=null){
			log4jDynamicConfigurer.destroy();
		}
	}

	
	
}
