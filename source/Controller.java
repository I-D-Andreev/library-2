/**
 * A class to model a Controller.
 *
 * @author Ivan Andreev
 */
// The controller is a superclass of all controller classes
// and keeps the data about the library.
public class Controller {
    private Library library;

    /**
     * Gets the library that we are working on.
     *
     * @return The library we are working on.
     */
    public Library getLibrary() {
        return library;
    }

    /**
     * Sets the library we are working on.
     *
     * @param library The new library to work on.
     */
    public void setLibrary(Library library) {
        this.library = library;
        onStart();
    }

    /**
     * Immediately invoked on load of form and with initialized parameters.
     * Should be overridden in the case when needed.
     */
    public void onStart() {
        // Override this method in the controller subclasses.
        // Equivalent of javafx initialize(), but initialize doesn't seem to be working
        // as the value of library is always null.
    }
}
