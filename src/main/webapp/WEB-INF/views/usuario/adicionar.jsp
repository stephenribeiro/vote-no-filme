<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Votos</title>
</head>
<body>
    <h3>Informações do usuário</h3>
    <form action="adicionarUsuario" method="post">
      Nome: <br />
      <textarea name="nome" rows="5" cols="100"></textarea><br />
      Email: <br />
      <textarea name="email" rows="5" cols="100"></textarea><br />
      
      <input type="submit" value="Votar">
    </form>
</body>
</html>