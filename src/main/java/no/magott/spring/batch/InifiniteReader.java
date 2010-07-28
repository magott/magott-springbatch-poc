package no.magott.spring.batch;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class InifiniteReader implements ItemReader<String>{

    private int count=0;
    
    public InifiniteReader() {
        // TODO Auto-generated constructor stub
    }

    public String read() throws Exception, UnexpectedInputException, ParseException {
        return "Read "+ ++count;
    }

}
