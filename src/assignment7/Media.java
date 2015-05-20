package assignment7;

import static assignment7.Media.Status.IN_STOCK;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Eric
 */
public class Media {
    public enum Status {
        IN_STOCK,
        LOANED_OUT
        
    };
    
    private Status currentStatus;
    private Date dateBorrowed;
    private Date dueDate;
    private Customer customer = null;
    
    Media() {
        currentStatus = IN_STOCK;
    }
    
    public Status getStatus() { return currentStatus; }
    public String getDateBorrowed() { return dateBorrowed.toString(); }
    public String getDueDate() { return dueDate.toString(); }
    public void setCustomer(Customer c) { customer = c; }
    public Customer getCustomer() { return customer; }
    
    public void rentOut() {
        currentStatus = Status.LOANED_OUT;
        dateBorrowed = new Date();
        dueDateCalc();
    }
    
    public void returnMedia() {
        currentStatus = Status.IN_STOCK;
        dateBorrowed = null;
        dueDate = null;
    }
    
    public void setStatus(Status newStatus) {
        currentStatus = newStatus;
        if (currentStatus == Status.LOANED_OUT) {
            dateBorrowed = new Date();
            dueDateCalc();            
        }
        else if (currentStatus == Status.IN_STOCK)
            System.out.println("Status set to IN_STOCK");
        
        
    }
    
    
    private void dueDateCalc() {
        SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("MMM dd, yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateBorrowed);

        System.out.println(sdf1.format(calendar.getTime()));
        calendar.add(Calendar.DATE,4);
        System.out.println(sdf2.format(calendar.getTime()));
        dueDate = calendar.getTime();
    }
    
    
}
