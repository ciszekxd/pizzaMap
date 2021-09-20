package com.example.mobilkidbbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



@RestController
public class Controllers {

    @Autowired
    JdbcTemplate DB;

    @GetMapping("/")
    public String index() {

        return prepareJSON();
    }

    String prepareJSON(){
        StringBuilder JSON = new StringBuilder("[");
        String sql = "Select * from pizzerias";
        List<Pizzeria> pizzeriasList = DB.query(sql, new PizzeriaRowMapper());

        for (Pizzeria singlePizzeria: pizzeriasList) {
            JSON.append("{\"Id\":");
            JSON.append(singlePizzeria.getId());
            JSON.append(",\"Name\":\"");
            JSON.append(singlePizzeria.getName());
            JSON.append("\",\"Longitude\":");
            JSON.append(singlePizzeria.getLongitude());
            JSON.append(",\"Latitude\":");
            JSON.append(singlePizzeria.getLatitude());
            JSON.append(",\"Description\":\"");
            JSON.append(singlePizzeria.getDescription());
            JSON.append("\"},");

        }
        JSON.delete(JSON.length()-1,JSON.length());
        JSON.append("]");

        return JSON.toString();
    }

}