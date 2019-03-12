import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;

/**
 * Controller for the User Resource Window.
 * Handles user interaction with the UI.
 *
 * @author Sian Pike, Ivan Andreev
 */
public class UserResourceController extends Controller {

    /**
     * The resource image.
     */
    @FXML
    private ImageView resourceImage;

    /**
     * Label showing the copy ID.
     */
    @FXML
    private Label copyIdLabel;

    /**
     * Label showing if the resource is available.
     */
    @FXML
    private Label isAvailableLabel;

    /**
     * Button to return to the dashboard.
     */
    @FXML
    private Button okButton;

    /**
     * The resource we clicked on.
     */
    @FXML
    private static Resource clickedResource;

    /**
     * The table showing the available copies.
     */
    @FXML
    private TableView<TableRepresentationCopyAvailable> tableView;

    /**
     * The column showing unique copy ID.
     */
    @FXML
    private TableColumn<TableRepresentationCopyAvailable, String> uniqueIDColumn;

    /**
     * The column showing if the copy is available.
     */
    @FXML
    private TableColumn<TableRepresentationCopyAvailable, String> isAvailableColumn;

    /**
     * The data inside the table.
     */
    @FXML
    private ObservableList<TableRepresentationCopyAvailable> data;

    /**
     * The trailer button to view DVD trailer.
     */
    @FXML
    private Button trailerButton;

    /**
     * The review button to leave a review on resource.
     */
    @FXML
    private Button reviewButton;


    /**
     * Table to show ratings and reviews of the resource.
     */
    @FXML
    private TableView<Resource> ratingReviewTable;

    /**
     * Column to show the ratings of the resource.
     */
    @FXML
    private TableColumn<Resource, Double> ratingsColumn;

    /**
     * Column to show the reviews of the resource.
     */
    @FXML
    private TableColumn<Resource, String> reviewsColumn;

    /**
     * Takes the user to the browse resource tab after the button is clicked.
     *
     * @param event The button is clicked.
     */
    @FXML
    public void okButtonClicked(ActionEvent event) {
        clickedResource = null;
        new NewWindow("resources/BrowseResources.fxml", event,
                "Browse Resources - TaweLib", getLibrary());
    }

    /**
     * Opens window to view trailer for DVD resource.
     *
     * @param event When the trailer button is clicked.
     */
    @FXML
    void trailerButtonClicked(ActionEvent event) throws Exception {
            if (clickedResource.getType().equals("Video Game")) {
                VideoPlayer trailer = new VideoPlayer(clickedResource.getTitle() + " video game");
                trailer.start(new Stage());
            } else if (clickedResource.getType().equals("DVD")) {
                VideoPlayer trailer = new VideoPlayer(clickedResource.getTitle());
                trailer.start(new Stage());
            }
    }


    @FXML
    void reviewPressed(ActionEvent event) throws Exception {
        Stage reviewStage = new Stage();
        reviewStage.initModality(Modality.WINDOW_MODAL);
        Stage oldStage = (Stage) reviewButton.getScene().getWindow();
        reviewStage.initOwner(oldStage);



        Ratings rate = new Ratings(clickedResource);
        rate.start(reviewStage);

    }


    /**
     * Sets the clicked resource to be the resource we last clicked on.
     *
     * @param resource The resource we clicked on.
     */
    public static void setClickedResource(Resource resource) {
        clickedResource = resource;
    }

    /**
     * Initializes the tab for the user.
     */
    @Override
    public void onStart() {

        if (!(clickedResource.getType().equals("DVD") || clickedResource.getType().equals("Video Game"))) {
            trailerButton.setVisible(false);
        }

        data = FXCollections.observableArrayList();
        uniqueIDColumn.setCellValueFactory(new PropertyValueFactory<TableRepresentationCopyAvailable, String>("uniqueCopyID"));
        isAvailableColumn.setCellValueFactory(new PropertyValueFactory<TableRepresentationCopyAvailable, String>("isAvailable"));

        for (Copy copy : clickedResource.getCopyManager().getListOfAllCopies()) {
            data.add(new TableRepresentationCopyAvailable(copy.getUniqueCopyID(),
                    (copy.isAvailable()) ? "available" : "not available"));
        }

        tableView.getItems().addAll(data);

        this.loadImage();

    }

    /**
     * Tries to load an image.
     */
    private void loadImage() {
        try {
            String path = clickedResource.getThumbnailImagePath();
            Image image;
            // relative path
            if (path.charAt(0) >= 'a' && path.charAt(0) <= 'z') {
                image = new Image(path);
            } else {
                // absolute path
                image = new Image(new File(path).toURI().toString());
            }
            resourceImage.setImage(image);

        } catch (Exception e) {
            try {
                resourceImage.setImage(new Image("resources/noImage.png"));
            } catch (Exception ex) {
                resourceImage.setImage(null);
            }
        }
    }
}