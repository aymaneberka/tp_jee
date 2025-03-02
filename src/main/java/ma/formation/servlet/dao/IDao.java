package ma.formation.servlet.dao;

import ma.formation.servlet.service.model.Personne;

import java.util.List;
public interface IDao {
    void init();
    List<Personne> getAll();
    Personne getById(Integer id);
    void save(Personne personne);
    void delete(Integer id);
}

