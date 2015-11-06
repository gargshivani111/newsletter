<%@include file="/apps/adobecampaign/global.jsp" %>
<%@page session="false"
        import="com.forpractice.adobecampaign.services.SendNewsletterService" %>

<%
    String[] technology = request.getParameterValues("newsletter_campaign");
    String email_id = request.getParameter("subscribe_email");
    SendNewsletterService service = sling.getService(SendNewsletterService.class);
    String messageToDisplay = service.sendNewsletter(technology, email_id, resourceResolver);

    if(messageToDisplay.equalsIgnoreCase("User Registered Successfully"))
        response.sendRedirect(currentPage.getPath()+".1.html");
    else
    if(messageToDisplay.equalsIgnoreCase("User already Registered"))
        response.sendRedirect(currentPage.getPath()+".2.html");
    else
    if(messageToDisplay.equalsIgnoreCase("Please provide the mail id to get subscribe."))
        response.sendRedirect(currentPage.getPath()+".3.html");
    else
    if(messageToDisplay.equalsIgnoreCase("You had not checked anything to get subscribed."))
        response.sendRedirect(currentPage.getPath()+".4.html");
    else
    if(messageToDisplay.equalsIgnoreCase("One of the group doesn't exist. please check and make the group"))
        response.sendRedirect(currentPage.getPath()+".5.html");
    else
        response.sendRedirect(currentPage.getPath()+".html");
%>
