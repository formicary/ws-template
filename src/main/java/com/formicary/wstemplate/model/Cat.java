package com.formicary.wstemplate.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "cat")
@XmlRootElement(name = "cat", namespace = "http://cat.formicary.net")
@XmlType(name = "cat", namespace = "http://cat.formicary.net")
public class Cat implements Serializable{

  private int id;
  private String catName;
  private int age;

  public Cat() {}

  @Id
  @GeneratedValue(generator="increment")
  @GenericGenerator(name="increment", strategy = "increment")
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @XmlElement
  public String getCatName() {
    return catName;
  }

  public void setCatName(String catName) {
    this.catName = catName;
  }

  @XmlElement
  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }
}
