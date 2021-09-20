package com.example.mobilkidbbackend;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PizzeriaRowMapper implements RowMapper<Pizzeria> {

    @Override
    public Pizzeria mapRow(ResultSet rs, int rowNum) throws SQLException {
        Pizzeria pizzeria = new Pizzeria();
        pizzeria.setId(rs.getLong("id"));
        pizzeria.setName(rs.getString("Name"));
        pizzeria.setLatitude(rs.getFloat("Latitude"));
        pizzeria.setLongitude(rs.getFloat("Longitude"));
        pizzeria.setDescription(rs.getString("Description"));

        return pizzeria;
    }
}
