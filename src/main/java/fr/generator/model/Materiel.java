package fr.generator.model;

import lombok.Getter;
import lombok.Setter;

import javax.imageio.IIOException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Materiel {
    private String code;
    private String label;

    public Materiel(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public static List<Materiel> getMaterielFromFile(String path) throws IOException {
        Path filePath = Path.of(path);
        List<String> lines = Files.readAllLines(filePath);
        List<Materiel> lesMateriels = new ArrayList<>();
        for (String line: lines
             ) {
            String[] lineAsArray = line.split(" ");
            Materiel m = new Materiel(lineAsArray[0], lineAsArray[1]);
            lesMateriels.add(m);
        }
        return lesMateriels;
    }

    @Override
    public String toString(){
        return label;
    }
}
