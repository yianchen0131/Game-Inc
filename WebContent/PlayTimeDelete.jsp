<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<title>Delete Playtime record</title>
</head>
<body>
	<h1>${messages.title}</h1>
	<form action="playtimedelete" method="post">
		<p>
			<!-- if the submit field is emtpym we do a dynamic rendering and hide the form -->
			<div <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
				<label for="playtimeID">Playtime ID</label>
				<input id="playtimeID" name="playtimeID" value="${fn:escapeXml(param.playtimeID)}">
			</div>
		</p>
		<p>
			<span id="submitButton" <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
			<input type="submit">
			</span>
		</p>
	</form>
	<br/><br/>
</body>
</html>