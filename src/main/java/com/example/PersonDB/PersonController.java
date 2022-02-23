package com.example.PersonDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.sql.DataSource;

@Controller
public class PersonController {
    @Autowired
    private DataSource dataSource;

    @GetMapping("/")
    public String showPerson(Model model) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        int countPersonInDB = jdbcTemplate.queryForObject("SELECT COUNT (*) FROM PERSON", Integer.class);

        model.addAttribute("personToSave", new Person());
        model.addAttribute("countPersonInDB", countPersonInDB);
        return "index";
    }

    @PostMapping("savePerson")
    public String savePerson(Model model, Person person) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("INSERT INTO PERSON VALUES(?, ?, ?, ?, ?, ?, ?, ?)", person.getId(), person.getName(), person.getBirthday(),
                person.getEmail(), person.getStreet(), person.getStreetNumber(), person.getPostalCode(), person.getCity());

        int countPersonInDB = jdbcTemplate.queryForObject("SELECT COUNT (*) FROM PERSON", Integer.class);

        model.addAttribute("personToSave", new Person());
        model.addAttribute("countPersonInDB", countPersonInDB);

        return "index";
    }
}
