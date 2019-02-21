import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Controller class for the Profile Image UI.  Handles interactions from the user.
 *
 * @author Sian Pike, Ivan Andreev
 */
public class ProfileImageController extends Controller {

    /**
     * The path where the file of the image is located.
     */
    private String filePath;

    /**
     * The button for the first image.
     */
    @FXML
    private Button imageOneButton;

    /**
     * The button for the second image.
     */
    @FXML
    private Button imageTwoButton;

    /**
     * The button for the third image.
     */
    @FXML
    private Button imageThreeButton;

    /**
     * The button for the fourth image.
     */
    @FXML
    private Button imageFourButton;

    /**
     * The button for the fifth image.
     */
    @FXML
    private Button imageFiveButton;

    /**
     * The button for the sixth image.
     */
    @FXML
    private Button imageSixButton;

    /**
     * The button for the seventh image.
     */
    @FXML
    private Button imageSevenButton;

    /**
     * The button for the eighth image.
     */
    @FXML
    private Button imageEightButton;

    /**
     * The button for the ninth image.
     */
    @FXML
    private Button imageNineButton;

    /**
     * Button to cancel current action.
     */
    @FXML
    private Button cancelButton;

    /**
     * Initializes the images for the user to select.
     */
    @FXML
    private void initialize() {

        imageOneButton.setGraphic(new ImageView("resources/avatar1.png"));
        imageOneButton.setText("");

        imageTwoButton.setGraphic(new ImageView("resources/avatar2.png"));
        imageTwoButton.setText("");

        imageThreeButton.setGraphic(new ImageView("resources/avatar3.png"));
        imageThreeButton.setText("");

        imageFourButton.setGraphic(new ImageView("resources/avatar4.png"));
        imageFourButton.setText("");

        imageFiveButton.setGraphic(new ImageView("resources/avatar5.png"));
        imageFiveButton.setText("");

        imageSixButton.setGraphic(new ImageView("resources/avatar6.png"));
        imageSixButton.setText("");

        imageSevenButton.setGraphic(new ImageView("resources/avatar7.png"));
        imageSevenButton.setText("");

        imageEightButton.setGraphic(new ImageView("resources/avatar8.png"));
        imageEightButton.setText("");

        imageNineButton.setGraphic(new ImageView("resources/avatar9.png"));
        imageNineButton.setText("");
    }

    /**
     * Returns user to the dashboard when the button is clicked.
     *
     * @param event The button is pressed.
     */
    @FXML
    public void cancelButtonClicked(ActionEvent event) {

        if (getLibrary().getCurrentUserLoggedIn().hasAdminAccess()) {

            new NewWindow("resources/LibrarianDashboard.fxml",
                    event, "View User - TaweLib", getLibrary());

        } else {

            new NewWindow("resources/UserDashboard.fxml", event,
                    "Dashboard - TaweLib", getLibrary());
        }

    }

    /**
     * Chooses the first image as the avatar when the button is clicked.
     *
     * @param event The button is clicked.
     */
    @FXML
    public void imageOneButtonClicked(ActionEvent event) {
        this.filePath = "resources/avatar1.png";
        this.goBackToPreviousWindow(event);
    }

    /**
     * Chooses the second image as the avatar when the button is clicked.
     *
     * @param event The button is clicked.
     */
    @FXML
    void imageTwoButtonClicked(ActionEvent event) {
        this.filePath = "resources/avatar2.png";
        this.goBackToPreviousWindow(event);
    }

    /**
     * Chooses the third image as the avatar when the button is clicked.
     *
     * @param event The button is clicked.
     */
    @FXML
    void imageThreeButtonClicked(ActionEvent event) {
        this.filePath = "resources/avatar3.png";
        this.goBackToPreviousWindow(event);
    }

    /**
     * Chooses the fourth image as the avatar when the button is clicked.
     *
     * @param event The button is clicked.
     */
    @FXML
    void imageFourButtonClicked(ActionEvent event) {
        this.filePath = "resources/avatar4.png";
        this.goBackToPreviousWindow(event);
    }

    /**
     * Chooses the fifth image as the avatar when the button is clicked.
     *
     * @param event The button is clicked.
     */
    @FXML
    void imageFiveButtonClicked(ActionEvent event) {
        this.filePath = "resources/avatar5.png";
        this.goBackToPreviousWindow(event);
    }

    /**
     * Chooses the sixth image as the avatar when the button is clicked.
     *
     * @param event The button is clicked.
     */
    @FXML
    void imageSixButtonClicked(ActionEvent event) {
        this.filePath = "resources/avatar6.png";
        this.goBackToPreviousWindow(event);
    }

    /**
     * Chooses the seventh image as the avatar when the button is clicked.
     *
     * @param event The button is clicked.
     */
    @FXML
    void imageSevenButtonClicked(ActionEvent event) {
        this.filePath = "resources/avatar7.png";
        this.goBackToPreviousWindow(event);

    }

    /**
     * Chooses the eighth image as the avatar when the button is clicked.
     *
     * @param event The button is clicked.
     */
    @FXML
    void imageEightButtonClicked(ActionEvent event) {
        this.filePath = "resources/avatar8.png";
        this.goBackToPreviousWindow(event);

    }

    /**
     * Chooses the ninth image as the avatar when the button is clicked.
     *
     * @param event The button is clicked.
     */
    @FXML
    void imageNineButtonClicked(ActionEvent event) {
        this.filePath = "resources/avatar9.png";
        this.goBackToPreviousWindow(event);
    }

    /**
     * Takes the user to the previous window when the button is clicked.
     *
     * @param event The button is clicked.
     */
    private void goBackToPreviousWindow(ActionEvent event) {
        getLibrary().getCurrentUserLoggedIn().setProfileImagePath(filePath);
        if (getLibrary().getCurrentUserLoggedIn() instanceof NormalUser) {
            new NewWindow("resources/UserDashboard.fxml", event,
                    "User Dashboard Controller", getLibrary());
        } else {
            new NewWindow("resources/LibrarianDashboard.fxml", event,
                    "Librarian Dashboard Controller", getLibrary());
        }
    }
}