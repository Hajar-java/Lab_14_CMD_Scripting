import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileScan {
    public static void main(String[] args) {
        File fileToScan = null;

        if (args.length > 0) {
            fileToScan = new File(args[0]);
            if (!fileToScan.exists()) {
                System.out.println("File not found: " + args[0]);
                return;
            }
        } else {
            JFileChooser chooser = new JFileChooser(".");
            int result = chooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                fileToScan = chooser.getSelectedFile();
            } else {
                System.out.println("No file selected.");
                return;
            }
        }

        int lineCount = 0;
        int wordCount = 0;
        int charCount = 0;

        try (Scanner fileScanner = new Scanner(fileToScan)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                System.out.println(line);

                lineCount++;
                String[] words = line.trim().split("\\s+");
                if (!line.trim().isEmpty()) {
                    wordCount += words.length;
                }
                charCount += line.length();
            }

            System.out.println("\n--- File Summary ---");
            System.out.println("File: " + fileToScan.getName());
            System.out.println("Lines: " + lineCount);
            System.out.println("Words: " + wordCount);
            System.out.println("Characters: " + charCount);

        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found or unreadable.");
        }
    }
}