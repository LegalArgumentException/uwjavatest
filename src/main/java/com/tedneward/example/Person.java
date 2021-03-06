package com.tedneward.example;

import java.beans.*;
import java.util.*;

public class Person implements Comparable<Person> {
  private int age;
  private String name;
  private double salary;
  private String ssn;
  private boolean propertyChangeFired = false;
  
  public Person() {
    this("", 0, 0.0d);
  }
  
  public Person(String n, int a, double s) {
    name = n;
    age = a;
    salary = s;
    ssn = "";
  }

  public void setAge(int newAge) {
    if (newAge < 0) {
      throw new IllegalArgumentException("Age cannot be less than zero");
    }
    int oldAge = this.age;
    this.age = newAge; 
    pcs.firePropertyChange(new PropertyChangeEvent(this, "age", oldAge, newAge));
  }

  public int getAge() {
    return this.age;
  }

  public void setName(String newName) {
    if(newName == null) {
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
    if(newSalary < 0) {
      throw new IllegalArgumentException("Salary cannot be less than zero");
    }
    double oldSalary = this.salary;
    this.salary = newSalary;
    pcs.firePropertyChange(new PropertyChangeEvent(this, "salary", oldSalary, newSalary));
  }

  public double getSalary() {
    return this.salary;
  }

  public void setSSN(String value) {
    String old = this.ssn;
    ssn = value;
    
    this.pcs.firePropertyChange("ssn", old, value);
    propertyChangeFired = true;
  }

  public boolean getPropertyChangeFired() {
    return propertyChangeFired;
  }

  public double calculateBonus() {
    return salary * 1.10;
  }
  
  public String becomeJudge() {
    return "The Honorable " + name;
  }
  
  public int timeWarp() {
    return age + 10;
  }
  
  @Override
  public String toString() {
    return "[Person name:" + this.name + " age:" + this.age + " salary:" + this.salary + "]";
  }

  public static class AgeComparator implements Comparator<Person> {
    public int compare(Person p1, Person p2) {
      return p1.age - p2.age;
    }
  }

  @Override
  public int compareTo(Person other) {
    if(this.salary > other.salary) {
      return -1;
    } else if(this.salary < other.salary) {
      return 1;
    }
    return 0;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof Person) {
      Person p = (Person)other;
      return p.name == this.name && p.age == this.age;
    }
    return false;
  }

  // Creates the Neward Family
  //
  public static List<Person> getNewardFamily() {
    List<Person> newards = new ArrayList<Person>();
    newards.add(new Person("Ted", 41, 250000));
    newards.add(new Person("Charlotte", 43, 150000));
    newards.add(new Person("Michael", 22, 10000));
    newards.add(new Person("Matthew", 15, 0));
    return newards;
  }

  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
  }
  public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
  }
}
