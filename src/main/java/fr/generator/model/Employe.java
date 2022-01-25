package fr.generator.model;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Getter
@Setter
public class Employe {
    private String nom;
    private String prenom;
    private String imgUrl;
    private String role;
    private String mdp;
    private List<Materiel> materielsEmpruntes;


    public Employe(String nom, String prenom, String imgUrl, String role, String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.imgUrl = imgUrl;
        this.role = role;
        this.mdp = mdp;
        this.materielsEmpruntes = new ArrayList<>();
    }

    public void addMateriel(Materiel materiel){
        materielsEmpruntes.add(materiel);
    }

    public String getNomHtml(){
        return prenom.toLowerCase().charAt(0) + nom.toLowerCase() + ".html";
    }

    @Override
    public String toString() {
        return "Employe{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", role='" + role + '\'' +
                ", mdp='" + mdp + '\'' +
                ", materielsEmprintes=" + materielsEmpruntes +
                '}';
    }

    public static List<Employe> getLesEmployeParFichier(String baseDir, String path, List<Materiel> lesMateriels) throws IOException {
        Path fileListEmployes = Path.of(baseDir + path);
        List<String> lines = Files.readAllLines(fileListEmployes);
        List<Employe> lesEmployes = new ArrayList<>();

        for(String line: lines){
            String[] lineAsList = Files.readString(Path.of(baseDir + line + ".txt")).split("\n\n");
            String[] infoEmploye = lineAsList[0].split("\n");
            String[] lstMaterielsEmpruntes = lineAsList[1].split("\n");
            String imgUrl = baseDir + line + ".jpg";
            Employe e = new Employe(infoEmploye[0], infoEmploye[1], imgUrl, infoEmploye[2], infoEmploye[3]);
            List<String> labelMateriels = lesMateriels.stream().map(Materiel::getCode).toList();
            int idx = 0;
            for (String materiel : lstMaterielsEmpruntes){
                if(labelMateriels.contains(materiel)){
                    e.addMateriel(lesMateriels.get(idx));
                }
                idx++;
            }
            lesEmployes.add(e);
        }

        return lesEmployes;
    }
}
