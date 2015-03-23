/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecadao.dao;

import bibliotecadao.conexion.Conexion;
import bibliotecadao.dto.AutorDTO;
import interfaces.Obligacion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rodrigo
 */
public class AutorDAO implements Obligacion<AutorDTO> {
    
    private final String SQL_INSERT = "INSERT INTO autor(id,nombre,id_libro) VALUES(null,?,?)";
    private final String SQL_UPDATE = "UPDATE autor SET nombre = ? AND id_libro = ? WHERE id = ?";
    private final String SQL_DELETE = "DELETE FROM autor WHERE id = ?";
    private final String SQL_SELECT = "SELECT id,nombre,id_libro FROM autor WHERE id = ?";
    private final String SQL_SELECTALL = "SELECT id,nombre,id_libro FROM autor";
    private final String SQL_SELECTRELATED = "SELECT id,nombre,id_libro FROM autor WHERE id_libro = ?";
    
    private static final Conexion conex = Conexion.estado();
    
    @Override
    public boolean create(AutorDTO c) {
        PreparedStatement ps;
        
        try {
            ps = conex.getCnn().prepareStatement(SQL_INSERT);
            ps.setString(1,c.getNombre());
            ps.setInt(2, c.getId_libro());
            if(ps.executeUpdate() > 0)
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(AutorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return false;
        
    }

    @Override
    public boolean delete(Object key) {
        PreparedStatement ps;
        try {
            ps = conex.getCnn().prepareStatement(SQL_DELETE);
            ps.setInt(1, (int) key);
            if(ps.executeUpdate() > 0)
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(AutorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return false;
        
    }

    @Override
    public boolean update(AutorDTO c) {
         PreparedStatement ps;
        
        try {
            ps = conex.getCnn().prepareStatement(SQL_UPDATE);
            ps.setString(1,c.getNombre());
            ps.setInt(2, c.getId_libro());
            ps.setInt(3, c.getId());
            if(ps.executeUpdate() > 0)
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(AutorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return false;
    }

    @Override
    public AutorDTO read(Object key) {
        PreparedStatement ps;
        ResultSet res;
        AutorDTO a = null;
        try {
            ps = conex.getCnn().prepareStatement(SQL_SELECT);
            ps.setInt(1, (int) key);
           res = ps.executeQuery();
           while(res.next()){
               a = new AutorDTO(res.getInt(1),res.getString(2),res.getInt(3));
           }
           return a;
        } catch (SQLException ex) {
            Logger.getLogger(AutorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return a;
    }

    @Override
    public ArrayList<AutorDTO> readAll() {
        PreparedStatement ps;
        ResultSet res;
        ArrayList Autores = new ArrayList();
        try {
            ps = conex.getCnn().prepareStatement(SQL_SELECTALL);
            res = ps.executeQuery();
            while(res.next()){
                Autores.add(new AutorDTO(res.getInt(1), res.getString(2), res.getInt(3)));
            }
            return Autores;
        } catch (SQLException ex) {
            Logger.getLogger(AutorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return Autores;
    }

    @Override
    public ArrayList<AutorDTO> readRelated(Object key) {
        PreparedStatement ps;
        ResultSet res;
        ArrayList l = new ArrayList();
        try {
            ps = conex.getCnn().prepareStatement(SQL_SELECTRELATED);
            ps.setInt(1, (int) key);
            res = ps.executeQuery();
            while(res.next()){
                l.add(new AutorDTO(res.getInt(1),res.getString(2),res.getInt(3)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AutorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }
    
    
}
