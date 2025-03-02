package ma.formation.servlet.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import ma.formation.servlet.service.model.Personne;

public class DaoImpList implements IDao {

    private List<Personne> personnes = new ArrayList<Personne>();
    private int id = -1;

    public void init() {
        try {
            Personne p1 = new Personne(-1, "Alami", "Fouad", new SimpleDateFormat("yyyy-MM-dd").parse("1977-01-01"),
                    true, 2);
            save(p1);
            Personne p2 = new Personne(-1, "Amrani", "Hassan", new SimpleDateFormat("yyyy-MM-dd").parse("1985-02-12"),
                    false, 1);
            save(p2);
            Personne p3 = new Personne(-1, "Kadiri", "Mohammed", new SimpleDateFormat("yyyy-MM-dd").parse("1986-03-01"),
                    false, 0);
            save(p3);
        } catch (ParseException ex) {
            throw new DaoException("Erreur d'initialisation de la couche [dao] : " + ex.toString(), 1);
        }
    }

    public List<Personne> getAll() {
        return personnes;
    }

    public Personne getById(Integer id) {
        int i = Utils.getPosition(personnes, id);
        if (i != -1) {
            return (Personne) personnes.get(i);
        } else {
            throw new DaoException("Personne d'id [" + id + "]inconnue", 2);
        }
    }

    public void save(Personne personne) {
        Utils.check(personne);
        if (personne.getId() == -1) {
            personne.setId(++id);
            personnes.add(personne);
            return;
        }
        int i = Utils.getPosition(personnes, personne.getId());
        if (i == -1) {
            throw new DaoException("La personne d'Id [" + personne.getId() + "] qu'on veut modifier n'existe pas", 2);
        }
        Personne personFound = (Personne) personnes.get(i);
        personFound.setNom(personne.getNom());
        personFound.setPrenom(personne.getPrenom());
        personFound.setDateNaissance((personne.getDateNaissance()));
        personFound.setMarie(personne.getMarie());
        personFound.setNbEnfants(personne.getNbEnfants());
    }

    public void delete(Integer id) {
        int i = Utils.getPosition(personnes, id);
        if (i == -1) {
            throw new DaoException("Personne d'id [" + id + "]	inconnue", 2);
        } else {
            personnes.remove(i);
        }
    }
}
