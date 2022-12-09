package ru.alishev.springcourse.models;

import javax.validation.constraints.*;

//PROJECT1:
public class Book {
    private int id;

    @NotEmpty(message = "Название не должно быть пустым")
    @Size(min = 2, max = 100, message = "Название должно быть от 2 до 100 символов длиной")
    private  String title;

    @NotEmpty(message = "Имя автора не должно быть пустым")
    @Size(min = 2, max = 100, message = "Имя автора должно быть от 2 до 100 символов длиной")
    private  String author;

    @Min(value = 1500, message = "Год выпуска книги должен быть больее, чем 1500")
    private int year;

    public Book() {
    }

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}


//PROJECT2:
import javax.persistence.Entity;


@Entity
@Table(name="Book")
public class Book {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name= "fullName")
    private String fullName;
    
    @Column(name= "yearOfBirth")
    private int yearOfBirth;
    
    public Person() {
    }
    
    public Person(String fullName, int yearOfBirth) {
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
    
    @Override
    public String toString() {
        return "Person " + id;
    }

}
