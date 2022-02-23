package com.example.PersonDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.sql.DataSource;
import java.util.List;

@Controller
public class PersonController {

   // @Autowired
   // private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/")
    public String showPerson(Model model) {
      //  JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        int countPersonInDB = jdbcTemplate.queryForObject("SELECT COUNT (*) FROM PERSON", Integer.class);
        List<Person> personList =jdbcTemplate.query("SELECT * FROM PERSON", new PersonRowMapper());

        model.addAttribute("countPersonInDB", countPersonInDB);
        model.addAttribute("personToSave", new Person());
        model.addAttribute("personList", personList);
        return "index";
    }

    @PostMapping("savePerson")
    public String savePerson(Model model, Person person) {
//        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("INSERT INTO PERSON VALUES(?, ?, ?, ?, ?, ?, ?, ?)",
                person.getId(),
                person.getName(),
                person.getBirthday(),
                person.getEmail(),
                person.getStreetName(),
                person.getStreetNumber(),
                person.getPostalCode(),
                person.getCity());

        return this.showPerson(model);
    }
}
