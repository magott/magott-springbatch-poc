package no.magott.spring.batch.processor;

import no.magott.spring.batch.domain.Postnummer;

import org.apache.commons.lang.StringUtils;
import org.springframework.batch.item.ItemProcessor;

public class PostnummerReverseProcessor implements ItemProcessor<Postnummer, Postnummer> {

    public Postnummer process(Postnummer item) throws Exception {
        return new Postnummer(StringUtils.reverse(item.getPostnummer()), StringUtils.reverse(item.getPoststed()));
    }

}
