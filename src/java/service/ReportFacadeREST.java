/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Report;
import java.math.BigDecimal;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
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
@Path("entities.report")
public class ReportFacadeREST extends AbstractFacade<Report> {

    @PersistenceContext(unitName = "CalorieTrackerPU")
    private EntityManager em;

    public ReportFacadeREST() {
        super(Report.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Report create(Report entity) {
        super.create(entity);
        return entity;
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Report entity) {
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
    public Report find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Report> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Report> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    @Path("findByReportDate/{reportDate}")
    @Produces({"application/json"})
    public List<Report> findByReportDate(@PathParam("reportDate") String reportDateString) {
        try {
            Date reportDate = new SimpleDateFormat("yyyy-MM-dd").parse(reportDateString);
            Query query = em.createNamedQuery("Report.findByReportDate");
            query.setParameter("reportDate", reportDate);
            return query.getResultList();
        } catch (ParseException e) {
            throw new IllegalArgumentException("reportDate must have 'yyyy-MM-dd' format.");
        }
    }

    @GET
    @Path("findByCaloriesConsumed/{caloriesConsumed}")
    @Produces({"application/json"})
    public List<Report> findByCaloriesConsumed(@PathParam("caloriesConsumed") BigDecimal caloriesConsumed) {
        Query query = em.createNamedQuery("Report.findByCaloriesConsumed");
        query.setParameter("caloriesConsumed", caloriesConsumed);
        return query.getResultList();
    }

    @GET
    @Path("findByCaloriesBurned/{caloriesBurned}")
    @Produces({"application/json"})
    public List<Report> findByCaloriesBurned(@PathParam("caloriesBurned") BigDecimal caloriesBurned) {
        Query query = em.createNamedQuery("Report.findByCaloriesBurned");
        query.setParameter("caloriesBurned", caloriesBurned);
        return query.getResultList();
    }

    @GET
    @Path("findByStepsTaken/{stepsTaken}")
    @Produces({"application/json"})
    public List<Report> findByStepsTaken(@PathParam("stepsTaken") int stepsTaken) {
        Query query = em.createNamedQuery("Report.findByStepsTaken");
        query.setParameter("stepsTaken", stepsTaken);
        return query.getResultList();
    }

    @GET
    @Path("findByCalorieGoal/{calorieGoal}")
    @Produces({"application/json"})
    public List<Report> findByCalorieGoal(@PathParam("calorieGoal") int calorieGoal) {
        Query query = em.createNamedQuery("Report.findByCalorieGoal");
        query.setParameter("calorieGoal", calorieGoal);
        return query.getResultList();
    }

    @GET
    @Path("findByUserId/{userId}")
    @Produces({"application/json"})
    public List<Report> findByUserId(@PathParam("userId") int userId) {
        TypedQuery<Report> query = em.createQuery("SELECT r FROM Report r WHERE r.userId.userId = :userId", Report.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    //--------------------------------------------------------------------------
    // Task 5 a
    @GET
    @Path("getReportByUserIdAndDate/{userId}/{reportDate}")
    @Produces({"application/json"})
    public Object getReportByUserIdAndDate(@PathParam("userId") int userId, @PathParam("reportDate") String reportDateString) throws SQLIntegrityConstraintViolationException {
        try {
            Date reportDate = new SimpleDateFormat("yyyy-MM-dd").parse(reportDateString);
            TypedQuery<Object[]> query = em.createQuery("SELECT r.caloriesConsumed, r.caloriesBurned, r.calorieGoal FROM Report AS r WHERE r.userId.userId = :userId AND r.reportDate = :reportDate", Object[].class);
            query.setParameter("userId", userId);
            query.setParameter("reportDate", reportDate);
            List<Object[]> queryList = query.getResultList();
            if (queryList.size() > 1) {
                throw new SQLIntegrityConstraintViolationException("userId and reportDate unique constraint was violated.");
            }

            double caloriesConsumed = ((BigDecimal) queryList.get(0)[0]).doubleValue();
            double caloriesBurned = ((BigDecimal) queryList.get(0)[1]).doubleValue();
            double goal = ((BigDecimal) queryList.get(0)[2]).doubleValue();
            double remainingCalories = (caloriesConsumed - caloriesBurned) - goal;
            JsonObject jsonObject = Json.createObjectBuilder()
                    .add("caloriesConsumed", caloriesConsumed)
                    .add("caloriesBurned", caloriesBurned)
                    .add("remainingCalories", remainingCalories).build();

            return jsonObject;
        } catch (ParseException e) {
            throw new IllegalArgumentException("reportDate must have 'yyyy-MM-dd' format.");
        }
    }

    //--------------------------------------------------------------------------
    // Task 5 b
    @GET
    @Path("getReportByUserIdBetweenDates/{userId}/{startDate}/{endDate}")
    @Produces({"application/json"})
    public Object getReportByUserIdBetweenDates(@PathParam("userId") int userId, @PathParam("startDate") String startDateString, @PathParam("endDate") String endDateString) {
        try {
            Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateString);
            Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDateString);
            TypedQuery<Object[]> query = em.createQuery("SELECT r.caloriesConsumed, r.caloriesBurned, r.stepsTaken FROM Report AS r WHERE r.userId.userId = :userId AND r.reportDate BETWEEN :startDate and :endDate", Object[].class);
            query.setParameter("userId", userId);
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);
            List<Object[]> queryResult = query.getResultList();

            double totalCaloriesConsumed = 0;
            double totalCaloriesBurned = 0;
            int totalStepsTaken = 0;
            for (Object[] row : queryResult) {
                totalCaloriesConsumed += ((BigDecimal) row[0]).doubleValue();
                totalCaloriesBurned += ((BigDecimal) row[1]).doubleValue();
                totalStepsTaken += (int) row[2];
            }
            JsonObject jsonObject = Json.createObjectBuilder()
                    .add("totalCaloriesConsumed", totalCaloriesConsumed)
                    .add("totalCaloriesBurned", totalCaloriesBurned)
                    .add("totalStepsTaken", totalStepsTaken).build();
            return jsonObject;
        } catch (ParseException e) {
            throw new IllegalArgumentException("reportDate must have 'yyyy-MM-dd' format.");
        }
    }

    //--------------------------------------------------------------------------
    // Assignment 3 Extension
    // ref: https://stackoverflow.com/questions/5050170/how-do-i-get-a-date-without-time-in-java
    @GET
    @Path("getReportPerDay/{userId}/{startDate}/{endDate}")
    @Produces({"application/json"})
    public JsonArray getReportPerDay(@PathParam("userId") int userId, @PathParam("startDate") String startDateString, @PathParam("endDate") String endDateString) {
        try {
            Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateString);
            Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDateString);
            TypedQuery<Object[]> query = em.createQuery("SELECT r.reportDate, r.caloriesConsumed, r.caloriesBurned FROM Report AS r WHERE r.userId.userId = :userId AND r.reportDate BETWEEN :startDate and :endDate", Object[].class);
            query.setParameter("userId", userId);
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);
            List<Object[]> list = query.getResultList();
            
            JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            for (int i = 0; i < list.size(); i++) {
                Date day = dateFormat.parse(dateFormat.format(((Date) list.get(i)[0])));
                JsonObject jsonObject = Json.createObjectBuilder()
                        .add("reportDate", day.getTime())
                        .add("caloriesConsumed", (BigDecimal) list.get(i)[1])
                        .add("caloriesBurned", (BigDecimal) list.get(i)[2])
                        .build();
                jsonArrayBuilder.add(jsonObject);
            }
            return jsonArrayBuilder.build();
        } catch (ParseException e) {
            throw new IllegalArgumentException("reportDate must have 'yyyy-MM-dd' format.");
        }
    }
}
