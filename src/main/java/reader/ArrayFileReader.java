package reader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class methods return file read data
 * (Collection format)
 * <p>
 * Decimal file data format: "." and "," are allowed
 */
public class ArrayFileReader {

    public ArrayFileReader() {
    }

    /**
     * Get values from file (double)
     * Throws NumberFormatException if read data are not double format
     */
    public List<Double> getDoubleList(File file) throws IOException, NumberFormatException {
        List<Double> dataFromFile = new ArrayList<>();

        try (FileReader reader = new FileReader(file)) {
            Scanner scan = new Scanner(reader);
            while (scan.hasNextLine()) {
                String s = scan.nextLine();

                if (s.contains(",")) s = s.replace(",", ".");
                if (s.isEmpty()) continue;
                dataFromFile.add(Double.parseDouble(s));
            }
            return dataFromFile;
        }
    }
}
