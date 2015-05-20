package assignment7;

/**
 *
 * @author Eric Dobroveanu
 */
public class DVD extends Media {
    private String title;
    private String year;
    public static int quantity = 0;
    
    DVD(String t, String y) {
        title = t;
        year = y;
    }
    
    public String getTitle() { return title; }
    public void setTitle(String t) { title = t; }
    public String getYear() { return year; }
    public void setYear(String y) { year = y; }
    public int getQty() { return quantity; }
}
