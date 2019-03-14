import java.text.DecimalFormat;
import java.io.Serializable;

public class Ratings implements Serializable {

    private Resource resource;
    private User currentUser;
    private Double rating;
    private String review;

    public Ratings(Resource resource, User user) {
        this.resource = resource;
        this.currentUser = user;
    }



    public Double getRating() {
        DecimalFormat formatter = new DecimalFormat("#.#");
        return Double.parseDouble(formatter.format(rating));
    }

    public String getReview() {
        return review;
    }

    public void setRating(double rating) { this.rating = rating; }

    public void setReview(String review) { this.review = review; }

    public Resource getResource() {return resource;}

    public User getUser() {return currentUser;}

}
