package com.training.spring.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Set;

public class ApplicationInfo {

    private String name;
    private Integer version;
    private Set<String> emails;
    private String webSiteUrl;

    public ApplicationInfo(String name, Integer version, Set<String> emails, String webSiteUrl) {
        this.name = name;
        this.version = version;
        this.emails = emails;
        this.webSiteUrl = webSiteUrl;
    }

    public String getName() {
        return name;
    }

    public Integer getVersion() {
        return version;
    }

    public Set<String> getEmails() {
        return emails;
    }

    public String getWebSiteUrl() {
        return webSiteUrl;
    }
}
