<%-- 
    Document   : editar
    Created on : 27-10-2020, 05:42:56 PM
    Author     : wwwsd
--%>
<%@page import="com.emergentes.modelo.Productos"%>
<%
    Productos productos = (Productos)request.getAttribute("productos");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Nuevo producto</h1>
        <form action="MainController" method="post">
            <table>
                <input type="hidden" name="ide" value="${productos.id}">
                <tr>
                    <td>Producto</td>
                    <td><input type="text" name="nombre_producto" value="${productos.nombre_producto}"></td>
                </tr>
                <tr>
                    <td>Precio</td>
                    <td><input type="number" name="precio" value="${productos.precio}"></td>
                </tr>
                <tr>
                    <td>Cantidad</td>
                    <td><input type="number" name="stock" value="${productos.stock}"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Enviar"/></td>
                </tr>
            </table>
        </form>
    </body>
</html>
