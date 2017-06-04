package ir.refactor.ViewModel;

/**
 * Created by 3tar on 3/27/2017.
 */
public class ContactViewModel{

    private int id;

    private String firstName;

    private String lastName;

    private String phone;

    private String fullName;

    public  ContactViewModel() {
        super();
    }

    public ContactViewModel(int id, String firstName ,String lastName , String phone)
    {
        super();
        this.id = id;
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

    public String getFullName() {
        return firstName + " " + lastName;
    }
}

