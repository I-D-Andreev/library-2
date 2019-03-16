import java.text.DecimalFormat;
import java.io.Serializable;

/**
 * A class to implement the ratings.
 *
 * @author Christina Meggs, Steven Lewkowicz
 */
public class Ratings implements Serializable {
    /**
     * The resource being rated.
     */
    private Resource resource;

    /**
     * The current user logged in.
     */
    private User currentUser;

    /**
     * The rating score.
     */
    private Double rating;

    /**
     * The textual review.
     */
    private String review;

    /**
     * Constructs a rating object.
     *
     * @param resource The resource being rated.
     * @param user     The user giving the rating.
     */
    public Ratings(Resource resource, User user) {
        this.resource = resource;
        this.currentUser = user;
    }

    /**
     * Gets the rating.
     *
     * @return The rating.
     */
    public Double getRating() {
        DecimalFormat formatter = new DecimalFormat("#.#");
        return Double.parseDouble(formatter.format(rating));
    }

    /**
     * Gets the review.
     *
     * @return The review.
     */
    public String getReview() {
        return review;
    }

    /**
     * Change the rating.
     *
     * @param rating The new rating.
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * Change the review.
     *
     * @param review The new review.
     */
    public void setReview(String review) {
        this.review = review;
    }

    /**
     * Get the resource being rated.
     *
     * @return The resource being rated.
     */
    public Resource getResource() {
        return resource;
    }

    /**
     * Get the user rating the resource.
     *
     * @return The user current logged in.
     */
    public User getUser() {
        return currentUser;
    }

}
