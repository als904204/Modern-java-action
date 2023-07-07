package part1.ch01;

import java.io.File;
import java.io.FileFilter;

public class Main {
    public static void main(String[] args) {
        File[] hiddenFiles = new File(".").listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isHidden();
            }
        });

        File[] streamFiles = new File(".").listFiles(File::isHidden);
    }
}
