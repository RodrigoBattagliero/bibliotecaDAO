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
public class ComentarioDTO {
    
    private int id,id_libro;
    private String nombre,comentario;

    public ComentarioDTO() {
    }

    public ComentarioDTO(int id, String nombre, String comentario,int id_libro) {
        this.id = id;
        this.id_libro = id_libro;
        this.nombre = nombre;
        this.comentario = comentario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_libro() {
        return id_libro;
    }

    public void setId_libro(int id_libro) {
        this.id_libro = id_libro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

   
   
    
    
}
