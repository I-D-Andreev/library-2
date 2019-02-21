import java.util.ArrayList;
import java.io.*;

/**
 * Models a History class that contains entries with information about events.
 *
 * @author Ivan Andreev
 */
public class History implements Serializable {
    /**
     * Holds all the different entries.
     */
    private ArrayList<HistoryEntry> history;

    /**
     * Creates an empty history object.
     */
    public History() {
        history = new ArrayList<>();
    }

    /**
     * Get the full history.
     *
     * @return A list of entries in the history.
     */
    public ArrayList<HistoryEntry> getHistory() {
        return history;
    }

    /**
     * Add an entry to the history.
     *
     * @param historyEntry The entry to be added.
     */
    public void addEntry(HistoryEntry historyEntry) {
        this.history.add(historyEntry);
    }
}
