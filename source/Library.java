/**
 * Library.
 * A class to manage the whole library system.
 *
 * @author Ivan Andreev
 */
public class Library {
    private UserManager userManager;
    private ResourceManager resourceManager;
    private EventManager eventManager;
    private User currentUserLoggedIn;

    /**
     * The library initializer.
     */
    public Library() {
        userManager = new UserManager();
        resourceManager = new ResourceManager(this);
        eventManager = new EventManager();
    }

    /**
     * Gets the user manager of the library.
     *
     * @return The user manager.
     */
    public UserManager getUserManager() {
        return userManager;
    }

    /**
     * Gets the resource manager of the library.
     *
     * @return The resource manager.
     */
    public ResourceManager getResourceManager() {
        return resourceManager;
    }

    /**
     * Gets the event manager of the library.
     *
     * @return The event manager.
     */
    public EventManager getEventManager() {
        return eventManager;
    }

    /**
     * Saves all the changes that happened in the library.
     */
    public void save() {
        this.userManager.save();
        this.resourceManager.save();
        this.eventManager.save();
        SaveStaticVariables saveStaticVariables = new SaveStaticVariables(User.getNextID(), Resource.getNextID(), Copy.getNextId(), Event.getNextId());
        saveStaticVariables.save();
    }

    /**
     * Gets the user currently logged in the library.
     *
     * @return The user currently logged in.
     */
    public User getCurrentUserLoggedIn() {
        return currentUserLoggedIn;
    }

    /**
     * Sets the user that is currently logged in.
     *
     * @param currentUserLoggedIn The new user logged in.
     */
    public void setCurrentUserLoggedIn(User currentUserLoggedIn) {
        this.currentUserLoggedIn = currentUserLoggedIn;
    }
}
