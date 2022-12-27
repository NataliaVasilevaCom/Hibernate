package ru.alishev.springcourse.dao;

//import jdk.vm.ci.meta.SpeculationLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;

import java.net.ConnectException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Neil Alishev
 */
@Component
public class PersonDAO {
    

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(full_name, year_of_birth) VALUES(?, ?)", person.getFullName(),
                person.getYearOfBirth());
    }


    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET full_name=?, year_of_birth=? WHERE id=?", updatedPerson.getFullName(),
                updatedPerson.getYearOfBirth(), id);
    }
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);

    }

    public Optional<Person> getPersonByFullName(String fullName) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE full_name=?", new Object[]{fullName},
        new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public List<Book> getBooksByPerson(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id=?", new Object[]{id},
        new BeanPropertyRowMapper<>(Book.class));
    }
/////////////////test batch update

    /*public void testMultipleUpdate() {
        List<Person> people = create1000people();
        long before = System.currentTimeMillis();
        for (Person person: people) {
            jdbcTemplate.update("INSERT INTO Person VALUES(?, ?, ?, ?)", person.getName(),
                    person.getAge(), person.getEmail());
        }
        long after = System.currentTimeMillis();

        System.out.println("Time " + (after-before));
    }


    public void testBatchUpdate() {
        List<Person> people = create1000people();
        long before = System.currentTimeMillis();
        for (Person person: people) {
            jdbcTemplate.batchUpdate("INSERT INTO Person VALUES(?, ?, ?, ?)", new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                    preparedStatement.setInt(1, people.get(i).getId());
                    preparedStatement.setString(2, people.get(i).getName());
                    preparedStatement.setInt(3, people.get(i).getAge());
                    preparedStatement.setString(4, people.get(i).getEmail());
                }

                @Override
                public int getBatchSize() {
                    return people.size();
                }
            });
        }
        long after = System.currentTimeMillis();

        System.out.println("Time " + (after-before));
    }
    private List<Person> create1000people() {
        List<Person> people = new ArrayList<>();
        for (int i=0; i<1000; i++) {
            people.add(new Person(i, "Name"+i, 30, "email" + i + "mail.ru", "some address"));
        }
        return  people;
    }*/
}
