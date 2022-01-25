package fr.generator.utils;

import fr.generator.html.Formatter;
import fr.generator.model.Employe;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static j2html.TagCreator.*;
import static j2html.TagCreator.h2;

import static j2html.TagCreator.*;

public class Utilitaire {

    public static void CreateAgentsListPage(List<Employe> lesEmployes) throws IOException {
        String Content = div(attrs("#employees"),
                h1("Liste d'employés : "),
                each(lesEmployes, employee ->
                        div(attrs(".employee"),
                                a().withHref("/" + employee.getNomHtml()).with(
                                        h2(employee.getNom() + " " + employee.getPrenom()))
                        )
                )
        ).render();

        FileWriter agents = new FileWriter("./web/agents.html");
        agents.write(Formatter.toHtml("./templates/agents.html", "Agents", Content));
        agents.close();
    }

    public static void CreateEachAgentsPage(List<Employe> lesEmployes) throws IOException{
        for(Employe employe : lesEmployes){
            FileWriter file = new FileWriter("./web/" + employe.getNomHtml());
            String content = div(attrs("#employe"),
                    h1(employe.getNom() + " " + employe.getPrenom()),
                    h2("Les matériels empruntés : "),
                    each(employe.getMaterielsEmpruntes(), materiel ->
                            div(attrs(".materiel"),
                                    h3(materiel.getLabel())
                            )
                    )
            ).render();

            file.write(Formatter.toHtml("./templates/details.html", employe.getNom(), content));
            file.close();
        }
    }
}
