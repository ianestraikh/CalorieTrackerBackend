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
    public void create(Usr entity) {
        super.create(entity);
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
    @Path("findByUserFname/{userFname}")
    @Produces({"application/json"})
    public List<Usr> findByUserFname(@PathParam("userFname") String userFname) {
        Query query = em.createNamedQuery("Usr.findByUserFname");
        query.setParameter("userFname", userFname);
        return query.getResultList();
    }

    @GET
    @Path("findByUserLname/{userLname}")
    @Produces({"application/json"})
    public List<Usr> findByUserLname(@PathParam("userLname") String userLname) {
        Query query = em.createNamedQuery("Usr.findByUserLname");
        query.setParameter("userLname", userLname);
        return query.getResultList();
    }

    @GET
    @Path("findByUserEmail/{userEmail}")
    @Produces({"application/json"})
    public List<Usr> findByUserEmail(@PathParam("userEmail") String userEmail) {
        Query query = em.createNamedQuery("Usr.findByUserEmail");
        query.setParameter("userEmail", userEmail);
        return query.getResultList();
    }

    @GET
    @Path("findByUserDob/{userDob}")
    @Produces({"application/json"})
    public List<Usr> findByUserDob(@PathParam("userDob") String userDobString) {
        try {
            Date userDob = new SimpleDateFormat("yyyy-MM-dd").parse(userDobString);
            Query query = em.createNamedQuery("Usr.findByUserDob");
            query.setParameter("userDob", userDob);
            return query.getResultList();
        } catch (ParseException e) {
            throw new IllegalArgumentException("userDob must have 'yyyy-MM-dd' format.");
        }
    }

    @GET
    @Path("findByUserHeight/{userHeight}")
    @Produces({"application/json"})
    public List<Usr> findByUserHeight(@PathParam("userHeight") String userHeightString) {
        try {
            BigDecimal userHeight = new BigDecimal(userHeightString);
            Query query = em.createNamedQuery("Usr.findByUserHeight");
            query.setParameter("userHeight", userHeight);
            return query.getResultList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("userHeigth must be decimal.");
        }
    }

    @GET
    @Path("findByUserWeight/{userWeight}")
    @Produces({"application/json"})
    public List<Usr> findByUserWeight(@PathParam("userWeight") String userWeightString) {
        try {
            BigDecimal userWeight = new BigDecimal(userWeightString);
            Query query = em.createNamedQuery("Usr.findByUserWeight");
            query.setParameter("userWeight", userWeight);
            return query.getResultList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("userWeight must be decimal.");
        }
    }

    @GET
    @Path("findByUserGender/{userGender}")
    @Produces({"application/json"})
    public List<Usr> findByUserGender(@PathParam("userGender") String userGenderString) {
        if (userGenderString.equals("F") || userGenderString.equals("M")) {
            Character userGender = userGenderString.charAt(0);
            Query query = em.createNamedQuery("Usr.findByUserGender");
            query.setParameter("userGender", userGender);
            return query.getResultList();
        } else {
            throw new IllegalArgumentException("userGender must be char 'F' or char 'M'.");
        }
    }

    @GET
    @Path("findByUserAddress/{userAddress}")
    @Produces({"application/json"})
    public List<Usr> findByUserAddress(@PathParam("userAddress") String userAddress) {
        Query query = em.createNamedQuery("Usr.findByUserAddress");
        query.setParameter("userAddress", userAddress);
        return query.getResultList();
    }

    @GET
    @Path("findByUserPostcode/{userPostcode}")
    @Produces({"application/json"})
    public List<Usr> findByUserPostcode(@PathParam("userPostcode") String userPostcode) {
        Query query = em.createNamedQuery("Usr.findByUserPostcode");
        query.setParameter("userPostcode", userPostcode);
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
        double userWeight = user.getUserWeight().doubleValue() * 2.20462; // in pounds

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
        Character gender = user.getUserGender();
        double weight = user.getUserWeight().doubleValue();
        double height = user.getUserHeight().doubleValue();

        // convert Date to LocalDate: Date -> Instant -> ZonedDateTime -> LocalDate
        LocalDate dob = user.getUserDob().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

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
