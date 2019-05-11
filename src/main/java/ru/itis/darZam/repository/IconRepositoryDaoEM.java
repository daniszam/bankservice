package ru.itis.darZam.repository;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.itis.darZam.models.Icon;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class IconRepositoryDaoEM implements RepositoryDao<Icon> {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    EntityManager em;

    @Autowired
    public IconRepositoryDaoEM(EntityManagerFactory emf) {
        em = emf.createEntityManager();
    }

    @Override
    public Optional<Icon> findOne(Long id) {
        Icon icon = em.find(Icon.class, id);
        return Optional.ofNullable(icon);
    }

    @Override
    public void save(Icon model) {
        em.getTransaction().begin();
        em.persist(model);
        em.getTransaction().commit();

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Icon> findAll() {
        List<Icon> icons = sessionFactory
                .openSession()
                .createQuery("FROM Icon").list();
        return icons;
    }

    @Override
    public void update(Icon model) {

    }
}
