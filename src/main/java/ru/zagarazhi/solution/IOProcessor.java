package ru.zagarazhi.solution;

import java.io.BufferedWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.SneakyThrows;

public class IOProcessor{
    @SneakyThrows
    public static Map<String, Integer> readFile(String fileName){
        URL resource = IOProcessor.class.getClassLoader().getResource(fileName);
        Path path = Paths.get(resource.toURI());

        return Files.lines(path, StandardCharsets.UTF_8)
            .map(str -> str.replaceAll("[,]", " "))
            .map(String::trim)
            .flatMap(line -> Arrays.stream(line.split("\\r?\\n")))
            .collect(Collectors.groupingBy(str -> str))
            .entrySet()
            .stream()
            .collect(Collectors.toMap(Map.Entry::getKey, ent -> ent.getValue().size()));
    }

    public static List<String> toStringList(Map<String, Integer> map){
        return map.keySet().stream()
            .collect(Collectors.groupingBy(str -> str))
            .entrySet()
            .stream()
            .flatMap(ent -> ent.getKey().lines())
            .toList();
    }

    @SneakyThrows
    public static void writeFile(Iterable<?> iterable, String fileName){
        Path path = Paths.get("")
            .toAbsolutePath()
            .resolve("results")
            .resolve(fileName);
        Files.deleteIfExists(path);

        try(BufferedWriter bw = Files.newBufferedWriter(path)){
            for(Object elem : iterable){
                bw.write(elem.toString());
                bw.newLine();
            }
        }
    }
}
