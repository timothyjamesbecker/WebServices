/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.table;

import data.Users;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Andrew
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> {
    @PersistenceContext(unitName = "ProjectPU")
    private EntityManager em;

    public UsersFacade() {
        super(Users.class);
        em = Persistence.createEntityManagerFactory("ProjectPU").createEntityManager();
    }

    public void create(Users entity) {
        super.create(entity);
    }

    public void edit(String id, Users entity) {
        super.edit(entity);
    }

    public void remove(String id) {
        super.remove(super.find(id));
    }

    public Users find(String id) {
        return super.find(id);
    }

    public List<Users> findAll() {
        return super.findAll();
    }

    public List<Users> findRange(Integer from, Integer to) {
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
