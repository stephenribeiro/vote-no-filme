<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Rank Votos</title>
<link href="<c:url value="/resources/css/estilos.css" />" rel="stylesheet" />
</head>
<body>
	<h3>Pesquisa: Que filme você mais gosta?</h3>

	<table id="tabelaEsquerda">
		<tr>
			<th>Rank <c:out value="${usuario.nome}"/></th>
		</tr>
		<c:forEach items="${rankUsuario}" var="filme">
			<tr>
				<td>${filme.nome}</td>
			</tr>
		</c:forEach>
	</table>
	
	<br />
	
	<table id="tabelaDireita">
		<tr>
			<th>Rank Geral</th>
		</tr>
		<c:forEach items="${rankGeral}" var="filme">
			<tr>
				<td>${filme.nome}</td>
			</tr>
		</c:forEach>
	</table>
	
	<a id="rodape" href="novoVoto">Votar Novamente</a>

</body>
</html>