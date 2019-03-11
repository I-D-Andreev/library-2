import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

/**
 * Controller class for the Ratings and Reviews Window.
 * Handles the interaction between the user and the UI.
 *
 * @author Sian Pike
 */
public class RatingsReviewsController {

    /**
     * Text field to type in unique ID of resource.
     */
    @FXML
    private VBox searchTextField;

    /**
     * Choice box containing different ratings.
     */
    @FXML
    private ChoiceBox<?> ratingChoiceBox;

    /**
     * Text area to allow the user to type a review.
     */
    @FXML
    private TextArea reviewTextArea;

    /**
     * Button to close the window and save review.
     */
    @FXML
    private Button okButton;

    /**
     * Closes the window and saves the review.
     *
     * @param event When the ok button is clicked.
     */
    @FXML
    void okButtonClicked(ActionEvent event) {

    }

}
