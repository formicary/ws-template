package com.formicary.wstemplate;

import com.formicary.wstemplate.model.Cat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author hani
 *         Date: 9/16/11
 *         Time: 4:05 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-integration.xml"})
public class IntegrationTests {
  @Autowired
  @Qualifier("catclientsoap")
  private CatService soapService;

  @Autowired
  @Qualifier("catclientrest")
  private CatService restService;

  @Test
  public void nameCatRest() {
    assertNotNull(restService.nameCat());
  }

  @Test
  public void makeCatRest() throws Exception {
    Cat flappy = restService.makeCat("flappy");
    assertEquals("flappy", flappy.getCatName());
  }

  @Test
  public void makeCat() throws Exception {
    Cat c = soapService.makeCat("flappy");
    assertEquals("flappy", c.getCatName());
  }

}
