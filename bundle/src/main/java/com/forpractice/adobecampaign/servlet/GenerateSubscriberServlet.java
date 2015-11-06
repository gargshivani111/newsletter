package com.forpractice.adobecampaign.servlet;

import com.forpractice.adobecampaign.services.SendNewsletterService;
import org.apache.felix.scr.annotations.*;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;

import java.io.IOException;

@Component(immediate = true, metatype = false, label = "Create Subscriber")
@Service
@Properties(value = {
        @Property(name = "sling.servlet.methods", value = "POST"),
        @Property(name = "sling.servlet.paths", value = "/bin/newsletterToSubscribe")
})
public class GenerateSubscriberServlet extends SlingAllMethodsServlet {
    @Reference
    SendNewsletterService sendNewsletterService;

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) {
        String[] checkedGroupName = request.getParameterValues("newsletter_campaign");
        String email_id = request.getParameter("subscribe_email");
        ResourceResolver resourceResolver = request.getResourceResolver();
        String messageToDisplay = sendNewsletterService.sendNewsletter(checkedGroupName, email_id, resourceResolver);
        try {
            response.getWriter().write(messageToDisplay);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}