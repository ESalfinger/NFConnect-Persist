package at.htl.nfconnect.business;
import at.htl.nfconnect.entities.Account;

import javax.ejb.Stateless;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TransactionRequiredException;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Stateless
public class EndpointFacade {
    @PersistenceContext
    EntityManager em;

    public List<Account> getAccounts() {
        return em.createNamedQuery("findAll", Account.class)
                .getResultList();
    }

    public Response logIn(JsonObject accountData) {
        System.out.println("log in attempted");
        String email;
        String password;
        try {
            email = accountData.getString("email");
            password = accountData.getString("password");
        } catch (NullPointerException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .header("cause", "Cant parse Json Object!").build();
        }
        Account requestedAccount;
        try {
            requestedAccount = em.createNamedQuery("findAccountByEmail", Account.class)
                    .setParameter("EMAIL", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .header("cause", "No Account found for Email: " + email).build();
        }

        if(requestedAccount.getPassword().equals(password)) {
            return Response.ok().entity(requestedAccount).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .header("cause", "Password doesnt match").build();
        }
    }

    public Response register(JsonObject accountData) {
        System.out.printf("registration attemted");
        String email, password, firstName, lastName;
        try {
            email = accountData.getString("email");
            password = accountData.getString("password");
            firstName = accountData.getString("firstName");
            lastName = accountData.getString("lastName");
        } catch (NullPointerException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .header("cause", "Cant parse Json Object!").build();
        }

        try {
            em.createNamedQuery("findAccountByEmail", Account.class)
                    .setParameter("EMAIL", email)
                    .getSingleResult();
            return Response.status(Response.Status.BAD_REQUEST)
                    .header("cause", "Email: " + email + " already in use!").build();
        } catch (NoResultException e) {
            Account registerAccount = new Account(email, password, firstName, lastName);
            try {
                em.persist(registerAccount);
                return Response.ok().entity(registerAccount).build();
            } catch (TransactionRequiredException ex) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .header("cause", "Couldnt persist account!").build();
            }
        }
    }

    public Response update(JsonObject accountData) {
        System.out.println("Account Update attemted");
        long id;
        String email, password, firstName, lastName, phone1, phone2, street, city, state, company, title;
        LocalDate gebDat;

        try {
            id = Long.parseLong(accountData.getString("id"));
            email = accountData.getString("email");
            password = accountData.getString("password");
            firstName = accountData.getString("firstName");
            lastName = accountData.getString("lastName");
            phone1 = accountData.getString("phone1");
            phone2 = accountData.getString("phone2");
            gebDat = LocalDate.parse(accountData.getString("gebDat"), DateTimeFormatter.BASIC_ISO_DATE);
            street = accountData.getString("street");
            city = accountData.getString("city");
            state = accountData.getString("state");
            company = accountData.getString("company");
            title = accountData.getString("title");
        } catch (NullPointerException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .header("cause", "Cant parse Json Object!")
                    .build();
        }

        Account updateAccount = em.createNamedQuery("findAccountById", Account.class)
                .setParameter("ID", id)
                .getSingleResult();

        updateAccount.setEmail(email);
        updateAccount.setPassword(password);
        updateAccount.setFirstName(firstName);
        updateAccount.setLastName(lastName);
        updateAccount.setPhone1(phone1);
        updateAccount.setPhone2(phone2);
        updateAccount.setGebDat(gebDat);
        updateAccount.setStreet(street);
        updateAccount.setCity(city);
        updateAccount.setState(state);
        updateAccount.setCompany(company);
        updateAccount.setTitle(title);

        try {
            em.merge(updateAccount);
        } catch (TransactionRequiredException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .header("cause", "Couldnt merge account!").build();
        }

        return  Response.ok().entity(updateAccount).build();
    }
}
