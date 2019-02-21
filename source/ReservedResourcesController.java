import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controller class for the reserved resources window.
 * Handles the interaction between the user and UI.
 *
 * @author Sian Pike, Ivan Andreev
 */
public class ReservedResourcesController extends Controller {

    /**
     * Button to return user to dashboard.
     */
    @FXML
    private Button okButton;

    /**
     * Table that shows the reserved resources.
     */
    @FXML
    private TableView<TableRepresentationCopyInformation> reservedResourcesTable;

    /**
     * The column showing the copy ID of the reserved resource.
     */
    @FXML
    private TableColumn<TableRepresentationCopyInformation, String> copyIDColumn;

    /**
     * The column showing the resource name.
     */
    @FXML
    private TableColumn<TableRepresentationCopyInformation, String> resourceNameColumn;

    /**
     * The column showing the resource type.
     */
    @FXML
    private TableColumn<TableRepresentationCopyInformation, String> resourceTypeColumn;

    /**
     * The data inside the table.
     */
    @FXML
    private ObservableList<TableRepresentationCopyInformation> data;

    /**
     * When the user clicks the button he is returned to the dashboard.
     *
     * @param event The button is clicked.
     */
    @FXML
    public void okButtonClicked(ActionEvent event) {
        new NewWindow("resources/UserDashboard.fxml", event, "Dashboard - TaweLib", getLibrary());
    }

    /**
     * Initializes the table, fills it with data.
     */
    @Override
    public void onStart() {
        data = FXCollections.observableArrayList();
        copyIDColumn.setCellValueFactory(
                new PropertyValueFactory<TableRepresentationCopyInformation, String>("copyID"));
        resourceNameColumn.setCellValueFactory(
                new PropertyValueFactory<TableRepresentationCopyInformation, String>("resourceName"));
        resourceTypeColumn.setCellValueFactory(
                new PropertyValueFactory<TableRepresentationCopyInformation, String>("resourceType"));

        this.fillInData();
        reservedResourcesTable.getItems().addAll(data);
    }

    /**
     * Fills the data.
     */
    private void fillInData() {
        User userCurrentlyLoggedIn = getLibrary().getCurrentUserLoggedIn();
        for (Copy copy : getLibrary().getResourceManager().getReservedCopiesFor(userCurrentlyLoggedIn)) {
            String copyID = copy.getUniqueCopyID();
            String resourceName = copy.getCopyOf().getTitle();
            String resourceType = copy.getCopyOf().getType();

            data.add(new TableRepresentationCopyInformation(copyID, resourceName, resourceType));
        }
    }
}