<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<title>Find Game Playtime by Playtime ID</title>
</head>
<body>
	<form action="findplaytime" method="post">
		<h1>Search for a playtime by playtime ID</h1>
		<p>
			 <h2> <label for="playtimeID">Playtime ID</label> </h2>
			 <!-- this is whats entered in URI as the parameter findplaytime/xxx and autofilled in form-->
			 <!-- security measures because XXX is what is suppose to be rendered on my page -->
			<input id="playtimeID" name="playtimeID" value="${fn:escapeXml(param.playtimeID)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/>
		</p>
	</form>
	
	<h3><div id="playtimecreate"></div><a href="playtimecreate">Insert new playtime record</a><div></h3>
	<br/>

	<div role="alert">
		<h2><span id="successMessage"><b>${messages.success}</b></span></h2>
	</div>


	<h1>PlayTime Results</h1>
		<table border="1"> 
			<tr>
				<th>Playtime ID</th>
				<th>Average Playtime</th>
				<th>Median Playtime</th>
				<th>Game</th>
				<th>Delete</th>
				<th>Update</th>
				
			</tr>
			<!-- object passed in setAttribute from servlet -->
			<!-- varaible p alias for the playtime.java model object that was sent from JSTL -->
			<!-- The type of the scoped variable is whatever type the value expression evaluates to. -->
			<c:set var="p" value="${playtime}" scope="request"/>
			<tr>
			<!-- if null, do nothing-->
			<c:if test="${p != null}">
					<td><c:out value="${p.getPlaytimeId()}"/></td>
					<td><c:out value="${p.getAveragePlaytime()}"/></td>
					<td><c:out value="${p.getMedianPlaytime()}"/></td>
					
					<td>
					<a href="gameread?gamename=<c:out value="${p.getGame().getGameName()}"/>">${p.getGame().getGameName()}</a>
					</td>
					<td>
					<a href="playtimedelete?playtimeID=<c:out value="${p.getPlaytimeId()}"/>">Delete</a>
					</td>
					<td>
					<a href="playtimeupdate?playtimeID=<c:out value="${p.getPlaytimeId()}"/>">Update</a>
					</td>
				</c:if>
			</tr>
		</table>

</body>
</html>





