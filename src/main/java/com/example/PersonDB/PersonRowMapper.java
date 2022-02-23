package com.example.PersonDB;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonRowMapper implements RowMapper<Person> {


    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        //ResultSet ist ein Datensatz aus der Tabelle
        Person result = new Person();
        result.setId(rs.getInt("id"));
        result.setName(rs.getString("name"));
        result.setBirthday(rs.getString("birthday"));
        result.setEmail(rs.getString("email"));
        result.setStreetName(rs.getString("streetName"));
        result.setStreetNumber(rs.getInt("streetNumber"));
        result.setPostalCode(rs.getInt("postalCode"));
        result.setCity(rs.getString("city"));
        return result;
    }
}
