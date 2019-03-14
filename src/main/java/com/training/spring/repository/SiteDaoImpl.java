package com.training.spring.repository;

import com.training.spring.model.Captor;
import com.training.spring.model.Site;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SiteDaoImpl implements SiteDao {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private CaptorDao captorDao;

    @Override
    public void persist(Site element) {
        em.persist(element);
    }

    @Override
    public Site findById(String s) {
        return em.find(Site.class, s);
    }

    @Override
    public List<Site> findAll() {
        return em.createQuery("select s from Site s", Site.class)
                .getResultList();
    }

    @Override
    public void delete(Site id) {
        em.remove(id);
    }

    public void deleteAttachForeignKey(Site site){
        em.createQuery("SELECT c FROM Captor c where c.site = :site", Captor.class)
                .setParameter("site", site)
                .getResultList().forEach(c -> {
                    captorDao.deleteAttachForeignKey(c);
                    captorDao.delete(c);
                });
    }
}
