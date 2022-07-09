<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<title>Update Playtime</title>
</head>
<body>
	<h1>Update Playtime hours</h1>
	<form action="playtimeupdate" method="post">
		<p>
			<label for="playtimeID">Playtime ID</label>
			<input id="playtimeID" name="playtimeID" value="${fn:escapeXml(param.playtimeID)}">
			<!-- req.getParameter	 -->
		</p>
		<p>
		<!-- new data for average playtime -->
			<label for="averagePlayTime">New Average PlayTime</label>
			<input id="averagePlayTime" name="averagePlayTime" value="">
			
			<label for="medianPlayTime">New Median PlayTime</label>
			<input id="medianPlayTime" name="medianPlayTime" value="">
		
		</p>
		<p>
			<input type="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
	

</body>
</html>