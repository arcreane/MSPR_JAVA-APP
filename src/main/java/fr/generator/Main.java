package fr.generator;

import fr.generator.model.Employe;
import fr.generator.model.Materiel;
import fr.generator.utils.Utilitaire;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        System.out.println("Hello");

        //System.out.println(Formatter.toHtml("./templates/base.html", "test", "Hello World !"));

        List<Materiel> lesMateriels = Materiel.getMaterielFromFile("./db/liste.txt");

        List<Employe> lesEmployes = Employe.getLesEmployeParFichier("./db/", "staff.txt", lesMateriels);
        for(Employe e : lesEmployes){
            System.out.println(e);
        }

        Utilitaire.CreateAgentsListPage(lesEmployes);
        Utilitaire.CreateEachAgentsPage(lesEmployes);
        //Utilitaire.test(lesEmployes);
    }
}