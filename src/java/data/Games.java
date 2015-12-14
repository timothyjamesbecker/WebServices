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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Andrew
 */
@Entity
@Table(name = "games")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Games.findAll", query = "SELECT g FROM Games g"),
    @NamedQuery(name = "Games.findByGame", query = "SELECT g FROM Games g WHERE g.game = :game"),
    @NamedQuery(name = "Games.findByGameState", query = "SELECT g FROM Games g WHERE g.gameState = :gameState"),
    @NamedQuery(name = "Games.findByTurn", query = "SELECT g FROM Games g WHERE g.turn = :turn")})
public class Games implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "game")
    private Integer game;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "gameState")
    private String gameState;
    @Basic(optional = false)
    @NotNull
    @Column(name = "turn")
    private boolean turn;

    public Games() {
    }

    public Games(Integer game) {
        this.game = game;
    }

    public Games(Integer game, String gameState, boolean turn) {
        this.game = game;
        this.gameState = gameState;
        this.turn = turn;
    }

    public Integer getGame() {
        return game;
    }

    public void setGame(Integer game) {
        this.game = game;
    }

    public String getGameState() {
        return gameState;
    }

    public void setGameState(String gameState) {
        this.gameState = gameState;
    }

    public boolean getTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (game != null ? game.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Games)) {
            return false;
        }
        Games other = (Games) object;
        if ((this.game == null && other.game != null) || (this.game != null && !this.game.equals(other.game))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "data.Games[ game=" + game + " ]";
    }
    
}
