package com.adventofcode;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class ResourceUtils {

    public static String read(String resource) {
        try {
            return IOUtils.toString(ResourceUtils.class.getClassLoader().getResourceAsStream(resource), UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Error reading resource", e);
        }
    }

    public static List<String> readLines(String resource) {
        try {
            return IOUtils.readLines(ResourceUtils.class.getClassLoader().getResourceAsStream(resource), UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Error reading resource", e);
        }
    }

}
