package at.htl.NFConnect;

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
    @PersistenceContext
    EntityManager em;

    public InitBean() {

    }

    @PostConstruct
    public void init() {
        Account account = new Account("test@gmail.com", "passme", "Elias", "Salfinger");
        account.AddContact(new Account("fag@gmail.com", "passme", "Julian", "Hoerbst"));
        em.persist(account);
    }
}
