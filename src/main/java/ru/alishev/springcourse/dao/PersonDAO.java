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
    
    private final SessionFactory sessionFactory;
    
    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Transactional
    public List<Person> index() {
        Session session = sessionFactory.getCurrentSession();
        //обычный hibernate код
        return null;
    }

    public Person show(int id) {
        return null;
    }

    public void save(Person person) {
        
    }


    public void update(int id, Person updatedPerson) {
        
    }
    public void delete(int id) {
        

    }

    /*public Optional<Person> getPersonByFullName(String fullName) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE full_name=?", new Object[]{fullName},
        new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public List<Book> getBooksByPerson(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id=?", new Object[]{id},
        new BeanPropertyRowMapper<>(Book.class));
    }*/
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
