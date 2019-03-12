package com.training.spring.repository;

import com.training.spring.model.Captor;
import com.training.spring.model.Site;

import java.util.List;

public interface CaptorDao extends CrudDao<Site, String> {
    List<Captor> findBySiteId(String siteId);
}
