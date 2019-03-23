/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ian
 */
@Entity
@Table(name = "CREDENTIAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Credential.findAll", query = "SELECT c FROM Credential c")
    , @NamedQuery(name = "Credential.findByCredId", query = "SELECT c FROM Credential c WHERE c.credId = :credId")
    , @NamedQuery(name = "Credential.findByUsername", query = "SELECT c FROM Credential c WHERE c.username = :username")
    , @NamedQuery(name = "Credential.findByPasswordHash", query = "SELECT c FROM Credential c WHERE c.passwordHash = :passwordHash")
    , @NamedQuery(name = "Credential.findBySignupDate", query = "SELECT c FROM Credential c WHERE c.signupDate = :signupDate")
    //--------------------------------------------------------------------------
    // Task 3 d
    , @NamedQuery(name = "Credential.findBySignupDateGreaterThanAndUserPostcodeFirstChar", query = "SELECT c FROM Credential c WHERE c.signupDate > :signupDate and c.userId.userPostcode LIKE :userPostcodeFirstChar")})
public class Credential implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CRED_ID")
    private Integer credId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "PASSWORD_HASH")
    private String passwordHash;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SIGNUP_DATE")
    @Temporal(TemporalType.DATE)
    private Date signupDate;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne(optional = false)
    private Usr userId;

    public Credential() {
    }

    public Credential(Integer credId) {
        this.credId = credId;
    }

    public Credential(Integer credId, String username, String passwordHash, Date signupDate) {
        this.credId = credId;
        this.username = username;
        this.passwordHash = passwordHash;
        this.signupDate = signupDate;
    }

    public Integer getCredId() {
        return credId;
    }

    public void setCredId(Integer credId) {
        this.credId = credId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Date getSignupDate() {
        return signupDate;
    }

    public void setSignupDate(Date signupDate) {
        this.signupDate = signupDate;
    }

    public Usr getUserId() {
        return userId;
    }

    public void setUserId(Usr userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (credId != null ? credId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Credential)) {
            return false;
        }
        Credential other = (Credential) object;
        if ((this.credId == null && other.credId != null) || (this.credId != null && !this.credId.equals(other.credId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Credential[ credId=" + credId + " ]";
    }

}
