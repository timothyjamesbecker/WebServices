/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Andrew
 */
@Entity
@Table(name = "activeplayers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Activeplayers.findAll", query = "SELECT a FROM Activeplayer a"),
    @NamedQuery(name = "Activeplayers.findByGame", query = "SELECT a FROM Activeplayer a WHERE a.game = :game"),
    @NamedQuery(name = "Activeplayers.findBySlot", query = "SELECT a FROM Activeplayer a WHERE a.slot = :slot")})
public class Activeplayer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "game")
    private Integer game;
    @Lob
    @Column(name = "player")
    private byte[] player;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "slot")
    private Integer slot;
    @JoinColumn(name = "inGameWith", referencedColumnName = "uid")
    @OneToOne
    private User inGameWith;
    @JoinColumn(name = "uid", referencedColumnName = "uid")
    @ManyToOne(optional = false)
    private User uid;

    public Activeplayer() {
    }

    public Activeplayer(Integer slot) {
        this.slot = slot;
    }

    public Integer getGame() {
        return game;
    }

    public void setGame(Integer game) {
        this.game = game;
    }

    public byte[] getPlayer() {
        return player;
    }

    public void setPlayer(byte[] player) {
        this.player = player;
    }

    public Integer getSlot() {
        return slot;
    }

    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    public User getInGameWith() {
        return inGameWith;
    }

    public void setInGameWith(User inGameWith) {
        this.inGameWith = inGameWith;
    }

    public User getUid() {
        return uid;
    }

    public void setUid(User uid) {
        this.uid = uid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (slot != null ? slot.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Activeplayer)) {
            return false;
        }
        Activeplayer other = (Activeplayer) object;
        if ((this.slot == null && other.slot != null) || (this.slot != null && !this.slot.equals(other.slot))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "data.Activeplayers[ slot=" + slot + " ]";
    }
    
}
