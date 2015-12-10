/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import data.table.ActiveplayersFacade;
import data.table.GamesFacade;
import data.table.UsersFacade;
import static java.util.Collections.list;
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

    public Activeplayers findActiveplayer(Users user) throws NullPointerException {
        try {
            Activeplayers activeplayer = activeplayersFacade.find(user.getUid());
            activeplayersFacade.edit(activeplayer);
            return (activeplayer);
        } catch (NullPointerException e) {
            return null;
        }
    }
    
        public Activeplayers findActiveplayers(String id) throws NullPointerException {
        try {
            Activeplayers activeplayers = activeplayersFacade.find(id);
            activeplayersFacade.edit(activeplayers);
            return activeplayers;
        } catch (NullPointerException e) {
            return null;
        }
    }

    public Activeplayers findOtherActiveplayer(Activeplayers activeplayer) throws NullPointerException {
        try {
            Activeplayers tempActiveplayer = activeplayersFacade.find(activeplayer.getInGameWith());
            activeplayersFacade.edit(tempActiveplayer);
            return (activeplayer);
        } catch (NullPointerException e) {
            return null;
        }
    }

    public Users findUser(String id) throws NullPointerException {
        try {
            Users user = usersFacade.find(id);
            usersFacade.edit(user);
            return user;
        } catch (NullPointerException e) {
            return null;
        }
    }

    public Games findGame(Users user) throws NullPointerException {
        try {
            Games game = gamesFacade.find(findActiveplayer(user).getGame());
            gamesFacade.edit(game);
            return game;
        } catch (NullPointerException e) {
            return null;
        }
    }

    public List<Activeplayers> findWaitingActiveplayersDetached(Activeplayers entity) {
        try {
            return activeplayersFacade.findByGame(entity.getUid());
        } catch (NoResultException e) {
            return null;
        }
    }

    public Activeplayers findWaitingActiveplayer(Activeplayers entity) {
        try {
            Activeplayers searchingplayer = activeplayersFacade.findByGame(entity.getUid()).get(0);
            return findActiveplayers(searchingplayer.getUid());
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

}
