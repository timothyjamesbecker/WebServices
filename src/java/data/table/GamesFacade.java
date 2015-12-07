/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.table;

import data.Game;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Andrew
 */
@Stateless
public class GamesFacade extends AbstractFacade<Game> {
    @PersistenceContext(unitName = "ProjectPU")
    private EntityManager em;

    public GamesFacade() {
        super(Game.class);
        em = Persistence.createEntityManagerFactory("ProjectPU").createEntityManager();
    }

    public void create(Game entity) {
        super.create(entity);
    }

    public void edit(Integer id, Game entity) {
        super.edit(entity);
    }

    public void remove(Integer id) {
        super.remove(super.find(id));
    }

    public Game find(Integer id) {
        return super.find(id);
    }

    public List<Game> findAll() {
       throw new UnsupportedOperationException();//return super.findAll();
    }

    public List<Game> findRange(Integer from, Integer to) {
       throw new UnsupportedOperationException();// return super.findRange(new int[]{from, to});
    }

    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
