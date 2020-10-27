

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="com.emergentes.modelo.Productos"%>
<%
    List<Productos> lista = (List<Productos>) request.getAttribute("lista");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>index</title>
    </head>
    <body>
        <h1>Productos</h1>
        <p><a href="MainController?op=nuevo">Nuevo</a></p>
        <table border="1">
            <tr>
                <th>Id</th>
                <th>Producto</th>
                <th>Precio</th>
                <th>Cantidad</th>
                <th></th>
                <th></th>
                
            </tr>
            <c:forEach var="item" items="${lista}">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.nombre_producto}</td>
                    <td>${item.precio}</td>
                    <td>${item.stock}</td>
                    <td><a href="MainController?op=eliminar&id=${item.id}">Eliminar</a></td>
                    <td><a href="">Editar</a></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
