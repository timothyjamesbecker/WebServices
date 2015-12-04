/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.table;

import data.Users;
import java.io.StringReader;
import java.util.List;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.MediaType;

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

//    public String start(String content){
//        String uid, name;
//        JsonReader reader;
//        try{
//            //read the posted data
//            reader = Json.createReader(new StringReader(content));
//            JsonObject json = reader.readObject();
//            reader.close();
//            uid = json.getString("uid");
//            name = json.getString("name");
//            
//        } catch(Exception e){
//            uid = "chunk";
//            name = "track";
//        }
//        create(new Users(uid,name));
//        Users u = super.find(uid);
//        return u.toString();
//    }
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
        throw new UnsupportedOperationException();//return super.findRange(new int[]{from, to});
    }

    public String countREST() {
        throw new UnsupportedOperationException();//return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
