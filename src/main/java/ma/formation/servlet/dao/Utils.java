package ma.formation.servlet.dao;

import java.util.List;

import ma.formation.servlet.service.model.Personne;

public class Utils {

    private static Utils instance;

    private Utils() {
    }

    public static Utils getInstance() {
        if (instance == null) {
            instance = new Utils();
        }
        return instance;
    }

    public static void check(Personne p) {
        if (p == null) {
            throw new DaoException("Personne null", 10);
        }
        if (p.getId() != -1 && p.getId() < 0) {
            throw new DaoException("Id [" + p.getId() + "] invalide", 11);
        }
        if (p.getDateNaissance() == null) {
            throw new DaoException("Date de naissance manquante", 12);
        }
        if (p.getNbEnfants() < 0) {
            throw new DaoException("Nombre d'enfants [" + p.getNbEnfants() + "] invalide", 13);
        }
        if (p.getNom() == null || p.getNom().trim().length() == 0) {
            throw new DaoException("Nom manquant", 14);
        }
        if (p.getPrenom() == null || p.getPrenom().trim().length() == 0) {
            throw new DaoException("Prénom manquant", 15);
        }
    }

    public static int getPosition(List<Personne> personnes, int id) {
        int rang = 0;
        for (int j = 0; j < personnes.size(); j++) {
            if (((Personne) personnes.get(j)).getId() == id) {
                return rang;
            }
            rang++;
        }
        return rang;
    }
}
