import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

/**
 * Controller class for the Browse Resources Page for Librarian/User.
 * Handles what happens when the user interacts with the UI.
 *
 * @author Chris McAuley, Ivan Andreev
 */

public class BrowseResourcesController extends Controller {

    /**
     * The button to close the tab.
     */
    @FXML
    private Button closeButton;

    /**
     * The button to filter the data to show dvds.
     */
    @FXML
    private CheckBox dvdFilter;

    /**
     * The button to filer the data to show book.
     */
    @FXML
    private CheckBox bookFilter;

    /**
     * The button to filter the data to show laptops.
     */
    @FXML
    private CheckBox laptopFilter;

    /**
     * The button to filter the data to show games.
     */
    @FXML
    private CheckBox gameFilter;

    /**
     * The button to browse resources for resources.
     */
    @FXML
    private TextField browseResourcesSearchTextField;

    /**
     * The button for searches.
     */
    @FXML
    private Button searchButton;

    /**
     * Table that displays resources.
     */
    @FXML
    private TableView<Resource> displayTable;

    /**
     * The unique ID column of the table.
     */
    @FXML
    private TableColumn<Resource, String> uniqueIDColumn;

    /**
     * The title column of the table.
     */
    @FXML
    private TableColumn<Resource, String> titleColumn;

    /**
     * The year column of the table.
     */
    @FXML
    private TableColumn<Resource, Integer> yearColumn;

    /**
     * The type of resource column of the table.
     */
    @FXML
    private TableColumn<Resource, String> typeColumn;

    /**
     * Button to take the user back to the dashboard.
     */
    @FXML
    private Button backButton;

    /**
     * The data inside the table.
     */
    private ObservableList<Resource> data;

    /**
     * An ArrayList containing which resources should be shown.
     */
    private ArrayList<String> acceptableTypes;

    /**
     * Fills the table with data.
     */
    public void initialize() {
        data = FXCollections.observableArrayList();
        acceptableTypes = new ArrayList<>();
        uniqueIDColumn.setCellValueFactory(new PropertyValueFactory<Resource, String>("uniqueID"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Resource, String>("title"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<Resource, Integer>("year"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Resource, String>("type"));

        displayTable.setOnMouseClicked(event -> {

            if (event.getClickCount() == 2) {
                if (getLibrary().getCurrentUserLoggedIn().hasAdminAccess()) {
                    LibrarianResourceController.setClickedResource(displayTable.getSelectionModel().getSelectedItem());
                    new NewWindow("resources/LibrarianResource.fxml", event,
                            "Librarian Copy View - Tawe Lib", getLibrary());


                } else {
                    UserResourceController.setClickedResource(displayTable.getSelectionModel().getSelectedItem());
                    new NewWindow("resources/UserResource.fxml", event,
                            "User Copy View - Tawe Lib", getLibrary());
                }
            }
        });
    }

    /**
     * Refreshes the table on startup.
     */
    @Override
    public void onStart() {
        this.updateTable();
    }


    /**
     * Updates the data shown on the table.
     */
    @FXML
    public void updateTable() {
        // clear previous data
        data.clear();
        displayTable.getItems().clear();

        // see which of the checked boxes are ticked
        this.manageCheckedBoxTypes();

        //Search for resource in list then populate table.
        String searchCriteria = browseResourcesSearchTextField.getText();

        // If search criteria is empty show all/ else show only the ones that match
        if (searchCriteria.equals("")) {
            for (Resource resource : getLibrary().getResourceManager().getAllResources()) {
                data.add(resource);
            }

        } else {
            for (Resource resource : getLibrary().getResourceManager().getAllResources()) {
                if (resource.getTitle().toLowerCase().indexOf(searchCriteria.toLowerCase()) != -1) {
                    data.add(resource);
                }
            }
        }

        // if none of the types have been ticked accept all
        // else remove the resources that are not permitted
        ArrayList<Resource> shouldNotBeDisplayed = new ArrayList<>();
        if (acceptableTypes.size() != 0) {
            for (Resource resource : data) {
                if (!acceptableTypes.contains(resource.getType())) {
                    shouldNotBeDisplayed.add(resource);
                }
            }
        }
        data.removeAll(shouldNotBeDisplayed);

        displayTable.getItems().addAll(data);
    }

    /**
     * Goes back to the previous window when the back button is clicked.
     *
     * @param event Clicking on the back button.
     */
    @FXML
    public void backButtonClicked(ActionEvent event) {
        //if librarian then go back to librarian dashboard else go back to user dashboard
        if (getLibrary().getCurrentUserLoggedIn().hasAdminAccess()) {
            new NewWindow("resources/LibrarianDashboard.fxml", event,
                    "Browse Resources - TaweLib", getLibrary());
        } else {
            new NewWindow("resources/UserDashboard.fxml", event,
                    "Dashboard - TaweLib", getLibrary());
        }
    }

    /**
     * Checks which boxes are ticked and shows data accordingly.
     */
    private void manageCheckedBoxTypes() {
        acceptableTypes.clear();
        if (dvdFilter.isSelected()) {
            acceptableTypes.add("DVD");
        }

        if (bookFilter.isSelected()) {
            acceptableTypes.add("Book");
        }

        if (laptopFilter.isSelected()) {
            acceptableTypes.add("Laptop");
        }

        if (gameFilter.isSelected()) {

            acceptableTypes.add("Game");
        }
    }
}

