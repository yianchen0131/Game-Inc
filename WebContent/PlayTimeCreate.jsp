<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<title>Create Playtime Record</title>
</head>
<body>
	<h1>Insert a new playtime record</h1>
	<form action="playtimecreate" method="post">
		<p>
			<label for="gameID">Game ID</label>
			<input id="gameID" name="gameID" value="">
		</p>
		<p>
			<label for="averagePlayTime">Average PlayTime</label>
			<input id="averagePlayTime" name="averagePlayTime" value="">
		</p>
		<p>
			<label for="medianPlayTime">Median PlayTime</label>
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

</html>