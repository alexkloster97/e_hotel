/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.hotel.entity;

/**
 * This class contains information about usergroup
 * @author Maria
 */
public class UserGroup extends Entity{
    private String group;

    /**
     *Constructor without parameters
     */
    public UserGroup() {
    }

    /**
     * Constructor with parameters
     * @param group
     * @param id
     */
    public UserGroup(String group, int id) {
        super(id);
        this.group = group;
    }

    /**
     * This method is used to return usergroup
     * @return usergroup
     */
    public String getGroup() {
        return group;
    }

    /**
     * This method is used to set usergroup
     * @param group - usergroup
     */
    public void setGroup(String group) {
        this.group = group;
    }
    
    
}
