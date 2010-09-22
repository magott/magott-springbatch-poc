package no.magott.spring.batch;

import org.springframework.batch.item.ItemProcessor;

public class ExceptionThrowingProcessor implements ItemProcessor<String, String> {

    int count = 0;
    public String process(String arg0) throws Exception {
        if(++count > 12){
            throw new IllegalArgumentException("foo");
        }
        return arg0;
    }


}
