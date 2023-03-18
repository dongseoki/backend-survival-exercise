package jpa.hibernate.example.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Entity
@Table
public class Person {
  @Id
  @Column(name = "name")
  private String name;

  @Column(name = "age")
  private Integer age;

  @Column(name = "gender")
  @Embedded
  private Gender gender;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "person_name")
  @OrderBy("name")
  private List<Item> items = new ArrayList<>();

  public Person(String name, int age, Gender gender) {
    this.name = name;
    this.age = age;
    this.gender = gender;
  }

  public Person() {

  }

  public String name() {
    return name;
  }

  public Integer age() {
    return age;
  }

  public Gender gender() {
    return gender;
  }

  public void changeAge(int age) {
    this.age = age;
  }

  public void addItem(String name, String usage) {
    Item item = new Item(name, usage, this.name);

    items.add(item);
  }

  public void removeItem(String name) {
    Optional<Item> item = items.stream()
                               .filter(i -> i.name().equals(name))
                               .findFirst();

    if (item.isEmpty()) {
      return;
    }

    items.remove(item.get());
  }

  public List<Item> items() {
    return new ArrayList<>(items);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Person person)) {
      return false;
    }

    if (!Objects.equals(name, person.name)) {
      return false;
    }
    if (!Objects.equals(age, person.age)) {
      return false;
    }
    return Objects.equals(gender, person.gender);
  }

  @Override
  public int hashCode() {
    int result = name != null ? name.hashCode() : 0;
    result = 31 * result + (age != null ? age.hashCode() : 0);
    result = 31 * result + (gender != null ? gender.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Person{" +
        "name='" + name + '\'' +
        ", age=" + age +
        ", gender='" + gender + '\'' +
        '}';
  }
}