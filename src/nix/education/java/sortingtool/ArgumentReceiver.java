package nix.education.java.sortingtool;

import java.io.*;
import java.util.Objects;

public class ArgumentReceiver {
    DataType type = new DataTypeWord(); //by default, it's word
    DataType.SortingType sorting = DataType.SortingType.NATURAL;
    InputStream inputStream = System.in;
    PrintStream outputStream = System.out;

    public ArgumentReceiver(String[] args) {
        for(int i = 0; i < args.length; i+=2) {
            if(Objects.equals(args[i], "-dataType")) {
                setType(args[i + 1]);
            } else if (Objects.equals(args[i], "-sortingType")) {
                setSorting(args[i + 1]);
            }
            else if (Objects.equals(args[i], "-inputFile")) {
                setInputFile(args[i + 1]);
            }
            else if (Objects.equals(args[i], "-outputFile")) {
                setOutputFile(args[i + 1]);
            }
            else {
                System.out.println("Wrong argument " + args[i]);
                System.exit(1);
            }
        }
    }
    public void print() {
        type.input(inputStream);
        outputStream.println(type);
        type.sort(sorting, outputStream);
    }
    private void setOutputFile(String arg) {
        try {
            FileOutputStream fos = new FileOutputStream(arg);
            outputStream = new PrintStream(fos, true);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while writing to file: " + e.getMessage());
            System.exit(1);
        }
    }

    private void setInputFile(String arg) {
        try {
            File inputFile = new File(arg);
            inputStream = new FileInputStream(inputFile);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while opening the file: " + e.getMessage());
            System.exit(1);
        }
    }

    private void setSorting(String argument) {
        switch (argument) {
            case "natural" -> sorting = DataType.SortingType.NATURAL;
            case "byCount" -> sorting = DataType.SortingType.BY_COUNT;
            default -> {
                System.out.println("Wrong type argument for -sortingType");
                System.exit(1);
            }
        }
    }

    private void setType(String argument) {
        switch (argument) {
            case "long" -> type = new DataTypeLong();
            case "line" -> type = new DataTypeLine();
            case "word" -> type = new DataTypeWord();
            default -> {
                System.out.println("Wrong type argument for -dataType");
                System.exit(1);
            }
        }
    }
}
