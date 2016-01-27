package at.htl.nfconnect.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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
                name = "findAllAccounts",
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
    //region Fields
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //---------------
    @NotNull(message = "Email required!")
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    @Column(unique = true)
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
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name="ACCOUNT_SHAREDCARDS"
            , joinColumns={
            @JoinColumn(name="ACCOUNT_ID")
    }
            , inverseJoinColumns={
            @JoinColumn(name="CARD_ID")
    }
    )
    private List<Card> sharedCards;
    //---------------
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name="ACCOUNT_MYCARDS"
            , joinColumns={
            @JoinColumn(name="ACCOUNT_ID")
    }
            , inverseJoinColumns={
            @JoinColumn(name="CARD_ID")
    }
    )
    private List<Card> myCards;
    //---------------

    @OneToOne(cascade = CascadeType.ALL)
    private Picture picture;
    //endregion
    public Account() {
        this("", "", "", "");
    }

    public Account(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sharedCards = new LinkedList<Card>();
        this.myCards = new LinkedList<Card>();
        myCards.add(new Card(firstName, lastName));
    }

    public Account(String email, String password, String firstName, String lastName, String codeValue) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sharedCards = new LinkedList<Card>();
        this.myCards = new LinkedList<Card>();
        Code code = new Code(codeValue);

        myCards.add(new Card(firstName, lastName, code));
    }

    public void AddContact(Card contact){
        if(contact != null && !sharedCards.contains(contact))
            sharedCards.add(contact);
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

    public List<Card> getSharedCards() {
        return sharedCards;
    }

    public void setSharedCards(List<Card> sharedCards) {
        this.sharedCards = sharedCards;
    }

    public List<Card> getMyCards() {
        return myCards;
    }

    public void setMyCards(List<Card> myCards) {
        this.myCards = myCards;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    //endregion
}
