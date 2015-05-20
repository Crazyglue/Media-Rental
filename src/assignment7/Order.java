package assignment7;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Eric Dobroveanu
 */
public class Order {
    private Date datePlaced;
    private Customer customer;
    private ArrayList<Media> items;
    private int totalItems;
    
    Order(Customer c) {
        items = new ArrayList<>();
        datePlaced = new Date();
        customer = c;
        totalItems = 0;
    }
    
    public String getDate() { return datePlaced.toString(); }
    public Customer getCustomer() { return customer; }
    public ArrayList<Media> getItems() { return items; }
    public void setCustomer(Customer c) { customer = c; }
    public int getTotalItems() { return totalItems; }
    
    public void add(Media item) { 
        items.add(item); 
        totalItems = items.size();
    }
    public void remove(Media item) { 
        items.remove(item); 
        totalItems = items.size();
    }
}
