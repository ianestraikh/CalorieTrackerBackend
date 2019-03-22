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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ian
 */
@Entity
@Table(name = "CONSUMPTION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Consumption.findAll", query = "SELECT c FROM Consumption c")
    , @NamedQuery(name = "Consumption.findByConsumId", query = "SELECT c FROM Consumption c WHERE c.consumId = :consumId")
    , @NamedQuery(name = "Consumption.findByConsumptionDate", query = "SELECT c FROM Consumption c WHERE c.consumptionDate = :consumptionDate")
    , @NamedQuery(name = "Consumption.findByQuantity", query = "SELECT c FROM Consumption c WHERE c.quantity = :quantity")})
public class Consumption implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CONSUM_ID")
    private Integer consumId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CONSUMPTION_DATE")
    @Temporal(TemporalType.DATE)
    private Date consumptionDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "QUANTITY")
    private int quantity;
    @JoinColumn(name = "FOOD_ID", referencedColumnName = "FOOD_ID")
    @ManyToOne(optional = false)
    private Food foodId;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne(optional = false)
    private Usr userId;

    public Consumption() {
    }

    public Consumption(Integer consumId) {
        this.consumId = consumId;
    }

    public Consumption(Integer consumId, Date consumptionDate, int quantity) {
        this.consumId = consumId;
        this.consumptionDate = consumptionDate;
        this.quantity = quantity;
    }

    public Integer getConsumId() {
        return consumId;
    }

    public void setConsumId(Integer consumId) {
        this.consumId = consumId;
    }

    public Date getConsumptionDate() {
        return consumptionDate;
    }

    public void setConsumptionDate(Date consumptionDate) {
        this.consumptionDate = consumptionDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Food getFoodId() {
        return foodId;
    }

    public void setFoodId(Food foodId) {
        this.foodId = foodId;
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
        hash += (consumId != null ? consumId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Consumption)) {
            return false;
        }
        Consumption other = (Consumption) object;
        if ((this.consumId == null && other.consumId != null) || (this.consumId != null && !this.consumId.equals(other.consumId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Consumption[ consumId=" + consumId + " ]";
    }
    
}
