package no.magott.spring.batch.mapper;

import no.magott.spring.batch.domain.Postnummer;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class PostnummerFieldSetMapper implements FieldSetMapper<Postnummer>{

    public Postnummer mapFieldSet(FieldSet fieldSet) throws BindException {
        String postnr = fieldSet.readString("Postnummer");
        String poststed = fieldSet.readString("Poststed");
        return new Postnummer(postnr, poststed);
    }

}
