package at.htl.nfconnect.web;

import at.htl.nfconnect.business.EndpointFacade;
import at.htl.nfconnect.entities.validators.HashUtil;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.io.Serializable;

@Model
public class RegisterController implements Serializable {

    @Inject
    EndpointFacade endpointFacade;

    private String name;
    private String lastname;
    private String email;
    private String password;
    private String confirmPassword;

    public RegisterController() {
    }

    public EndpointFacade getEndpointFacade() {
        return endpointFacade;
    }

    public void setEndpointFacade(EndpointFacade endpointFacade) {
        this.endpointFacade = endpointFacade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String performRegister() {
        boolean success;
        if (password.equals(confirmPassword)) {
            System.out.println("Entered registerWeb");
            success = endpointFacade.registerWeb(email, HashUtil.hash(password), name, lastname);
            System.out.println("Register " + success);
            return "mainpage?faces-redirect=true";
        } else {
            System.out.println("failed");
            success = false;
            System.out.println("Register " + success);
        }

        return "";
    }

}
