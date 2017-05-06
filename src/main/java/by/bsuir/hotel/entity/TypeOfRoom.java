/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.hotel.entity;

/**
 * This class contains information about type of room
 * @author Maria
 */
public class TypeOfRoom extends Entity{
    private String type;
    private String description;

    /**
     *Constructor without parameters
     */
    public TypeOfRoom() {
    }

    /**
     * Constructor with parameters
     * @param type
     * @param description
     * @param id
     */
    public TypeOfRoom(String type, String description, int id) {
        super(id);
        this.type = type;
        this.description = description;
    }

    /**
     * This method is used to return description of type 
     * @return description of type
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method is used to set description of type
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method is used to return type of room
     * @return type of room
     */
    public String getType() {
        return type;
    }

    /**
     * This method is used to set type of room
     * @param type - type of room
     */
    public void setType(String type) {
        this.type = type;
    }
    
}
