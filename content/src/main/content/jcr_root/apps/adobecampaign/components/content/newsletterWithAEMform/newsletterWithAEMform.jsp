<%@include file="/apps/adobecampaign/global.jsp" %>

<div class="newsletterAEMform">
    <c:set var = "selector" scope = "page" value = "<%= slingRequest.getRequestPathInfo().getSelectors()%>"/>
    <div class="errorWrapper" style="color: #ff0000">
        <c:choose>
            <c:when test="${empty selector[0]}">

            </c:when>
            <c:when test="${selector[0] == '1'}">
                User Registered Successfully
            </c:when>
            <c:when test="${selector[0] == '2'}">
                User already Registered
            </c:when>
            <c:when test="${selector[0] == '3'}">
                Please provide the mail id to get subscribe.
            </c:when>
            <c:when test="${selector[0] == '4'}">
                You had not checked anything to get subscribed.
            </c:when>
            <c:when test="${selector[0] == '5'}">
                One of the group doesn't exist. please check and create a new group.
            </c:when>
            <c:otherwise>
                Something Went WRONG...!!!
            </c:otherwise>
        </c:choose>
    </div>
    <cq:include path="newsletterParsys" resourceType="foundation/components/parsys"/>
</div>
<div style="clear: both"></div>
