package paulius.apulskis.pokerhandscomparison.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

public class FileReaderUtils {

    private FileReaderUtils() {
    }

    public static Stream<String> getStreamOfLines(String fileName) {
        try {
            return Files.lines(Objects.requireNonNull(getPath(fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Path getPath(String fileName) {
        try {
            return Paths.get(ClassLoader.getSystemResource(fileName).toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
