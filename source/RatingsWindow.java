import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

public class RatingsWindow extends Application {
    private UserResourceController oldWindow;
    private Resource currentResource;
    private NormalUser currentUser;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        primaryStage.setTitle("Rate this resource");
        primaryStage.setScene(new Scene(root, 400, 350));

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

        reviewText.setMaxWidth(300);
        reviewText.setMaxHeight(200);
        reviewText.setWrapText(true);

        rating.setPartialRating(true);
        rating.setUpdateOnHover(true);


        submitButton.setOnAction(event -> {

            Boolean previouslyBorrowed = false;

            for (Copy copy : currentResource.getCopyManager().getListOfAllCopies()) {
                for (HistoryEntry history : copy.getLoanHistory().getHistory()) {
                    HistoryEntryItemTransaction entry = (HistoryEntryItemTransaction) history;
                    if (entry.getBorrowedBy().equals(currentUser)) {
                        previouslyBorrowed = true;
                    }
                }
            }

            if (previouslyBorrowed) {
                Ratings rate = new Ratings(currentResource, currentUser);
                rate.setRating(rating.getRating());
                rate.setReview(reviewText.getText());

                currentResource.getRatings().add(rate);
                reviewText.setText("Rating & review submitted!");
                oldWindow.updateReviewTable();
            } else {
                reviewText.setText("You haven't taken out this resource before, you can't rate it!");
            }

        });

        primaryStage.show();
    }

    public void setOldWindow(UserResourceController oldWindow){
        this.oldWindow = oldWindow;
    }

    public void setResource(Resource resource) {
        this.currentResource = resource;
    }

    public void setUser(NormalUser user) {
        this.currentUser = user;
    }
}
