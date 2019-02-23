/**
 * Library.
 * A class to manage the whole library system.
 *
 * @author Ivan Andreev
 */
public class Library {
    private UserManager userManager;
    private ResourceManager resourceManager;
    private User currentUserLoggedIn;

    /**
     * The library initializer.
     */
    public Library() {
        userManager = new UserManager();
        resourceManager = new ResourceManager(this);
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
     * Saves all the changes that happened in the library.
     */
    public void save() {
        this.userManager.save();
        this.resourceManager.save();
        SaveStaticVariables saveStaticVariables = new SaveStaticVariables(User.getNextID(), Resource.getNextID(), Copy.getNextId());
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
