/**
 * Class to model a video game resource.
 * @author Ivan Andreev
 */
public class VideoGame extends Resource {

    private String publisher;
    private String genre;
    private double certificateRating;
    private boolean hasMultiplayerSupport;

    private double lateReturnFinePerDay;
    private double maxFineAmount;

    public VideoGame(String title, int year, String thumbnailImagePath, String publisher, String genre,
                     double certificateRating, boolean hasMultiplayerSupport) {
        super(title, year, thumbnailImagePath);
        this.publisher = publisher;
        this.genre = genre;
        this.certificateRating = certificateRating;
        this.hasMultiplayerSupport = hasMultiplayerSupport;

        this.lateReturnFinePerDay = 2.00;
        this.maxFineAmount = 25.00;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getCertificateRating() {
        return certificateRating;
    }

    public void setCertificateRating(double certificateRating) {
        this.certificateRating = certificateRating;
    }

    public boolean hasMultiplayerSupport() {
        return hasMultiplayerSupport;
    }

    public void setHasMultiplayerSupport(boolean hasMultiplayerSupport) {
        this.hasMultiplayerSupport = hasMultiplayerSupport;
    }

    public String getType(){
        return "Video Game";
    }

    public double getLateReturnFinePerDay(){
        return lateReturnFinePerDay;
    }

    public double getMaxFineAmount(){
        return maxFineAmount;
    }
}
