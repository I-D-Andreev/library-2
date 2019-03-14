
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Controller class for the Librarian Dashboard.
 * Handles what happens when the user interacts with the UI.
 *
 * @author Sian Pike, Ivan Andreev
 */

public class LibrarianDashboardController extends Controller {

    /**
     * The button to log out.
     */
    @FXML
    private Button logOutButton;

    /**
     * The button to take you to the manage resources tab.
     */
    @FXML
    private Button manageResourcesButton;

    /**
     * The button to create or edit resources.
     */
    @FXML
    private Button createEditResourcesButton;

    /**
     * The button to browse resources.
     */
    @FXML
    private Button browseResourcesButton;

    /**
     * The button to view users.
     */
    @FXML
    private Button viewUserButton;

    /**
     * The button to filter users.
     */
    @FXML
    private Button userButton;

    /**
     * The button to view the overdue resources.
     */
    @FXML
    private Button overdueResourcesButton;

    /**
     * The button to view copy history.
     */
    @FXML
    private Button copyHistoryButton;

    /**
     * The button to edit accounts.
     */
    @FXML
    private Button editAccountButton;

    /**
     * The label that displays the username.
     */
    @FXML
    private Label usernameLabel;

    /**
     * The label that displays the first name.
     */
    @FXML
    private Label firstNameLabel;

    /**
     * The label that displays the last name.
     */
    @FXML
    private Label lastNameLabel;

    /**
     * The label that shows the phone number.
     */
    @FXML
    private Label phoneNumberLabel;

    /**
     * The label that shows the staff ID.
     */
    @FXML
    private Label staffIDLabel;

    /**
     * The profile image of the librarian.
     */
    @FXML
    private ImageView librarianImage;

    /**
     * Button to view all users currently borrowing.
     */
    @FXML
    private Button usersBorrowing;

    /**
     * Label that shows the employment date of the librarian.
     */
    @FXML
    private Label employmentDateLabel;

    /**
     * Button to open the statistics window.
     */
    @FXML
    private Button statisticsButton;

    /**
     * Button to open the event window.
     */
    @FXML
    private Button eventButton;

    /**
     * Button to open the fines statistics window.
     */
    @FXML
    private Button finesStaticsButton;


    /**
     * Loads the librarian information on startup.
     */
    @Override
    public void onStart() {
        this.loadLibrarianInformation();
    }

    /**
     * Opens the statistics window.
     *
     * @param event When the statistics button is clicked.
     */
    @FXML
    void statisticsButtonClicked(ActionEvent event) {

        new NewWindow("resources/LibrarianStatistics.fxml", event, "Statistics - TaweLib",
                getLibrary());
    }

    /**
     * Opens the event window.
     *
     * @param event When the create event button is clicked.
     */
    @FXML
    void eventButtonClicked(ActionEvent event) {

        new NewWindow("resources/LibrarianEvent.fxml", event, "Events - TaweLib", getLibrary());
    }

    /**
     * Loads the librarian information into the dashboard.
     */
    private void loadLibrarianInformation() {

        try {
            String path = getLibrary().getCurrentUserLoggedIn().getProfileImagePath();
            Image image;
            // relative path
            if (!path.isEmpty() && path.charAt(0) >= 'a' && path.charAt(0) <= 'z') {
                image = new Image(path);
            } else {
                // absolute path
                image = new Image(new File(path).toURI().toString());
            }
            librarianImage.setImage(image);

        } catch (IllegalArgumentException e) {
            librarianImage.setImage(new Image("resources/noImage.png"));
        } catch (NullPointerException e) {
            librarianImage.setImage(null);
        }
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        usernameLabel.setText(getLibrary().getCurrentUserLoggedIn().getUsername());
        firstNameLabel.setText(getLibrary().getCurrentUserLoggedIn().getFirstName());
        lastNameLabel.setText(getLibrary().getCurrentUserLoggedIn().getLastName());
        phoneNumberLabel.setText(getLibrary().getCurrentUserLoggedIn().getPhoneNumber());
        staffIDLabel.setText(((Librarian) (getLibrary().getCurrentUserLoggedIn())).getStaffNumber());
        employmentDateLabel.setText(
                dateFormat.format(
                        ((Librarian)(getLibrary().getCurrentUserLoggedIn())).getEmploymentDate()
                )
        );
    }

    /**
     * Opens the browse resources window.
     * @param event Button clicked event.
     */
    @FXML
    public void browseButtonClicked(ActionEvent event) {

        new NewWindow("resources/BrowseResources.fxml", event,
                "Browse Resources - TaweLib", getLibrary());
    }

    /**
     * Takes the user to the edit account tab when the button is clicked.
     * @param event The button is clicked.
     */
    public void editAccountButtonClicked(ActionEvent event) {
        new NewWindow("resources/EditAccount.fxml", event,
                "Browse Resources - TaweLib", getLibrary());
    }

    /**
     * Opens the create/edit resources window.
     * @param event Button clicked event.
     */
    @FXML
    public void createEditButtonClicked(ActionEvent event) {

        new NewWindow("resources/CreateEdit.fxml", event,
                "Create/Edit Resources - TaweLib", getLibrary());
    }

    /**
     * Takes the user back to the login window.
     * @param event Button clicked event.
     */
    @FXML
    public void logOutButtonClicked(ActionEvent event) {
        getLibrary().save();
        new NewWindow("resources/Login.fxml", event,
                "Login - TaweLib", getLibrary());
    }

    /**
     * Opens the manage resources window.
     * @param event Button clicked event.
     */
    @FXML
    public void manageResourcesButtonClicked(ActionEvent event) {

        new NewWindow("resources/ManageResources.fxml", event, "Manage Resources - TaweLib",
                getLibrary());
    }

    /**
     * Opens the view user window.
     * @param event Button clicked event.
     */
    @FXML
    public void viewUserButtonClicked(ActionEvent event) {

        new NewWindow("resources/ViewUser.fxml", event, "View User - TaweLib",
                getLibrary());
    }

    /**
     * The copy history button is clicked.
     *
     * @param event The button is clicked.
     */
    @FXML
    public void copyHistoryButtonClicked(ActionEvent event) {

        new NewWindow("resources/CopyHistory.fxml", event, "Copy History - TaweLib", getLibrary());
    }

    /**
     * Shows the overdue resources when the button is clicked.
     *
     * @param event The button is clicked.
     */
    @FXML
    public void overdueResourcesButtonClicked(ActionEvent event) {

        new NewWindow("resources/OverdueCopies.fxml", event, "Overdue Copies - TaweLib", getLibrary());
    }

    /**
     * The user button being clicked.
     *
     * @param event The button is clicked.
     */
    @FXML
    public void usersBorrowingButtonClicked(ActionEvent event) {
        new NewWindow("resources/ViewAllUsersCurrentlyBorrowing.fxml", event,
                "View All Users Currently Borrowing", getLibrary());

    }

    /**
     * The fines statistics button being clicked.
     * @param event The button is clicked.
     */
    @FXML
    public void finesStaticsButtonClicked(ActionEvent event){
        new NewWindow("resources/ViewAllUsersCurrentlyBorrowing.fxml", event,
                "Fines Statistics", getLibrary());
    }
}
