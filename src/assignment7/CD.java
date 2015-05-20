/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment7;

/**
 *
 * @author Eric
 */
public class CD extends Media {
    private String artist;
    private String album;
    public static int quantity = 0;
    
    CD(String art, String alb) {
        artist = art;
        album = alb;
        
    }
    
    public String getArtist() { return artist; }
    public String getAlbum() { return album; }
    
}
