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
        spoofDateData();
    }

    /**
     * Adds additional borrowings to a user's history.
     * Artificially adds entries so that the statistics can be showcased that they work
     * for months back. (Because when we borrow a resource it gets the current date, and we might want to
     * check whether the previous month is working).
     */
    private void spoofDateData(){
//        // adds history entries to the borrowing history of
//        // user sianspike
//        NormalUser sianspike = (NormalUser)this.getUserManager().getUserByUsername("sianspike");
//
//        // get a random copy
//        Copy copy = this.getResourceManager().getCopyById("L-2-0");
//
//        // now directly change the copy's borrowing history to say that sianspike has borrowed it on some day
//        Date date;
//
//        date = new GregorianCalendar(2019, Calendar.MARCH, 11).getTime();
//        copy.getLoanHistory().addEntry(new HistoryEntryItemTransaction( date, true, sianspike));
//
//        date = new GregorianCalendar(2019, Calendar.FEBRUARY, 11).getTime();
//        copy.getLoanHistory().addEntry(new HistoryEntryItemTransaction( date, true, sianspike));
//
//        date = new GregorianCalendar(2019, Calendar.JANUARY, 30).getTime();
//        copy.getLoanHistory().addEntry(new HistoryEntryItemTransaction( date, true, sianspike));

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
