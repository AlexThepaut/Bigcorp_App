package com.training.spring.repository;

import com.training.spring.model.Site;

public interface SiteDao extends CrudDao<Site, String> {
    void deleteAttachForeignKey(Site site);
}
