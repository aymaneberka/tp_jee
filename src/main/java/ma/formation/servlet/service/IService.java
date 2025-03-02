package ma.formation.servlet.service;


import ma.formation.servlet.service.model.Personne;

import java.util.List;
public interface IService {
    void init();
    List<Personne> getAll();
    Personne getById(Integer id);
    void save(Personne personne);
    void delete(Integer id);
}

