<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<title>Create a Review</title>
</head>
<body>
	<h1>Write a Review</h1>
	<form action="reviewcreate" method="post">
		<p>
			<label for="gamename">GameName</label>
			<input id="gamename" name="gamename" value="">
		</p>
		<p>
			<label for="userid">User ID</label>
			<input id="userid" name="userid" value="">
		</p>
		<p>
			<label for="hourlyPlayed">How many hours you have played this game?</label>
			<input id="hourlyPlayed" name="hourlyPlayed" value="">
		</p>
		<p>
			<label for="isearly">Is early accessed?</label>
			<input type="checkbox" id="isearly" name="isearly" value="true">
			<label for="isearly">Yes</label>
			<input type="checkbox" id="isearly" name="isearly" value="false">
			<label for="isearly">No</label>
		</p>
		<p>
			<label for="recommendation">Do you recommend it?</label>
			<input type="checkbox" id="recommendation" name="recommendation" value="Recommended">
			<label for="recommendation">Yes</label>
			<input type="checkbox" id="recommendation" name="recommendation" value="NotRecommended">
			<label for="recommendation">No</label>
		</p>
		<p>
			<label for="content">Review</label><br>
			<textarea id = "content" name = "content">Your review goes here</textarea>
		</p>
		<p>
			<input type="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
	<p>
		<a href = "/GameCrossing/findreviews">go back</a>
	</p>
	
</body>
</html>