/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Consumption;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
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
@Path("entities.consumption")
public class ConsumptionFacadeREST extends AbstractFacade<Consumption> {

    @PersistenceContext(unitName = "CalorieTrackerPU")
    private EntityManager em;

    public ConsumptionFacadeREST() {
        super(Consumption.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Consumption create(Consumption entity) {
        super.create(entity);
        return entity;
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Consumption entity) {
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
    public Consumption find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Consumption> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Consumption> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    @GET
    @Path("findByConsumptionDate/{consumptionDate}")
    @Produces({"application/json"})
    public List<Consumption> findByConsumptionDate(@PathParam("consumptionDate") String consumptionDateString) {
        try {
            Date consumptionDate = new SimpleDateFormat("yyyy-MM-dd").parse(consumptionDateString);
            Query query = em.createNamedQuery("Consumption.findByConsumptionDate");
            query.setParameter("consumptionDate", consumptionDate);
            return query.getResultList();
        } catch (ParseException e) {
            throw new IllegalArgumentException("consumptionDate must have 'yyyy-MM-dd' format.");
        }
    }

    @GET
    @Path("findByQuantity/{quantity}")
    @Produces({"application/json"})
    public List<Consumption> findByQuantity(@PathParam("quantity") int quantity) {
        Query query = em.createNamedQuery("Consumption.findByQuantity");
        query.setParameter("quantity", quantity);
        return query.getResultList();
    }

    @GET
    @Path("findByUserId/{userId}")
    @Produces({"application/json"})
    public List<Consumption> findByUserId(@PathParam("userId") int userId) {
        TypedQuery<Consumption> query = em.createQuery("SELECT c FROM Consumption c WHERE c.userId.userId = :userId", Consumption.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @GET
    @Path("findByFoodId/{foodId}")
    @Produces({"application/json"})
    public List<Consumption> findByFoodId(@PathParam("foodId") int foodId) {
        TypedQuery<Consumption> query = em.createQuery("SELECT c FROM Consumption c WHERE c.foodId.foodId = :foodId", Consumption.class);
        query.setParameter("foodId", foodId);
        return query.getResultList();
    }

    //--------------------------------------------------------------------------
    // Task 3 b
    @GET
    @Path("findByUserIdAndConsumptionDate/{userId}/{consumptionDate}")
    @Produces({"application/json"})
    public List<Consumption> findByUserIdAndConsumptionDate(@PathParam("userId") int userId, @PathParam("consumptionDate") String consumptionDateString) {
        try {
            Date consumptionDate = new SimpleDateFormat("yyyy-MM-dd").parse(consumptionDateString);
            TypedQuery<Consumption> query = em.createQuery("SELECT c FROM Consumption c WHERE c.userId.userId = :userId and c.consumptionDate = :consumptionDate", Consumption.class);
            query.setParameter("userId", userId);
            query.setParameter("consumptionDate", consumptionDate);
            return query.getResultList();
        } catch (ParseException e) {
            throw new IllegalArgumentException("consumptionDate must have 'yyyy-MM-dd' format.");
        }
    }

    //--------------------------------------------------------------------------
    // Task 3 c
    @GET
    @Path("findByCalorieAmountGreaterThanAndUserGender/{calorieAmount}/{userGender}")
    @Produces({"application/json"})
    public List<Consumption> findByCalorieAmountAndUserGender(@PathParam("calorieAmount") BigDecimal calorieAmount, @PathParam("userGender") String userGenderString) {
        if (userGenderString.equals("F") || userGenderString.equals("M")) {
            Character userGender = userGenderString.charAt(0);
            TypedQuery<Consumption> query = em.createQuery("SELECT c FROM Consumption c WHERE c.foodId.calorieAmount > :calorieAmount and c.userId.userGender = :userGender", Consumption.class);
            query.setParameter("calorieAmount", calorieAmount);
            query.setParameter("userGender", userGender);
            return query.getResultList();
        } else {
            throw new IllegalArgumentException("userGender must be char 'F' or char 'M'.");
        }
    }

    //--------------------------------------------------------------------------
    // Task 4 d
    @GET
    @Path("calculateTotalCaloriesConsumed/{userId}/{consumptionDate}")
    @Produces({"text/plain"})
    public BigDecimal calculateTotalCaloriesConsumed(@PathParam("userId") int userId, @PathParam("consumptionDate") String consumptionDateString) {
        List<Consumption> consumptions = findByUserIdAndConsumptionDate(userId, consumptionDateString);
        double totalCaloriesAmount = 0;
        for (Consumption c: consumptions) {
            double caloriesAmount = c.getFoodId().getCalorieAmount().doubleValue() * c.getQuantity();
            totalCaloriesAmount += caloriesAmount;
        }
        return BigDecimal.valueOf(totalCaloriesAmount);
    }
}
