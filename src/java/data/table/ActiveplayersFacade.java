/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.table;

import data.Activeplayer;
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
public class ActiveplayersFacade extends AbstractFacade<Activeplayer> {
    @PersistenceContext(unitName = "ProjectPU")
    private EntityManager em;

    public ActiveplayersFacade() {
        super(Activeplayer.class);
        em = Persistence.createEntityManagerFactory("ProjectPU").createEntityManager();
    }

    public void create(Activeplayer entity) {
        super.create(entity);
    }

    public void edit(String id, Activeplayer entity) {
        super.edit(entity);
    }

    public void remove(String id) {
        super.remove(super.find(id));
    }

    public Activeplayer find(String id) {
        return super.find(id);
    }

    public List<Activeplayer> findAll() {
       throw new UnsupportedOperationException();//  return super.findAll();
    }

    public List<Activeplayer> findRange(Integer from, Integer to) {
       throw new UnsupportedOperationException();//  return super.findRange(new int[]{from, to});
    }

    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @Override
    protected EntityManager getEntityManager() {
        em = Persistence.createEntityManagerFactory("ProjectPU").createEntityManager();
        return em;
    }
    
}
