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
    , @NamedQuery(name = "Usr.findByUserFname", query = "SELECT u FROM Usr u WHERE u.userFname = :userFname")
    , @NamedQuery(name = "Usr.findByUserLname", query = "SELECT u FROM Usr u WHERE u.userLname = :userLname")
    , @NamedQuery(name = "Usr.findByUserEmail", query = "SELECT u FROM Usr u WHERE u.userEmail = :userEmail")
    , @NamedQuery(name = "Usr.findByUserDob", query = "SELECT u FROM Usr u WHERE u.userDob = :userDob")
    , @NamedQuery(name = "Usr.findByUserHeight", query = "SELECT u FROM Usr u WHERE u.userHeight = :userHeight")
    , @NamedQuery(name = "Usr.findByUserWeight", query = "SELECT u FROM Usr u WHERE u.userWeight = :userWeight")
    , @NamedQuery(name = "Usr.findByUserGender", query = "SELECT u FROM Usr u WHERE u.userGender = :userGender")
    , @NamedQuery(name = "Usr.findByUserAddress", query = "SELECT u FROM Usr u WHERE u.userAddress = :userAddress")
    , @NamedQuery(name = "Usr.findByUserPostcode", query = "SELECT u FROM Usr u WHERE u.userPostcode = :userPostcode")
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
    @Column(name = "USER_FNAME")
    private String userFname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "USER_LNAME")
    private String userLname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "USER_EMAIL")
    private String userEmail;
    @Basic(optional = false)
    @NotNull
    @Column(name = "USER_DOB")
    @Temporal(TemporalType.DATE)
    private Date userDob;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "USER_HEIGHT")
    private BigDecimal userHeight;
    @Basic(optional = false)
    @NotNull
    @Column(name = "USER_WEIGHT")
    private BigDecimal userWeight;
    @Basic(optional = false)
    @NotNull
    @Column(name = "USER_GENDER")
    private Character userGender;
    @Size(max = 100)
    @Column(name = "USER_ADDRESS")
    private String userAddress;
    @Size(max = 4)
    @Column(name = "USER_POSTCODE")
    private String userPostcode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LEVEL_OF_ACTIVITY")
    private int levelOfActivity;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STEPS_PER_MILE")
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
        this.userFname = userFname;
        this.userLname = userLname;
        this.userEmail = userEmail;
        this.userDob = userDob;
        this.userHeight = userHeight;
        this.userWeight = userWeight;
        this.userGender = userGender;
        this.levelOfActivity = levelOfActivity;
        this.stepsPerMile = stepsPerMile;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserFname() {
        return userFname;
    }

    public void setUserFname(String userFname) {
        this.userFname = userFname;
    }

    public String getUserLname() {
        return userLname;
    }

    public void setUserLname(String userLname) {
        this.userLname = userLname;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Date getUserDob() {
        return userDob;
    }

    public void setUserDob(Date userDob) {
        this.userDob = userDob;
    }

    public BigDecimal getUserHeight() {
        return userHeight;
    }

    public void setUserHeight(BigDecimal userHeight) {
        this.userHeight = userHeight;
    }

    public BigDecimal getUserWeight() {
        return userWeight;
    }

    public void setUserWeight(BigDecimal userWeight) {
        this.userWeight = userWeight;
    }

    public Character getUserGender() {
        return userGender;
    }

    public void setUserGender(Character userGender) {
        this.userGender = userGender;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserPostcode() {
        return userPostcode;
    }

    public void setUserPostcode(String userPostcode) {
        this.userPostcode = userPostcode;
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
