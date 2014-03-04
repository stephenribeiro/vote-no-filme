<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<link href="<c:url value="/resources/css/estilos.css" />" rel="stylesheet" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Votos</title>
</head>
<body>
	<h3>Pesquisa: Que filme você mais gosta?</h3>

	<table id="tabelaFilmes">
		<tr>
			<c:forEach items="${filmes}" var="filme">
				<td id="nomeFilme"><a href="adicionarVoto?filme=${filme}">${filme.nome}</a></td>
			</c:forEach>
		</tr>
	</table>
</body>
</html>