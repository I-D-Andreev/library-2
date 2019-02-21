import java.util.ArrayList;

/**
 *  * A class to model a dvd.
 *
 * @author Kleanthis Liontis
 */
public class DVD extends Resource {

    /**
     * The director of the dvd.
     */
    private String director;

    /**
     * The runtime of the dvd.
     */
    private int runtime;

    /**
     * The language of the dvd.
     */
    private String language;

    /**
     * An ArrayList containing all the subtitle languages of the dvd.
     */
    private ArrayList<String> listOfSubtitleLanguages;

    /**
     * The late return fine per day for the dvd.
     */
    private final double lateReturnFinePerDay;

    /**
     * The max fine amount for a dvd.
     */
    private final double maxFineAmount;

    /**
     * The constructor for a dvd.
     *
     * @param title     The title of a dvd.
     * @param year      The year the dvd was produced.
     * @param thumbnail The thumbnail of the dvd.
     * @param director  The director of the dvd.
     * @param runtime   The runtime of the dvd.
     */
    public DVD(String title, int year, String thumbnail, String director, int runtime) {
        super(title, year, thumbnail);
        this.director = director;
        this.runtime = runtime;

        this.lateReturnFinePerDay = 2.00;
        this.maxFineAmount = 25.00;
        this.listOfSubtitleLanguages = new ArrayList<>();
    }

    /**
     * The constructor of a dvd including the language and list of subtitle,
     * languages of the dvd.
     *
     * @param title                   The title of a dvd.
     * @param year                    The year the dvd was produced.
     * @param thumbnail               The thumbnail of the dvd.
     * @param director                The director of the dvd.
     * @param runtime                 The runtime of the dvd.
     * @param language                The language of the dvd.
     * @param listOfSubtitleLanguages The list of all subtitle languages of the dvd.
     */
    public DVD(String title, int year, String thumbnail, String director, int runtime,
               String language, ArrayList<String> listOfSubtitleLanguages) {
        // call the "smaller" DVD constructor
        this(title, year, thumbnail, director, runtime);
        this.language = language;
        this.listOfSubtitleLanguages = listOfSubtitleLanguages;
    }

    /**
     * Sets the director of the dvd.
     *
     * @param director The new director of the dvd.
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * Gets the director of the dvd.
     *
     * @return The director of the dvd.
     */
    public String getDirector() {
        return this.director;
    }

    /**
     * Sets the run the runtime of the dvd.
     *
     * @param runtime The new runtime of the dvd.
     */
    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    /**
     * Gets the runtime of the dvd.
     *
     * @return The runtime of the dvd.
     */
    public int getRuntime() {
        return this.runtime;
    }

    /**
     * Sets the language of the dvd.
     *
     * @param language The new language of the dvd.
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Gets the language of the dvd.
     *
     * @return The language of the dvd.
     */
    public String getLanguage() {
        return this.language;
    }

    /**
     * Sets the subtitle language ArrayList.
     *
     * @param listOfSubtitleLanguages The new subtitle language ArrayList.
     */
    public void setListOfSubtitleLanguages(ArrayList<String> listOfSubtitleLanguages) {
        this.listOfSubtitleLanguages = listOfSubtitleLanguages;
    }

    /**
     * Gets the subtitle language ArrayList.
     *
     * @return The subtitle language ArrayList.
     */
    public ArrayList<String> getListOfSubtitleLanguages() {
        return this.listOfSubtitleLanguages;
    }

    /**
     * Adds subtitle languages in the ArrayList of subtitle languages.
     *
     * @param language The language to be added.
     */
    public void addSubtitleLanguage(String language) {
        // add language only if it doesn't already exist
        if (this.listOfSubtitleLanguages.indexOf(language) != -1) {
            this.listOfSubtitleLanguages.add(language);
        }
    }

    /**
     * Removes a subtitle language from the ArrayList of all subtitle languages.
     *
     * @param language The language to be removed.
     */
    public void removeSubtitleLanguage(String language) {
        this.listOfSubtitleLanguages.remove(language);
    }

    /**
     * Gets the late return fine per day of the dvd.
     *
     * @return The late return fine per day.
     */
    public double getLateReturnFinePerDay() {
        return this.lateReturnFinePerDay;
    }

    /**
     * Gets the maximum fine amount for a dvd.
     *
     * @return The maximum fine amount for a dvd.
     */
    public double getMaxFineAmount() {
        return this.maxFineAmount;
    }

    /**
     * Gets the string of what kind of object this object is.
     *
     * @return A String "DVD" the object this object is.
     */
    public String getType() {
        return "DVD";
    }
}
