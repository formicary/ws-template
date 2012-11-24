package com.formicary.wstemplate;

import com.formicary.wstemplate.model.Cat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author hani
 *         Date: 9/16/11
 *         Time: 10:28 AM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring-test.xml" })
public class CatServiceTest {
  @Autowired
  @Qualifier("CatService")
  private CatService service;

  @Test
  public void createCatName() {
    assertNotNull(service.nameCat());
  }

  @Test
  public void adoptAndSaveCat() {
    Cat c = service.adoptCat();
    int id = service.saveCat(c);
    assertTrue(id > 0);
  }

  @Test
  public void fetchPreviousRevision() {
    Cat cat = new Cat();
    cat.setCatName("whiskers");
    cat.setAge(5);
    int id = service.saveCat(cat);
    cat.setAge(6);
    service.updateCat(cat);
    cat = service.getPreviousRevision(id);
    assertEquals(cat.getAge(), 5);
  }
}
