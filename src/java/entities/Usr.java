/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ian
 */
@Entity
@Table(name = "USR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usr.findAll", query = "SELECT u FROM Usr u")
    , @NamedQuery(name = "Usr.findByUserId", query = "SELECT u FROM Usr u WHERE u.userId = :userId")
    , @NamedQuery(name = "Usr.findByUserFname", query = "SELECT u FROM Usr u WHERE u.fname = :fname")
    , @NamedQuery(name = "Usr.findByUserLname", query = "SELECT u FROM Usr u WHERE u.lname = :lname")
    , @NamedQuery(name = "Usr.findByUserEmail", query = "SELECT u FROM Usr u WHERE u.email = :email")
    , @NamedQuery(name = "Usr.findByUserDob", query = "SELECT u FROM Usr u WHERE u.dob = :dob")
    , @NamedQuery(name = "Usr.findByUserHeight", query = "SELECT u FROM Usr u WHERE u.height = :height")
    , @NamedQuery(name = "Usr.findByUserWeight", query = "SELECT u FROM Usr u WHERE u.weight = :weight")
    , @NamedQuery(name = "Usr.findByUserGender", query = "SELECT u FROM Usr u WHERE u.gender = :gender")
    , @NamedQuery(name = "Usr.findByUserAddress", query = "SELECT u FROM Usr u WHERE u.address = :address")
    , @NamedQuery(name = "Usr.findByUserPostcode", query = "SELECT u FROM Usr u WHERE u.postcode = :postcode")
    , @NamedQuery(name = "Usr.findByLevelOfActivity", query = "SELECT u FROM Usr u WHERE u.levelOfActivity = :levelOfActivity")
    , @NamedQuery(name = "Usr.findByStepsPerMile", query = "SELECT u FROM Usr u WHERE u.stepsPerMile = :stepsPerMile")})
public class Usr implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "USER_ID")
    private Integer userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "FNAME")
    private String fname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "LNAME")
    private String lname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "EMAIL")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DOB")
    @Temporal(TemporalType.DATE)
    private Date dob;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "HEIGHT")
    private BigDecimal height;
    @Basic(optional = false)
    @NotNull
    @Column(name = "WEIGHT")
    private BigDecimal weight;
    @Basic(optional = false)
    @NotNull
    @Column(name = "GENDER")
    private Character gender;
    @Size(max = 100)
    @Column(name = "ADDRESS")
    private String address;
    @Size(max = 4)
    @Column(name = "POSTCODE")
    private String postcode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LEVELOFACTIVITY")
    private int levelOfActivity;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STEPSPERMILE")
    private int stepsPerMile;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<Credential> credentialCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<Report> reportCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<Consumption> consumptionCollection;

    public Usr() {
    }

    public Usr(Integer userId) {
        this.userId = userId;
    }

    public Usr(Integer userId, String userFname, String userLname, String userEmail, Date userDob, BigDecimal userHeight, BigDecimal userWeight, Character userGender, int levelOfActivity, int stepsPerMile) {
        this.userId = userId;
        this.fname = userFname;
        this.lname = userLname;
        this.email = userEmail;
        this.dob = userDob;
        this.height = userHeight;
        this.weight = userWeight;
        this.gender = userGender;
        this.levelOfActivity = levelOfActivity;
        this.stepsPerMile = stepsPerMile;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public int getLevelOfActivity() {
        return levelOfActivity;
    }

    public void setLevelOfActivity(int levelOfActivity) {
        this.levelOfActivity = levelOfActivity;
    }

    public int getStepsPerMile() {
        return stepsPerMile;
    }

    public void setStepsPerMile(int stepsPerMile) {
        this.stepsPerMile = stepsPerMile;
    }

    @XmlTransient
    public Collection<Credential> getCredentialCollection() {
        return credentialCollection;
    }

    public void setCredentialCollection(Collection<Credential> credentialCollection) {
        this.credentialCollection = credentialCollection;
    }

    @XmlTransient
    public Collection<Report> getReportCollection() {
        return reportCollection;
    }

    public void setReportCollection(Collection<Report> reportCollection) {
        this.reportCollection = reportCollection;
    }

    @XmlTransient
    public Collection<Consumption> getConsumptionCollection() {
        return consumptionCollection;
    }

    public void setConsumptionCollection(Collection<Consumption> consumptionCollection) {
        this.consumptionCollection = consumptionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usr)) {
            return false;
        }
        Usr other = (Usr) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Usr[ userId=" + userId + " ]";
    }
    
}
