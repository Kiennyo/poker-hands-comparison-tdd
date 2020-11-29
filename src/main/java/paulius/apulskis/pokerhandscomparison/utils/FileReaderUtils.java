package paulius.apulskis.pokerhandscomparison.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class FileReaderUtils {

    private FileReaderUtils() {
    }

    public static Stream<String> getStreamOfLines(String fileName) {
        var systemResourceAsStream = ClassLoader.getSystemResourceAsStream(fileName);
        var inputStreamReader = new InputStreamReader(systemResourceAsStream);
        return new BufferedReader(inputStreamReader).lines();
    }
}
