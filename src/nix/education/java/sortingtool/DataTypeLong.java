package nix.education.java.sortingtool;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class DataTypeLong extends DataType<Integer>{

    @Override
    public void input(InputStream in) {
        Scanner scanner = new Scanner(in);
        ArrayList<String> inputList = new ArrayList<>();
        while (scanner.hasNext()) {
            inputList.add(scanner.next());
        }
        for(String s : inputList) {
            try {
                list.add(Integer.parseInt(s));
            } catch (NumberFormatException e) {
                System.out.println(s + " is not a long. It will be skipped");
            }
        }
    }

    @Override
    public Integer getLargest() {
        return Collections.max(list);
    }
}
