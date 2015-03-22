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
public class LibroDTO {
    private int id;
    private String nombre, isbn;

    public LibroDTO() {
    }

    public LibroDTO(int id) {
        this.id = id;
    }

    public LibroDTO(int id, String nombre, String isbn) {
        this.id = id;
        this.nombre = nombre;
        this.isbn = isbn;
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    
}
