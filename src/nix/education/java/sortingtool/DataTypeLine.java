package nix.education.java.sortingtool;

import java.io.InputStream;
import java.util.*;

public class DataTypeLine extends DataType<String>{
    @Override
    public void input(InputStream in) {
        Scanner scanner = new Scanner(in);
        while (scanner.hasNext()) {
            list.add(scanner.nextLine());
        }
    }

    @Override
    public String getLargest() {
        int largest = 0;
        for(int i = 0; i < list.size(); i++) {
            if (list.get(i).length() > list.get(largest).length()) {
                largest = i;
            }
        }
        return list.get(largest);
    }
}
