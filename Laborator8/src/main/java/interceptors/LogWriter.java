package interceptors;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class LogWriter {

    public static boolean writtenTemporary=false;
    public static boolean writeAllTimeLog(String message)
    {
        String textToAppend = "\r\n"+message;
        Path path = Paths.get("C:\\Users\\Claudiu\\Desktop\\Master\\Java\\Laboratories-Java_Technologies\\Laborator_8\\src\\Resources\\allTimeLog.txt");
        try {
            Files.write(path, textToAppend.getBytes(), StandardOpenOption.APPEND);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    public static boolean writeTemporaryLog(String message)
    {
        String textToAppend = "\r\n"+message;
        Path path = Paths.get("C:\\Users\\Claudiu\\Desktop\\Master\\Java\\Laboratories-Java_Technologies\\Laborator_8\\src\\Resources\\temporaryLog.txt");
        try {
            if(writtenTemporary)
            {
                Files.write(path, textToAppend.getBytes(), StandardOpenOption.APPEND);
            }
            else
            {
                Files.write(path, textToAppend.getBytes(), StandardOpenOption.WRITE);
                writtenTemporary=true;
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
