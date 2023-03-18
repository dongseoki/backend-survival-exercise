package jpa.hibernate.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpa.hibernate.example.models.Gender;
import jpa.hibernate.example.models.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class JpaTest {
  private EntityManagerFactory entityManagerFactory;

  private EntityManager entityManager;

  @BeforeEach
  void setUp() {
    entityManagerFactory = Persistence.createEntityManagerFactory("demo");
    entityManager = entityManagerFactory.createEntityManager();
  }

  @AfterEach
  void tearDown() {
    entityManager.close();
    entityManagerFactory.close();
  }

  @Test
  void query() {
    Person person = entityManager.find(Person.class, "견우");

    System.out.println("*".repeat(80));
    System.out.println(person);
    System.out.println("*".repeat(80));
  }

  @Test
  void queryTestButNotInDb() {
    Person person = new Person("asdf", 35, Gender.male());
    entityManager.persist(person);
  }

  @Test
  void queryTestBSuccessDataInDb() {
    EntityTransaction transaction = entityManager.getTransaction();
    transaction.begin();
    Person person = new Person("asdf", 35, Gender.male());
    entityManager.persist(person);
    transaction.commit();

    //template start
//    {
//      transaction.begin();
//      transaction.commit();
//    }
    // template end
  }

  @Test
  void crud() {
    EntityTransaction transaction = entityManager.getTransaction();
    {
      transaction.begin();
      Person person = new Person("Mr.Big", 35, Gender.male());
      entityManager.persist(person);
      transaction.commit();
    }
    {
      transaction.begin();
      Person person = new Person("Mr.Big", 35, Gender.male());
      Person found = entityManager.find(Person.class, "Mr.Big");
      assertEquals(person, found);
      transaction.commit();
    }
    {
      transaction.begin();
      Person person = entityManager.find(Person.class, "Mr.Big");
      assertEquals("Mr.Big", person.name());
      transaction.commit();
    }
    {
      transaction.begin();
      Person person = entityManager.find(Person.class, "Mr.Big");
      person.changeAge(30);
      transaction.commit();
    }
    {
      transaction.begin();
      Person person = entityManager.find(Person.class, "Mr.Big");
      entityManager.remove(person);
      transaction.commit();
    }

    {
      transaction.begin();
      Person found = entityManager.find(Person.class, "Mr.Big");
      assertNull(found);
      transaction.commit();
    }

    {
      transaction.begin();
      Person person = entityManager.find(Person.class, "Mr.Big");
      assertNull(person);
      transaction.commit();
    }
  }

  @Test
  void queryAll() {
    String jpql = "SELECT person FROM Person person";

    List<Person> people = entityManager
        .createQuery(jpql, Person.class) // 타입을 지정하지 않으면 Query, 지정하면 TypedQuery.
        .getResultList();

    assertEquals(1, people.size());
  }

  @Test
  void createAndRemoveItem() {
    EntityTransaction transaction = entityManager.getTransaction();
    {
      transaction.begin();
      Person person = entityManager.find(Person.class, "asdf");
      person.addItem("Z", "this is end.");
      transaction.commit();
    }

    {
      transaction.begin();
      Person person = entityManager.find(Person.class, "asdf");
      assertEquals(1, person.items().size());
      person.removeItem("Z");
      transaction.commit();
    }
    {
      transaction.begin();
      Person person = entityManager.find(Person.class, "asdf");
      assertEquals(1, person.items().size());
      person.removeItem("Z");
      transaction.commit();
    }
  }

}
