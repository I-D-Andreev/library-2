import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class NewAdditionsController extends Controller{

    /**
     * Button to close the window
     */
    @FXML
    private Button okButton;

    @FXML
    private TableView<Resource> newAdditionsTable;

    @FXML
    private TableColumn<Resource, String> idColumn;

    @FXML
    private TableColumn<Resource, String> nameColumn;

    @FXML
    private TableColumn<Resource, String> typeColumn;

    /**
     * The data inside the table.
     */
    @FXML
    private ObservableList<Resource> data;

    /**
     * Button that closes the current window.
     *
     * @param event When the ok button is clicked.
     */
    @FXML
    void okButtonClicked(ActionEvent event) {
        // after a user has already seen the new additions, the list should empty
        NormalUser normalUser = (NormalUser)getLibrary().getCurrentUserLoggedIn();
        normalUser.getNewAdditions().clear();

        // switch to previous window
        new NewWindow("resources/UserDashboard.fxml", event, "Dashboard - TaweLib", getLibrary());
    }

    /**
     * Initializes the table and then fills it with data.
     */
    @Override
    public void onStart() {
        data = FXCollections.observableArrayList();
        System.out.println("Test");
        idColumn.setCellValueFactory(
                new PropertyValueFactory<Resource, String>("uniqueID"));
        nameColumn.setCellValueFactory(
                new PropertyValueFactory<Resource, String>("title"));
        typeColumn.setCellValueFactory(
                new PropertyValueFactory<Resource, String>("type"));

        this.fillInData();
        newAdditionsTable.getItems().addAll(data);
    }

    /**
     * Method that fills the table with data.
     */
    private void fillInData() {
        NormalUser currentlyLoggedIn = (NormalUser) getLibrary().getCurrentUserLoggedIn();
        for(Resource resource : currentlyLoggedIn.getNewAdditions()){
            data.add(resource);
           // data.add(new );
        }
    }

}
