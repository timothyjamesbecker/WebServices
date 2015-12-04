/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.Serializable;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Andrew
 */
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByUid", query = "SELECT u FROM Users u WHERE u.uid = :uid"),
    @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password"),
    @NamedQuery(name = "Users.findByWins", query = "SELECT u FROM Users u WHERE u.wins = :wins"),
    @NamedQuery(name = "Users.findByLosses", query = "SELECT u FROM Users u WHERE u.losses = :losses"),
    @NamedQuery(name = "Users.findByTies", query = "SELECT u FROM Users u WHERE u.ties = :ties"),
    @NamedQuery(name = "Users.findByProfilePic", query = "SELECT u FROM Users u WHERE u.profilePic = :profilePic")})
public class Users implements Serializable {
   // @OneToOne(cascade = CascadeType.ALL, mappedBy = "users")
   // private Activeplayers activeplayers;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "uid")
    private String uid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "password")
    private String password;
    @Column(name = "wins")
    private Integer wins;
    @Column(name = "losses")
    private Integer losses;
    @Column(name = "ties")
    private Integer ties;
    @Size(max = 255)
    @Column(name = "profilePic")
    private String profilePic;

    public Users() {
    }

    public Users(String uid) {
        this.uid = uid;
    }

    public Users(String uid, String password) {
        this.uid = uid;
        this.password = password;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public Integer getLosses() {
        return losses;
    }

    public void setLosses(Integer losses) {
        this.losses = losses;
    }

    public Integer getTies() {
        return ties;
    }

    public void setTies(Integer ties) {
        this.ties = ties;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uid != null ? uid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.uid == null && other.uid != null) || (this.uid != null && !this.uid.equals(other.uid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "data.Users[ uid=" + uid + " ]";
    }

//    public Activeplayers getActiveplayers() {
//        return activeplayers;
//    }
//
//    public void setActiveplayers(Activeplayers activeplayers) {
//        this.activeplayers = activeplayers;
//    }
    
}
