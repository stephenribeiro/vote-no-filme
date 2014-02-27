<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Votos</title>
</head>
<body>
	<a href="novoVoto">Votar Novamente</a>

	<br />
	<br />

	<table>
		<tr>
			<th>Id</th>
			<th>Filme</th>
			<th>Usuario</th>
		</tr>
		<c:forEach items="${votos}" var="voto">
			<tr>
				<td>${voto.id}</td>
				<td>${voto.filme.nome}</td>
				<td>${voto.usuario.nome}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>