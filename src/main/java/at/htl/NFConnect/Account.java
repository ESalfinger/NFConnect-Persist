package at.htl.NFConnect;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by SaliBabba on 29/10/15.
 */

@Entity
@XmlRootElement
@NamedQueries({
        @NamedQuery(
                name = "findAccountByEmail",
                query = "select a from Account a " +
                        "where a.email = :EMAIL"
        ),
        @NamedQuery(
                name = "findAccountByFirstName",
                query = "select a from Account a " +
                        "where a.firstName = :FIRSTNAME"
        ),
        @NamedQuery(
                name = "findAccountByLastName",
                query = "select a from Account a " +
                        "where a.lastName = :LASTNAME"
        ),
        @NamedQuery(
                name = "findAccountByFullName",
                query = "select a from Account a " +
                        "where a.firstName = :FIRSTNAME and a.lastName = :LASTNAME"
        )
})
public class Account implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone1;
    private String phone2;
    private String adress;
    private String city;
    private String state;
    private String company;
    private String title;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Account> contacts = new LinkedList<Account>();

    public Account() {
    }

    public Account(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void AddContact(Account contact){
        if(contact != null && !contacts.contains(contact))
            contacts.add(contact);
    }

    //region Getter Setter
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    //endregion
}
