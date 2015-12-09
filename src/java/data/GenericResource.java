/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import data.table.ActiveplayersFacade;
import data.table.GamesFacade;
import data.table.UsersFacade;
import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.persistence.EntityManager;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author Andrew
 */
@Path("generic")
public class GenericResource {

    UsersFacade usersFacade;
    GamesFacade gamesFacade;
    ActiveplayersFacade activeplayersFacade;
    @Context
    private UriInfo context;
    private Database d;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
        usersFacade = new UsersFacade();
        gamesFacade = new GamesFacade();
        activeplayersFacade = new ActiveplayersFacade();
        d = new Database();
    }

    /**
     * Retrieves representation of an instance of data.GenericResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path("{id}")
    @Produces("application/json")
    public String getJson(@PathParam("id") String id) {
        gamesFacade.remove(gamesFacade.find(activeplayersFacade.find(id).getGame()));
        return "Asdf";
        //return activeplayersFacade.find(usersFacade.find(id).getUid()).toString();//usersFacade.find(id).getPassword();
    }

    /**
     * Retrieves representation of an instance of data.GenericResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path("{id}/{password}")
    @Produces("application/json")
    public String getJsonChangepass(@PathParam("id") String id, @PathParam("password") String password) {
        Users user = usersFacade.find(id);
        user.setPassword(password);
        // usersFacade.edit(user);
        return "Password changed to " + usersFacade.find(id).getPassword() + " (it should show: " + password + ")";
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
    
    @POST
    @Consumes("text/plain")
    @Produces("text/plain")
    public String postJSON(String content) {
       JsonReader reader;
       try{
            //read the posted data
            reader = Json.createReader(new StringReader(content));
            JsonObject json = reader.readObject();
            reader.close();
            String action, uid, password;
            action = json.getString("action"); 
            
        if(action.contains(("login"))){
            return loginUser(json.getString("uid"), json.getString("pwd"));     
        }else if(action.equals("logout")){
            return "logout action";
        }else if (action.contains("create")){
            return createUser(json.getString("uid"), json.getString("pwd"));
        }else if (action.contains("findGame")){
            return "finding game";
        }else if (action.contains("play")){
            return "playing game";
        }
        return "communicated with the server, no action!";
    }   catch(Exception e){
            return null;
        }
    }

    private String createUser(String uid, String password) {
        if(!userExists(uid)){
            d.create(new Users(uid, password));
        }
        return uid + " account created";
    }

    private String loginUser(String username, String password) {
        Users curUser = d.findUser(username);
        if(curUser.getPassword().equals(password)){
            d.create(new Activeplayers(username));
            return username + "was logged in";
        }else{
            return "unable to login";
        }
    }
    
    private boolean userExists(String username){
        if(d.findUser(username) != null){
            return true;
        }
        return false;
    }
}
