package com.formicary.wstemplate.model;

import java.io.Serializable;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "cat", namespace = "http://cat.formicary.net")
@XmlType(name = "cat", namespace = "http://cat.formicary.net")
public class Cat implements Serializable{

  private String catName;
  private int age;

  public Cat() {}

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
