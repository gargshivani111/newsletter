package com.forpractice.adobecampaign.services;

import org.apache.sling.api.resource.ResourceResolver;

public interface SendNewsletterService {
    public String sendNewsletter(String[] technology, String email_id, ResourceResolver resourceResolver);
}
