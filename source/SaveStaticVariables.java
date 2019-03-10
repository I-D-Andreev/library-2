import java.io.*;

/**
 * The sole purpose of this class is to store static variables as non-static ones in order to be saved easily.
 *
 * @author Ivan Andreev
 */
public class SaveStaticVariables implements Serializable {

    /**
     * The user next unique ID.
     */
    private int userNextID;

    /**
     * The resource next unique ID.
     */
    private int resourceNextID;

    /**
     * The copy next unique ID.
     */
    private int copyNextID;

    /**
     * The event next unique ID.
     */
    private int eventNextID;

    /**
     * Saves the static variables we use for unique ID.
     *
     * @param userNextID     The user next unique ID.
     * @param resourceNextID The resource next unique ID.
     * @param copyNextID     The copy next unique ID.
     * @param eventNextID    The event next unique ID.
     */
    public SaveStaticVariables(int userNextID, int resourceNextID, int copyNextID, int eventNextID) {
        this.userNextID = userNextID;
        this.resourceNextID = resourceNextID;
        this.copyNextID = copyNextID;
        this.eventNextID = eventNextID;
    }

    /**
     * Populates the static variables we are saving.
     */
    public SaveStaticVariables() {
        this.selfPopulate();
    }

    /**
     * Gets the user's next unique ID.
     *
     * @return The user's next unique ID.
     */
    public int getUserNextID() {
        return userNextID;
    }

    /**
     * Sets the user's next unique ID.
     *
     * @param userNextID The next unique user ID.
     */
    public void setUserNextID(int userNextID) {
        this.userNextID = userNextID;
    }

    /**
     * Gets the next resource unique ID.
     *
     * @return The next unique resource ID.
     */
    public int getResourceNextID() {
        return resourceNextID;
    }

    /**
     * Sets the resource's next unique ID.
     *
     * @param resourceNextID The resources next unique ID.
     */
    public void setResourceNextID(int resourceNextID) {
        this.resourceNextID = resourceNextID;
    }

    /**
     * Gets the next unique copy ID.
     *
     * @return The next unique copy ID.
     */
    public int getCopyNextID() {
        return copyNextID;
    }

    /**
     * Sets the next copy's unique ID.
     *
     * @param copyNextID The copy's next unique ID.
     */
    public void setCopyNextID(int copyNextID) {
        this.copyNextID = copyNextID;
    }

    /**
     * Gets the next unique event ID.
     *
     * @return The next unique event ID.
     */
    public int getEventNextID() {
        return eventNextID;
    }

    /**
     * Sets the next event's unique ID.
     *
     * @param eventNextID The event's next unique ID.
     */
    public void setEventNextID(int eventNextID) {
        this.eventNextID = eventNextID;
    }

    /**
     * Saves the IDs.
     */
    public void save() {

        // Get a file to write in or create it if it doesn't exist.
        File file = this.fileToReadWrite();

        try {
            // file writer
            // every time overwrite the file instead of just appending it
            FileOutputStream fileOut = new FileOutputStream(file, false);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            // save the object to the file
            out.writeObject(this);

            // close the file writer
            out.close();
            fileOut.close();

        } catch (IOException e) {
            System.out.println("Problem creating a file writer.");
            e.printStackTrace();
            System.exit(0);
        } catch (Exception e) {
            System.out.println("Couldn't write to file.");
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println("Successfully written values to file.");
    }

    /**
     * Fills the static variables with data.
     */
    private void selfPopulate() {
        // Get a file to read from or create it if it doesn't exist.
        File file = this.fileToReadWrite();

        try {
            // file reader
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            // read the object in the file
            SaveStaticVariables saveStaticVariables = (SaveStaticVariables) in.readObject();

            // close the reader
            in.close();
            fileIn.close();

            // assign the variable we just read
            this.resourceNextID = saveStaticVariables.getResourceNextID();
            this.userNextID = saveStaticVariables.getUserNextID();
            this.copyNextID = saveStaticVariables.getCopyNextID();
            this.eventNextID = saveStaticVariables.getEventNextID();

        } catch (IOException e) {
            System.out.println("Couldn't access file to read from.");
            e.printStackTrace();
            System.exit(0);
        } catch (Exception e) {
            System.out.println("Problem in writing to file.");
            e.printStackTrace();
            System.exit(0);
        }

        System.out.println("Successfully read values from file.");

    }

    /**
     * Get a file to read/write or create it if it doesn't exist.
     */
    private File fileToReadWrite() {
        File file = null;
        try {
            file = new File("source/resources/values.ser");
            file.createNewFile();
        } catch (Exception e) {
            System.out.println("Problem accessing file.");
            e.printStackTrace();
            System.exit(0);
        }
        return file;
    }

    /**
     * Fills the static values with dummy test data.
     */
    // Might be needed to rebuild the program if it is crashing.
    // More in the README file.
    public void selfPopulate1() {
        this.resourceNextID = 4;
        this.userNextID = 3;
        this.copyNextID = 4;
        this.eventNextID = 4;
    }
}
