package com.formicary.wstemplate;

import java.util.ArrayList;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;

import com.formicary.wstemplate.model.Cat;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("CatService")
@Produces("application/json")
@Transactional
public class CatServiceImpl implements CatService {

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

  public CatServiceImpl() {}

  public String nameCat() {
    int index = (int)Math.round(Math.random()*((catNames.size()-1)));
    System.out.println("New cat name chosen: " + catNames.get(index));
    return catNames.get(index);
  }

  public Cat makeCat(String name) {
    Cat cat = new Cat();
    cat.setCatName(name);
    return cat;
  }

  public Cat adoptCat() {
    Cat cat = new Cat();
    cat.setCatName(nameCat());
    int age = (int)Math.round(Math.random()*((20)));
    cat.setAge(age);
    return cat;
  }

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

  public Cat getCatById(final Integer catId) {
    final Cat cat = entityManager.find(Cat.class, catId);
    return cat;
  }

  public int saveCat(Cat c) {
    entityManager.persist(c);
    return c.getId();
  }

  public void updateCat(Cat cat) {
    entityManager.merge(cat);
  }

  public Cat getPreviousRevision(int catId) {
    Cat oldCat = null;
    AuditReader auditReader = AuditReaderFactory.get(entityManager);
    List<Number> revisions = auditReader.getRevisions(Cat.class, catId);
    if (revisions.size() > 1) {
      int previousRevision = revisions.get(revisions.size() - 2).intValue();
      oldCat = auditReader.find(Cat.class, catId, previousRevision);
    }
    return oldCat;
  }
}
