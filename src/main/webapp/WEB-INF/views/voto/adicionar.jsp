<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Votos</title>
</head>
<body>
    <h3>Vote no filme</h3>
    <form action="adicionarVoto" method="post">
      Descrição: <br />
      <textarea name="descricao" rows="5" cols="100"></textarea><br />
      
      <input type="submit" value="Votar">
    </form>
</body>
</html>