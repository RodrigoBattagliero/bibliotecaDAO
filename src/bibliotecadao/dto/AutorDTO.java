/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecadao.dto;

/**
 *
 * @author rodrigo
 */
public class AutorDTO {
    int id;
    String nombre;
    int id_libro;

    public AutorDTO() {
    }

    public AutorDTO(int id, String nombre, int id_libro) {
        this.id = id;
        this.nombre = nombre;
        this.id_libro = id_libro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId_libro() {
        return id_libro;
    }

    public void setId_libro(int id_libro) {
        this.id_libro = id_libro;
    }
    
    
    
}
