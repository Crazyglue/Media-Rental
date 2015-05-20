package assignment7;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Eric Dobroveanu
 */
public class Customer {
    private LinkedList<Media> currentHolding;
    private LinkedList<Order> customerOrders;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String password;
    
    Customer(String n, String e, String p) {
        name = n;
        email = e;
        password = p;
        currentHolding = new LinkedList<>();
        customerOrders = new LinkedList<>();
    }
    Customer(String n, String p) {
        name = n;
        password = p;
    }
    
    Customer(ResultSet rs) {
        try {
            name = rs.getString("name");
            email = rs.getString("email");
            phone = rs.getString("phone");
            address = rs.getString("address");
            password = rs.getString("password");
            
            
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
    }
    
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    
    
    
    
    public void addMedia() {
        
        
    }
}
