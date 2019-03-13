package com.training.spring.repository;

import com.training.spring.model.Captor;
import com.training.spring.model.Site;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("captorDao")
public interface CaptorDao extends CrudDao<Captor, String> {
    List<Captor> findBySiteId(String siteId);
}
