package com.esonpaguia.javadesignpatterns.soliddesignprinciples;

import java.io.File;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * SRP - Single Responsibility Principle
 *
 * @author Eson Paguia
 */
public class SingleResponsibilityPrincipleDemo {

    public static void main(String[] args) throws Exception {
        Journal j = new Journal();
        j.addEntry("I cried today");
        j.addEntry("I ate a bug");
        System.out.println(j);

        Persistence p = new Persistence();
        String filename = "/Users/eddisonpaguia/Downloads/journal.txt";
        p.saveToFile(j, filename, true);

        Runtime.getRuntime().exec(" open /Applications/TextEdit.app " + filename);
    }

}

class Journal {

    private final List<String> entries = new ArrayList<>();

    public static int count = 0;

    public void addEntry(String text) {
        entries.add("" + ++count + ": " + text);
    }

    public void removeEntry(int index) {
        entries.remove(index);
    }

    @Override
    public String toString() {
        return String.join(System.lineSeparator(), entries);
    }

    // Here we break SRP -- BEGIN
    // public void save(String filename) throws Exception {
    // try (PrintStream out = new PrintStream(filename)) {
    // out.println(toString());
    // }
    // }
    //
    // public void load(String filename) {}
    // public void load(URL url) {}
    // Here we break SRP -- END

}

// Handles the responsibility of persisting objects
class Persistence {

    public void saveToFile(Journal j, String filename, boolean overwrite) throws Exception {

        if (overwrite || new File(filename).exists())
            try (PrintStream out = new PrintStream(filename)) {
                out.println(j.toString());
            }

    }

    public void load(Journal j, String filename) {
    }

    public void load(Journal j, URL url) {
    }
}