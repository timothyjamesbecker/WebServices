/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author Andrew
 */
public class EntityManagerMaker {

    EntityManager em = Persistence.createEntityManagerFactory("ProjectPU").createEntityManager();

    public EntityManager getEm() {
        return em;
    }
}
