package at.htl.nfconnect.rest;

import at.htl.nfconnect.entities.Account;
import at.htl.nfconnect.entities.Card;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Elias Salfinger on 05/11/15.
 */
@Startup
@Singleton
public class InitBean {
    @PersistenceContext (name = "myPU")
    EntityManager em;

    public InitBean() {

    }

    @PostConstruct
    public void init() {
        Account account = new Account("test@mail.com", "passme", "Elias", "Salfinger");
        account.getMyCards().get(0).setEmail("nicememe@mail.com");
        Card card = new Card("Julian", "Hoerbst");
        card.setEmail("fag@mail.com");
        account.AddContact(card);
        em.persist(account);
    }
}
