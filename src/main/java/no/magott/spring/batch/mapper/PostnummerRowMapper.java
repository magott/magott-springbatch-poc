package no.magott.spring.batch.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import no.magott.spring.batch.domain.Postnummer;

import org.springframework.jdbc.core.RowMapper;

public class PostnummerRowMapper implements RowMapper<Postnummer> {

    public Postnummer mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Postnummer(rs.getLong("ID"), rs.getString("POSTNUMMER"), rs.getString("POSTSTED"));
    }

}
