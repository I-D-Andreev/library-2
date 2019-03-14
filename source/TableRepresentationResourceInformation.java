/**
 * Class to model the representation of the resources in a table.
 *
 * @author Ivan Andreev.
 */
public class TableRepresentationResourceInformation {
    private String resourceID;
    private String resourceName;
    private String resourceType;
    private Integer positionInQueue;
    private int numberOfTimesBorrowed;

    /**
     * Constructor for a table representation of a resource.
     *
     * @param resourceID      The unique ID of the resource.
     * @param resourceName    The name of the resource.
     * @param resourceType    The type of the resource.
     * @param positionInQueue The position in the queue of all resources of the resource.
     */
    public TableRepresentationResourceInformation(String resourceID,
                                                  String resourceName, String resourceType, Integer positionInQueue) {
        this.resourceID = resourceID;
        this.resourceName = resourceName;
        this.resourceType = resourceType;
        this.positionInQueue = positionInQueue;
    }


    public TableRepresentationResourceInformation(String resourceID,
                                                  String resourceName, String resourceType, int numberOfTimesBorrowed) {
        this.resourceID = resourceID;
        this.resourceName = resourceName;
        this.resourceType = resourceType;
        this.numberOfTimesBorrowed = numberOfTimesBorrowed;
    }

    public Integer getNumberOfTimesBorrowed() {
        return (Integer)numberOfTimesBorrowed;
    }

    /**
     * Gets the resource unique ID.
     *
     * @return The unique ID of the resource.
     */
    public String getResourceID() {
        return resourceID;
    }

    /**
     * Gets the resource name.
     *
     * @return The resource name.
     */
    public String getResourceName() {
        return resourceName;
    }

    /**
     * Gets the resource type.
     *
     * @return The type of resource this resource is.
     */
    public String getResourceType() {
        return resourceType;
    }

    /**
     * The position in the queue of all resources of this resource.
     *
     * @return The position in the queue of all resources of this resource.
     */
    public Integer getPositionInQueue() {
        return positionInQueue;
    }
}
