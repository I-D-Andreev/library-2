import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controller class for the requested resources window.
 * Handles the interaction between the user and UI.
 *
 * @author Sian Pike, Ivan Andreev
 */
public class RequestedResourcesController extends Controller {

    /**
     * Button to return to the dashboard.
     */
    @FXML
    private Button okButton;

    /**
     * The table that showing the requested resources.
     */
    @FXML
    private TableView<TableRepresentationResourceInformation> requestedResourcesTable;

    /**
     * The column showing resource ID.
     */
    @FXML
    private TableColumn<TableRepresentationResourceInformation, String> resourceID;

    /**
     * The column showing the resource name.
     */
    @FXML
    private TableColumn<TableRepresentationResourceInformation, String> resourceName;

    /**
     * The column showing the resource type.
     */
    @FXML
    private TableColumn<TableRepresentationResourceInformation, String> resourceType;

    /**
     * The column showing the number of resources in queue.
     */
    @FXML
    private TableColumn<TableRepresentationResourceInformation, Integer> numberInQueue;

    /**
     * The data inside the table.
     */
    @FXML
    private ObservableList<TableRepresentationResourceInformation> data;

    /**
     * When the button is clicked returns user back to the dashboard.
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
        resourceID.setCellValueFactory(
                new PropertyValueFactory<TableRepresentationResourceInformation, String>("resourceID"));

        resourceName.setCellValueFactory(
                new PropertyValueFactory<TableRepresentationResourceInformation, String>("resourceName"));

        resourceType.setCellValueFactory(
                new PropertyValueFactory<TableRepresentationResourceInformation, String>("resourceType"));

        numberInQueue.setCellValueFactory(
                new PropertyValueFactory<TableRepresentationResourceInformation, Integer>("positionInQueue"));

        this.fillInData();
        requestedResourcesTable.getItems().addAll(data);
    }

    /**
     * Fills the tables data.
     */
    private void fillInData() {
        User currentlyLoggedIn = getLibrary().getCurrentUserLoggedIn();
        for (Resource resource : getLibrary().getResourceManager().getRequestedResourcesBy(currentlyLoggedIn)) {
            String resID = resource.getUniqueID();
            String name = resource.getTitle();
            String type = resource.getType();
            Integer positionInQueue = resource.getCopyManager().positionInQueue(currentlyLoggedIn);
            data.add(new TableRepresentationResourceInformation(resID, name, type, positionInQueue));
        }
    }
}
