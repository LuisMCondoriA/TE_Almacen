/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.controller;

import com.emergentes.ConexionDB;
import com.emergentes.modelo.Productos;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author wwwsd
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String op;
        op = (request.getParameter("op") != null) ? request.getParameter("op") : "list";
        ArrayList<Productos> lista=new ArrayList<Productos>();
        ConexionDB canal = new ConexionDB();
        Connection conn = canal.conectar();
        PreparedStatement ps;
        ResultSet rs;
        if (op.equals("list")){
            try {
                String sql = "SELECT * FROM productos";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                
                while (rs.next()){
                    Productos lib = new Productos();
                    lib.setId(rs.getInt("id"));
                    lib.setNombre_producto(rs.getString("nombre_producto"));
                    lib.setPrecio(rs.getDouble("precio"));
                    lib.setStock(rs.getInt("stock"));
                    lista.add(lib);
                }
                
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } catch (SQLException ex){
                System.out.println("Error de SQL: "+ ex.getMessage());
            } finally {
                canal.desconectar();
            }
        }
        if (op.equals("nuevo")){
            Productos l = new Productos();
            request.setAttribute("Productos", 1);
            request.getRequestDispatcher("editar.jsp").forward(request, response);
        }
        
        if (op.equals("modificar")){
            Productos l = new Productos();
            request.setAttribute("Productos", 1);
            request.getRequestDispatcher("modificar.jsp").forward(request, response);
        }
        
        if (op.equals("eliminar")){
            
            
            int id = Integer.parseInt(request.getParameter("id"));
            try {
                String sql = "delete from productos where id = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                ps.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("Error en SQL: "+ ex.getMessage());
            } finally {
                canal.desconectar();
            }
            response.sendRedirect("MainController");
        }
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String nombre_producto = request.getParameter("nombre_producto");
        double precio = Double.parseDouble(request.getParameter("precio"));
        int stock = Integer.parseInt(request.getParameter("stock"));        
        Productos l = new Productos();
        l.setNombre_producto(nombre_producto);
        l.setPrecio(precio);
        l.setStock(stock);
           
        ConexionDB canal = new ConexionDB();
        Connection conn = canal.conectar();
        PreparedStatement ps;
        ResultSet rs;
            try {
                String sql = "insert into productos (nombre_producto,precio,stock) values (?,?,?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1, l.getNombre_producto());
                ps.setDouble(2, l.getPrecio());
                ps.setInt(3, l.getStock());
                ps.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("Error en SQL: " + ex.getMessage());
            } finally {
                canal.desconectar();
            }
     response.sendRedirect("MainController");     
    }
}
