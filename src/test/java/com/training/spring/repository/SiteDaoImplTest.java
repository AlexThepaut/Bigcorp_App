package com.training.spring.repository;

import com.training.spring.model.Site;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan
public class SiteDaoImplTest {
    @Autowired
    private SiteDao siteDao;

    private Site site;

    @Autowired
    private EntityManager entityManager;

    @Before
    public void init(){
        site = new Site("name");
        site.setId("site1");
    }

    @Test
    public void findById() {
        Site site = siteDao.findById("site1");
        Assertions.assertThat(site.getName()).isEqualTo("Bigcorp Lyon");
    }

    @Test
    public void findByIdShouldReturnNullWhenIdUnknown() {
        Site site = siteDao.findById("unknown");
        Assertions.assertThat(site).isNull();
    }

    @Test
    public void findAll() {
        List<Site> sites = siteDao.findAll();
        Assertions.assertThat(sites)
                .hasSize(1)
                .extracting("id", "name")
                .contains(Tuple.tuple("site1", "Bigcorp Lyon"));
    }

    @Test
    public void create() {
        Assertions.assertThat(siteDao.findAll()).hasSize(1);
        siteDao.persist(new Site("New site"));
        Assertions.assertThat(siteDao.findAll()).hasSize(2);
    }

    @Test
    public void update() {
        Site site = siteDao.findById("site1");
        Assertions.assertThat(site.getName()).isEqualTo("Bigcorp Lyon");
        site.setName("site updated");
        siteDao.persist(site);
        site = siteDao.findById("site1");
        Assertions.assertThat(site.getName()).isEqualTo("site updated");
    }

    @Test
    public void deleteById() {
        Assertions.assertThat(siteDao.findAll()).hasSize(1);
        siteDao.deleteAttachForeignKey(siteDao.findById("site1"));
        siteDao.delete(siteDao.findById("site1"));
        Assertions.assertThat(siteDao.findAll()).hasSize(0);
    }

    @Test
    public void deleteByIdShouldThrowExceptionWhenIdIsUsedAsForeignKey() {
        site = siteDao.findById("site1");
        Assertions
                .assertThatThrownBy(() -> {
                    siteDao.delete(site);
                    entityManager.flush();
                })
                .isExactlyInstanceOf(PersistenceException.class)
                .hasCauseExactlyInstanceOf(ConstraintViolationException.class);
    }
}
