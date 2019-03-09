import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * The Class EventManager.
 *
 * @author James Hodder
 */
public class EventManager implements Serializable {

    private ArrayList<Event> events;

    /**
     * Initializes the event manager.
     */
    public EventManager() {
        events = new ArrayList<>();

        this.selfPopulate();
    }

    /**
     * Saves the changes that happened in the event manager.
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
            out.writeObject(events);

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
        System.out.println("Successfully written event to file.");
    }

    /**
     * Fills the event manager with data.
     */
    private void selfPopulate() {
        // Get a file to read from or create it if it doesn't exist.
        File file = this.fileToReadWrite();

        try {
            // file reader
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            // read the object in the file
            ArrayList<Event> readEvent = (ArrayList<Event>) in.readObject();

            // close the reader
            in.close();
            fileIn.close();

            // assign the variable we just read
            this.events = readEvent;

        } catch (IOException e) {
            System.out.println("Couldn't access file to read from.");
            e.printStackTrace();
            System.exit(0);
        } catch (Exception e) {
            System.out.println("Problem in writing to file.");
            e.printStackTrace();
            System.exit(0);
        }

        System.out.println("Successfully read event from file.");

    }

    /**
     * Get a file to read/write or create it if it doesn't exist.
     */
    private File fileToReadWrite() {
        File file = null;
        try {
            file = new File("source/resources/events.ser");
            file.createNewFile();
        } catch (Exception e) {
            System.out.println("Problem accessing file.");
            e.printStackTrace();
            System.exit(0);
        }
        return file;
    }

    /**
     * Adds an event to the event manager.
     *
     * @param event The event to be added.
     */
    public void addEvent(Event event) {
        this.events.add(event);
    }

    /**
     * Removes an event from the event manager.
     *
     * @param event The event to be removed.
     */
    public void removeEvent(Event event) {
        this.events.remove(event);
    }

    /**
     * Gets all the events.
     *
     * @return An ArrayList of all the events.
     */
    public ArrayList<Event> getAllEvents() {
        return events;
    }

    /**
     * Returns an event attended by the user.
     *
     * @param user The user attending the event.
     * @return The event they are requesting.
     */
    public ArrayList<Event> getEventsBy(User user) {
        ArrayList<Event> attendedEvent = new ArrayList<>();
        if (!user.hasAdminAccess()) {
            for (Event event : events) {
                if (event.containsUser((NormalUser) user)) {
                    attendedEvent.add(event);
                }
            }
        }
        return attendedEvent;
    }

    /**
     * Allows a user to attend an event.
     *
     * @param event The event the user is trying to attend.
     * @param user   The user that wants to attend the event.
     * @return true if the user is not an admin and the admission was successful.
     */
    public boolean attendEvent(Event event, User user) {
        if (!user.hasAdminAccess()) {
            return event.addAttendent((NormalUser) user);
        }
        return false;
    }

    /**
     * Gets all the upcoming attended events for a user.
     *
     * @param user The user that has upcoming events.
     * @return An ArrayList of the upcoming events attended by the user.
     */
    public ArrayList<Event> getAttendingEventsFor(User user) {
        ArrayList<Event> attendedEvents = new ArrayList<>();
        if (!user.hasAdminAccess()) {
            for (Event event : this.getAllEvents()) {
                if ((event.containsUser((NormalUser) user) &&
                        (event.startDate.atTime(event.startTime).isEqual(LocalDateTime.now())||
                                (event.startDate.atTime(event.startTime).isAfter(LocalDateTime.now()))))) {
                    attendedEvents.add(event);
                }
            }
        }

        return attendedEvents;
    }

    /**
     * Gets all the finished attended events for a user.
     *
     * @param user The user that has attended events.
     * @return An ArrayList of the finished events attended by the user.
     */
    public ArrayList<Event> getAttendedEventsFor(User user) {
        ArrayList<Event> attendedEvents = new ArrayList<>();
        if (!user.hasAdminAccess()) {
            for (Event event : this.getAllEvents()) {
                if ((event.containsUser((NormalUser) user) && event.startDate.atTime(event.startTime).isBefore(LocalDateTime.now()))) {
                    attendedEvents.add(event);
                }
            }
        }

        return attendedEvents;
    }

    /**
     * Method that fills the event manager with dummy data.
     */
    // Might be needed to rebuild the program if it is crashing.
    // More in the README file.
    public void selfPopulate1() {
        Event e1 = new Event("Christmas Celebrations", LocalDate.of(2019, 12,25),
                LocalTime.of(14, 0), 5, "Christmas celebration for those staying on campus during holidays.");
        Event e2 = new Event("New Years Celebrations", LocalDate.of(2018, 12,31),
                LocalTime.of(23, 0), 5, "New Years Countdown for those staying on campus during holidays.");

        this.addEvent(e1);
        this.addEvent(e2);

        this.addEvent(new Event("Varsity", LocalDate.of(2019, 4,10),
                LocalTime.of(15, 30), 10, "Varsity 2019 Rugby Final."));
    }
}
