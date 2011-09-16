package com.formicary.wstemplate;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * @author hani
 *         Date: 9/16/11
 *         Time: 10:28 AM
 */
public class CatServiceTest {

  @Test
  public void createCatName() {
    CatService service = new CatService();
    assertNotNull(service.nameCat());
  }
}
