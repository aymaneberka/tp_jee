package ma.formation.servlet.service;


import ma.formation.servlet.dao.DaoImpList;
import ma.formation.servlet.dao.IDao;
import ma.formation.servlet.service.model.Personne;

import java.util.List;


public class ServiceImpl implements IService {

    private IDao dao;

    @Override
    public void delete(Integer id) {
        dao.delete(id);
    }

    @Override
    public List<Personne> getAll() {
        return dao.getAll();
    }

    @Override
    public Personne getById(Integer id) {
        return dao.getById(id);
    }

    @Override
    public void init() {
        dao = new DaoImpList();
        dao.init();
    }

    @Override
    public void save(Personne personne) {
        dao.save(personne);
    }

}
