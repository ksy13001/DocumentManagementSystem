package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

public class TextFile {

    public static final String PATH = "PATH";

    private final Map<String, String> attributes;
    private final List<String> lines;

    public TextFile(File file) throws IOException {
        attributes = new HashMap<>();
        attributes.put(PATH, file.getPath());
        lines = Files.lines(file.toPath()).collect(toList());
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    int addLines(final int start, final Predicate<String> isEnd, final String attributeName){
        final StringBuilder builder = new StringBuilder();
        int lineNumber;
        for(lineNumber=start; lineNumber<lines.size(); lineNumber++){
            final String line = lines.get(lineNumber);
            if (isEnd.test(line)){
                break;
            }
            builder.append(line).append("\n");
        }
        attributes.put(attributeName, builder.toString().trim());
        return lineNumber;
    }

    void addLineSuffix(final String suffix, final String attributeName){
        for(final String line : lines){
            if(line.startsWith(suffix)){
                attributes.put(attributeName, line.substring(suffix.length()));
                break;
            }
        }
    }
}
