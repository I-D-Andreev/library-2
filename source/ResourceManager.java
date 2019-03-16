import javafx.util.Pair;

import java.io.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * The Class ResourceManager.
 *
 * @author Steven Lewkowicz, Christina Meggs, Ivan Andreev
 */

public class ResourceManager implements Serializable {

    /**
     * Reference to the library the resource manager manages.
     */
    private Library library;

    /**
     * List of resources.
     */
    private ArrayList<Resource> resources;

    /**
     * Initializes the resource manager.
     *
     * @param library The library the resource manager will manage.
     */
    public ResourceManager(Library library) {
        this.library = library;
        resources = new ArrayList<>();

        this.selfPopulate();

        // assign next ID static variable to the user class
        Resource.setNextID(new SaveStaticVariables().getResourceNextID());

        // assign the next ID static variable to the copy class
        Copy.setNextId(new SaveStaticVariables().getCopyNextID());

    }

    /**
     * Saves the changes that happened in the resource manager.
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
            out.writeObject(resources);

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
        System.out.println("Successfully written resource to file.");
    }

    /**
     * Fills the resource manager with data.
     */
    private void selfPopulate() {
        // Get a file to read from or create it if it doesn't exist.
        File file = this.fileToReadWrite();

        try {
            // file reader
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            // read the object in the file
            ArrayList<Resource> readResource = (ArrayList<Resource>) in.readObject();

            // close the reader
            in.close();
            fileIn.close();

            // assign the variable we just read
            this.resources = readResource;

        } catch (IOException e) {
            System.out.println("Couldn't access file to read from.");
            e.printStackTrace();
            System.exit(0);
        } catch (Exception e) {
            System.out.println("Problem in writing to file.");
            e.printStackTrace();
            System.exit(0);
        }

        System.out.println("Successfully read resource from file.");

    }

    /**
     * Get a file to read/write or create it if it doesn't exist.
     */
    private File fileToReadWrite() {
        File file = null;
        try {
            file = new File("source/resources/resources.ser");
            file.createNewFile();
        } catch (Exception e) {
            System.out.println("Problem accessing file.");
            e.printStackTrace();
            System.exit(0);
        }
        return file;
    }

    /**
     * Adds a resource to the resource manager.
     *
     * @param resource The resource to be added.
     */
    public void addResource(Resource resource) {
        this.resources.add(resource);
        this.updateNewAdditions(resource);
    }

    // Adds the new resource as a New Addition to the users
    private void updateNewAdditions(Resource resource) {
        ArrayList<User> allUsers = library.getUserManager().getAllUsers();
        for (User user : allUsers) {

            // only show new additions to normal users
            // add the new resource to every normal user
            if (user instanceof NormalUser) {
                NormalUser normalUser = (NormalUser) user;
                normalUser.getNewAdditions().add(resource);
            }
        }
    }

    /**
     * Adds a copy of a resource in the copy manager of the resource using the loan duration and type of resource.
     *
     * @param loanDuration The loan duration of the copy.
     * @param resource     The resource the copy is a copy of.
     */
    public void addCopyOfResource(int loanDuration, Resource resource) {
        resource.getCopyManager().addCopy(loanDuration);
    }

    /**
     * Adds a copy of a resource in the copy manager of the resource using the copy and type of resource.
     *
     * @param copy     The copy to be added.
     * @param resource The resource this copy is a copy of.
     */
    public void addCopyOfResource(Copy copy, Resource resource) {
        resource.getCopyManager().addCopy(copy);
    }

    /**
     * Removes a resource from the resource manager.
     *
     * @param resource The resource to be removed.
     */
    public void removeResource(Resource resource) {
        this.resources.remove(resource);
    }

    /**
     * Removes a resource from the resource manager using its ID.
     *
     * @param resourceId The ID of the resource to be removed.
     */
    public void removeResource(String resourceId) {
        if (this.getResourceById(resourceId) != null) {
            this.removeResource(this.getResourceById(resourceId));
        }
    }

    /**
     * Edits a resource.
     *
     * @param resource  The resource to be edited.
     * @param title     The new title of the resource.
     * @param year      The new year of the resource.
     * @param imagePath The new image path of the resource.
     */
    private void editResource(Resource resource, String title, int year, String imagePath) {
        resource.setTitle(title);
        resource.setYear(year);
        resource.setThumbnailImagePath(imagePath);
    }

    /**
     * Method to edit a laptop resource.
     *
     * @param laptop       The laptop resource to be edited.
     * @param title        The new title of the resource.
     * @param year         The new year the laptop was manufactured.
     * @param imagePath    The new thumbnail image path of the resource.
     * @param manufacturer The new manufacturer of the laptop.
     * @param model        The new model of the laptop.
     * @param installedOS  The new OS on the laptop.
     */
    public void editLaptop(Laptop laptop, String title, int year, String imagePath,
                           String manufacturer, String model, String installedOS) {
        editResource(laptop, title, year, imagePath);
        laptop.setManufacturer(manufacturer);
        laptop.setModel(model);
        laptop.setInstalledOS(installedOS);
    }

    /**
     * Method to edit a book resource.
     *
     * @param book      The book resource to be edited.
     * @param title     The new title of the book.
     * @param year      The new year the book was published.
     * @param imagePath The new thumbnail image path of the book.
     * @param author    The new author of the book.
     * @param publisher The new publisher of the book.
     * @param genre     The new genre of the book.
     * @param ISBN      The new ISBN of the book.
     * @param language  The new language of the book.
     */
    public void editBook(Book book, String title, int year, String imagePath,
                         String author, String publisher, String genre, String ISBN, String language) {
        editResource(book, title, year, imagePath);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setGenre(genre);
        book.setISBN(ISBN);
        book.setLanguage(language);
    }

    /**
     * Method to edit a DVD resource.
     *
     * @param dvd                     The dvd resource to be edited.
     * @param title                   The new title of the dvd.
     * @param year                    The new year the dvd came out.
     * @param imagePath               The new thumbnail image path of the dvd.
     * @param director                The new director of the dvd.
     * @param runtime                 The new runtime of the dvd.
     * @param language                The new language of the dvd.
     * @param listOfSubtitleLanguages The new list of subtitles of the dvd.
     */
    public void editDVD(DVD dvd, String title, int year, String imagePath,
                        String director, int runtime, String language, ArrayList<String> listOfSubtitleLanguages) {
        editResource(dvd, title, year, imagePath);
        dvd.setDirector(director);
        dvd.setRuntime(runtime);
        dvd.setLanguage(language);
        dvd.setListOfSubtitleLanguages(listOfSubtitleLanguages);
    }


    /**
     * Method to edit a video game resource.
     *
     * @param videoGame             The video game resource to be edited.
     * @param title                 The new title of the video game resource.
     * @param year                  The new year of the video game resource.
     * @param imagePath             The new thumbnail image path of the video game.
     * @param publisher             The new publisher of the video game.
     * @param genre                 The new genre of the video game.
     * @param certificateRating     The new certificate rating of the video game.
     * @param hasMultiplayerSupport The new multiplayer supporting of the video game.
     */
    public void editVideoGame(VideoGame videoGame, String title, int year, String imagePath,
                              String publisher, String genre, String certificateRating, boolean hasMultiplayerSupport) {
        editResource(videoGame, title, year, imagePath);
        videoGame.setPublisher(publisher);
        videoGame.setGenre(genre);
        videoGame.setCertificateRating(certificateRating);
        videoGame.setHasMultiplayerSupport(hasMultiplayerSupport);
    }

    /**
     * Gets a resource by that resource's ID.
     *
     * @param resourceId The ID of the resource.
     * @return The resource if found, null otherwise.
     */
    public Resource getResourceById(String resourceId) {
        Resource resource = null;
        for (Resource resourceInList : resources) {
            if (resourceInList.getUniqueID().equals(resourceId)) {
                resource = resourceInList;
            }
        }
        return resource;
    }

    /**
     * Gets all the resources.
     *
     * @return An ArrayList of all the resources.
     */
    public ArrayList<Resource> getAllResources() {
        return resources;

    }

    /**
     * Returns the resources requested by a user.
     *
     * @param user The user requesting resources.
     * @return The resources he is requesting.
     */
    public ArrayList<Resource> getRequestedResourcesBy(User user) {
        ArrayList<Resource> requestedResource = new ArrayList<>();
        for (Resource resource : resources) {
            if (resource.getCopyManager().requestQueueContains(user)) {
                requestedResource.add(resource);
            }
        }
        return requestedResource;
    }

    /**
     * Removes a copy from the resources.
     *
     * @param copy The copy to be removed.
     */
    public void removeCopy(Copy copy) {
        for (Resource resource : resources) {
            resource.getCopyManager().removeCopy(copy);
        }
    }

    /**
     * Removes a copy by using its copy ID.
     *
     * @param copyId The copy's ID to be removed.
     */
    public void removeCopy(String copyId) {
        for (Resource resource : resources) {
            resource.getCopyManager().removeCopyById(copyId);
        }
    }

    /**
     * Gets a copy by its ID.
     *
     * @param copyId The copy's to be returned ID.
     * @return The copy.
     */
    public Copy getCopyById(String copyId) {
        Copy returnCopy = null;

        for (Resource resource : resources) {
            if (resource.getCopyManager().findCopyById(copyId) != null) {
                returnCopy = resource.getCopyManager().findCopyById(copyId);
            }
        }

        return returnCopy;
    }

    /**
     * Loans a copy to a user using the resource he is requesting and the user we are loaning it to.
     *
     * @param resource The resource requested.
     * @param toUser   The user that the copy is loaned to.
     * @return The user that has loaned the copy if he isn't an admin, null otherwise.
     */
    public Copy loanCopy(Resource resource, User toUser) {
        if (!toUser.hasAdminAccess()) {
            return resource.getCopyManager().loanCopy((NormalUser) toUser);
        }
        return null;
    }

    /**
     * Reserves a copy for a user.
     *
     * @param resource The resource the copy should be.
     * @param forUser  The user requesting it.
     */
    public void reserveCopy(Resource resource, User forUser) {
        resource.getCopyManager().reserveCopy((NormalUser) forUser);
    }

    /**
     * Called when a copy is to be returned.
     *
     * @param copy The copy to be returned.
     * @return The copy asked to be returned.
     */
    public double returnCopy(Copy copy) {
        return copy.returnCopy();
    }

    /**
     * Gets all the copies
     *
     * @return An ArrayList of all the copies.
     */
    public ArrayList<Copy> getAllCopies() {
        ArrayList<Copy> listOfAllCopies = new ArrayList<>();
        for (Resource resource : resources) {
            listOfAllCopies.addAll(resource.getCopyManager().getListOfAllCopies());
        }

        return listOfAllCopies;
    }

    /**
     * Gets all the reserved copies for a particular user.
     *
     * @param user The user that has reserved sine copies.
     * @return An ArrayList of the copies reserved for the user.
     */
    public ArrayList<Copy> getReservedCopiesFor(User user) {
        ArrayList<Copy> reservedCopies = new ArrayList<>();
        for (Copy copy : this.getAllCopies()) {
            if (copy.getReservedFor() != null && copy.getReservedFor().getId().equals(user.getId())) {
                reservedCopies.add(copy);
            }
        }

        return reservedCopies;
    }

    /**
     * Gets all the overdue copies.
     *
     * @return An ArrayList of the overdue copies.
     */
    public ArrayList<Copy> getOverdueCopies() {
        ArrayList<Copy> overdueCopies = new ArrayList<>();

        for (Copy copy : this.getAllCopies()) {
            if (copy.isOverdue()) {
                overdueCopies.add(copy);
            }
        }
        return overdueCopies;
    }

    /**
     * Gets all the borrowed copies for a specific user.
     *
     * @param user The user that we are finding the copies for.
     * @return An ArrayList with all the copies the user has borrowed.
     */
    public ArrayList<Copy> getBorrowedCopiesBy(User user) {
        if (!user.hasAdminAccess()) {
            return ((NormalUser) user).getBorrowedCopies();
        }
        return null;
    }

    /**
     * Find the most popular resource.
     *
     * @return A pair containing the most popular resource and the number of times it was borrowed.
     */
    public Pair<Resource, Integer> mostPopularResource() {
        if (this.getAllResources().size() == 0) {
            return null;
        }

        Resource mostPopularResource = this.getAllResources().get(0);
        int numberOfTimeBorrowed = 0;

        // Go through the history of each copy in each resource
        // and count the number of times they were borrowed.
        // The resource, whose sum of copies' borrowings is the most, will be the most popular one.

        for (Resource resource : this.getAllResources()) {
            int currentResourceNumberOfTimesBorrowed = 0;

            for (Copy copy : resource.getCopyManager().getListOfAllCopies()) {
                for (HistoryEntry entry : copy.getLoanHistory().getHistory()) {
                    if (((HistoryEntryItemTransaction) entry).isBorrowed()) {
                        currentResourceNumberOfTimesBorrowed++;
                    }
                }
            }

            if (currentResourceNumberOfTimesBorrowed > numberOfTimeBorrowed) {
                mostPopularResource = resource;
                numberOfTimeBorrowed = currentResourceNumberOfTimesBorrowed;
            }
        }

        return new Pair<Resource, Integer>(mostPopularResource, numberOfTimeBorrowed);
    }

    /**
     * Get number of borrowed resources by a certain user on a certain day.
     *
     * @param byUser The user who borrowed the resource.
     * @param onDate The day.
     * @return The number of resources said user has borrowed for the whole day.
     */
    public int getNumberOfBorrowedResourcesOn(NormalUser byUser, Date onDate) {
        Date startOfDay = getStartOfDay(onDate);
        Date endOfDay = getEndOfDay(onDate);


        int count = getNumberOfBorrowedResourcesBetween(byUser, startOfDay, endOfDay);
        return count;
    }

    /**
     * Get number of borrowed resources by a certain user on a certain week.
     *
     * @param byUser   The user who borrows the resources.
     * @param weekDate Any day of the week.
     * @return The number of resources borrowed by the user from the start(Monday) to the end(Sunday) of the week.
     */
    public int getNumberOfBorrowedResourcesForTheWeek(NormalUser byUser, Date weekDate) {
        final int MILLISECONDS_IN_A_DAY = (24 * 3600 * 1000);

        // becomes the very first second of the given day
        Date startOfTheWeek = getStartOfDay(weekDate);

        // becomes the very last second of the given day
        Date endOfTheWeek = getEndOfDay(weekDate);


        // calculates the very start of the week
        // if it is currently Sunday
        if (startOfTheWeek.getDay() == 0) {
            // move six days behind to become monday
            startOfTheWeek = new Date(startOfTheWeek.getTime() - 6 * MILLISECONDS_IN_A_DAY);
        } else {
            int howManyDaysToMoveBack = startOfTheWeek.getDay() - 1;
            startOfTheWeek = new Date(startOfTheWeek.getTime() - howManyDaysToMoveBack * MILLISECONDS_IN_A_DAY);
        }

        // calculates the very end of the week
        // it is not sunday
        if (endOfTheWeek.getDay() != 0) {
            int howManyDaysToMoveFront = 7 - endOfTheWeek.getDay();
            endOfTheWeek = new Date(endOfTheWeek.getTime() + howManyDaysToMoveFront * MILLISECONDS_IN_A_DAY);

        }

        int count = getNumberOfBorrowedResourcesBetween(byUser, startOfTheWeek, endOfTheWeek);
        return count;
    }

    /**
     * Get number of borrowed resources by a certain user on a certain month.
     *
     * @param byUser    The user who borrows the resources.
     * @param monthDate any day in a month.
     * @return The number of resources borrowed by the user from the start of the month to the end of the month.
     */
    public int getNumberOfBorrowedResourcesForTheMonth(NormalUser byUser, Date monthDate) {
        int[] daysInAMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        // get the first day of the month for the given date
        // copies the Date
        Date startOfTheMonth = new Date(monthDate.getTime());
        // sets the day of the month to the first one
        startOfTheMonth.setDate(1);
        // sets the time to the first second of the day
        startOfTheMonth = getStartOfDay(startOfTheMonth);


        // get the last date of the month for a given date

        // copies the date
        Date endOfTheMonth = new Date(monthDate.getTime());
        int monthNumber = endOfTheMonth.getMonth(); // values 0 - 11

        // gets the year + 1900, because getYear return the difference of current year and 1900
        int year = endOfTheMonth.getYear() + 1900;
        endOfTheMonth.setDate(daysInAMonth[monthNumber]);

        // check if leap year
        boolean isLeapYear = new GregorianCalendar().isLeapYear(year);
        if (isLeapYear && monthNumber == 1) {
            endOfTheMonth.setDate(29);
        }

        // set to the last second in said day
        endOfTheMonth = getEndOfDay(endOfTheMonth);

        int count = getNumberOfBorrowedResourcesBetween(byUser, startOfTheMonth, endOfTheMonth);
        return count;
    }

    /**
     * Gets a number of resources borrowed by a user between two dates (incl.)
     *
     * @param byUser   The user borrowing.
     * @param fromDate The start date.
     * @param toDate   The end date.
     * @return The number of resources borrowed by the user in the period [fromDate, toDate].
     */
    private int getNumberOfBorrowedResourcesBetween(NormalUser byUser, Date fromDate, Date toDate) {
        int count = 0;


        // loop through all copies in the library
        for (Copy copy : getAllCopies()) {

            // for each entry in the copy's history
            for (HistoryEntry historyEntry : copy.getLoanHistory().getHistory()) {

                if (historyEntry instanceof HistoryEntryItemTransaction) {
                    HistoryEntryItemTransaction itemTransaction =
                            (HistoryEntryItemTransaction) historyEntry;

                    // check if the copy was borrowed by the said person and inbetween the dates
                    if (itemTransaction.isBorrowed() && itemTransaction.getBorrowedBy().equals(byUser)
                            && fromDate.compareTo(itemTransaction.getDate()) <= 0   // fromDate <= borrow Date
                            && toDate.compareTo(itemTransaction.getDate()) >= 0) {  // toDate >= borrowDate
                        count++;
                    }
                }
            }

        }

        return count;
    }

    /**
     * Get the number of times a certain resource was borrowed on a certain day.
     *
     * @param resource The resource.
     * @param onDate   The date of the day (any time in that day).
     * @return The number of times a resource was borrowed in between the start of the day and the end of the day.
     */
    public int getNumberOfTimesResourceWasBorrowedOn(Resource resource, Date onDate) {
        Date startOfDay = getStartOfDay(onDate);
        Date endOfDay = getEndOfDay(onDate);

        int count = getNumberOfTimesResourceWasBorrowedBetween(resource, startOfDay, endOfDay);
        return count;
    }

    /**
     * Get the number of times a certain resource was borrowed from today to one week back.
     *
     * @param resource The resource being borrowed.
     * @param today    The date today (Also the date the one week period ends).
     * @return The number of times said resource was borrowed for the time period.
     */
    public int getNumberOfTimesResourceWasBorrowedPastWeek(Resource resource, Date today) {
        final int MILLISECONDS_IN_A_DAY = (24 * 3600 * 1000);

        // get the last second of the day (i.e. 23:59:59)
        Date endOfWeek = getEndOfDay(today);


        // move 1 week back (6 days)
        // and set the day to the first second of that day
        Date startOfWeek = new Date(today.getTime() - 6 * MILLISECONDS_IN_A_DAY);
        startOfWeek = getStartOfDay(startOfWeek);

        int count = getNumberOfTimesResourceWasBorrowedBetween(resource, startOfWeek, endOfWeek);
        return count;
    }

    /**
     * Get the number of times said resource was borrowed for all the time.
     *
     * @param resource The resource.
     * @return The number of times a resource was borrowed.
     */
    public int getNumberOfTimesResourceWasBorrowedForAllTime(Resource resource) {
        final int MILLISECONDS_IN_A_DAY = (24 * 3600 * 1000);

        // the start date will just be set a lot of time back
        Date startDate = new GregorianCalendar(1, Calendar.JANUARY, 1).getTime();

        // the end date will just be 1 day into the future
        Date today = new Date();
        Date endDate = new Date(today.getTime() + MILLISECONDS_IN_A_DAY);

        int count = getNumberOfTimesResourceWasBorrowedBetween(resource, startDate, endDate);
        return count;
    }

    /**
     * Get a number of times a resource was borrowed between two dates.
     *
     * @param resource The resource.
     * @param fromDate Start date.
     * @param toDate   End date.
     * @return The number of times a resource was borrowed in the period [fromDate,toDate].
     */
    private int getNumberOfTimesResourceWasBorrowedBetween(Resource resource, Date fromDate, Date toDate) {
        int count = 0;

        // loop through all copies of the resource
        for (Copy copy : resource.getCopyManager().getListOfAllCopies()) {

            // loop through each copy's history
            for (HistoryEntry historyEntry : copy.getLoanHistory().getHistory()) {

                if (historyEntry instanceof HistoryEntryItemTransaction) {
                    HistoryEntryItemTransaction itemTransaction =
                            (HistoryEntryItemTransaction) historyEntry;

                    if (itemTransaction.isBorrowed() &&
                            fromDate.compareTo(itemTransaction.getDate()) <= 0          // fromDate <= borrowDate
                            && toDate.compareTo(itemTransaction.getDate()) >= 0) {       // toDate >= borrowDate
                        count++;
                    }
                }
            }

        }

        return count;
    }

    /**
     * Get the number of fines in a certain time period.
     *
     * @param listOfFines The list of fines.
     * @param fromDate    The start of the period.
     * @param toDate      The end of the period.
     * @return The number of fines occurring inbetween fromDate and toDate.
     */
    private int getNumberOfFinesBetween(ArrayList<HistoryEntryFine> listOfFines,
                                        Date fromDate, Date toDate) {
        int count = 0;
        for (HistoryEntryFine fine : listOfFines) {
            if (fromDate.compareTo(fine.getDate()) <= 0                         // fromDate <= fineDate
                    && toDate.compareTo(fine.getDate()) >= 0) {                  // toDate >= fineDate
                count++;
            }
        }
        return count;
    }

    /**
     * Given a day (any time in a day) and list of fines, return how many fines occurred that day.
     *
     * @param listOfFines The list of fines.
     * @param onDay       The day.
     * @return The number of fines occurring on that day.
     */
    public int getNumberOfFinesOn(ArrayList<HistoryEntryFine> listOfFines, Date onDay) {
        int count = 0;
        Date startOfDay = getStartOfDay(onDay);
        Date endOfDay = getEndOfDay(onDay);

        count = getNumberOfFinesBetween(listOfFines, startOfDay, endOfDay);
        return count;
    }

    /**
     * Given a day return the very start of that day.
     *
     * @param date Any time in the day.
     * @return A date representing the day we have given at its earliest time. (first second of the day).
     */
    private Date getStartOfDay(Date date) {
        // convert date to localDateTime
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        // get start of day
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);

        // convert back to date
        Date startOfDayDate = Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
        return startOfDayDate;
    }

    /**
     * Given a day returns the very end of that day.
     *
     * @param date A day (any time in the day).
     * @return The very end of the day.
     */
    private Date getEndOfDay(Date date) {
        // convert date to localDateTime
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());

        // get end of day
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);

        // convert back to date
        Date endOfDayDate = Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());

        return endOfDayDate;

    }

    /**
     * Method that fills the resource manager with dummy data.
     */
    // Might be needed to rebuild the program if it is crashing.
    // More in the README file.
    public void selfPopulate1() {
        Book b = new Book("Harry Potter", 2010, "", "J.K.R.", "dn");
        Book b2 = new Book("Harry Potter2", 2010, "", "J.K.R.", "dn");
        Laptop l1 = new Laptop("Lenovo", 2015, "", "LN", "S3", "W");

        this.addResource(b);
        this.addResource(b2);

        this.addResource(l1);
        this.addResource(new DVD("The Godfather", 2015, "", "P", 201));

        this.addCopyOfResource(2, l1);
        this.addCopyOfResource(3, l1);
        this.addCopyOfResource(4, l1);
        this.addCopyOfResource(1, b);

    }
}
