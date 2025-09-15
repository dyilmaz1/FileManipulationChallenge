import java.io.*;

public class FileHandlingActivity {
    public static void main(String[] args) {
        // Your code here

        // a. Create main directory
        File mainDir = new File("JavaFileSystem");
        if (!mainDir.exists()) {
            mainDir.mkdir();
        }
        // b. Create three text files
        File notesFile = new File(mainDir, "notes.txt");
        File dataFile = new File(mainDir, "data.txt");
        File logFile = new File(mainDir, "log.txt");
        try {
            notesFile.createNewFile();
            dataFile.createNewFile();
            logFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // c. Write messages to files
        try (FileWriter notesWriter = new FileWriter(notesFile)) {
            notesWriter.write("Welcome to your notes!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileWriter dataWriter = new FileWriter(dataFile)) {
            dataWriter.write("Data file initialized.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileWriter logWriter = new FileWriter(logFile)) {
            logWriter.write("Log started.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // d. Read and display file contents
        try (BufferedReader notesReader = new BufferedReader(new FileReader(notesFile))) {
            System.out.println("notes.txt: " + notesReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader dataReader = new BufferedReader(new FileReader(dataFile))) {
            System.out.println("data.txt: " + dataReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader logReader = new BufferedReader(new FileReader(logFile))) {
            System.out.println("log.txt: " + logReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // e. Create backup directory
        File backupDir = new File("JavaFileSystemBackup");
        if (!backupDir.exists()) {
            backupDir.mkdir();
        }

        // f. Copy contents to backup file
        File[] filesToCopy = mainDir.listFiles();
        if (filesToCopy != null) {
            for (File file : filesToCopy) {
                File backupFile = new File(backupDir, file.getName());
                try (
                        FileInputStream in = new FileInputStream(file);
                        FileOutputStream out = new FileOutputStream(backupFile)) {
                    out.write(in.readAllBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // g. List all files in both directories
        System.out.println("Files in JavaFileSystem:");
        File[] mainFiles = mainDir.listFiles();
        if (mainFiles != null) {
            for (File f : mainFiles) {
                System.out.println(" - " + f.getName());
            }
        }

        System.out.println("Files in JavaFileSystemBackup:");
        File[] backupFiles = backupDir.listFiles();
        if (backupFiles != null) {
            for (File f : backupFiles) {
                System.out.println(" - " + f.getName());
            }
        }

        // Debug time
        debugFileOperation();
    }
    
    public static void debugFileOperation() {
        try {
            // Creating a file with an invalid name (forward slash is invalid for file names on many OS)
            File file = new File("fileName.txt");
            
            // Attempting to write to the invalid file
            FileWriter writer = new FileWriter(file);
            writer.write("Will this fail?");
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace(); // Critical error that will terminate the program
        }
    }
}