/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.table;

import data.Games;
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
public class GamesFacade extends AbstractFacade<Games> {
    @PersistenceContext(unitName = "ProjectPU")
    private EntityManager em;

    public GamesFacade() {
        super(Games.class);
        em = Persistence.createEntityManagerFactory("ProjectPU").createEntityManager();
    }

    public void create(Games entity) {
        super.create(entity);
    }

    public void edit(String id, Games entity) {
        super.edit(entity);
    }

    public void remove(String id) {
        super.remove(super.find(id));
    }

    public Games find(String id) {
        return super.find(id);
    }

    public List<Games> findAll() {
        return super.findAll();
    }

    public List<Games> findRange(Integer from, Integer to) {
        return super.findRange(new int[]{from, to});
    }

    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
