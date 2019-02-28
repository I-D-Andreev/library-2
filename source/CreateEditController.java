import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.util.Arrays;
import java.util.ArrayList;

/**
 * Controller class for the Create/Edit screen for the Librarian.
 * Handles what happens when the user interacts with the UI.
 *
 * @author Chris McAuley, Ivan Andreev
 */

public class CreateEditController extends Controller {

    /**
     * The book's title field.
     */
    @FXML
    private TextField titleBookTextField;

    /**
     * The book's year field.
     */
    @FXML
    private TextField yearBookTextField;

    /**
     * The book's thumbnail image path field.
     */
    @FXML
    private TextField imagePathBookTextField;

    /**
     * The book's author field.
     */
    @FXML
    private TextField authorBookTextField;

    /**
     * The book's publisher field.
     */
    @FXML
    private TextField publisherBookTextField;

    /**
     * The book's genre field.
     */
    @FXML
    private TextField genreBookTextField;

    /**
     * The book's ISBN field.
     */
    @FXML
    private TextField isbnBookTextField;

    /**
     * The book's language field.
     */
    @FXML
    private TextField languageBookTextField;

    /**
     * The button that creates book resources.
     */
    @FXML
    private Button createBookButton;

    /**
     * The DVD's title field.
     */
    @FXML
    private TextField titleDVDTextField;

    /**
     * The DVD's year field.
     */
    @FXML
    private TextField yearDVDTextField;

    /**
     * The DVD's thumbnail image path field.
     */
    @FXML
    private TextField imagePathDVDTextField;

    /**
     * The DVD's director field.
     */
    @FXML
    private TextField directorDVDTextField;

    /**
     * The DVD's runtime field.
     */
    @FXML
    private TextField runtimeDVDTextField;

    /**
     * The DVD's language field.
     */
    @FXML
    private TextField languageDVDTextField;

    /**
     * The DVD's language of subtitles field.
     */
    @FXML
    private TextField languageSubtitlesDVDTextField;

    /**
     * The button that creates a DVD resource.
     */
    @FXML
    private Button createDVDButton;

    /**
     * The laptop's title field.
     */
    @FXML
    private TextField titleLaptopTextField;

    /**
     * The laptop's year field.
     */
    @FXML
    private TextField yearLaptopTextField;

    /**
     * The laptop's thumbnail image path field.
     */
    @FXML
    private TextField imagePathLaptopTextField;

    /**
     * The laptop's manufacturer field.
     */
    @FXML
    private TextField manufacturerLaptopTextField;

    /**
     * The laptop's model field.
     */
    @FXML
    private TextField modelLaptopTextField;

    /**
     * The laptops operating system field.
     */
    @FXML
    private TextField operatingSystemLaptopTextField;

    /**
     * The button that creates laptop resources.
     */
    @FXML
    private Button createLaptopButton;

    /**
     * The book's field where we edit the unique ID.
     */
    @FXML
    private TextField uniqueIDSearchEditBookTextField;

    /**
     * The book's field where we edit the title.
     */
    @FXML
    private TextField titleEditBookTextField;

    /**
     * The book's field where we edit the year.
     */
    @FXML
    private TextField yearEditBookTextField;

    /**
     * The book's field where we edit the author.
     */
    @FXML
    private TextField authorEditBookTextField;

    /**
     * The book's field where we edit the thumbnail image path.
     */
    @FXML
    private TextField imagePathEditBook;

    /**
     * The book's field where we edit the publisher.
     */
    @FXML
    private TextField publisherEditBookTextField;

    /**
     * The book's field where we edit the genre.
     */
    @FXML
    private TextField genreEditBookTextField;

    /**
     * The book's field where we edit the ISBN.
     */
    @FXML
    private TextField isbnEditBookTextField;

    /**
     * The book's field where we edit the language.
     */
    @FXML
    private TextField languageEditBookTextField;

    /**
     * The button that allows us to edit a book.
     */
    @FXML
    private Button editBookButton;

    /**
     * The book's edit search button.
     */
    @FXML
    private Button editBookSearchButton;

    /**
     * The DVD's edit search field by unique ID.
     */
    @FXML
    private TextField uniqueIDSearchEditDVDTextField;

    /**
     * The DVD's field where we edit the title.
     */
    @FXML
    private TextField titleEditDVDTextField;

    /**
     * The DVD's field where we edit the year.
     */
    @FXML
    private TextField yearEditDVDTextField;

    /**
     * The DVD's field where we edit the thumbnail image path.
     */
    @FXML
    private TextField imagePathEditDVD;

    /**
     * The DVD's field where we edit the director.
     */
    @FXML
    private TextField directorEditDVDTextField;

    /**
     * The DVD's field where we edit the runtime.
     */
    @FXML
    private TextField runtimeEditDVDTextField;

    /**
     * The DVD's field where we edit the language.
     */
    @FXML
    private TextField languageEditDVDTextField;

    /**
     * The DVD's field where we edit the subtitle languages of the dvd.
     */
    @FXML
    private TextField languageSubtitlesEditDVDTextField;

    /**
     * The edit button of the DVDs.
     */
    @FXML
    private Button editDVDButton;

    /**
     * The DVD's button that allows us to search for a dvd to edit.
     */
    @FXML
    private Button editDVDSearchButton;

    /**
     * The tab where we edit a laptop.
     */
    @FXML
    private Tab editLaptopTab;

    /**
     * The laptop's field where we search for a laptop to edit by unique ID.
     */
    @FXML

    private TextField uniqueIDSearchEditLaptopTextField;

    /**
     * The laptop's field where we edit the title.
     */
    @FXML
    private TextField titleEditLaptopTextField;

    /**
     * The laptop's field where we edit the year.
     */
    @FXML
    private TextField yearEditLaptopTextField;

    /**
     * The laptop's field where we edit the manufacturer.
     */
    @FXML
    private TextField manufacturerEditLaptopTextField;

    /**
     * The laptop's field where we edit the model.
     */
    @FXML
    private TextField modelEditLaptopTextField;

    /**
     * The laptop's field where we edit the OS.
     */
    @FXML
    private TextField operatingSystemEditLaptopTextField;

    /**
     * The button that allows us to edit a laptop.
     */
    @FXML
    private Button editLaptopButton;

    /**
     * The button that allows us to search for laptop to edit.
     */
    @FXML
    private Button editLaptopSearchButton;

    /**
     * The laptop's field where we edit the thumbnail image path.
     */
    @FXML
    private TextField imagePathEditLaptop;

    /**
     * The back button.
     */
    @FXML
    private Button backButton;

    /**
     * Button for laptop deletion.
     */
    @FXML
    private Button laptopDeleteButton;

    /**
     * Button for DVD deletion.
     */
    @FXML
    private Button dvdDeleteButton;

    /**
     * Button for book deletion.
     */
    @FXML
    private Button bookDeleteButton;

    /**
     * The resources field for unique ID.
     */
    @FXML
    private TextField resourceUniqueIDtextField;

    /**
     * The resources search button.
     */
    @FXML
    private Button resourceSearchButton;

    /**
     * The copy's loan duration field.
     */
    @FXML
    private TextField copyLoanDurationTextField;

    /**
     * The button that creates a copy.
     */
    @FXML
    private Button copyCreateButton;

    /**
     * The button that creates a book image.
     */
    @FXML
    private Button createBookFindImageButton;

    /**
     * The button that creates a dvd image.
     */
    @FXML
    private Button createDvdFindImageButton;

    /**
     * The button that creates a laptop image.
     */
    @FXML
    private Button createLaptopFindImageButton;

    /**
     * The button that creates a book image.
     */
    @FXML
    private Button editBookFindImageButton;

    /**
     * The edit dvd image button.
     */
    @FXML
    private Button editDvdFindImageButton;

    /**
     * The edit laptop image button.
     */
    @FXML
    private Button editLaptopFindImageButton;

    /**
     * Field for the edit of the search ID of the copy.
     */
    @FXML
    private TextField editCopyIdSearchTextField;

    /**
     * Field for the edit of the loan duration of the copy.
     */
    @FXML
    private TextField editCopyLoanDurationTextField;

    /**
     * The button to search for a copy.
     */
    @FXML
    private Button copySearchButton;

    /**
     * The button to edit a copy.
     */
    @FXML
    private Button editCopyButton;

    /**
     * The button to delete a copy.
     */
    @FXML
    private Button deleteCopyButton;

    /**
     * The button to draw an image in the create book tab.
     */
    @FXML
    private Button drawButtonCreateBook;

    /**
     * The button to draw an image in the create DVD tab.
     */
    @FXML
    private Button drawButtonCreateDVD;

    /**
     * The button to draw an image in the create laptop tab.
     */
    @FXML
    private Button drawButtonCreateLaptop;

    /**
     * The button to draw an image in the edit book tab.
     */
    @FXML
    private Button drawButtonEditBook;

    /**
     * The button to draw an image in the edit DVD tab.
     */
    @FXML
    private Button drawButtonEditDVD;

    /**
     * The button to draw an image in the edit laptop tab.
     */
    @FXML
    private Button drawButtonEditLaptop;
//////////////////////////////////////////////////
//////////////////////////////////////////////////
//////////////////////////////////////////////////
    /**
     * The tab to create a game resource.
     */
    @FXML
    private Tab createGameTab;

    /**
     * The text field to type the game resource title.
     */
    @FXML
    private TextField createGameTitleTextField;

    /**
     * The text field to type the game resource year.
     */
    @FXML
    private TextField createGameYearTextField;

    /**
     * The text field to display the image path for the game resource thumbnail.
     */
    @FXML
    private TextField createGameImagePathTextField;

    /**
     * The button to find the location of the image thumbnail for the game resource.
     */
    @FXML
    private Button createGameFindImageButton;

    /**
     * The button to draw an avatar for the thumbnail of the game resource.
     */
    @FXML
    private Button createGameDrawButton;

    /**
     * The text field to type the game resource publisher.
     */
    @FXML
    private TextField createGamePublisherTextField;

    /**
     * The text field to type the game resource genre.
     */
    @FXML
    private TextField createGameGenreTextField;

    /**
     * The choice box to choose a certification rating for the game resource.
     */
    @FXML
    private ChoiceBox<?> createGameCertRatingChoiceBox;

    /**
     * The choice box to choose whether the game resource is multiplayer.
     */
    @FXML
    private ChoiceBox<?> createGameMultiplayerChoiceBox;

    /**
     * The button that adds the new game resource to the resources.
     */
    @FXML
    private Button createGameCreateButton;

    //////////////////////////////////////////////
    //////////////////////////////////////////////
    //////////////////////////////////////////////

    /**
     * The tab to edit a game resource.
     */
    @FXML
    private Tab editGameTab;

    /**
     * The text field to input a game resource ID.
     */
    @FXML
    private TextField editGameUniqueIDSearchTextField;

    /**
     * The button to search for the game resource.
     */
    @FXML
    private Button editGameSearchButton;

    /**
     * The text field to input the game resource title.
     */
    @FXML
    private TextField editGameTitleTextField;

    /**
     * The text field to input the game resource year.
     */
    @FXML
    private TextField editGameYearTextField;

    /**
     * The text field to input the game resource thumbnail image path.
     */
    @FXML
    private TextField editGameImagePathTextField;

    /**
     * The text field to input the game resource publisher.
     */
    @FXML
    private TextField editGamePublisherTextField;

    /**
     * The text field to input the game resource genre.
     */
    @FXML
    private TextField editGameGenreTextField;

    /**
     * The choice box to choose the game resource certificate rating.
     */
    @FXML
    private ChoiceBox<?> editGameCertRatingChoiceBox;

    /**
     * The choice box to choose whether the game resource supports multiplayer.
     */
    @FXML
    private ChoiceBox<?> editGameMultiplayerChoiceBox;

    /**
     * The button to choose the image path for the game resource thumbnail.
     */
    @FXML
    private Button editGameImagePathButton;

    /**
     * The button to draw an avatar for the game resource thumbnail.
     */
    @FXML
    private Button editGameDrawButton;

    /**
     * The button to delete the game resource.
     */
    @FXML
    private Button editGameDeleteButton;

    /**
     * The button to submit the changes made to the game resource.
     */
    @FXML
    private Button editGameEditButton;

    /**
     * Creates a new video game resource when the button is clicked.
     *
     * @param event When the create button is clicked.
     */
    @FXML
    void createGameCreateButtonClicked(ActionEvent event) {
        // mandatory information - all fields

        if (createGameTitleTextField.getText().isEmpty() || createGameYearTextField.getText().isEmpty()
                || createGameImagePathTextField.getText().isEmpty() || createGamePublisherTextField.getText().isEmpty()
                || createGameGenreTextField.getText().isEmpty() || createGameCertRatingChoiceBox.getSelectionModel().isEmpty()
                || createGameMultiplayerChoiceBox.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill in all the required fields.",
                    ButtonType.OK);
            alert.show();
        } else if (!isStringNumber(createGameYearTextField.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "The year text field must be a number.",
                    ButtonType.OK);
            alert.show();
        } else {
            // gather the information
            String title = createGameTitleTextField.getText();
            int year = Integer.parseInt(createGameYearTextField.getText());
            String thumbnail = createGameImagePathTextField.getText();
            String publisher = createGamePublisherTextField.getText();
            String genre = createGameGenreTextField.getText();
            String rating = createGameCertRatingChoiceBox.getSelectionModel().getSelectedItem().toString();
            boolean hasMultiplayer = createGameMultiplayerChoiceBox.getSelectionModel()
                    .getSelectedItem().toString().equals("Yes") ? true: false;

            // create a Video Game and add it
            getLibrary().getResourceManager().addResource(new VideoGame(title, year, thumbnail,
                    publisher, genre,rating, hasMultiplayer));

            // notify the user
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Video game resource created successfully.",
                    ButtonType.OK);
            alert.show();

            // clear all the fields
            this.clearAllCreateBookFields();
        }
    }

    /**
     * Opens the drawing interface.
     *
     * @param event When the draw button is clicked.
     */
    @FXML
    void createGameDrawButtonClicked(ActionEvent event) {
        callDrawingInterface(createGameImagePathTextField);
    }

    /**
     * Opens file explorer so that an image for the game resource can be chosen.
     *
     * @param event When the find image button is clicked.
     */
    @FXML
    void createGameFindImageButtonClicked(ActionEvent event) {
        selectFile(createGameImagePathTextField);
    }

    /**
     * Deleted the game resource.
     *
     * @param event When the delete button is clicked.
     */
    @FXML
    void editGameDeleteButtonClicked(ActionEvent event) {
    }

    /**
     * Opens the drawing interface.
     *
     * @param event When the draw button is clicked.
     */
    @FXML
    void editGameDrawButtonClicked(ActionEvent event) {
        callDrawingInterface(editGameImagePathTextField);
    }

    /**
     * Saves the changes made to the game resource.
     *
     * @param event When the edit button is clicked.
     */
    @FXML
    void editGameEditButtonClicked(ActionEvent event) {

    }

    /**
     * Opens file explorer so an image can be chosen for the game resource thumbnail.
     *
     * @param event When the image path button is clicked.
     */
    @FXML
    void editGameImagePathButtonClicked(ActionEvent event) {
        selectFile(editGameImagePathTextField);
    }

    /**
     * Finds the game resource with the unique ID input in the search text field.
     *
     * @param event When the search button is clicked.
     */
    @FXML
    void editGameSearchButtonClicked(ActionEvent event) {
        String gameID = editGameUniqueIDSearchTextField.getText();

        Resource resource = getLibrary().getResourceManager().getResourceById(gameID);

        if (resource == null || !resource.getType().equals("Video Game")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Couldn't find a video game with such ID.",
                    ButtonType.OK);
            alert.show();
        } else {
            // Video game is successfully found.
            VideoGame videoGame = (VideoGame) resource;

            // fill the fields with data
            editGameTitleTextField.setText(videoGame.getTitle());
            editGameYearTextField.setText(String.valueOf(videoGame.getYear()));
            editGameImagePathTextField.setText(videoGame.getThumbnailImagePath());
            editGamePublisherTextField.setText(videoGame.getPublisher());
            editGameGenreTextField.setText(videoGame.getGenre());



            // choose age rating in the box
            String rating = videoGame.getCertificateRating();

            for(int i=0; i<editGameCertRatingChoiceBox.getItems().size(); i++){
                // if the choice box has the same String as the rating, then select it
                if(editGameCertRatingChoiceBox.getItems().get(i).equals(rating)){
                    System.out.println(editGameCertRatingChoiceBox.getItems().get(i));
                    editGameCertRatingChoiceBox.getSelectionModel().select(i);
                }
            }

            // set Yes/No of multiplayer support
            if(videoGame.hasMultiplayerSupport()){
                editGameMultiplayerChoiceBox.getSelectionModel().select(0);
            } else {
                editGameMultiplayerChoiceBox.getSelectionModel().select(1);
            }

            // lock the id field
            editGameUniqueIDSearchTextField.setDisable(true);
        }
    }

    /**
     * Goes back to the librarian dashboard when clicked.
     *
     * @param event When the back button is clicked.
     */
    @FXML
    public void backButtonClicked(ActionEvent event) {

        new NewWindow("resources/LibrarianDashboard.fxml", event, "Dashboard - TaweLib", getLibrary());
    }

    /**
     * Creates a book when the button is clicked.
     *
     * @param event When the button is clicked.
     */
    @FXML
    public void createBookButtonClicked(ActionEvent event) {
        // mandatory information- title, year, thumbnail, author, publisher
        // optional information- genre, isbn, language

        if (titleBookTextField.getText().isEmpty() || yearBookTextField.getText().isEmpty()
                || imagePathBookTextField.getText().isEmpty() || publisherBookTextField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill in all the required fields.",
                    ButtonType.OK);
            alert.show();
        } else if (!isStringNumber(yearBookTextField.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "The year text field must be a number.",
                    ButtonType.OK);
            alert.show();
        } else {
            // gather the information
            String title = titleBookTextField.getText();
            int year = Integer.parseInt(yearBookTextField.getText());
            String thumbnail = imagePathBookTextField.getText();
            String author = authorBookTextField.getText();
            String publisher = publisherBookTextField.getText();
            String genre = genreBookTextField.getText();
            String isbn = isbnBookTextField.getText();
            String language = languageBookTextField.getText();

            // create a Book and add it
            getLibrary().getResourceManager().addResource(new Book(title, year, thumbnail, author, publisher,
                    genre, isbn, language));

            // notify the user
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Book resource created successfully.",
                    ButtonType.OK);
            alert.show();

            // clear all the fields
            this.clearAllCreateBookFields();
        }
    }

    /**
     * Creates dvd when the button is clicked
     *
     * @param event The button is clicked.
     */
    @FXML
    public void createDVDButtonClicked(ActionEvent event) {
        // mandatory information- title, year, thumbnail, director, runtime
        // optional information- language, listOfSubtitleLanguages
        if (titleDVDTextField.getText().isEmpty() || yearDVDTextField.getText().isEmpty()
                || imagePathDVDTextField.getText().isEmpty() || directorDVDTextField.getText().isEmpty()
                || runtimeDVDTextField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill in all the required fields.",
                    ButtonType.OK);
            alert.show();
        } else if (!isStringNumber(yearDVDTextField.getText()) || !isStringNumber(runtimeDVDTextField.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "The year and runtime text fields must be a number.",
                    ButtonType.OK);
            alert.show();
        } else {
            // gather the information
            String title = titleDVDTextField.getText();
            int year = Integer.parseInt(yearDVDTextField.getText());
            String thumbnail = imagePathDVDTextField.getText();
            String director = directorDVDTextField.getText();
            int runtime = Integer.parseInt(runtimeDVDTextField.getText());
            String language = languageDVDTextField.getText();

            // convert a comma separated string into an array list
            ArrayList<String> listOfSubtitleLanguages =
                    stringToArrayListOfStrings(languageSubtitlesDVDTextField.getText());

            // create a DVD and add it
            getLibrary().getResourceManager().addResource(new DVD(title, year, thumbnail, director,
                    runtime, language, listOfSubtitleLanguages));

            // notify the user
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "DVD resource created successfully.",
                    ButtonType.OK);
            alert.show();

            // clear all the fields
            this.clearAllCreateDVDFields();
        }
    }

    /**
     * Converts a string with the subtitle languages into a ArrayList.
     *
     * @param subtitleLanguages The subtitle languages read from a string.
     * @return The list of subtitle languages.
     */
    private ArrayList<String> stringToArrayListOfStrings(String subtitleLanguages) {
        // split the string into smaller strings on new comma or space
        String[] arrayOfSubtitleLanguages = subtitleLanguages.split(",| ");

        // convert that array into array list
        ArrayList<String> listOfSubtitleLanguages = new ArrayList<>(Arrays.asList(arrayOfSubtitleLanguages));

        // remove empty strings
        for (int i = 0; i < listOfSubtitleLanguages.size(); i++) {
            if (listOfSubtitleLanguages.get(i).equals("")) {
                listOfSubtitleLanguages.remove(i);
            }
        }
        return listOfSubtitleLanguages;
    }

    /**
     * Creates a laptop when the button is clicked.
     *
     * @param event The button is clicked.
     */
    @FXML
    public void createLaptopButtonClicked(ActionEvent event) {
        // mandatory information - title, year, thumbnail, manufacturer, model, installedOS
        if (titleLaptopTextField.getText().isEmpty() || yearLaptopTextField.getText().isEmpty()
                || imagePathLaptopTextField.getText().isEmpty() || manufacturerLaptopTextField.getText().isEmpty()
                || modelLaptopTextField.getText().isEmpty() || operatingSystemLaptopTextField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill in all the required fields.",
                    ButtonType.OK);
            alert.show();
        } else if (!isStringNumber(yearLaptopTextField.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "The year text field must be a number.",
                    ButtonType.OK);
            alert.show();
        } else {
            // gather the information
            String title = titleLaptopTextField.getText();
            int year = Integer.parseInt(yearLaptopTextField.getText());
            String thumbnail = imagePathLaptopTextField.getText();
            String manufacturer = manufacturerLaptopTextField.getText();
            String model = modelLaptopTextField.getText();
            String installedOS = operatingSystemLaptopTextField.getText();

            // create a Laptop object and add it
            getLibrary().getResourceManager().addResource(new Laptop(title, year, thumbnail, manufacturer,
                    model, installedOS));

            // notify the user
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Laptop resource created successfully.",
                    ButtonType.OK);
            alert.show();

            // clear all the fields
            this.clearAllCreateLaptopFields();
        }
    }

    /**
     * Searches for a book with a specific ID to edit after you click the button.
     *
     * @param event The button is clicked.
     */
    @FXML
    public void editBookSearchButtonClicked(ActionEvent event) {
        String bookID = uniqueIDSearchEditBookTextField.getText();
        Resource resource = getLibrary().getResourceManager().getResourceById(bookID);

        if (resource == null || !resource.getType().equals("Book")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Couldn't find a book with such ID.",
                    ButtonType.OK);
            alert.show();
        } else {
            // Book is successfully found.
            Book book = (Book) resource;

            // fill the fields with data
            titleEditBookTextField.setText(book.getTitle());
            yearEditBookTextField.setText(String.valueOf(book.getYear()));
            authorEditBookTextField.setText(book.getAuthor());
            imagePathEditBook.setText(book.getThumbnailImagePath());
            publisherEditBookTextField.setText(book.getPublisher());
            genreEditBookTextField.setText(book.getGenre());
            isbnEditBookTextField.setText(book.getISBN());
            languageEditBookTextField.setText(book.getLanguage());

            // lock the id field
            uniqueIDSearchEditBookTextField.setDisable(true);
        }
    }

    /**
     * After the button is clicked we can edit the Book.
     *
     * @param event The button is clicked.
     */
    @FXML
    public void editBookButtonClicked(ActionEvent event) {
        // mandatory information- title, year, thumbnail, author, publisher
        // optional information- genre, isbn, language

        if (titleEditBookTextField.getText().isEmpty() || yearEditBookTextField.getText().isEmpty()
                || imagePathEditBook.getText().isEmpty() || publisherEditBookTextField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill in all the required fields.",
                    ButtonType.OK);
            alert.show();
        } else if (!isStringNumber(yearEditBookTextField.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "The year text field must be a number.",
                    ButtonType.OK);
            alert.show();
        } else {
            // gather the information
            String title = titleEditBookTextField.getText();
            int year = Integer.parseInt(yearEditBookTextField.getText());
            String thumbnail = imagePathEditBook.getText();
            String author = authorEditBookTextField.getText();
            String publisher = publisherEditBookTextField.getText();
            String genre = genreEditBookTextField.getText();
            String isbn = isbnEditBookTextField.getText();
            String language = languageEditBookTextField.getText();

            // get the book
            Book book = (Book) getLibrary().getResourceManager().
                    getResourceById(uniqueIDSearchEditBookTextField.getText());
            // edit the book
            getLibrary().getResourceManager().editBook(book, title, year, thumbnail, author, publisher,
                    genre, isbn, language);

            // notify the user
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Book resource edited successfully.",
                    ButtonType.OK);
            alert.show();

            // enable the search ID again and clear all the fields
            uniqueIDSearchEditBookTextField.setDisable(false);
            this.clearAllEditBookFields();
        }
    }

    /**
     * After the button is clicked we search for the DVD to edit.
     *
     * @param event The button is clicked.
     */
    @FXML
    public void editDVDSearchButtonClicked(ActionEvent event) {
        String dvdId = uniqueIDSearchEditDVDTextField.getText();
        Resource resource = getLibrary().getResourceManager().getResourceById(dvdId);

        if (resource == null || !resource.getType().equals("DVD")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Couldn't find a DVD with such ID.",
                    ButtonType.OK);
            alert.show();
        } else {
            // DVD is successfully found.
            DVD dvd = (DVD) resource;

            // fill the fields with data
            titleEditDVDTextField.setText(dvd.getTitle());
            yearEditDVDTextField.setText(String.valueOf(dvd.getYear()));
            imagePathEditDVD.setText(dvd.getThumbnailImagePath());
            directorEditDVDTextField.setText(dvd.getDirector());
            runtimeEditDVDTextField.setText(String.valueOf(dvd.getRuntime()));
            languageEditDVDTextField.setText(dvd.getLanguage());


            // convert the array list into comma separated string
            String subtitleLanguages = toCommaSeparatedString(dvd.getListOfSubtitleLanguages());

            languageSubtitlesEditDVDTextField.setText(subtitleLanguages);

            // lock the id field
            uniqueIDSearchEditDVDTextField.setDisable(true);
        }
    }

    /**
     * Returns a string with the subtitle languages split up.
     *
     * @param listOfSubtitleLanguages The ArrayList to be turned into a string.
     * @return A string with the subtitle languages.
     */
    private String toCommaSeparatedString(ArrayList<String> listOfSubtitleLanguages) {
        String subtitleLanguages = "";
        for (String language : listOfSubtitleLanguages) {
            subtitleLanguages = subtitleLanguages + language + ',';
        }

        // remove the last comma, if the subtitle language is not empty
        if (!subtitleLanguages.isEmpty()) {
            subtitleLanguages = subtitleLanguages.substring(0, subtitleLanguages.length() - 1);
        }
        return subtitleLanguages;
    }

    /**
     * After the button is clicked we can edit the DVD .
     *
     * @param event The button is clicked.
     */
    @FXML
    public void editDVDButtonClicked(ActionEvent event) {
        // mandatory information- title, year, thumbnail, director, runtime
        // optional information- language, listOfSubtitleLanguages
        if (titleEditDVDTextField.getText().isEmpty() || yearEditDVDTextField.getText().isEmpty()
                || imagePathEditDVD.getText().isEmpty() || directorEditDVDTextField.getText().isEmpty()
                || runtimeEditDVDTextField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill in all the required fields.",
                    ButtonType.OK);
            alert.show();
        } else if (!isStringNumber(yearEditDVDTextField.getText()) ||
                !isStringNumber(runtimeEditDVDTextField.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "The year and runtime text fields must be a number.",
                    ButtonType.OK);
            alert.show();
        } else {
            // gather the information
            String title = titleEditDVDTextField.getText();
            int year = Integer.parseInt(yearEditDVDTextField.getText());
            String imagePath = imagePathEditDVD.getText();
            String director = directorEditDVDTextField.getText();
            int runtime = Integer.parseInt(runtimeEditDVDTextField.getText());
            String language = languageEditDVDTextField.getText();

            // convert a comma separated string into an array list
            ArrayList<String> listOfSubtitleLanguages =
                    stringToArrayListOfStrings(languageSubtitlesEditDVDTextField.getText());

            // find the DVD
            DVD dvd = (DVD) getLibrary().getResourceManager().
                    getResourceById(uniqueIDSearchEditDVDTextField.getText());

            getLibrary().getResourceManager().editDVD(dvd, title, year, imagePath,
                    director, runtime, language, listOfSubtitleLanguages);

            // notify the user
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "DVD resource edited successfully.",
                    ButtonType.OK);
            alert.show();

            // enable the ID text field
            uniqueIDSearchEditDVDTextField.setDisable(false);
            // clear all the fields
            this.clearAllEditDVDFields();
        }
    }

    /**
     * After the button is clicked we search for the laptop to edit.
     *
     * @param event The button is clicked.
     */
    @FXML
    public void editLaptopSearchButtonClicked(ActionEvent event) {
        String laptopId = uniqueIDSearchEditLaptopTextField.getText();
        Resource resource = getLibrary().getResourceManager().getResourceById(laptopId);

        if (resource == null || !resource.getType().equals("Laptop")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Couldn't find a laptop with such ID.",
                    ButtonType.OK);
            alert.show();
        } else {
            // Laptop is successfully found.
            Laptop laptop = (Laptop) resource;

            // fill the fields with data
            titleEditLaptopTextField.setText(laptop.getTitle());
            yearEditLaptopTextField.setText(String.valueOf(laptop.getYear()));
            imagePathEditLaptop.setText(laptop.getThumbnailImagePath());
            manufacturerEditLaptopTextField.setText(laptop.getManufacturer());
            modelEditLaptopTextField.setText(laptop.getModel());
            operatingSystemEditLaptopTextField.setText(laptop.getInstalledOS());

            // lock the id field
            uniqueIDSearchEditLaptopTextField.setDisable(true);
        }
    }

    /**
     * After the button is clicked we can edit the laptop.
     *
     * @param event The button is clicked
     */
    @FXML
    public void editLaptopButtonClicked(ActionEvent event) {
        // mandatory information - title, year, thumbnail, manufacturer, model, installedOS
        if (titleEditLaptopTextField.getText().isEmpty() || yearEditLaptopTextField.getText().isEmpty()
                || imagePathEditLaptop.getText().isEmpty() || manufacturerEditLaptopTextField.getText().isEmpty()
                || modelEditLaptopTextField.getText().isEmpty() || operatingSystemEditLaptopTextField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill in all the required fields.",
                    ButtonType.OK);
            alert.show();
        } else if (!isStringNumber(yearEditLaptopTextField.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "The year text field must be a number.",
                    ButtonType.OK);
            alert.show();
        } else {
            // gather the information
            String title = titleEditLaptopTextField.getText();
            int year = Integer.parseInt(yearEditLaptopTextField.getText());
            String imagePath = imagePathEditLaptop.getText();
            String manufacturer = manufacturerEditLaptopTextField.getText();
            String model = modelEditLaptopTextField.getText();
            String installedOS = operatingSystemEditLaptopTextField.getText();

            // Find the laptop.
            Laptop laptop = (Laptop) getLibrary().getResourceManager().
                    getResourceById(uniqueIDSearchEditLaptopTextField.getText());

            // Change the laptop
            getLibrary().getResourceManager().editLaptop(laptop, title, year, imagePath,
                    manufacturer, model, installedOS);

            // notify the user
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Laptop resource edited successfully.",
                    ButtonType.OK);
            alert.show();

            // enable the search bar and clear all the fields
            uniqueIDSearchEditLaptopTextField.setDisable(false);
            this.clearAllEditLaptopFields();
        }
    }

    /**
     * Deletes a laptop resource.
     *
     * @param event The button is clicked.
     */
    @FXML
    public void laptopDeleteButtonClicked(ActionEvent event) {
        Resource resource = getLibrary().getResourceManager().
                getResourceById(uniqueIDSearchEditLaptopTextField.getText());

        if (!canDeleteResource(resource)) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Can't delete resource, because some of its" +
                    " copies are still in use!",
                    ButtonType.OK);
            alert.show();
        } else {
            getLibrary().getResourceManager().removeResource(uniqueIDSearchEditLaptopTextField.getText());
            this.clearAllEditLaptopFields();
            uniqueIDSearchEditLaptopTextField.setDisable(false);

            // notify the user
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Laptop deleted successfully.",
                    ButtonType.OK);
            alert.show();
        }
    }

    /**
     * Deletes a DVD resource.
     *
     * @param event The button is clicked.
     */
    @FXML
    public void dvdDeleteButtonClicked(ActionEvent event) {

        Resource resource = getLibrary().getResourceManager().
                getResourceById(uniqueIDSearchEditDVDTextField.getText());

        if (!canDeleteResource(resource)) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Can't delete resource, because some of its" +
                    " copies are still in use!",
                    ButtonType.OK);
            alert.show();
        } else {
            getLibrary().getResourceManager().removeResource(uniqueIDSearchEditDVDTextField.getText());
            this.clearAllEditDVDFields();
            uniqueIDSearchEditDVDTextField.setDisable(false);

            // notify the user
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "DVD deleted successfully.",
                    ButtonType.OK);
            alert.show();
        }
    }

    /**
     * Deletes a book resource.
     *
     * @param event The button is clicked.
     */
    @FXML
    public void bookDeleteButtonClicked(ActionEvent event) {
        Resource resource = getLibrary().getResourceManager().
                getResourceById(uniqueIDSearchEditBookTextField.getText());

        if (!canDeleteResource(resource)) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Can't delete resource, because some of its" +
                    " copies are still in use!",
                    ButtonType.OK);
            alert.show();
        } else {
            getLibrary().getResourceManager().removeResource(uniqueIDSearchEditBookTextField.getText());
            this.clearAllEditBookFields();
            uniqueIDSearchEditBookTextField.setDisable(false);

            // notify the user
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Book deleted successfully.",
                    ButtonType.OK);
            alert.show();
        }
    }

    /**
     * Says if a resource can be delete.
     *
     * @return True if all of the resource's copies are available (in the library), false otherwise.
     */
    private boolean canDeleteResource(Resource resource) {
        return resource != null && resource.getCopyManager().getNumOfAvailableCopies()
                == resource.getCopyManager().getListOfAllCopies().size();
    }

    /**
     * Creates a copy of the chosen resource.
     *
     * @param event The button is clicked.
     */
    @FXML
    public void copyCreateButtonClicked(ActionEvent event) {
        // Find the resource.
        Resource resource = getLibrary().getResourceManager().getResourceById(resourceUniqueIDtextField.getText());

        // mandatory information - loan duration
        if (copyLoanDurationTextField.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill in all the required fields.",
                    ButtonType.OK);

            alert.show();

        } else if (!isStringNumber(copyLoanDurationTextField.getText())) {

            Alert alert = new Alert(Alert.AlertType.ERROR, "The text field must be a number.",
                    ButtonType.OK);

            alert.show();

        } else if (resource == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Could not find resource.",
                    ButtonType.OK);

            alert.show();
        } else {

            // gather the information
            int loanDuration = Integer.parseInt(copyLoanDurationTextField.getText());


            // Change the resource.
            getLibrary().getResourceManager().addCopyOfResource(loanDuration, resource);

            // notify the user
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Resource copy added successfully.",
                    ButtonType.OK);

            alert.show();

            // enable the search bar and clear all the fields
            resourceUniqueIDtextField.setDisable(false);

            //Clear fields
            copyLoanDurationTextField.setText("");
            resourceUniqueIDtextField.setText("");
        }
    }

    /**
     * Searches for a resource after the button is clicked.
     *
     * @param event The button is clicked.
     */
    @FXML
    public void resourceSearchButtonClicked(ActionEvent event) {

        Resource resource = getLibrary().getResourceManager().getResourceById(resourceUniqueIDtextField.getText());

        if (resource == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Couldn't find a resource with such ID.",
                    ButtonType.OK);

            alert.show();

        } else {

            // lock the id field
            resourceUniqueIDtextField.setDisable(true);

            // notify the user
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Resource found.",
                    ButtonType.OK);

            alert.show();
        }
    }

    /**
     * Searches for a copy after the button is clicked.
     *
     * @param event The button is clicked.
     */
    @FXML
    public void copySearchButtonClicked(ActionEvent event) {
        Copy copy = getLibrary().getResourceManager().getCopyById(editCopyIdSearchTextField.getText());

        if (copy == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Couldn't find a copy with such ID.",
                    ButtonType.OK);

            alert.show();

        } else {
            // lock the id field
            editCopyIdSearchTextField.setDisable(true);

            // fill in the data
            editCopyLoanDurationTextField.setText(String.valueOf(copy.getLoanDurationInDays()));
        }
    }

    /**
     * The copy is edited after the button is clicked.
     *
     * @param event The button is clicked.
     */
    @FXML
    public void editCopyButtonClicked(ActionEvent event) {
        // mandatory - loan duration
        if (editCopyLoanDurationTextField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill in the required text fields.",
                    ButtonType.OK);

            alert.show();
        } else if (!isStringNumber(editCopyLoanDurationTextField.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "The loan duration must be a number.",
                    ButtonType.OK);

            alert.show();
        } else {
            // get information
            String copyId = editCopyIdSearchTextField.getText();
            int loanDuration = Integer.parseInt(editCopyLoanDurationTextField.getText());

            // edit the copy
            getLibrary().getResourceManager().getCopyById(copyId).setLoanDurationInDays(loanDuration);


            // notify the user
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Copy successfully changed.",
                    ButtonType.OK);

            alert.show();

            // unlock the search id
            editCopyIdSearchTextField.setDisable(false);

            // clear the text fields
            editCopyIdSearchTextField.setText("");
            editCopyLoanDurationTextField.setText("");
        }
    }

    /**
     * The copy is deleted after the button is clicked.
     *
     * @param event The button is clicked.
     */
    @FXML
    public void deleteCopyButtonClicked(ActionEvent event) {
        Copy copy = getLibrary().getResourceManager().getCopyById(editCopyIdSearchTextField.getText());

        if (copy.getBorrowedBy() != null || copy.getReservedFor() != null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Can not delete copy, " +
                    "because it is currently in use.",
                    ButtonType.OK);
            alert.show();
        } else {

            // delete copy
            getLibrary().getResourceManager().removeCopy(copy);

            // notify the user
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Copy successfully deleted.",
                    ButtonType.OK);

            alert.show();

            // unlock the search id
            editCopyIdSearchTextField.setDisable(false);

            //clear the text fields
            editCopyIdSearchTextField.clear();
            editCopyLoanDurationTextField.clear();
        }
    }

    /**
     * Opens file explorer so that an image can be chosen.
     *
     * @param event The button is clicked.
     */
    @FXML
    public void createBookFindImageButtonClicked(ActionEvent event) {

        selectFile(imagePathBookTextField);
    }

    /**
     * Opens file explorer so that an image can be chosen.
     *
     * @param event The button is clicked.
     */
    @FXML
    public void createDvdFindImageButtonClicked(ActionEvent event) {

        selectFile(imagePathDVDTextField);
    }

    /**
     * Opens file explorer so that an image can be chosen.
     *
     * @param event The button is clicked.
     */
    @FXML
    public void createLaptopFindImageButtonClicked(ActionEvent event) {

        selectFile(imagePathLaptopTextField);
    }

    /**
     * Opens file explorer so that an image can be chosen.
     *
     * @param event The button is clicked.
     */
    @FXML
    public void editBookFindImageButtonClicked(ActionEvent event) {

        selectFile(imagePathEditBook);
    }

    /**
     * Opens file explorer so that an image can be chosen.
     *
     * @param event The button is clicked.
     */
    @FXML
    public void editDvdFindImageButtonClicked(ActionEvent event) {

        selectFile(imagePathEditDVD);
    }

    /**
     * Opens file explorer so that an image can be chosen.
     *
     * @param event The button is clicked.
     */
    @FXML
    public void editLaptopFindImageButtonClicked(ActionEvent event) {

        selectFile(imagePathEditLaptop);
    }

    /**
     * Opens the drawing interface.
     *
     * @param event The button is clicked.
     */
    @FXML
    public void drawButtonCreateBookClicked(ActionEvent event) {
        callDrawingInterface(imagePathBookTextField);
    }


    /**
     * Opens the drawing interface.
     *
     * @param event The button is clicked.
     */
    @FXML
    public void drawButtonCreateDVDClicked(ActionEvent event) {
        callDrawingInterface(imagePathDVDTextField);

    }

    /**
     * Opens the drawing interface.
     *
     * @param event The button is clicked.
     */
    @FXML
    public void drawButtonCreateLaptopClicked(ActionEvent event) {
        callDrawingInterface(imagePathLaptopTextField);

    }

    /**
     * Opens the drawing interface.
     *
     * @param event The button is clicked.
     */
    @FXML
    public void drawButtonEditBookClicked(ActionEvent event) {
        callDrawingInterface(imagePathEditBook);

    }

    /**
     * Opens the drawing interface.
     *
     * @param event The button is clicked.
     */
    @FXML
    public void drawButtonEditDVDClicked(ActionEvent event) {
        callDrawingInterface(imagePathEditDVD);

    }

    /**
     * Opens the drawing interface.
     *
     * @param event The button is clicked.
     */
    @FXML
    public void drawButtonEditLaptopClicked(ActionEvent event) {
        callDrawingInterface(imagePathEditLaptop);

    }

    /**
     * Calls the drawing interface.
     *
     * @param textField The text field to put the file path from drawing.
     */
    private void callDrawingInterface(TextField textField) {
        DrawAvatar drawAvatar = new DrawAvatar(textField);
        Stage newerStage = new Stage();
        drawAvatar.start(newerStage);
    }

    /**
     * Check if a certain string contains digits only.
     *
     * @param s The string.
     * @return True if the string contains only digits, false otherwise.
     */
    private boolean isStringNumber(String s) {
        // regular expression
        // \\d+ means 1 or more digits
        return s.matches("\\d+");
    }

    /**
     * Opens file explorer and adds the file path to the specified text field when an image is chosen.
     *
     * @param imageTextField The specified text field that contains the file path.
     */
    private void selectFile(TextField imageTextField) {

        Stage currentStage = (Stage) languageBookTextField.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG Files", "*.png"),
                new FileChooser.ExtensionFilter("JPEG Files", "*.jpg"));

        try {
            File selectedFile = fileChooser.showOpenDialog(currentStage);
            imageTextField.setText(selectedFile.toString());
        } catch (Exception e) {
            imageTextField.setText("");
            e.printStackTrace();
        }
    }

    /**
     * Makes all the fields null for a book resource.
     */
    private void clearAllCreateBookFields() {
        titleBookTextField.setText("");
        authorBookTextField.setText("");
        imagePathBookTextField.setText("");
        authorBookTextField.setText("");
        publisherBookTextField.setText("");
        genreBookTextField.setText("");
        isbnBookTextField.setText("");
        languageBookTextField.setText("");
        yearBookTextField.setText("");
    }

    /**
     * Makes all the fields null for a laptop.
     */
    private void clearAllCreateLaptopFields() {
        titleLaptopTextField.setText("");
        yearLaptopTextField.setText("");
        imagePathLaptopTextField.setText("");
        manufacturerLaptopTextField.setText("");
        modelLaptopTextField.setText("");
        operatingSystemLaptopTextField.setText("");
    }

    /**
     * Makes all the fields null for a DVD.
     */
    private void clearAllCreateDVDFields() {
        titleDVDTextField.setText("");
        yearDVDTextField.setText("");
        imagePathDVDTextField.setText("");
        directorDVDTextField.setText("");
        runtimeDVDTextField.setText("");
        languageDVDTextField.setText("");
        languageSubtitlesDVDTextField.setText("");
    }

    /**
     * Makes all the fields null for a book edit.
     */
    private void clearAllEditBookFields() {
        uniqueIDSearchEditBookTextField.setText("");
        titleEditBookTextField.setText("");
        yearEditBookTextField.setText("");
        authorEditBookTextField.setText("");
        imagePathEditBook.setText("");
        publisherEditBookTextField.setText("");
        genreEditBookTextField.setText("");
        isbnEditBookTextField.setText("");
        languageEditBookTextField.setText("");
    }

    /**
     * Makes all the fields null for a book edit.
     */
    private void clearAllEditLaptopFields() {
        uniqueIDSearchEditLaptopTextField.setText("");
        titleEditLaptopTextField.setText("");
        yearEditLaptopTextField.setText("");
        imagePathEditLaptop.setText("");
        manufacturerEditLaptopTextField.setText("");
        modelEditLaptopTextField.setText("");
        operatingSystemEditLaptopTextField.setText("");
    }

    /**
     * Makes all the fields null for a DVD edit.
     */
    private void clearAllEditDVDFields() {
        uniqueIDSearchEditDVDTextField.setText("");
        titleEditDVDTextField.setText("");
        yearEditDVDTextField.setText("");
        imagePathEditDVD.setText("");
        directorEditDVDTextField.setText("");
        runtimeEditDVDTextField.setText("");
        languageEditDVDTextField.setText("");
        languageSubtitlesEditDVDTextField.setText("");
    }
}

