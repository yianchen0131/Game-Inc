<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<title>Create a Game</title>
</head>
<body>
	<h1>Create Game</h1>
	<form action="gamecreate" method="post">
		<p>
			<label for="gameid">GameID</label>
			<input id="gameid" name="gameid" value="">
		</p>
		<p>
			<label for="gamename">GameName</label>
			<input id="gamename" name="gamename" value="">
		</p>
		<p>
			<label for="datereleased">DateReleased (yyyy-mm-dd)</label>
			<input id="datereleased" name="datereleased" value="">
		</p>
		<p>
			<label for="isenglish">isEnglish</label>
			<input id="isenglish" name="isenglish" value="">
		</p>
		<p>
			<label for="platforms">Platforms</label>
			<input id="platforms" name="platforms" value="">
		</p>
		<p>
			<label for="requiredage">RequiredAge (choose from: 0, 16, 18, 12, 7, 3)</label>
			<input id="requiredage" name="requiredage" value="">
		</p>
		<p>
			<label for="categories">Categories</label>
			<input id="categories" name="categories" value="">
		</p>
		<p>
			<label for="genres">Genres</label>
			<input id="genres" name="genres" value="">
		</p>
		<p>
			<label for="numberofachievements">NumberOfAchievements</label>
			<input id="numberofachievements" name="numberofachievements" value="">
		</p>
		<p>
			<label for="numberofowners">NumberOfOwners</label>
			<input id="numberofowners" name="numberofowners" value="">
		</p>
		<p>
			<label for="price">Price</label>
			<input id="price" name="price" value="">
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














