import java.util.ArrayList;
import java.io.*;

/**
 * Class to model a copy manager.
 *
 * @author Kleanthis Liontis, Ivan Andreev
 */
public class CopyManager implements Serializable {

    /**
     * An ArrayList containing all the users requesting a copy.
     */
    private ArrayList<User> requestQueue;

    /**
     * An ArrayList containing all the copies.
     */
    private ArrayList<Copy> listOfAllCopies;

    /**
     * The resource it is a copy manager of.
     */
    private Resource copyManagerOf;

    /**
     * Initializes the copyManager for a resource.
     *
     * @param copyManagerOf The resource it is a copy manager of.
     */
    public CopyManager(Resource copyManagerOf) {
        this.copyManagerOf = copyManagerOf;
        requestQueue = new ArrayList<>();
        listOfAllCopies = new ArrayList<>();
    }

    /**
     * Gets the resource it is a copy manager of.
     *
     * @return The resource it is a copy manager of.
     */
    public Resource getCopyManagerOf() {
        return copyManagerOf;
    }

    /**
     * Checks if the request queue is empty.
     *
     * @return true if empty,false otherwise.
     */
    public boolean isQueueEmpty() {
        return requestQueue.size() == 0;
    }

    /**
     * Checks if a user is inside the request queue.
     *
     * @param user The user to check for in the queue.
     * @return true if the user is in the queue,false otherwise.
     */
    public boolean requestQueueContains(User user) {
        return (requestQueue.indexOf(user) != -1);
    }

    /**
     * Gets the first user in the queue.
     *
     * @return The first user in the queue.
     */
    public User getFirstUserInQueue() {
        return this.requestQueue.get(0);
    }

    /**
     * Removes the first user from the queue.
     */
    public void removeFirstUserInQueue() {
        this.requestQueue.remove(0);
    }

    /**
     * Adds a user in the queue.
     *
     * @param user The user to be added to the queue.
     */
    public void addUserToTheQueue(User user) {
        this.requestQueue.add(user);
    }

    /**
     * Checks the user in in the queue.
     *
     * @param user The user to check for.
     * @return true if user is in queue,false otherwise.
     */
    public boolean isUserInQueue(User user) {
        return requestQueue.indexOf(user) != -1;
    }

    /**
     * An ArrayList of all copies.
     *
     * @return The ArrayList of all copies.
     */
    public ArrayList<Copy> getListOfAllCopies() {
        return this.listOfAllCopies;
    }

    /**
     * Gets a list of all available copies.
     *
     * @return A list of all available copies.
     */
    public ArrayList<Copy> getListOfAvailableCopies() {
        ArrayList<Copy> availableCopies = new ArrayList<>();

        for (int i = 0; i < listOfAllCopies.size(); i++) {
            if (listOfAllCopies.get(i).isAvailable()) {
                availableCopies.add(listOfAllCopies.get(i));
            }
        }
        return availableCopies;
    }

    /**
     * Gets the number of available copies.
     *
     * @return The number of available copies.
     */
    public int getNumOfAvailableCopies() {
        return this.getListOfAvailableCopies().size();
    }

    /**
     * Adds a copy in the ArrayList of all copies.
     *
     * @param copy The copy to be added in the lst.
     */
    public void addCopy(Copy copy) {
        this.listOfAllCopies.add(copy);
        this.newAvailableCopyEvent();
    }

    /**
     * Adds copy with its parameters, instead of copy object.
     *
     * @param loanDuration The loanDuration of the copy.
     */
    public void addCopy(int loanDuration) {
        this.listOfAllCopies.add(new Copy(loanDuration, this));
        this.newAvailableCopyEvent();
    }

    /**
     * Removes a copy from the ArrayList of all available copies.
     *
     * @param copy The copy to be removed.
     */
    public void removeCopy(Copy copy) {
        if (!copy.isAvailable()) {
            return; // error?
        }
        this.listOfAllCopies.remove(copy);
    }

    /**
     * Find a copy by its ID inside the list of all copies.
     *
     * @param copyId The id of the copy to be found.
     * @return The copy if found by its id, null otherwise.
     */
    public Copy findCopyById(String copyId) {
        Copy returnCopy = null;
        for (Copy copy : listOfAllCopies) {
            if (copy.getUniqueCopyID().equals(copyId)) {
                returnCopy = copy;
            }
        }
        return returnCopy;
    }

    /**
     * Removes a copy from the list of all copies by its ID.
     *
     * @param copyId The id of the copy to be removed.
     */
    public void removeCopyById(String copyId) {
        if (this.findCopyById(copyId) == null) {
            return;
        }
        this.listOfAllCopies.remove(this.findCopyById(copyId));
    }

    /**
     * Checks if a copy is available and if that copy is reserved for a user,
     * loans the copy to him.
     *
     * @param toUser The user who will receive the loaned copy.
     * @return Return the copy to loan to user if one is available or null if there are no available copies to loan.
     */
    public Copy loanCopy(NormalUser toUser) {
        // We look if there is a reserved copy for the User.
        for (Copy copy : listOfAllCopies) {
            if (copy.getReservedFor() != null && copy.getReservedFor().equals(toUser)) {
                copy.loanCopyTo(toUser);
                return copy;
            }
        }

        if (this.getNumOfAvailableCopies() == 0) {
            return null;
        }

        // We loan the first available copy.
        return this.getListOfAvailableCopies().get(0).loanCopyTo(toUser);
    }

    /**
     * Reserves a copy for a user.
     *
     * @param forUser The user the copy is being reserved for.
     */
    public void reserveCopy(NormalUser forUser) {
        this.requestQueue.add(forUser);
        this.setDueDateOfOldestBorrowedCopy();
    }

    /**
     * Find the oldest borrowed copy with no due date set
     * and set its due date.
     */
    private void setDueDateOfOldestBorrowedCopy() {
        // ** There is the possibility that all copies' due dates are already set.
        Copy oldestCopy = null;
        for (Copy copy : listOfAllCopies) {
            // we are only interested in copies with unset due dates
            if (copy.getDueDate() == null) {

                // if our oldest copy is not yet assigned, assign it to the first copy
                // which is borrowed
                if (oldestCopy == null && copy.getBorrowedBy() != null) {
                    oldestCopy = copy;
                } else if (oldestCopy != null && copy.getBorrowedBy() != null) {
                    // now we have our "oldest" copy and another borrowed copy with no due date set
                    // assign it to oldest, only if the other copy is older
                    if (copy.getBorrowedOn().compareTo(oldestCopy.getBorrowedOn()) == -1) {
                        oldestCopy = copy;
                    }
                }
            }

        }

        if (oldestCopy != null) {
            oldestCopy.setDueDate();
        }
    }


    /**
     * Called when a copy is returned.
     * Decides what to do with said copy.
     */
    public void newAvailableCopyEvent() {
        ArrayList<Copy> availableCopies = this.getListOfAvailableCopies();
        int numberOfCopies = availableCopies.size();
        int numberOfUsers = requestQueue.size();


        // Reserve as many available copies as possible.
        for (int i = 0; i < Math.min(numberOfUsers, numberOfCopies); i++) {
            // Reserve a copy for the first user in the request queue.
            availableCopies.get(i).setReservedFor(this.getFirstUserInQueue());
            this.removeFirstUserInQueue();
        }
    }

    /**
     * Gives the user's position in the request queue.
     *
     * @param user The user.
     * @return The position in the queue.
     */
    public int positionInQueue(User user) {
        // add +1 because positions start from 1, and not 0
        return this.requestQueue.indexOf(user) + 1;
    }
}
