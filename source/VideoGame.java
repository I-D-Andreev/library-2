/**
 * Class to model a video game resource.
 *
 * @author Ivan Andreev
 */
public class VideoGame extends Resource {
    /**
     * The publisher of the video game.
     */
    private String publisher;

    /**
     * The genre of the video game.
     */
    private String genre;

    /**
     * The rating of the video game.
     */
    private String certificateRating;

    /**
     * Whether the game has multiplayer support.
     */
    private boolean hasMultiplayerSupport;

    /**
     * The late return fine per day for the game.
     */
    private double lateReturnFinePerDay;

    /**
     * The maximum fine a user can incur for returning a game late.
     */
    private double maxFineAmount;

    /**
     * The amount of points the resource contributions towards the resource cap.
     */
    private final int resourceCapContribution;

    /**
     * Constructs a video game object.
     *
     * @param title                 The title of the video game.
     * @param year                  The year of the video game.
     * @param thumbnailImagePath    The path to the image of the video game.
     * @param publisher             The publisher of the video game.
     * @param genre                 The genre of the video game.
     * @param certificateRating     The rating of the video game.
     * @param hasMultiplayerSupport Whether the game has multiplayer support.
     */
    public VideoGame(String title, int year, String thumbnailImagePath, String publisher, String genre,
                     String certificateRating, boolean hasMultiplayerSupport) {
        super(title, year, thumbnailImagePath);
        this.publisher = publisher;
        this.genre = genre;
        this.certificateRating = certificateRating;
        this.hasMultiplayerSupport = hasMultiplayerSupport;

        this.lateReturnFinePerDay = 2.00;
        this.maxFineAmount = 25.00;
        this.resourceCapContribution = 1;
    }

    /**
     * Gets the publisher of the video game.
     *
     * @return The publisher.
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Changes the publisher of the video game.
     *
     * @param publisher The new publisher.
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * Gets the genre of the video game.
     *
     * @return The genre.
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Changes the genre of the video game.
     *
     * @param genre The new genre.
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Gets the rating of the video game.
     *
     * @return The rating of the video game.
     */
    public String getCertificateRating() {
        return certificateRating;
    }

    /**
     * Changes the rating of the video game.
     *
     * @param certificateRating The new rating.
     */
    public void setCertificateRating(String certificateRating) {
        this.certificateRating = certificateRating;
    }

    /**
     * Says whether the game has multiplayer support.
     *
     * @return True - has multiplayer support, false - doesn't have.
     */
    public boolean hasMultiplayerSupport() {
        return hasMultiplayerSupport;
    }

    /**
     * Change whether the game has multiplayer support.
     *
     * @param hasMultiplayerSupport The new multiplayer support (True - has multiplayer support, false - doesn't have.)
     */
    public void setHasMultiplayerSupport(boolean hasMultiplayerSupport) {
        this.hasMultiplayerSupport = hasMultiplayerSupport;
    }

    /**
     * Ges the type of the resource.
     *
     * @return Video game
     */
    public String getType() {
        return "Video Game";
    }

    /**
     * Gets the late return fine per day.
     *
     * @return The fine for a late return of 1 day.
     */
    public double getLateReturnFinePerDay() {
        return lateReturnFinePerDay;
    }

    /**
     * Gets the maximum fine amount a user can incur for returning a video game late.
     *
     * @return The maximum fine amount a user can incur for returning a video game late.
     */
    public double getMaxFineAmount() {
        return maxFineAmount;
    }

    /**
     * Gets the resource cap contribution of the resource.
     *
     * @return Returns the resource cap contribution of the particular resource.
     */
    public int getCapContribution() {
        return resourceCapContribution;
    }
}
