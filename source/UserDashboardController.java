import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Pair;

import javax.annotation.Resources;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

/**
 * Controller class for the User Dashboard.
 * Handles what happens when the user interacts with the UI.
 *
 * @author Sian Pike, Ivan Andreev
 */

public class UserDashboardController extends Controller {

    /**
     * Button to browse the resources.
     */
    @FXML
    private Button browseResourcesButton;

    /**
     * Button to log out.
     */
    @FXML
    private Button logOutButton;

    /**
     * Button to show borrowed resources.
     */
    @FXML
    private Button borrowedResourcesButton;

    /**
     * Button to show requested resources.
     */
    @FXML
    private Button requestedResourcesButton;

    /**
     * Button to show the reserved resources.
     */
    @FXML
    private Button reservedResourcesButton;

    /**
     * Button to show the overdue resources.
     */
    @FXML
    private Button overdueResourcesButton;

    /**
     * Button to show the transaction history.
     */
    @FXML
    private Button transHistoryButton;

    /**
     * The user image.
     */
    @FXML
    private ImageView userImage;

    /**
     * Label showing user's username.
     */
    @FXML // fx:id="usernameLabel"
    private Label usernameLabel;

    /**
     * Label showing user's first name.
     */
    @FXML
    private Label firstNameLabel;

    /**
     * Label showing user's last name.
     */
    @FXML
    private Label lastNameLabel;

    /**
     * Label showing user's phone number.
     */
    @FXML
    private Label phoneNumberLabel;

    /**
     * Label showing the popular resources.
     */
    @FXML
    private Label popularResourceLabel;

    /**
     * Button to edit user profile.
     */
    @FXML
    private Button editProfileButton;

    /**
     * Button to show most popular resources.
     */
    @FXML
    private Button mostPopularResourceButton;

    /**
     * Label that shows the user's balance.
     */
    @FXML
    private Label balanceLabel;

    /**
     * Label that shows when the account was created.
     */
    @FXML
    private Label accountCreationLabel;

    /**
     * Button to open new additions window
     */
    @FXML
    private Button newAdditionsButton;

    /**
     * Button to open the statistics window
     */
    @FXML
    private Button statisticsButton;

    /**
     * Opens the statistics window.
     *
     * @param event When the statistics button is clicked.
     */
    @FXML
    void statisticsButtonClicked(ActionEvent event) {

        new NewWindow("resources/UserStatistics.fxml", event, "Statistics - TaweLib", getLibrary());
    }

    /**
     * Opens the new additions window.
     *
     * @param event When the new additions button is clicked.
     */
    @FXML
    void newAdditionsButtonClicked(ActionEvent event) {
        new NewWindow("resources/NewAdditions.fxml", event, "New additions - TaweLib",
                getLibrary());
    }

    /**
     * Takes the user to the borrowed resources tab after the button is clicked.
     *
     * @param event The button is clicked.
     */
    @FXML
    public void borrowedResourcesButtonClicked(ActionEvent event) {

        new NewWindow("resources/BorrowedResources.fxml", event, "Borrowed Resources - TaweLib",
                getLibrary());
    }

    /**
     * Takes the user to the edit account tab when the button is clicked.
     *
     * @param event The button is clicked.
     */
    @FXML
    public void editProfileButtonClicked(ActionEvent event) {
        new NewWindow("resources/EditAccount.fxml", event,
                "Edit Account - TaweLib", getLibrary());
    }

    /**
     * When the button is clicked shows the most popular resources in the library.
     *
     * @param event the button is clicked.
     */
    @FXML
    public void mostPopularResourceButtonClicked(ActionEvent event) {
        if (getLibrary().getResourceManager().getAllResources().size() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "There are no resources in the library.",
                    ButtonType.OK);
        } else {
            Pair<Resource, Integer> mostPopularResourcePair = getLibrary().getResourceManager().mostPopularResource();
            String content = "The most popular resource in the library is " +
                    mostPopularResourcePair.getKey().getTitle() + "! It was borrowed " +
                    mostPopularResourcePair.getValue() + " times!";
            Alert alert = new Alert(Alert.AlertType.INFORMATION, content, ButtonType.OK);
            alert.setTitle("Most Popular Resource");
            alert.setHeaderText("Most Popular Resource");
            alert.show();
        }
    }

    /**
     * Loads information into the user dashboard.
     */
    @Override
    public void onStart() {
        this.loadUserInformation();
    }

    /**
     * Opens the browse resources window.
     * @param event Button clicked event.
     */
    @FXML
    public void browseResourcesButtonClicked(ActionEvent event) {

        new NewWindow("resources/BrowseResources.fxml", event,
                "Browse Resources - TaweLib", getLibrary());

    }

    /**
     * Takes the user back to the login screen.
     * @param event Button clicked event.
     */
    @FXML
    public void logOutButtonClicked(ActionEvent event) {
        getLibrary().save();
        new NewWindow("resources/Login.fxml", event,
                "Login - TaweLib", getLibrary());
    }

    /**
     * Takes the user to the overdue resources tab when the button is clicked.
     *
     * @param event The button is clicked.
     */
    @FXML
    public void overdueResourcesButtonClicked(ActionEvent event) {

        new NewWindow("resources/OverdueResources.fxml", event, "Overdue Resources - TaweLib",
                getLibrary());
    }

    /**
     * Takes the user to the resources requested tab after the button is clicked.
     *
     * @param event The button is clicked.
     */
    @FXML
    public void requestedResourcesButtonClicked(ActionEvent event) {

        new NewWindow("resources/RequestedResources.fxml", event, "Requested Resources - TaweLib",
                getLibrary());
    }

    /**
     * Takes the user to the reserved resources tab after the button is clicked.
     *
     * @param event The button is clicked.
     */
    @FXML
    public void reservedResourcesButtonClicked(ActionEvent event) {

        new NewWindow("resources/ReservedResources.fxml", event, "Reserved Resources - TaweLib",
                getLibrary());
    }

    /**
     * Takes the user to the transaction history tab after the button is clicked.
     *
     * @param event The button is clicked.
     */
    @FXML
    public void transHistoryButtonClicked(ActionEvent event) {

        new NewWindow("resources/TransactionHistory.fxml", event, "Transaction History - TaweLib",
                getLibrary());
    }

    /**
     * Loads the user information into the table.
     */
    private void loadUserInformation() {

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
            userImage.setImage(image);

        } catch (IllegalArgumentException e) {
            userImage.setImage(new Image("resources/noImage.png"));
        } catch (NullPointerException e) {
            userImage.setImage(null);
        }

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        usernameLabel.setText(getLibrary().getCurrentUserLoggedIn().getUsername());
        firstNameLabel.setText(getLibrary().getCurrentUserLoggedIn().getFirstName());
        lastNameLabel.setText(getLibrary().getCurrentUserLoggedIn().getLastName());
        phoneNumberLabel.setText(getLibrary().getCurrentUserLoggedIn().getPhoneNumber());
        balanceLabel.setText(String.valueOf(((NormalUser) getLibrary().getCurrentUserLoggedIn()).getBalance()));
        accountCreationLabel.setText(
                dateFormat.format(
                        ((NormalUser) getLibrary().getCurrentUserLoggedIn()).getAccountCreationDate()
                )
        );
    }
}