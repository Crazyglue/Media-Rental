/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment7;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.Callback;


/**
 * FXML Controller class
 *
 * @author Eric
 */
public class MainController implements Initializable {
    private static Connection sqlConnection = null;
    private static final String databaseUrl = "jdbc:mysql://localhost:3306/mediarental";
    private static final String user = "root";
    private static final String password = "mysql";
    private static PreparedStatement sqlAddAlbumQuery = null;
    private static PreparedStatement sqlAddAlbumStatement = null;
    private static String cdQuery = "SELECT * FROM `cd` WHERE album = ? OR artist = ?;";
    private static String movieQuery = "SELECT * FROM `dvds` WHERE title = ? OR year = ?;";
    ObservableList<Media> orderList;
    private static Customer customer;
    private static Order currentOrder;
    ObservableList<Media> tempOrder;
    
    @FXML
    private TextField searchLibraryTextField;
    @FXML
    private Button searchLibrarySearchButton;
    @FXML
    private ListView<Media> searchLibraryResultsListView;
    @FXML
    private ListView<Media> searchLibraryOrderListView;
    @FXML
    private Button searchLibraryAddButton;
    @FXML
    private TextField renterInfoNameTextField;
    @FXML
    private TextField renterInfoPasswordTextField;
    @FXML
    private RadioButton renterInfoExistingCustomerRadioButton;
    @FXML
    private RadioButton renterInfoNewCustomerRadioButton;
    @FXML
    private TextField renterInfoEmailTextField;
    @FXML
    private Button renterInfoSubmitButton;
    @FXML
    private Button addMusicBrowseFolder;
    @FXML
    private Accordion accordionWrapper;
    @FXML
    private Button searchLibraryNextButton;
    @FXML
    private TitledPane addMediaTitledPane;
    @FXML
    private TitledPane customerInformationTitledPane;
    @FXML
    private TitledPane confirmationTitledPane;
    @FXML
    private ToggleGroup customerInformationToggleGroup;
    @FXML
    private Label customerInformationEmailLabel;
    @FXML
    private TextField addMovieTitleTextField;
    @FXML
    private TextField addMovieYearTextField;
    @FXML
    private Button addMovieSubmitButton;
    @FXML
    private ListView<Media> confirmationOrdersListView;
    @FXML
    private Label confirmationTotalItemsLabel;
    @FXML
    private Label confirmationDueDateLabel;
    @FXML
    private Button confirmationPlaceOrderButton;
    @FXML
    private Label confimationCustomerNameLabel;
    @FXML
    private Label confimationCustomerAddressLabel;
    @FXML
    private Label confimationCustomerPhoneLabel;
    @FXML
    private Label confimationCustomerEmailLabel;
    @FXML
    private Button searchLibraryRemoveButton;
    @FXML
    private RadioButton addMediaMusicRadioButton;
    @FXML
    private ToggleGroup addMediaRadioGroup;
    @FXML
    private RadioButton addMediaMovieRadioButton;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        orderList = FXCollections.observableArrayList();
        tempOrder = FXCollections.observableArrayList();
        confirmationOrdersListView.setItems(tempOrder);
        searchLibraryResultsListView.setCellFactory(new Callback<ListView<Media>, ListCell<Media>>(){
 
            @Override
            public ListCell<Media> call(ListView<Media> p) {
                 
                ListCell<Media> cell = new ListCell<Media>(){
 
                    @Override
                    protected void updateItem(Media item, boolean bln) {
                        super.updateItem(item, bln);
                        if (item != null) {
                            if (item instanceof CD) {
                                CD newCD = (CD) item;
                                setText(newCD.getArtist() + " - " + newCD.getAlbum());
                            }
                            else if (item instanceof DVD) {
                                DVD newDVD = (DVD) item;
                                setText(newDVD.getTitle() + " (" + newDVD.getYear() + ")");
                            }
                        }
                    }
 
                };
                 
                return cell;
            }
        });
        
        searchLibraryOrderListView.setCellFactory(new Callback<ListView<Media>, ListCell<Media>>(){
 
            @Override
            public ListCell<Media> call(ListView<Media> p) {
                 
                ListCell<Media> cell = new ListCell<Media>(){
 
                    @Override
                    protected void updateItem(Media item, boolean bln) {
                        super.updateItem(item, bln);
                        if (item != null) {
                            if (item instanceof CD) {
                                CD newCD = (CD) item;
                                setText(newCD.getArtist() + " - " + newCD.getAlbum());
                            }
                            else if (item instanceof DVD) {
                                DVD newDVD = (DVD) item;
                                setText(newDVD.getTitle() + " (" + newDVD.getYear() + ")");
                            }
                        }
                    }
 
                };
                 
                return cell;
            }
        });
        confirmationOrdersListView.setCellFactory(new Callback<ListView<Media>, ListCell<Media>>(){
 
            @Override
            public ListCell<Media> call(ListView<Media> p) {
                 
                ListCell<Media> cell = new ListCell<Media>(){
 
                    @Override
                    protected void updateItem(Media item, boolean bln) {
                        super.updateItem(item, bln);
                        if (item != null) {
                            if (item instanceof CD) {
                                CD newCD = (CD) item;
                                setText(newCD.getArtist() + " - " + newCD.getAlbum());
                            }
                            else if (item instanceof DVD) {
                                DVD newDVD = (DVD) item;
                                setText(newDVD.getTitle() + " (" + newDVD.getYear() + ")");
                            }
                        }
                    }
 
                };
                 
                return cell;
            }
        });
        
        
        try {
            sqlConnection = DriverManager.getConnection(databaseUrl, user, password); 

        } catch (SQLException ex) {
            ex.printStackTrace();
        }  
    }    

    @FXML
    private void searchLibrarySearchButtonListener(ActionEvent event) {
        try {
        ObservableList<Media> resultList = FXCollections.observableArrayList();
        //String movieQuery = "SELECT * FROM `dvds` WHERE title = ? OR year = ?;";
        //String cdQuery = "SELECT * FROM `cd` WHERE album = ? OR artist = ?;";
        String searchTerm = searchLibraryTextField.getText();
        
        PreparedStatement movieStatement = sqlConnection.prepareStatement(movieQuery);
        PreparedStatement cdStatement = sqlConnection.prepareStatement(cdQuery);
        movieStatement.setString(1, searchTerm);
        movieStatement.setString(2, searchTerm);
        
        cdStatement.setString(1, searchTerm);
        cdStatement.setString(2, searchTerm);
        
        //ResultSet rsCD = cdStatement.executeQuery();
        System.out.println("Queries executed");
        
        if (addMediaMusicRadioButton.isSelected()) {
            ResultSet rsCD = cdStatement.executeQuery();
            while (rsCD.next()) {
                resultList.add(new CD(rsCD.getString("artist"), rsCD.getString("album")));
                System.out.println("Added: " + rsCD.getString("artist"));
            }
        }
        else if (addMediaMovieRadioButton.isSelected()) {
            ResultSet rsMovie = movieStatement.executeQuery();
            while (rsMovie.next()) {
                resultList.add(new DVD(rsMovie.getString("title"), rsMovie.getString("year")));
                System.out.println("Added: " + rsMovie.getString("title"));
            }
        }
        
       
        
        
        
        searchLibraryResultsListView.setItems(resultList);
        
        
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        
        
    }
    

    // need to take the selected item's string value, extract the artist and album names.  turn them into CD and DVD objects
    // add them to an array list that will later be used to submit the order to the database.
    // just need to get the selected object into a database, and add it to the order list view
    @FXML
    private void searchLibraryAddButtonListener(ActionEvent event) {
        Media selected = searchLibraryResultsListView.getSelectionModel().getSelectedItem();
        /*String cdArtist = selected.substring(0, selected.indexOf(" -- "));
        String cdAlbum = selected.substring(selected.indexOf(" -- ") + 4, selected.length());
        String movie = selected;
        String addCDquery = "SELECT * FROM `cd` WHERE album = ? AND artist = ?;";
        
        if (addMediaMusicRadioButton.isSelected()) {
            Media newItem = new CD(cdArtist, cdAlbum);
        }
        else if (addMediaMovieRadioButton.isSelected()) {
            Media newItem = new DVD(dvdTitle, dvdYear);
        }
        
        */
        tempOrder.add(selected);
        searchLibraryOrderListView.setItems(tempOrder);
        updateCustomerBlock();
        
    }

    private void updateCustomerBlock() {
        confirmationTotalItemsLabel.setText(String.valueOf(tempOrder.size()));
        confimationCustomerNameLabel.setText(customer.getName());
        confimationCustomerAddressLabel.setText(customer.getAddress());
        confimationCustomerPhoneLabel.setText(customer.getPhone());
        confimationCustomerEmailLabel.setText(customer.getEmail());
        
    }
    
    @FXML
    private void renterInfoSubmitButtonListener(ActionEvent event) {
        String customerName = renterInfoNameTextField.getText();
        String customerPass = renterInfoPasswordTextField.getText();
        String customerEmail; 
        
        try {
            if (renterInfoNewCustomerRadioButton.isSelected()) {
                System.out.println("new customer is selected");
                customerEmail = renterInfoEmailTextField.getText();
                String sqlAddNewCustomer = "INSERT INTO `mediarental`.`borrowers` (`name`, `password`, `email`) VALUES (?, ?, ?);";
                PreparedStatement sqlAddNewCustomerStatement = sqlConnection.prepareStatement(sqlAddNewCustomer);
                sqlAddNewCustomerStatement.setString(1, customerName);
                sqlAddNewCustomerStatement.setString(2, customerPass);
                sqlAddNewCustomerStatement.setString(3, customerEmail);
                
                sqlAddNewCustomerStatement.executeUpdate();

                customer = new Customer(customerName, customerEmail, customerPass);
            }
            else if (renterInfoExistingCustomerRadioButton.isSelected()) {
                System.out.println("existing customer is selected");
                String sqlExistingLookup = "SELECT * FROM borrowers WHERE name = ?;";
                PreparedStatement sqlExistingStatement = sqlConnection.prepareStatement(sqlExistingLookup);
                sqlExistingStatement.setString(1, customerName);
                ResultSet existingResult = sqlExistingStatement.executeQuery();
                existingResult.next();
                System.out.println("Results set");
                if (existingResult != null) 
                    if (!existingResult.isBeforeFirst()) {
                        System.out.println("We have results!");
                        if (existingResult.getString("password").equals(customerPass)){
                            System.out.println("Logged In!");
                            System.out.println(existingResult.getString("name") + " " + existingResult.getString("password"));
                            customer = new Customer(existingResult);
                            accordionWrapper.setExpandedPane(confirmationTitledPane);
                            updateCustomerBlock();
                            //customer = new Customer(existingResult.getString("name"), existingResult.getString("password"));
                        }
                        else
                            System.out.println("Invalid Password");
                    }
                    else
                        System.out.println("isBeforeFirst()!!!!!");
                else
                    System.out.println("ResultSet is NULL");
            }
            else
                System.out.println("Somethign went wrong");

            String sqlCustomerLogin = "SELECT `name` AND `password` FROM `borrowers` WHERE name = ?";
            PreparedStatement sqlLoginPrep = sqlConnection.prepareStatement(sqlCustomerLogin);

        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        
        System.out.println("Customer Object: " + customer.getName() + " " + customer.getEmail() + " " + customer.getPassword());
                
        
    }

    @FXML
    private void addMusicFolder(ActionEvent event) {
        DirectoryChooser dchooser = new DirectoryChooser();
        dchooser.setTitle("Choose your music folder...");
        File directory = dchooser.showDialog(new Stage());
        
        
        Mp3File musicFile;
        String fileName;
        String fileExtension;
        System.out.println("Entering scanMusicFolder for-loop");
        for (File temp : directory.listFiles()) {
            System.out.println("Current file: \t" + temp.toString());
            fileName = temp.getName();
            //FilenameUtils.getExtension(fileName);
            fileExtension = fileName.substring(fileName.indexOf(".") + 1, temp.getName().length());
            System.out.println("It's extension is: \t" + fileExtension);
            if (temp.isFile()) {
                if(fileExtension.equals("mp3")) {
                System.out.println("Its an mp3");
                try {
                    musicFile = new Mp3File(temp);
                    System.out.println("Entering addMusicToDatabase() function");
                    addMusicToDatabase(musicFile);
                    
                } catch (IOException io) {
                    io.printStackTrace();
                } catch(UnsupportedTagException ute) {
                    ute.printStackTrace();
                } catch(InvalidDataException ide) {
                    ide.printStackTrace();
                } catch (SQLException sql) {
                    sql.printStackTrace();
                }
                }
                else
                    System.out.println("It's not an .mp3 file");
            }
        }
    }
    
    private void addMusicToDatabase(Mp3File file) throws SQLException {
        ID3v2 tag = file.getId3v2Tag();
        String songArtist = tag.getArtist();
        String songAlbum = tag.getAlbum();
        
        String albumQuery = "SELECT `artist` FROM `cd` WHERE artist = ?;";
        String albumStatement = "INSERT INTO `mediarental`.`cd` (`artist`, `album`) VALUES (?, ?);";
        
        sqlAddAlbumQuery = sqlConnection.prepareStatement(albumQuery);
        sqlAddAlbumQuery.setString(1, songArtist);
        ResultSet rs = sqlAddAlbumQuery.executeQuery();
        if (rs != null)  
                if (!rs.isBeforeFirst()) {
                    sqlAddAlbumStatement = sqlConnection.prepareStatement(albumStatement);
                    sqlAddAlbumStatement.setString(1, songArtist);
                    sqlAddAlbumStatement.setString(2, songAlbum);
                    sqlAddAlbumStatement.executeUpdate();
                }
    }

    @FXML
    private void searchLibraryNextButtonListener(ActionEvent event) {
        accordionWrapper.setExpandedPane(customerInformationTitledPane);
        //customerInformationTitledPane.setExpanded(true);
        
    }

    @FXML
    private void customerInformationExistingCustomerListener(ActionEvent event) {
        customerInformationEmailLabel.setVisible(false);
        renterInfoEmailTextField.setVisible(false);
        
    }

    @FXML
    private void customerInformationNewCustomerListener(ActionEvent event) {
        customerInformationEmailLabel.setVisible(true);
        renterInfoEmailTextField.setVisible(true);
    }

    @FXML
    private void addMovieSubmitButtonListener(ActionEvent event) {
        String sqlStatement = "INSERT into dvds ";
        
    }

    @FXML
    private void confirmationPlaceOrderButtonListener(ActionEvent event) {
        //currentOrder = new Order(customer);
        try {
            //Get customer ID
            String sqlGetCustomerID = "SELECT * FROM borrowers WHERE name = ? AND password = ?;";
            PreparedStatement queryID = sqlConnection.prepareStatement(sqlGetCustomerID);
            queryID.setString(1, customer.getName());
            queryID.setString(2, customer.getPassword());
            
            ResultSet customerResult = queryID.executeQuery();
            customerResult.next();
            String customerID = customerResult.getString("idborrower");

            //Insert Command
            String sqlOrderInsert = "INSERT INTO orders (`borrower`, `cd`, `duedate`, `dateborrowed`, `dvd`) VALUES (?, ?, ?, ?, ?);";
            PreparedStatement insertOrder = sqlConnection.prepareStatement(sqlOrderInsert);
            insertOrder.setString(1, customerID);
            
            //For loop will step through each item on the order list and determine whether it is a CD or DVD.  Then it Query's the
            //database to get the ID of the CD/DVD and sets the appropriate prepared statement fields.
            for (Media mediaItem : tempOrder) {
                if (mediaItem instanceof CD) {
                    //Get CD ID
                    CD cd = (CD) mediaItem;
                    String sqlGetCDid = "SELECT * FROM cd WHERE artist = ? AND album = ?;";
                    PreparedStatement getCDid = sqlConnection.prepareStatement(sqlGetCDid);
                    getCDid.setString(1, cd.getArtist());
                    getCDid.setString(2, cd.getAlbum());
                    ResultSet rsCDid = getCDid.executeQuery();
                    rsCDid.next();
                    int cdID = rsCDid.getInt("idCD");
                    insertOrder.setInt(2, cdID);
                    insertOrder.setNull(5, java.sql.Types.NULL);
                }
                else if (mediaItem instanceof DVD) {
                    //Get DVD ID
                    DVD dvd = (DVD) mediaItem;
                    
                    String sqlGetDVDid = "SELECT * FROM dvds WHERE title = ? AND year = ?;";
                    PreparedStatement getDVDid = sqlConnection.prepareStatement(sqlGetDVDid);
                    getDVDid.setString(1, dvd.getTitle());
                    getDVDid.setString(2, dvd.getYear());
                    ResultSet rsDVDid = getDVDid.executeQuery();
                    rsDVDid.next();
                    int dvdID = rsDVDid.getInt("idDVD");
                    insertOrder.setInt(5, dvdID);
                    insertOrder.setNull(2, java.sql.Types.NULL);
                } 
            }
            java.util.Date tempDate = new Date();
            java.sql.Date now = new java.sql.Date(tempDate.getTime());
            java.sql.Date due = new java.sql.Date(dueDateCalc(tempDate).getTime());
            
            insertOrder.setDate(4, now);
            insertOrder.setDate(3, due);
            insertOrder.executeUpdate();
        
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
       // for (Media mediaItem : orderList)
    }

    
    private Date dueDateCalc(Date date) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("MMM dd, yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        System.out.println(sdf1.format(calendar.getTime()));
        calendar.add(Calendar.DATE,4);
        System.out.println(sdf2.format(calendar.getTime()));
        return calendar.getTime();
    }
    
    @FXML
    private void searchLibraryRemoveButtonListener(ActionEvent event) {
    }
    
}
