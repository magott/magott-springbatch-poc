package no.magott.spring.batch.tasklet;

import javax.sql.DataSource;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.util.ExecutionContextUserSupport;
import org.springframework.batch.repeat.RepeatStatus;

public class SqlExecutingTasklet implements Tasklet, ItemStream {

    private DataSource dataSource;
    private ExecutionContextUserSupport ecSupport;
    
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        return null;
        
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void close() throws ItemStreamException {
        // TODO Auto-generated method stub
        
    }

    public void open(ExecutionContext executionContext) throws ItemStreamException {
        // TODO Auto-generated method stub
        
    }

    public void update(ExecutionContext executionContext) throws ItemStreamException {
        // TODO Auto-generated method stub
        
    }


}
