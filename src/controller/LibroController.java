/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bibliotecadao.dao.LibroDAO;
import bibliotecadao.dto.LibroDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rodrigo
 */
public class LibroController {
    
    private LibroDAO dao;

    public LibroController() {
        dao = new LibroDAO();
    }
    
    public ArrayList<LibroDTO> getTodos(){
        return dao.readAll();
    }
    
}
