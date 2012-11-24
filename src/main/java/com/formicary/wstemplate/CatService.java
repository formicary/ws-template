package com.formicary.wstemplate;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.*;

import com.formicary.wstemplate.model.Cat;

@Path("/cat")
@Produces("application/json")
@WebService
public interface CatService {

  @GET
  @Path("name")
  @WebMethod
  String nameCat();

  @POST
  @Path("make/{name}")
  @WebMethod
  Cat makeCat(@PathParam("name") String name);

  @WebMethod
  Cat adoptCat();

  @WebMethod
  List<Cat> makeLitter();

  Cat getCatById(final Integer catId);

  int saveCat(Cat c);

  void updateCat(Cat cat);

  Cat getPreviousRevision(int catId);

}
