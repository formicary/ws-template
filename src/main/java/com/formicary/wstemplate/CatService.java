package com.formicary.wstemplate;

import java.util.ArrayList;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.formicary.wstemplate.model.Cat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("CatService")
@WebService
@Transactional
public class CatService {

  private static List<String> catNames;

  @PersistenceContext(unitName = "pu")
  private EntityManager entityManager;

  static {
    catNames = new ArrayList<String>();
    catNames.add("Tibbles");
    catNames.add("Snowball");
    catNames.add("Spot");
    catNames.add("Miss Fluffy");
    catNames.add("Whiskers");
    catNames.add("Smokey");
    catNames.add("Puss");
    catNames.add("Felix");
  }

  public CatService() {}

  @WebMethod
  public String nameCat() {
    int index = (int)Math.round(Math.random()*((catNames.size()-1)));
    System.out.println("New cat name chosen: " + catNames.get(index));
    return catNames.get(index);
  }

  @WebMethod
  public Cat makeCat(String name) {
    Cat cat = new Cat();
    cat.setCatName(name);
    return cat;
  }

  @WebMethod
  public Cat adoptCat() {
    Cat cat = new Cat();
    cat.setCatName(nameCat());
    int age = (int)Math.round(Math.random()*((20)));
    cat.setAge(age);
    return cat;
  }

  @WebMethod
  public List<Cat> makeLitter() {
    int kittenCount = (int)Math.round(Math.random()*((6))) + 1;
    List<Cat> kittens = new ArrayList<Cat>();
    for (int i = 0; i < kittenCount; i++) {
      Cat kitten = new Cat();
      kitten.setCatName(nameCat());
      kittens.add(kitten);
    }
    return kittens;
  }

  @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
  public Cat getCatById(final Integer catId) {
    final Cat cat = entityManager.find(Cat.class, catId);
    return cat;
  }

}
