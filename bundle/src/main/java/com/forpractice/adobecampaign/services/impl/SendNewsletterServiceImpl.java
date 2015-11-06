package com.forpractice.adobecampaign.services.impl;


import com.forpractice.adobecampaign.services.SendNewsletterService;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.jackrabbit.api.security.user.*;
import org.apache.sling.api.resource.ResourceResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.*;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.nodetype.NoSuchNodeTypeException;
import javax.jcr.version.VersionException;
import java.util.Iterator;

@Component(metatype = true)
@Service(value= {SendNewsletterService.class})
@org.apache.felix.scr.annotations.Properties({
        @Property(name = "service.vendor", value = "Havells India"),
        @Property(name = "service.description", value = "Sending an Email to the users.")
})
public class SendNewsletterServiceImpl implements SendNewsletterService {

    protected static final Logger logger = LoggerFactory.getLogger(SendNewsletterServiceImpl.class);

    @Override
    public String sendNewsletter(String[] checkedGroupName, String email_id, ResourceResolver resourceResolver) {

        boolean flag = true;
        String messageToDisplay = "";
        User subscriberUser;

        try {
            Session session = resourceResolver.adaptTo(Session.class);
            ValueFactory valueFactory = session.getValueFactory();
            if(checkedGroupName != null){
                if(email_id != "" && resourceResolver != null){
                    for(String check : checkedGroupName){
                        UserManager userManager = resourceResolver.adaptTo(UserManager.class);
                        if (userManager != null) {
                            Group group = resourceResolver.resolve("/home/groups/n/newsletter-"+check).adaptTo(Group.class);
                            if(group != null){

                                Iterator<Authorizable> authorizableIterator = group.getMembers();
                                while (authorizableIterator.hasNext()) {
                                    if (authorizableIterator.next().getPrincipal().getName().equals(email_id)) {
                                        messageToDisplay = "User already Registered";
                                        flag = false;
                                        break;
                                    }
                                }
                                if (flag) {
                                    Authorizable user = userManager.getAuthorizable(email_id);
                                    if(user == null) {
                                        System.out.println(true);
                                        subscriberUser = userManager.createUser(email_id, RandomStringUtils.randomAlphanumeric(20).toUpperCase());
                                        subscriberUser.setProperty("cq:authorizableCategory", valueFactory.createValue("mcm"));
                                        subscriberUser.setProperty("profile/email", valueFactory.createValue(email_id));
                                    }
                                    else{
                                        subscriberUser = (User) userManager.getAuthorizable(email_id);
                                    }
                                    group.addMember(subscriberUser);
                                    session.save();
                                    if (subscriberUser != null) {
                                        messageToDisplay = "User Registered Successfully";
                                    }
                                }
                            }  // create group
                            //  group = userManager.createGroup("newsletter-"+check);
                            //  group.setProperty("cq:authorizableCategory", valueFactory.createValue("mcm"));
                            //  session.save();
                            else{
                                messageToDisplay = "One of the group doesn't exist. please check and make the group";
                                break;
                            }
                        }
                    }
                }
                else{
                    messageToDisplay = "Please provide the mail id to get subscribe.";
                }
            }
            else{
                messageToDisplay = "You had not checked anything to get subscribed.";
            }
            return messageToDisplay;
        } catch (NullPointerException nullPointerException) {
            logger.error(nullPointerException.getMessage());
        } catch (AuthorizableExistsException authorizableExistsException) {
            logger.error(authorizableExistsException.getMessage());
        } catch (VersionException versionException) {
            logger.error(versionException.getMessage());
        } catch (ReferentialIntegrityException referentialIntegrityException) {
            logger.error(referentialIntegrityException.getMessage());
        } catch (LockException lockException) {
            logger.error(lockException.getMessage());
        } catch (ConstraintViolationException constraintViolationException) {
            logger.error(constraintViolationException.getMessage());
        } catch (UnsupportedRepositoryOperationException unsupportedRepositoryOperationException) {
            logger.error(unsupportedRepositoryOperationException.getMessage());
        } catch (InvalidItemStateException invalidItemStateException) {
            logger.error(invalidItemStateException.getMessage());
        } catch (ItemExistsException itemExistsException) {
            logger.error(itemExistsException.getMessage());
        } catch (NoSuchNodeTypeException noSuchNodeTypeException) {
            logger.error(noSuchNodeTypeException.getMessage());
        } catch (AccessDeniedException accessDeniedException) {
            logger.error(accessDeniedException.getMessage());
        } catch (RepositoryException repositoryException) {
            logger.error(repositoryException.getMessage());
        }
        return messageToDisplay;
    }

}
