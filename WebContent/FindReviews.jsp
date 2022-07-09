<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<title>Find Reviews</title>
</head>
<body>

	<form action="findreviews" method="post">
		<h1>Search for Reviews by Game Name</h1>
		<p>
			<label for="gamename">Game Name</label>
			<input id="gamename" name="gamename" placeholder = "Enter a game name here" value="${fn:escapeXml(param.gamename)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<div id="reviewCreate"><a href="reviewcreate">Create a review</a></div>
	<br/>
	<h1>Matching Reviews</h1>
        <table border="1">
            <tr>
            	<th>ReviewId</th>
                <th>GameId</th>
                <th>UserId</th>
                <th>Date Posted</th>
                <th>Recommendation</th>
                <th>Content</th>
            </tr>
            <c:forEach items="${reviews}" var="review" >
                <tr>
                    <td><c:out value="${review.getReviewId()}" /></td>
                    <td><c:out value="${review.getGame().getGameId()}" /></td>
                    <td><c:out value="${review.getUser().getUserId()}" /></td>
                    <td><fmt:formatDate value="${review.getDatePosted()}" pattern="yyyy-MM-dd"/></td>
                    <td><c:out value="${review.getRecommendation().getValue()}" /></td>
                    <td><c:out value = "${review.getWrittenContent()}" /></td>
<%--                     <td><a href="userblogposts?username=<c:out value="${blogUser.getUserName()}"/>">BlogPosts</a></td>
                    <td><a href="blogcomments?username=<c:out value="${blogUser.getUserName()}"/>">BlogComments</a></td> --%>
                    <td><a href="reviewdelete?reviewid=<c:out value="${review.getReviewId()}"/>">Delete</a></td>
                    <td><a href="reviewupdate?reviewid=<c:out value="${review.getReviewId()}"/>">Update Content</a></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>