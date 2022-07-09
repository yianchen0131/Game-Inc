<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<title>Find a Game</title>
</head>
<body>
	<form action="gameread" method="post">
		<h1>Search for a Game by GameName</h1>
		<p>
			<label for="gamename">GameName</label>
			<input id="gamename" name="gamename" value="${fn:escapeXml(param.gamename)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<div id="gameCreate"><a href="gamecreate">Create Game</a></div>
	<br/>
	<h1>Matching Game</h1>
        <table border="1">
            <tr>
                <th>GameID</th>
                <th>GameName</th>
                <th>DateReleased</th>
                <th>isEnglish</th>
                <th>Platforms</th>
                <th>RequiredAge</th>
                <th>Categories</th>
                <th>Genres</th>
                <th>NumberOfAchievement</th>
                <th>NumberOfOwners</th>
                <th>Price</th>
                <th>Delete Game</th>
                <th>Update Game</th>
            </tr>
            <c:set value="${game}" var="game" ></c:set>
                <tr>
                    <td><c:out value="${game.getGameId()}" /></td>
                    <td><c:out value="${game.getGameName()}" /></td>
                    <td><fmt:formatDate value="${game.getDateReleased()}" pattern="yyyy-MM-dd"/></td>
                    <td><c:out value="${game.getIsEnglish()}" /></td>
                    <td><c:out value="${game.getPlatforms()}" /></td>
                    <td><c:out value="${game.getRequiredAge().getValue()}" /></td>
                    <td><c:out value="${game.getCategories()}" /></td>
                    <td><c:out value="${game.getGenres()}" /></td>
                    <td><c:out value="${game.getNumberOfAchievements()}" /></td>
                    <td><c:out value="${game.getNumberOfOwners()}" /></td>
                    <td><c:out value="${game.getPrice()}" /></td>
                    <td><a href="gamedelete?gameid=<c:out value="${game.getGameId()}"/>">Delete</a></td>
                    <td><a href="gameupdate?gameid=<c:out value="${game.getGameId()}"/>">Update</a></td>
                </tr>
            
       </table>
</body>
</html>