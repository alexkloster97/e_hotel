/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.hotel.entity;

/**
 * Abstract class Entity
 * @author Maria
 */
public abstract class Entity {
    private int id;

    /**
     * Constructor with parameters
     * @param id
     */
    public Entity(int id) {
        this.id = id;
    }

    /**
     *Constructor without parameters
     */
    public Entity() {
    }

    /**
     * This method is used to return id 
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * This method is used to set id
     * @param id - id
     */
    public void setId(int id) {
        this.id = id;
    }
    
    
}
