package no.magott.spring.batch.processor;

import no.magott.spring.batch.domain.Postnummer;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.dao.DeadlockLoserDataAccessException;

public class DeadlockLooserProducerProcessor implements ItemProcessor<Postnummer, Postnummer> {

	private int count;
	
	public Postnummer process(Postnummer item) throws Exception {
		if(++count % 500 == 0){
			throw new DeadlockLoserDataAccessException("Simulated deadlock", null);
		}
		return item;
	}

}
