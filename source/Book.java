/**
 * Class to model a book.
 *
 * @author Kleanthis Liontis, Ivan Andreev
 */
public class Book extends Resource {

    /**
     * The author of the book.
     */
    private String author;

    /**
     * The publisher of the book.
     */
    private String publisher;

    /**
     * The genre of the book.
     */
    private String genre;

    /**
     * The International Standard Book Number of the book.
     */
    private String ISBN;

    /**
     * The language of the book.
     */
    private String language;

    /**
     * The late return fine per day of the book.
     */
    private final double lateReturnFinePerDay;

    /**
     * The maximum return fine amount of the book.
     */
    private final double maxFineAmount;

    /**
     * The amount of points the resource contributions towards the resource cap.
     */
    private final int resourceCapContribution;

    /**
     * The constructor for a book.
     *
     * @param title     The title of the book.
     * @param year      The year the book was published.
     * @param thumbnail The thumbnail of the book.
     * @param author    The author of the book.
     * @param publisher The publisher of the book.
     */
    public Book(String title, int year, String thumbnail, String author, String publisher) {
        super(title, year, thumbnail);
        this.author = author;
        this.publisher = publisher;

        this.lateReturnFinePerDay = 2.00;
        this.maxFineAmount = 25.00;
        this.resourceCapContribution = 1;
    }

    /**
     * Constructor for book but with all details included.
     *
     * @param title     The title of the book.
     * @param year      The year the book was published.
     * @param thumbnail The thumbnail of the book.
     * @param author    The author of the book.
     * @param publisher The publisher of the book.
     * @param genre     the genre of the book.
     * @param ISBN      The International Standard Book Number of the book.
     * @param language  The language the book is written in.
     */
    public Book(String title, int year, String thumbnail, String author, String publisher,
                String genre, String ISBN, String language) {
        // call the "smaller" constructor
        this(title, year, thumbnail, author, publisher);
        this.genre = genre;
        this.ISBN = ISBN;
        this.language = language;
    }

    /**
     * Sets the author of the book.
     *
     * @param author The new author of the book.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Gets the author of the book.
     *
     * @return author The author of the book.
     */
    public String getAuthor() {
        return this.author;
    }

    /**
     * Sets the publisher of the book.
     *
     * @param publisher The new publisher of the book.
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * Gets the publisher of the book.
     *
     * @return publisher The publisher of the book.
     */
    public String getPublisher() {
        return this.publisher;
    }

    /**
     * Sets the genre of the book.
     *
     * @param genre The new genre of the book.
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Gets the genre of the book.
     *
     * @return genre The genre of the book.
     */
    public String getGenre() {
        return this.genre;
    }

    /**
     * Sets the ISBN of the book.
     *
     * @param ISBN The new ISBN of the book.
     */
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    /**
     * Gets the ISBN of the book.
     *
     * @return ISBN The ISBN of the book.
     */
    public String getISBN() {
        return this.ISBN;
    }

    /**
     * Sets the language of the book.
     *
     * @param language The new language of the book.
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Gets the language of the book.
     *
     * @return language The language of the book.
     */
    public String getLanguage() {
        return this.language;
    }

    /**
     * Gets the late return fine per day of the book.
     *
     * @return lateReturnFinePerDay The late return fine of the book.
     */
    public double getLateReturnFinePerDay() {
        return this.lateReturnFinePerDay;
    }

    /**
     * Gets the maximum fine amount of the book.
     *
     * @return maxFineAmount The maximum fine amount of the book.
     */
    public double getMaxFineAmount() {
        return this.maxFineAmount;
    }

    /**
     * Gets the a String with the type of the this object.
     *
     * @return A string with the type of the object.
     */
    public String getType() {
        return "Book";
    }

    /**
     * Gets the resource cap contribution of the resource.
     *
     * @rerturn Returns the resource cap contribution of the particular resource.
     */
    public int getCapContribution() {
        return resourceCapContribution;
    }
}
