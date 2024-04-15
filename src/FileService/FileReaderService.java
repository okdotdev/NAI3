package FileService;

import java.io.*;
import java.util.*;

public class FileReaderService {


    public static String readFile(String fileAddress) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileAddress));
            String line;
            while ((line = bufferedReader.readLine()) != null)
                stringBuilder.append(line);
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static List<String> getListOfSubdirectories(String directoryAddress) {
        return Arrays.asList(Objects.requireNonNull(new File(directoryAddress).list((current, name) -> new File(current, name).isDirectory())));
    }

    public static List<String> getListOfFilesInDirectory(String directoryAddress) {
        return Arrays.asList(Objects.requireNonNull(new File(directoryAddress).list()));
    }

}
