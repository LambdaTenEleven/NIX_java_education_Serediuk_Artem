package nix.education.java.sortingtool;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;

/**
 * Abstract class for -dataType param, implementing Strategy pattern
 * @param <T> any comparable type
 */
public abstract class DataType<T extends Comparable<T>> {
    enum SortingType{
        NATURAL, BY_COUNT
    }

    ArrayList<T> list = new ArrayList<>();

    /**
     * The way the program gets its input
     */
    public abstract void input(InputStream in);

    public ArrayList<T> getList() {
        return list;
    }

    /**
     * Get array count
     * @return array size
     */
    public int getCount() {
        return list.size();
    }

    /**
     * Get the largest object. Size will be determined by its type
     * @return the largest object
     */
    public abstract T getLargest();

    /**
     * How many times the larges object appeared in array
     * @return frequency of the largest object
     */
    public int getLargestCount() {
        return Collections.frequency(list, getLargest());
    }

    /**
     * Same as getLargestCount() but in percents (0-100)
     * @return percent of frequency of the largest object
     */
    public int getLargestCountPercent() {
        return getLargestCount() * 100 / getCount();
    }

    /**
     * Sorts the list by order or frequency
     * @param type by order or frequency
     */
    public void sort(SortingType type, PrintStream outputStream) { // WHY THERE'S NO DEFAULT PARAMETERS IN JAVA????
        if(type == SortingType.NATURAL) {
            Collections.sort(list);
            outputStream.println(list);
        } else if(type == SortingType.BY_COUNT) {
            sortByCount(outputStream);
        }
    }

    private void sortByCount(PrintStream outputStream) {
        Map<T, Integer> map = new HashMap<>();
        for (T s : list) {
            if (map.containsKey(s)) {
                map.put(s, map.get(s) + 1);
            } else {
                map.put(s, 1);
            }
        }
        ArrayList<Map.Entry<T, Integer>> listOfEntry = new ArrayList(map.entrySet());
        listOfEntry.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        for (Map.Entry<T, Integer> entry : listOfEntry)
        {
            outputStream.println(entry.getKey() + ": " + entry.getValue() + " time(s), " +
                    (entry.getValue() * 100 / list.size()) + "%");
        }
    }

    @Override
    public String toString() {
        return "Total items: " + getCount() + "\nThe greatest item: " + getLargest() + " (" + getLargestCount() +
                " times, " + getLargestCountPercent() + "%)";
    }
}
