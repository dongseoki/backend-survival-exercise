package jpa.hibernate.example.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class Gender {
  @Column(name = "gender")
  private String value;

  public Gender() {
  }

  private Gender(String value) {
    this.value = value;
  }

  public static Gender male() {
    return new Gender("남");
  }

  public static Gender female() {
    return new Gender("여");
  }

  @Override
  public String toString() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Gender gender)) {
      return false;
    }

    return Objects.equals(value, gender.value);
  }

  @Override
  public int hashCode() {
    return value != null ? value.hashCode() : 0;
  }
}