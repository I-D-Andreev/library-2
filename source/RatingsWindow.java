import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

/**
 * A class for the window for rating a resource.
 *
 * @author Christina Meggs, Steven Lewkowicz
 */
public class RatingsWindow extends Application {
    /**
     * The old window from which we come from.
     */
    private UserResourceController oldWindow;

    /**
     * The resource being rated.
     */
    private Resource currentResource;

    /**
     * The current user being logged in.
     */
    private NormalUser currentUser;

    /**
     * Opens the ratings window.
     *
     * @param primaryStage The stage of the window.
     */
    @Override
    public void start(Stage primaryStage) {
        final int SCENE_WIDTH = 400;
        final int SCENE_HEIGHT = 350;
        final int REVIEW_TEXT_WIDTH = 300;
        final int REVIEW_TEXT_HEIGHT = 200;

        BorderPane root = new BorderPane();
        primaryStage.setTitle("Rate this resource");
        primaryStage.setScene(new Scene(root, SCENE_WIDTH, SCENE_HEIGHT));

        Rating rating = new Rating(5);

        Label ratingLabel = new Label("Rate this resource!");

        VBox vertical = new VBox();
        vertical.getChildren().addAll(ratingLabel, rating);
        vertical.setAlignment(Pos.CENTER);

        root.setTop(vertical);
        root.setAlignment(vertical, Pos.TOP_CENTER);
        root.setMargin(vertical, new Insets(30, 12, 12, 12));

        TextArea reviewText = new TextArea("");

        Label reviewLabel = new Label("Tell us your opinion...");

        Button submitButton = new Button("Submit");

        VBox vertical2 = new VBox();
        vertical2.getChildren().addAll(reviewLabel, reviewText, submitButton);

        vertical2.setAlignment(Pos.CENTER);
        root.setCenter(vertical2);

        reviewText.setMaxWidth(REVIEW_TEXT_WIDTH);
        reviewText.setMaxHeight(REVIEW_TEXT_HEIGHT);
        reviewText.setWrapText(true);

        rating.setPartialRating(true);
        rating.setUpdateOnHover(true);

        // what happens when we click on the submit button
        submitButton.setOnAction(event -> {

            Boolean previouslyBorrowed = false;
            Boolean alreadyRated = false;

            // check if it was previously borrowed
            // (if it has not been borrowed by the user it can't be rated)
            for (Copy copy : currentResource.getCopyManager().getListOfAllCopies()) {
                for (HistoryEntry history : copy.getLoanHistory().getHistory()) {
                    HistoryEntryItemTransaction entry = (HistoryEntryItemTransaction) history;
                    if (entry.getBorrowedBy().equals(currentUser)) {
                        previouslyBorrowed = true;
                    }
                }
            }

            // check if it has already been rated
            for (Ratings rate : currentResource.getRatings()) {
                if (rate.getResource().equals(currentResource) && rate.getUser().equals(currentUser)) {
                    alreadyRated = true;
                }
            }

            if (previouslyBorrowed && !alreadyRated) {
                Ratings rate = new Ratings(currentResource, currentUser);
                rate.setRating(rating.getRating());
                rate.setReview(reviewText.getText());

                currentResource.getRatings().add(rate);
                reviewText.setText("Rating & review submitted!");
                oldWindow.updateReviewTable();
                primaryStage.close();

            } else if (alreadyRated) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Can't rate resource");
                alert.setContentText("You have already rated this resource");
                alert.showAndWait();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Can't rate resource");
                alert.setContentText("You haven't borrowed this resource before");
                alert.showAndWait();
            }

        });

        primaryStage.show();
    }

    /**
     * Change the window we come from.
     *
     * @param oldWindow The new window.
     */
    public void setOldWindow(UserResourceController oldWindow) {
        this.oldWindow = oldWindow;
    }

    /**
     * Change the resource we are rating.
     *
     * @param resource The new resource.
     */
    public void setResource(Resource resource) {
        this.currentResource = resource;
    }

    /**
     * Change the user that is currently rating.
     *
     * @param user The new user.
     */
    public void setUser(NormalUser user) {
        this.currentUser = user;
    }
}
