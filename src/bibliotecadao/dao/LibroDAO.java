/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecadao.dao;

import bibliotecadao.conexion.Conexion;
import bibliotecadao.dto.LibroDTO;
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
public class LibroDAO implements Obligacion<LibroDTO> {
    
    private final String SQL_INSERT = "INSERT INTO libros(id,nombre,isbn) VALUES (null,?,?)";
    private final String SQL_DELETE = "DELETE FROM libros WHERE id = ?";
    private final String SQL_UPDATE = "UPDATE libros SET nombre = ?, isbn = ?, WHERE id = '";
    private final String SQL_SELECT = "SELECT id,nombre,isbn FROM libros WHERE id = ?";
    private final String SQL_SELECTALL = "SELECT id,nombre,isbn FROM libros";
    
    private static final Conexion conex = Conexion.estado();

    public LibroDAO() {
    }
    

    @Override
    public boolean create(LibroDTO c) {
        PreparedStatement ps;
        try {
            ps = conex.getCnn().prepareStatement(SQL_INSERT);
            ps.setString(1,c.getNombre());
            ps.setString(2, c.getIsbn());
            
            if(ps.executeUpdate() > 0) 
                return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(LibroDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            conex.closeConecxion();
        }
        return false;
    }
    
    @Override
    public boolean delete(Object key) {
        PreparedStatement ps;
        try {
            ps = conex.getCnn().prepareCall(SQL_DELETE);
            ps.setInt(1, (int) key);
            if(ps.executeUpdate() > 0)
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(LibroDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return false;
    }

    @Override
    public boolean update(LibroDTO c) {
        PreparedStatement ps;
        try {            
            ps = conex.getCnn().prepareStatement(SQL_UPDATE);
            ps.setString(1,c.getNombre());
            ps.setString(2,c.getIsbn());
            ps.setInt(3, c.getId());
            if(ps.executeUpdate() > 0)
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(LibroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            conex.closeConecxion();
        }
        return false;
    }

    @Override
    public LibroDTO read(Object key) {
        PreparedStatement ps;
        ResultSet res;
        LibroDTO l = null;
            
        try {
            ps = conex.getCnn().prepareStatement(SQL_SELECT);
            ps.setInt(1, (int) key);
            res = ps.executeQuery();
            while(res.next()){
                l = new LibroDTO(res.getInt(1),res.getString(2) , res.getString(3));
            }
            return l;
        } catch (SQLException ex) {
            Logger.getLogger(LibroDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return l;
    }
    
     @Override
    public ArrayList<LibroDTO> readAll() {
        PreparedStatement ps;
        ResultSet res;
        ArrayList<LibroDTO> libros = new ArrayList();
        
        try {
            
            ps = conex.getCnn().prepareStatement(SQL_SELECTALL);
            res = ps.executeQuery();
            while(res.next()){
                libros.add( new LibroDTO(res.getInt(1),res.getString(2),res.getString(3)) );
            }
            return libros;
        } catch (SQLException ex) {
            Logger.getLogger(LibroDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return libros;
        
    }

    @Override
    public List<LibroDTO> readRelated(Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
