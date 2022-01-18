package encryptdecrypt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SecurityUtils {
    private String mode;
    private String algorithm;
    private int key;
    private String message;
    private String writeToFilePath;

    {
        mode = "enc";
        algorithm = "shift";
        key = 0;
        message = "";
        writeToFilePath = "";
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setWriteToFilePath(String writeToFilePath) {
        this.writeToFilePath = writeToFilePath;
    }

    public void processMessage() {
        SelectionContext selectionContext;
        if ("unicode".equals(algorithm)) {
            if ("dec".equals(mode)) {
                selectionContext = new SelectionContext(new UnicodeDecryptAlgorithm());
            } else {
                selectionContext = new SelectionContext(new UnicodeEncryptAlgorithm());
            }
        } else {
            if ("dec".equals(mode)) {
                selectionContext = new SelectionContext(new ShiftDecryptAlgorithm());
            } else {
                selectionContext = new SelectionContext(new ShiftEncryptAlgorithm());
            }
        }

        String processedMessage = selectionContext.process(message, key);
        writeToFile(writeToFilePath, processedMessage);
    }

    public String readFile(String readFromFilePath) {
        String msgRead = "";
        File file = new File(readFromFilePath);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                msgRead = scanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found: " + readFromFilePath);
        }
        return msgRead;
    }

    public void writeToFile(String writeToFilePath, String processedMessage) {
        if (!"".equals(writeToFilePath)) {
            File file = new File(writeToFilePath);
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(processedMessage);
                System.out.println(processedMessage);
            } catch (IOException e) {
                System.out.printf("An exception occurs %s", e.getMessage());
            }
        }
    }
}
