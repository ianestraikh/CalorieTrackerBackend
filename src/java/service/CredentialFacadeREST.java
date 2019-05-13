/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Credential;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ian
 */
@Stateless
@Path("entities.credential")
public class CredentialFacadeREST extends AbstractFacade<Credential> {

    @PersistenceContext(unitName = "CalorieTrackerPU")
    private EntityManager em;

    public CredentialFacadeREST() {
        super(Credential.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Credential create(Credential entity) {
        super.create(entity);
        return entity;
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Credential entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Credential find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Credential> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Credential> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    //--------------------------------------------------------------------------
    // Task 3 a
    // Changed for Assignment 3
    @GET
    @Path("findByUsername/{username}")
    @Produces({"application/json"})
    public Credential findByUsername(@PathParam("username") String username) {
        Query query = em.createNamedQuery("Credential.findByUsername");
        query.setParameter("username", username);
        return (Credential) query.getSingleResult();
    }

    @GET
    @Path("findByPasswordHash/{passwordHash}")
    @Produces({"application/json"})
    public List<Credential> findByPasswordHash(@PathParam("passwordHash") String passwordHash) {
        Query query = em.createNamedQuery("Credential.findByPasswordHash");
        query.setParameter("passwordHash", passwordHash);
        return query.getResultList();
    }

    @GET
    @Path("findBySignupDate/{signupDate}")
    @Produces({"application/json"})
    public List<Credential> findBySignupDate(@PathParam("signupDate") String signupDateString) {
        try {
            Date signupDate = new SimpleDateFormat("yyyy-MM-dd").parse(signupDateString);
            Query query = em.createNamedQuery("Credential.findBySignupDate");
            query.setParameter("signupDate", signupDate);
            return query.getResultList();
        } catch (ParseException e) {
            throw new IllegalArgumentException("signupDate must have 'yyyy-MM-dd' format.");
        }
    }
    
    @GET
    @Path("findByUserId/{userId}")
    @Produces({"application/json"})
    public List<Credential> findByUserId(@PathParam("userId") int userId) {
        TypedQuery<Credential> query = em.createQuery("SELECT c FROM Credential c WHERE c.userId.userId = :userId", Credential.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }
    
    //--------------------------------------------------------------------------
    // Task 3 d
    @GET
    @Path("findBySignupDateGreaterThanAndUserPostcodeFirstChar/{signupDate}/{postcodeFirstChar}")
    @Produces({"application/json"})
    public List<Credential> findBySignupDate(@PathParam("signupDate") String signupDateString, @PathParam("postcodeFirstChar") String userPostcodeFirstChar) {
        try {
            if (userPostcodeFirstChar.length() != 1) {
                throw new IllegalArgumentException("userPostcodeFirstChar must be 1 char.");
            }
            Date signupDate = new SimpleDateFormat("yyyy-MM-dd").parse(signupDateString);
            Query query = em.createNamedQuery("Credential.findBySignupDateGreaterThanAndUserPostcodeFirstChar");
            query.setParameter("signupDate", signupDate);
            query.setParameter("postcodeFirstChar", userPostcodeFirstChar.charAt(0) + "%");
            return query.getResultList();
        } catch (ParseException e) {
            throw new IllegalArgumentException("signupDate must have 'yyyy-MM-dd' format.");
        }
    }
    
    //--------------------------------------------------------------------------
    // Assignment 3 Extension
    @GET
    @Path("usernameExists/{username}")
    @Produces({"application/json"})
    public Object usernameExists(@PathParam("username") String username) {
        Query query = em.createQuery("SELECT c.username FROM Credential c WHERE c.username = :username");
        query.setParameter("username", username);
        return Json.createObjectBuilder()
                .add("usernameExists", query.getResultList().size())
                .build();
    }
}
