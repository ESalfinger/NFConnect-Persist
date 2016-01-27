package at.htl.nfconnect.rest;

import at.htl.nfconnect.entities.Account;
import at.htl.nfconnect.entities.Code;
import at.htl.nfconnect.entities.validators.HashUtil;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Elias Salfinger on 26/01/16.
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
        /*Code code = new Code("testcode");
        Account account = new Account("test@mail.com", HashUtil.hash("passme"), "Elias", "Salfinger");
        account.getMyCards().get(0).setCode(code);
        em.persist(account);*/
    }
}
