package com.tedneward.example;

import java.beans.*;
import java.util.*;

class Person implements Comparable<Person> {
  private String name;
  private int age;
  private double salary;
  private String ssn;
  private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

  public static class AgeComparator implements Comparator<Person> {
    public int compare(Person p1, Person p2) {
      return p1.age - p2.age;
    }
  }

  public Person(String name, int age, int salary, String ssn) {
    this.name = name;
    this.age = age;
    this.salary = salary;
    this.ssn = ssn;
  }

  public Person(String name, int age, int salary) {
    this(name, age, salary, "");
  }

  public Person() {
    this("", 0, 0, "");
  }

  public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }

  public void setAge(int newAge) {
    if (age < 0) {
      throw new IllegalArgumentException("Age cannot be less than zero");
    }
    int oldAge = this.age;
    this.age = newAge; 
    pcs.firePropertyChange(new PropertyChangeEvent(this, "age", oldAge, newAge));
  }

  public double getAge() {
    if(this.age < 0) {
      throw new IllegalArgumentException("Age is invalid value");
    }
    return this.age;
  }

  public void setName(String newName) {
    if(name == null) {
      throw new IllegalArgumentException("Must enter a valid name");
    }
    String oldName = this.name;
    this.name = newName;
    pcs.firePropertyChange(new PropertyChangeEvent(this, "name", oldName, newName));
  }

  public String getName() {
    return this.name;
  }

  public void setSalary(double newSalary) {
    if(this.salary < 0) {
      throw new IllegalArgumentException("Salary cannot be less than zero");
    }
    double oldSalary = this.salary;
    this.salary = newSalary;
    pcs.firePropertyChange(new PropertyChangeEvent(this, "salary", oldSalary, newSalary));
  }

  public double getSalary() {
    return this.salary;
  }

  public void setSSN(String newSSN) {
    if(newSSN == null || newSSN == "") {
      throw new IllegalArgumentException("Must ender valid SSN number");
    }
    String oldSSN = this.ssn;
    this.ssn = newSSN;
    pcs.firePropertyChange(new PropertyChangeEvent(this, "ssn", oldSSN, newSSN));
  }

  public String getSSN() {
    return this.ssn;
  }

  public String becomeJudge() {
    return "The Honorable " + this.name;
  }

  public int timeWarp() {
    return this.age + 10;
  }

  public double calculateBonus() {
    return this.salary * 1.1;
  }

  
  @Override
  public String toString() {
    return "[Person name:" + this.name + " age:" + this.age + " salary:" + this.salary + "]";
  }

  @Override
  public int compareTo(Person other) {
    if(this.salary < other.salary) {
      return -1;
    } else if(this.salary > other.salary) {
      return 1;
    }
    return 0;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof Person) {
      Person p = (Person)other;
      return p.name == this.name && p.age == this.age && p.salary == this.salary;
    }
    return false;
  }

  public static List<Person> getNewardFamily() {
    List<Person> newards = new ArrayList<Person>();
    newards.add(new Person("Ted", 41, 250000));
    newards.add(new Person("Charlotte", 43, 150000));
    newards.add(new Person("Michael", 22, 10000));
    newards.add(new Person("Matthew", 15, 0));
    return newards;
  }
}