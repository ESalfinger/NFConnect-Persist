package at.htl.nfconnect.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.DefaultValue;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Elias Salfinger on 10/12/15.
 */
@Entity
@XmlRootElement
@NamedQueries({
        @NamedQuery(
                name = "findAllCards",
                query = "select c from Card c"
        ),
        @NamedQuery(
                name = "findCardById",
                query = "select c from Card c " +
                        "where c.id = :ID"
        ),
        @NamedQuery(
                name = "findCardByEmail",
                query = "select c from Card c " +
                        "where c.email = :EMAIL"
        ),
        @NamedQuery(
                name = "findCardByFullName",
                query = "select c from Card c " +
                        "where c.firstName = :FIRSTNAME and c.lastName = :LASTNAME"
        ),
        @NamedQuery(
                name = "findCardByCode",
                query = "select c from Card c " +
                        "where c.code = :CODE"
        )
})
public class Card implements Serializable{
    //region Fields
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //---------------
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
            message = "Email invalid!")
    private String email;
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
    private LocalDate gebDat;
    //---------------
    private String street;
    //---------------
    private String houseNumber;
    //---------------
    @Size(min = 3, max = 10, message = "ZipCode has to be between 3 and 10")
    private String zipCode;
    //---------------
    private String city;
    //---------------
    private String country;
    //---------------
    private String company;
    //---------------
    private String title;
    //---------------
    @DefaultValue(value = "false")
    private boolean locked;
    //---------------
    @OneToOne (cascade = CascadeType.PERSIST)
    @JoinColumn(name = "CODE")
    private Code code;
    //endregion

    public Card() {
        this("", "");
    }

    public Card(String firstName, String lastName, Code code) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.code = code;
    }

    public Card(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    //region Getter and Setter
    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
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

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String housenumber) {
        this.houseNumber = housenumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipcode) {
        this.zipCode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String state) {
        this.country = state;
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

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public String getFullName() {
        return firstName + " " + lastName+ ((title == null || title.equals("")) ? "" : " " + title);
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }
    //endregion
}
