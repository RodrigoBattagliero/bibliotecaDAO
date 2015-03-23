/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecadao.dao;

import bibliotecadao.conexion.Conexion;
import bibliotecadao.dto.ComentarioDTO;
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
public class ComentarioDAO implements Obligacion<ComentarioDTO> {
    
    private final String SQL_INSERT = "INSERT INTO comentarios(id,nombre,comentario,id_libro) VALES(null,?,?,?)";
    private final String SQL_UPDATE = "UPDATE comentarios SET nombre = ? AND comentario = ? WHERE id = ? ";
    private final String SQL_DELETE = "DELETE FROM comentarios WHERE id = ?";
    private final String SQL_SELECT = "SELECT id,nombre,comentario,id_libro FROM comentarios WHERE id = ?";
    private final String SQL_SELECTALL = "SELECT id,nombre,comentario,id_libro FROM comentarios";
    
    private static final Conexion conex = Conexion.estado();

    @Override
    public boolean create(ComentarioDTO c) {
        PreparedStatement ps;
        try {
            ps = conex.getCnn().prepareStatement(SQL_INSERT);
            ps.setString(1,c.getNombre());
            ps.setString(2, c.getComentario());
            ps.setInt(3,c.getId_libro());
            
            if(ps.executeUpdate() > 0)
                return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(ComentarioDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            ps.setInt(1,(int) key);
            if(ps.executeUpdate() > 0)
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(ComentarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return false;
    }

    @Override
    public boolean update(ComentarioDTO c) {
        PreparedStatement ps;
        try {
            // "UPDATE comentarios SET nombre = ? AND comentario = ? WHERE id = ? ";
            ps = conex.getCnn().prepareStatement(SQL_UPDATE);
            ps.setString(1,c.getNombre());
            ps.setString(2, c.getComentario());
            ps.setInt(3, c.getId());
            if(ps.executeUpdate() > 0)
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(ComentarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return false;
    }

    @Override
    public ComentarioDTO read(Object key) {
        PreparedStatement ps;
        ResultSet res;
        ComentarioDTO c = null;
        try {
            ps = conex.getCnn().prepareStatement(SQL_SELECT);
            ps.setInt(1, (int) key);
            res = ps.executeQuery();
            while(res.next()){
                c = new ComentarioDTO(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ComentarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return c;
    }

    @Override
    public List<ComentarioDTO> readAll() {
        PreparedStatement ps;
        ResultSet res;
        ArrayList comentarios = new ArrayList();
        
        try {
            ps = conex.getCnn().prepareStatement(SQL_SELECTALL);
            res = ps.executeQuery();            
            while(res.next()){
                comentarios.add(new ComentarioDTO(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4)));
            }
            return comentarios;
        } catch (SQLException ex) {
            Logger.getLogger(ComentarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.closeConecxion();
        }
        return comentarios;
    }

    @Override
    public List<ComentarioDTO> readRelated(Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
            
}
