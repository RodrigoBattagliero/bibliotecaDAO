/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bibliotecadao.dao.AutorDAO;
import bibliotecadao.dto.AutorDTO;
import java.util.ArrayList;

/**
 *
 * @author rodrigo
 */
public class AutorController {
    
    AutorDAO Autor;

    public AutorController() {
        Autor = new AutorDAO();
    }
    
    public ArrayList<AutorDTO> getTodos(){
        return Autor.readAll();
    }
    
    public ArrayList<AutorDTO> getRelacionados(int key){
        return Autor.readRelated(key);
    }
    
    
}
