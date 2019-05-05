/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Usr;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
@Path("entities.usr")
public class UsrFacadeREST extends AbstractFacade<Usr> {

    @PersistenceContext(unitName = "CalorieTrackerPU")
    private EntityManager em;

    public UsrFacadeREST() {
        super(Usr.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Usr create(Usr entity) {
        super.create(entity);
        return entity;
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Usr entity) {
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
    public Usr find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Usr> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Usr> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    @Path("findByUserFname/{fname}")
    @Produces({"application/json"})
    public List<Usr> findByUserFname(@PathParam("fname") String userFname) {
        Query query = em.createNamedQuery("Usr.findByUserFname");
        query.setParameter("fname", userFname);
        return query.getResultList();
    }

    @GET
    @Path("findByUserLname/{lname}")
    @Produces({"application/json"})
    public List<Usr> findByUserLname(@PathParam("lname") String userLname) {
        Query query = em.createNamedQuery("Usr.findByUserLname");
        query.setParameter("lname", userLname);
        return query.getResultList();
    }

    @GET
    @Path("findByUserEmail/{email}")
    @Produces({"application/json"})
    public List<Usr> findByUserEmail(@PathParam("email") String userEmail) {
        Query query = em.createNamedQuery("Usr.findByUserEmail");
        query.setParameter("email", userEmail);
        return query.getResultList();
    }

    @GET
    @Path("findByUserDob/{dob}")
    @Produces({"application/json"})
    public List<Usr> findByUserDob(@PathParam("dob") String userDobString) {
        try {
            Date userDob = new SimpleDateFormat("yyyy-MM-dd").parse(userDobString);
            Query query = em.createNamedQuery("Usr.findByUserDob");
            query.setParameter("dob", userDob);
            return query.getResultList();
        } catch (ParseException e) {
            throw new IllegalArgumentException("dob must have 'yyyy-MM-dd' format.");
        }
    }

    @GET
    @Path("findByUserHeight/{height}")
    @Produces({"application/json"})
    public List<Usr> findByUserHeight(@PathParam("height") String userHeightString) {
        try {
            BigDecimal userHeight = new BigDecimal(userHeightString);
            Query query = em.createNamedQuery("Usr.findByUserHeight");
            query.setParameter("height", userHeight);
            return query.getResultList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("heigth must be decimal.");
        }
    }

    @GET
    @Path("findByUserWeight/{weight}")
    @Produces({"application/json"})
    public List<Usr> findByUserWeight(@PathParam("weight") String userWeightString) {
        try {
            BigDecimal userWeight = new BigDecimal(userWeightString);
            Query query = em.createNamedQuery("Usr.findByUserWeight");
            query.setParameter("weight", userWeight);
            return query.getResultList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("userWeight must be decimal.");
        }
    }

    @GET
    @Path("findByUserGender/{gender}")
    @Produces({"application/json"})
    public List<Usr> findByUserGender(@PathParam("gender") String userGenderString) {
        if (userGenderString.equals("F") || userGenderString.equals("M")) {
            Character userGender = userGenderString.charAt(0);
            Query query = em.createNamedQuery("Usr.findByUserGender");
            query.setParameter("gender", userGender);
            return query.getResultList();
        } else {
            throw new IllegalArgumentException("gender must be char 'F' or char 'M'.");
        }
    }

    @GET
    @Path("findByUserAddress/{address}")
    @Produces({"application/json"})
    public List<Usr> findByUserAddress(@PathParam("address") String userAddress) {
        Query query = em.createNamedQuery("Usr.findByUserAddress");
        query.setParameter("address", userAddress);
        return query.getResultList();
    }

    @GET
    @Path("findByUserPostcode/{userPostcode}")
    @Produces({"application/json"})
    public List<Usr> findByUserPostcode(@PathParam("postcode") String userPostcode) {
        Query query = em.createNamedQuery("Usr.findByUserPostcode");
        query.setParameter("postcode", userPostcode);
        return query.getResultList();
    }

    @GET
    @Path("findByLevelOfActivity/{levelOfActivity}")
    @Produces({"application/json"})
    public List<Usr> findByLevelOfActivity(@PathParam("levelOfActivity") int levelOfActivity) {
        Query query = em.createNamedQuery("Usr.findByLevelOfActivity");
        query.setParameter("levelOfActivity", levelOfActivity);
        return query.getResultList();
    }

    @GET
    @Path("findByStepsPerMile/{stepsPerMile}")
    @Produces({"application/json"})
    public List<Usr> findByStepsPerMile(@PathParam("stepsPerMile") int stepsPerMile) {
        Query query = em.createNamedQuery("Usr.findByStepsPerMile");
        query.setParameter("stepsPerMile", stepsPerMile);
        return query.getResultList();
    }

    //--------------------------------------------------------------------------
    // Task 4 a
    @GET
    @Path("calculateCaloriesBurnedPerStep/{userId}")
    @Produces({"text/plain"})
    public double calculateCaloriesBurnedPerStep(@PathParam("userId") int userId) {
        Usr user = super.find(userId);

        int stepsPerMile = user.getStepsPerMile();
        double userWeight = user.getWeight().doubleValue() * 2.20462; // in pounds

        double caloriesBurnedPerMile = userWeight * 0.49;

        return caloriesBurnedPerMile / stepsPerMile;
    }

    //--------------------------------------------------------------------------
    // Task 4 b
    @GET
    @Path("calculateBasalMetabolicRate/{userId}")
    @Produces({"text/plain"})
    public double calculateBasalMetabolicRate(@PathParam("userId") int userId) {
        Usr user = super.find(userId);
        Character gender = user.getGender();
        double weight = user.getWeight().doubleValue();
        double height = user.getHeight().doubleValue();

        // convert Date to LocalDate: Date -> Instant -> ZonedDateTime -> LocalDate
        LocalDate dob = user.getDob().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        int age = Period.between(dob, LocalDate.now()).getYears();

        double basalMetabolicRate = 0.0;
        if (gender == 'F') {
            basalMetabolicRate = 9.563 * weight + 1.85 * height - 4.676 * age + 655.1;
        } else if (gender == 'M') {
            basalMetabolicRate = 13.75 * weight + 5.003 * height - 6.755 * age + 66.5;
        }

        return basalMetabolicRate;
    }

    //--------------------------------------------------------------------------
    // Task 4 c
    @GET
    @Path("calculateTotalCaloriesBurned/{userId}")
    @Produces({"text/plain"})
    public double calculateTotalCaloriesBurned(@PathParam("userId") int userId) {
        Usr user = super.find(userId);
        int levelOfActivity = user.getLevelOfActivity();
        double basalMetabolicRate = calculateBasalMetabolicRate(userId);
        switch (levelOfActivity) {
            case 1:
                return basalMetabolicRate * 1.2;
            case 2:
                return basalMetabolicRate * 1.375;
            case 3:
                return basalMetabolicRate * 1.55;
            case 4:
                return basalMetabolicRate * 1.725;
            case 5:
                return basalMetabolicRate * 1.9;
            default:
                return 0.0;
        }
    }
    
}
