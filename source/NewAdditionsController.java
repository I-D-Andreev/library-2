import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * A class to showcase the new additions (new resources).
 *
 * @author Ivan Andreev, Sian Pike
 */
public class NewAdditionsController extends Controller {

    /**
     * Button to close the window
     */
    @FXML
    private Button okButton;

    /**
     * The table to show the new additions.
     */
    @FXML
    private TableView<Resource> newAdditionsTable;

    /**
     * A column of the table to show the ID of the resource.
     */
    @FXML
    private TableColumn<Resource, String> idColumn;

    /**
     * A column of the table to show the name of the resource.
     */
    @FXML
    private TableColumn<Resource, String> nameColumn;

    /**
     * A column of the table to show the type of the resource.
     */
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
        NormalUser normalUser = (NormalUser) getLibrary().getCurrentUserLoggedIn();
        normalUser.getNewAdditions().clear();

        // switch to previous window
        new NewWindow("resources/UserDashboard.fxml", event, "Dashboard - TaweLib", getLibrary());
    }

    /**
     * Initializes the table and then fills it with data on load of the window.
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
        for (Resource resource : currentlyLoggedIn.getNewAdditions()) {
            data.add(resource);
        }
    }

}
