/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import data.table.ActiveplayersFacade;
import data.table.GamesFacade;
import data.table.UsersFacade;
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

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
        usersFacade = new UsersFacade();
        gamesFacade = new GamesFacade();
        activeplayersFacade = new ActiveplayersFacade();
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
    @Consumes("application/json")
    @Produces("application/json")
    public String postJSON(String content) {
        String[] variables = content.split("&");
        String action = variables[0];
        if(action.contains(("login"))){
            return "login action detected!";
        }else if(action.equals("logout")){
            return "logout action";
        }else if (action.contains("create")){
            return "create new user";
        }else if (action.contains("findGame")){
            return "finding game";
        }else if (action.contains("play")){
            return "playing game";
        }
        return "communicated with the server, no action!";
    }
}
