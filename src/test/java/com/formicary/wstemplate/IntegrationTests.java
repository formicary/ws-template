package com.formicary.wstemplate;

import java.io.IOException;
import javax.xml.ws.Endpoint;

import com.formicary.wstemplate.model.Cat;
import org.apache.commons.io.IOUtils;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
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
  private Endpoint endpoint;
  private static final HttpClient client = new DefaultHttpClient();

  @Autowired
  @Qualifier("catclient")
  private CatService proxy;

  @Test
  public void nameCatRest() {
    assertNotNull(proxy.nameCat());
  }

  @Test
  public void makeCatRest() throws Exception {
    Cat flappy = proxy.makeCat("flappy");
    assertEquals("flappy", flappy.getCatName());
  }

  @Test
  public void getWSDL() throws IOException {
    HttpGet get = new HttpGet(((EndpointImpl)endpoint).getAddress() + "?wsdl");
    HttpResponse response = client.execute(get);
    System.out.println(IOUtils.toString(response.getEntity().getContent()));
  }

  @Test
  public void makeCat() throws Exception {
    Client client = createClient();
    Object[] res = client.invoke("makeCat", "flappy");
    assertTrue(res[0].getClass().getName(), res[0].getClass().getName().equals(Cat.class.getName()));
    Cat c = (Cat)res[0];
    assertEquals("flappy", c.getCatName());
  }

  private Client createClient() {
    JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
    Client client = dcf.createClient(((EndpointImpl)endpoint).getAddress() + "?wsdl");
    client.getInInterceptors().add(new LoggingInInterceptor());
    client.getOutInterceptors().add(new LoggingOutInterceptor());
    return client;
  }
}
