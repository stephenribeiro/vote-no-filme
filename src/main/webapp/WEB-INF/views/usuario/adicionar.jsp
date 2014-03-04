<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Votos</title>
<link href="<c:url value="/resources/css/estilos.css" />" rel="stylesheet" />
</head>
<body>
	<h3>Informações do usuário</h3>
	<form action="computarVotos" method="post">
	<br /> 
		Nome: <input name="nome" type="text"> <br />
		Email: <input name="email" type="text"> <br /> <br />  
		<input type="submit" value="Ver Resultados">
	</form>
</body>
</html>