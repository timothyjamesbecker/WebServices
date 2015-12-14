/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
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

    @Context
    private UriInfo context;
    private Database d;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
        d = new Database();
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
            return logoutUser(json.getString("uid"));
        }else if (action.contains("create")){
            return createUser(json.getString("uid"), json.getString("pwd"));
        }else if (action.contains("win")){
            return incrementWin(json.getString("uid"));
        }else if (action.contains("loss")){
           return incrementLoss(json.getString("uid"));
        }else if (action.contains("tie")){
           return incrementTie(json.getString("uid"));
        }else if (action.contains("check")){
            return getStat(json.getString("uid"), json.getString("type"));
        }
        return "communicated with the server, no action!";
    }   catch(Exception e){
            return "an unexpected error has occured";
        }
    }

    private String createUser(String uid, String password) {
        if(!userExists(uid)){
            d.create(new Users(uid, password));
        }
        return "success";
    }

    private String loginUser(String username, String password) {
        Users curUser = d.findUser(username);
        if(curUser.getPassword().equals(password)){
            d.create(new Activeplayers(username));
            return username + "success";
        }else{
            return "error";
        }
    }
    
    private boolean userExists(String username){
        if(d.findUser(username) != null){
            return true;
        }
        return false;
    }

    private String logoutUser(String username) {
        d.remove(d.findActiveplayer(d.findUser(username)));
        return "success";
    }

    private String incrementWin(String username) {
        Users user = d.findUser(username);
        user.setWins(user.getWins() + 1);
        d.save(user);
        return username + "wins";
    }

    private String incrementLoss(String username) {
        Users user = d.findUser(username);
        user.setLosses(user.getLosses() + 1);
        d.save(user);
        return username + "loses";
    }

    private String incrementTie(String username) {
        Users user = d.findUser(username);
        user.setTies(user.getTies() + 1);
        d.save(user);
        return username + "tied";
    }

    private String getStat(String username, String stat) {
        Users user = d.findUser(username);
        switch (stat) {
            case "win":
                return user.getWins().toString() + " wins!";
            case "tie":
                return user.getLosses().toString() + " ties.";
            case "loss":
                return user.getLosses().toString() + " losses :(";
            default:
                break;
        }
        return "no stats available";
    }
}
