/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import data.table.ActiveplayersFacade;
import data.table.GamesFacade;
import data.table.UsersFacade;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author Andrew
 */
public class Database {

    protected UsersFacade usersFacade;
    protected GamesFacade gamesFacade;
    protected ActiveplayersFacade activeplayersFacade;

    public Database() {
        usersFacade = new UsersFacade();
        gamesFacade = new GamesFacade();
        activeplayersFacade = new ActiveplayersFacade();
    }

    public Activeplayers findActiveplayer(Users user){
            return activeplayersFacade.find(user.getUid());
    }

    public Activeplayers findActiveplayers(String id){
            return activeplayersFacade.find(id);
    }

    public Activeplayers findOtherActiveplayer(Activeplayers activeplayer){
            return activeplayersFacade.find(activeplayer.getInGameWith());
    }

    public Users findUser(String id){
            return usersFacade.find(id);
    }

    public Games findGame(Users user){
            return gamesFacade.find(findActiveplayer(user).getGame());
    }

    public List<Activeplayers> findWaitingActiveplayers(Activeplayers entity) {
        try {
            return activeplayersFacade.findByGame(entity.getUid());
        } catch (NoResultException e) {
            return null;
        }
    }

    public Activeplayers findWaitingActiveplayer(Activeplayers entity) {
        try {
            return activeplayersFacade.findByGame(entity.getUid()).get(0);
        } catch (NoResultException e) {
            return null;
        }
    }

    public Activeplayers create(Activeplayers entity) {
        activeplayersFacade.create(entity);
        return activeplayersFacade.find(entity.getUid());
    }

    public Games create(Games entity) {
        gamesFacade.create(entity);
        return gamesFacade.find(entity.getGame());
    }

    public Users create(Users entity) {
        usersFacade.create(entity);
        return usersFacade.find(entity.getUid());
    }

    public void remove(Users entity) {
        usersFacade.remove(entity);
    }

    public void remove(Activeplayers entity) {
        activeplayersFacade.remove(entity);
    }

    public void remove(Games entity) {
        gamesFacade.remove(entity);
    }

    public void save(Users entity) {
        usersFacade.edit(entity);
    }

    public void save(Activeplayers entity) {
        activeplayersFacade.edit(entity);
    }

    public void save(Games entity) {
        gamesFacade.edit(entity);
    }
}
