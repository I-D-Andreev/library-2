import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * @author James Hodder.
 * Class to model an Event.
 */

public class Event implements Serializable {

    /**
     * The unique ID of an event.
     */
    private String uniqueEventID;

    /**
     * The next unique ID of an event.
     */
    private static int nextId;

    /**
     * The title of the event.
     */
    private String title;

    /**
     * The start date of the event.
     */
    LocalDate startDate;

    /**
     * the start time of the event.
     */
    LocalTime startTime;

    /**
     * The maximum capacity of the event.
     */
    private int maxAttendees;

    /**
     * A description of the event.
     */
    private String description;

    /**
     * A list of current attendees.
     */
    private ArrayList<NormalUser> attendees = new ArrayList<NormalUser>();

    /**
     * The constructor of an Event.
     *
     * @param title             The title of the event.
     * @param date              The date of the event.
     * @param time              The start time of the event.
     * @param maxAttendees      The maximum capacity of the event.
     * @param description       A description of the event.
     */
    public Event(String title, LocalDate date, LocalTime time, int maxAttendees, String description) {
        this.title = title;
        this.startDate = date;
        this.startTime = time;
        this.maxAttendees = maxAttendees;
        this.description = description;
        this.setUniqueEventID();
    }

    /**
     * Gets the unique ID of the event.
     *
     * @return The unique copy ID of an event.
     */
    public String getUniqueEventID() {
        return uniqueEventID;
    }

    /**
     * Sets each event its unique ID.
     */
    private void setUniqueEventID() {
        this.uniqueEventID = "E" + nextId;
        nextId++;
    }

    /**
     * Get the Event's next id.
     *
     * @return The event's next id.
     */
    public static int getNextId() {
        return nextId;
    }

    /**
     * Set the event's next id.
     *
     * @param nextId The event's next id.
     */
    public static void setNextId(int nextId) {
        Event.nextId = nextId;
    }

    /**
     * Gets the title of the event.
     *
     * @return The title of the event.
     */
    public String getTitle() { return this.title; }

    /**
     * Sets the title of the event.
     *
     * @param title The new title of the event.
     */
    public void setTitle(String title) { this.title = title; }

    /**
     * Gets the start date of the event.
     *
     * @return The start date of the event.
     */
    public LocalDate getStartDate() { return this.startDate; }

    /**
     * Sets the start date of the event.
     *
     * @param date The date of the event.
     */
    public void setStartDate(LocalDate date) { this.startDate = date; }

    /**
     * Gets the start time of the event.
     *
     * @return The start time of the event.
     */
    public LocalTime getStartTime() { return this.startTime; }

    /**
     * Sets the start time of the event.
     *
     * @param time The time of the event.
     */
    public void setStartTime(LocalTime time) { this.startTime = time; }



    /**
     * Gets the max capacity of the event.
     *
     * @return The max capacity of the event.
     */
    public int getMaxAttendees() { return this.maxAttendees; }

    /**
     * Sets the max capacity of the event.
     *
     * @param maxAttendees The max capacity of the event.
     */
    public void setMaxAttendees(int maxAttendees) { this.maxAttendees = maxAttendees; }

    /**
     * Gets the current number of attendees.
     *
     * @return The current number of attendees.
     */
    public int attendeeCount() { return attendees.size(); };

    /**
     * Gets the description of the event.
     *
     * @return The description of the event.
     */
    public String getDescription() { return this.description; }

    /**
     * Sets the description of the event.
     *
     * @param description The description of the event.
     */
    public void setDescription(String description) { this.description = description; }

    /**
     * Checks to see if a user is part of the attendents list.
     *
     * @param user The user to check.
     * @return True if the user is on the attendents list.
     */
    public boolean containsUser(NormalUser user) { return this.attendees.contains(user); }

    /**
     * Adds a user to the attendees list if they aren't already attending and the event isn't at max capacity.
     *
     * @param user The user to add.
     * @return True if the user has been added.
     */
    public boolean addAttendent(NormalUser user) {
        if((attendeeCount() > maxAttendees) || (containsUser(user))) { return false; }
        attendees.add(user);
        return true;
    }
}