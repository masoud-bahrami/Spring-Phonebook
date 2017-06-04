package ir.refactor.model;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Created by 3tar on 3/27/2017.
 */

@Entity
@Table(name = "Contact")
public class Contact implements Serializable {

   @Id
   @Column(name = "Id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "Phone")
    private String phone;

    public  Contact() {
        super();
    }

    public Contact(String firstName ,String lastName , String phone)
    {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public int getId(){return this.id;}
    public void setId(int id){this.id = id;}


    public String getFirstName(){return this.firstName;}
    public void setFirstName(String firstName){this.firstName = firstName;}

    public String getLastName(){return this.lastName;}
    public void setLastName(String lastName){this.lastName = lastName;}

    public String getPhone(){return this.phone;}
    public void setPhone(String phone){this.phone = phone;}


    @Override
    public String toString() {
        return "Contact [firstName=" + firstName+ ", lastName=" + lastName
                + ", phone=" + phone + "]";
    }
}
