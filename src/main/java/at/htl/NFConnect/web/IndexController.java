package at.htl.nfconnect.web;

import at.htl.nfconnect.business.EndpointFacade;
import at.htl.nfconnect.entities.validators.HashUtil;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.io.Serializable;

@Model
public class IndexController implements Serializable{

    @Inject
    EndpointFacade endpointFacade;

    private String email;
    private String password;




    public IndexController() {
    }

    public EndpointFacade getEndpointFacade() {
        return endpointFacade;
    }

    public void setEndpointFacade(EndpointFacade endpointFacade) {
        this.endpointFacade = endpointFacade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void performLogin(){
        boolean succes;

        succes = endpointFacade.logInWeb(email, HashUtil.hash(password));
        System.out.println("LOGIN : " + succes);

    }


}
