package ru.alishev.springcourse.models;

import javax.validation.constraints.*;

PROJECT1 
public class Person {
    private int id;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 100, message = "Имя должно быть от 2 до 100 символов длиной")
    private String fullName;

    @Min(value = 1900, message = "Возраст должен быть более, чем 1900")
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

}
PROJECT2
@Entity
@Table(name="Person")
public class Person {
    
    @Id
    @Column(name= "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name= "fullName")
    private String fullName;
    
    @Column(name= "yearOfBirth")
    private int yearOfBirth;
    
    @OneToMany(mappedBy = "owner")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private List<Book> books;
    
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
    
    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
        book.setOwner(this);
    }
    
    //рефакторинг
    public void addBook(Book book) {
        if (this.book == null)
            this.book = new ArrayLast<>();
        this.books.add(book);
        book.setOwner(this);
    }
    
    @Override
    public String toString() {
        return "Person " + id;
    }

}
///////////PROJECT2: new directory in models App
public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class).addAnnotatedClass(Book.class).;
        
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        
        try {
            session.beginTransaction();

            Person person1 = new Person("Test1", 30);
            Person person2 = new Person("Test2", 40);
            Person person3 = new Person("Test3", 50);
            
            session.save(person1);
            session.save(person2);
            session.save(person3);
            ////////
            Person person = session.get(Person.class, 2);
            person.setName("New name");
            ////////
            Person person = session.get(Person.class, 2);
            person.delete(person);
            ////////
            Person person = session.get(Person.class, 2);
            person.save(person);
            ////////
            Person person = session.get(Person.class, 3);
            List<Book> books = person.getBooks();
            ////////////
            Book book = session.get(Book.class, 5);
            Person person = book.getOwner();
            ////////////
            Peerson person = session.get(Person.class, 2);
            Book newBook = new Book("Book from Hibernate", person);
            person.getBooks().add(newBook);
            session.save(newBook);
            ////////////создать нового человека и новый заказ
            Person person = new Person ("Test person", 30);
            Book newBook = new Book("Book from Hibernate 2", person);
            person.setBooks(new ArrayList<>(Collections.singletoneList(newBook)));
            session.save(person);
            session.save(newBook);
            ////////////удалить книгу у человека
            Person person = session.get(Person.class, 3);
            List<Book> books = person.getBooks();
            for(Book book : books) {
                session.remove(book);
            }
            person.getBooks().clear();
            ////////////удалить человека
            Person person = session.get(Person.class, 2);
            session.remove(person);
            person.getBooks().forEach(i -> i.setOwner(null));
            ////////////поменять владельца у существующего товара
            Person person = session.get(Person.class, 4);
            Book book = session.getBook(Book.class, 1);
            book.getOwner().getBooks().remove(book);
            book.setOwner(person);
            person.getBooks().add(book);            
            ////////////каскадирование в Hibernate
            Person person = new Person("Test name", 30);
            Book book = new Book("Test cascading book");
            person.setBooks(new ArrayList<>(Collections.singletoneList(book)));
            session.save(person);//save поменяли на persist
            ////////////рефакторинг
            Person person = new Person("Test name", 30);
            Book book1 = new Book("Book1");
            Book book2 = new Book("Book2");
            Book book3 = new Book("Book3");
            ////////////
            session.getTransaction.commit();
        } finally {
            sessionFactory.close();
        }
    }
}
