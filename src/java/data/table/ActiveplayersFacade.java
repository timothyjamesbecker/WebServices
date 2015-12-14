/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.table;

import data.Activeplayers;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Andrew
 */
@Stateless
public class ActiveplayersFacade extends AbstractFacade<Activeplayers> {
    @PersistenceContext(unitName = "ProjectPU")
    private EntityManager em;

    public ActiveplayersFacade() {
        super(Activeplayers.class);
        em = Persistence.createEntityManagerFactory("ProjectPU").createEntityManager();
    }

    public void create(Activeplayers entity) {
        super.create(entity);
    }

    public void edit(String id, Activeplayers entity) {
        super.edit(entity);
    }

    public void remove(String id) {
        super.remove(super.find(id));
    }

    public Activeplayers find(String id) {
        return super.find(id);
    }

    public List<Activeplayers> findByGame(String searchingPlayerId) {
        Query query = em.createNamedQuery("Activeplayers.findBySearching");
        query.setParameter("uid", searchingPlayerId);query.setParameter("searching", true);
        return query.getResultList();
    }

    public List<Activeplayers> findAll() {
        return super.findAll();
    }

    public List<Activeplayers> findRange(Integer from, Integer to) {
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
