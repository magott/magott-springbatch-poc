package no.magott.spring.batch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemProcessor;

public class ExceptionThrowingProcessor implements ItemProcessor<String, String> {

    private static final Log log = LogFactory.getLog(ExceptionThrowingProcessor.class);
    
    int count = 0;
    public String process(String arg0) throws Exception {
        if(++count > 12){
            throw new IllegalArgumentException("foo");
        }
        log.debug("Process count "+count);
        return arg0;
    }


}
