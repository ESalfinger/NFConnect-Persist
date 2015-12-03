package at.htl.nfconnect.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.DataBindingException;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by SaliBabba on 29/10/15.
 */

@Entity
@XmlRootElement
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
@NamedQueries({
        @NamedQuery(
                name = "findAll",
                query = "select a from Account a"
        ),
        @NamedQuery(
                name = "findAccountById",
                query = "select a from Account a " +
                        "where a.id = :ID"
        ),
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
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //---------------
    @NotNull(message = "Email required!")
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    private String email;
    //---------------
    @NotNull(message = "Password required!")
    private String password;
    //---------------
    @NotNull(message = "FirstName required!")
    @Pattern(regexp = "^$|[A-ZÄÖÜ][a-zäöü]*|[A-ZÄÖÜ][a-zäöü]*-[A-ZÄÖÜ][a-zäöü]*",
            message = "FirstName has to start with capital letter and can only contain letters; Double names have to be split with a hyphen!")
    @Size(max = 32, message = "FirstName can only be 32 characters long!")
    private String firstName;
    //---------------
    @NotNull(message = "LastName required!")
    @Pattern(regexp = "^$|[A-ZÄÖÜ][a-zäöü]*|[A-ZÄÖÜ][a-zäöü]*-[A-ZÄÖÜ][a-zäöü]*",
            message = "LastName has to start with capital letter and can only contain letters; Double names have to be split with a hyphen!")
    @Size(max = 32, message = "LastName can only be 32 characters long!")
    private String lastName;
    //---------------
    @Pattern(regexp = "^$|[0-9/]*", message = "Phonenumber has to be in this format ('0123/12345')!")
    @Size(max = 16, message = "Phonenumber can only be 16 characters long!")
    private String phone1;
    //---------------
    @Pattern(regexp = "^$|[0-9/]*", message = "Phonenumber has to be in this format ('0123/12345')!")
    @Size(max = 16, message = "Phonenumber can only be 16 characters long!")
    private String phone2;
    //---------------
    @Temporal(javax.persistence.TemporalType.DATE)
    @Past(message = "Date of birth has ti be in the past!")
    private LocalDate gebDat;
    //---------------
    private String street;
    //---------------
    private String city;
    //---------------
    private String state;
    //---------------
    private String company;
    //---------------
    private String title;
    //---------------

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="ACCOUNT_REF"
            , joinColumns={
            @JoinColumn(name="ACCOUNT_ID")
    }
            , inverseJoinColumns={
            @JoinColumn(name="CONTACTS_ID")
    }
    )
    private List<Account> contacts;

    public Account() {
        this.email = "";
        this.password = "";
        this.firstName = "";
        this.lastName = "";
        this.phone1 = "";
        this.phone2 = "";
        this.gebDat = null;
        this.street = "";
        this.city = "";
        this.state = "";
        this.company = "";
        this.title = "";
        this.contacts = new LinkedList<Account>();
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

    public LocalDate getGebDat() {
        return gebDat;
    }

    public void setGebDat(LocalDate gebDat) {
        this.gebDat = gebDat;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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

    public List<Account> getContacts() {
        return contacts;
    }

    public String getFullName() {
        return firstName + " " + lastName+ ((title == null || title.equals("")) ? "" : " " + title);
    }

    public void setContacts(List<Account> contacts) {
        this.contacts = contacts;
    }
    //endregion
}
