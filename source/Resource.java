import java.io.*;

/**
 * @author Kleanthis Liontis, Ivan Andreev.
 * Class to model a resource.
 */
public abstract class Resource implements Serializable {
    private static int nextID = 0;

    /**
     * The unique ID of a resource.
     */
    private String uniqueID;

    /**
     * The title of the resource.
     */
    private String title;

    /**
     * The year the resource was produced.
     */
    private int year;

    /**
     * The thumbnail image path of the resource.
     */
    private String thumbnailImagePath;

    /**
     * The copy manager of the resource.
     */
    private CopyManager copyManager;

    /**
     * The constructor of a resource.
     *
     * @param title              The title of the resource.
     * @param year               The year the resource was manufactured.
     * @param thumbnailImagePath The thumbnail image path of the resource.
     */
    public Resource(String title, int year, String thumbnailImagePath) {
        this.title = title;
        this.year = year;
        this.thumbnailImagePath = thumbnailImagePath;
        this.copyManager = new CopyManager(this);
        this.setUniqueID();
    }

    /**
     * Gets the unique ID of the resource.
     *
     * @return The unique ID of the resource.
     */
    public String getUniqueID() {
        return this.uniqueID;
    }

    /**
     * Gets the title of the resource.
     *
     * @return The title of the resource.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Sets the title of the resource.
     *
     * @param title The new title of the resource.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the year of the resource.
     *
     * @return The year of the resource was produced.
     */
    public int getYear() {
        return this.year;
    }

    /**
     * Sets the year of the resource.
     *
     * @param year The new year of the resource.
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Gets the thumbnail image path.
     *
     * @return The thumbnail image path of the resource.
     */
    public String getThumbnailImagePath() {
        return this.thumbnailImagePath;
    }

    /**
     * Sets the thumbnail image path.
     *
     * @param thumbnailImagePath The new thumbnail image path.
     */
    public void setThumbnailImagePath(String thumbnailImagePath) {
        this.thumbnailImagePath = thumbnailImagePath;
    }

    /**
     * Gets the next id variable.
     *
     * @return The next id variable.
     */
    public static int getNextID() {
        return nextID;
    }

    /**
     * Sets the next id variable.
     *
     * @param nextID The new nextId variable.
     */
    public static void setNextID(int nextID) {
        Resource.nextID = nextID;
    }

    /**
     * Gets the max fine amount.
     *
     * @return null To be overwritten by subclasses.
     */
    public abstract double getMaxFineAmount();

    /**
     * Gets the late return fine per day.
     *
     * @return null To be overwritten by subclasses.
     */
    public abstract double getLateReturnFinePerDay();

    /**
     * Gets the type of string of the resource.
     *
     * @return null To be overwritten by subclasses.
     */
    public abstract String getType();

    /**
     * Gets the copy manager of the resource.
     *
     * @return The copy manager of the resources.
     */
    public CopyManager getCopyManager() {
        return copyManager;
    }

    /**
     * Sets the copy manager of the resource.
     *
     * @param copyManager The new copy manager of the resource.
     */
    public void setCopyManager(CopyManager copyManager) {
        this.copyManager = copyManager;
    }

    /**
     * Sets the unique ID of the resource.
     */
    private void setUniqueID() {
        this.uniqueID = this.title.charAt(0) + "-" + nextID;
        nextID++;
    }

    /**
     * Checks that the resource is equal to an object of type resource.
     *
     * @param obj The resource to be checked.
     * @return The resource's unique ID if true,false otherwise.
     */
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof Resource)) return false;
        Resource o = (Resource) obj;
        return o.getUniqueID().equals(this.getUniqueID());
    }
}
