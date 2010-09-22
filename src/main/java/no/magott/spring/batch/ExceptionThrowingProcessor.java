package no.magott.spring.batch;

import org.springframework.batch.item.ItemProcessor;

public class ExceptionThrowingProcessor implements ItemProcessor<String, String> {

    public String process(String arg0) throws Exception {
        throw new IllegalArgumentException("foo");
    }


}
