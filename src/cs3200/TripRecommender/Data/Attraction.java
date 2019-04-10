package cs3200.TripRecommender.Data;

import java.util.HashMap;

/**
 * Represents an attraction in Trip4U database.
 */
public class Attraction {

    private int id;
    private String name;
    private String description;
    private int destinationID;
    private int costID;
    private int typeID;

    /**
     * Getter for id of this attraction.
     * @return the id of this attraction.
     */
    public int getId() {
        return id;
    }

    /**
     * Set the id for this attraction.
     * @param id the id to be set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the name of this attraction.
     * @return the name of this attraction.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of this attraction.
     * @param name the name to be set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the description of this attraction.
     * @return the description of this attraction.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description of this attraction.
     * @param description the description to be set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the id of this destination of this attraction.
     * @return the id of this attraction's destination.
     */
    public int getDestinationID() {
        return destinationID;
    }

    /**
     * Set the id of the destination of this attraction.
     * @param destinationID the id to be set.
     */
    public void setDestinationID(int destinationID) {
        this.destinationID = destinationID;
    }

    /**
     * Get the id of the cost of this attraction.
     * @return the id of the cost of this attraction.
     */
    public int getCostID() {
        return costID;
    }

    /**
     * Set the id of the cost of this attraction.
     * @param costID the id to be set.
     */
    public void setCostID(int costID) {
        this.costID = costID;
    }

    /**
     * Get the id of the type of this attraction.
     * @return the id of the type of this attraction.
     */
    public int getTypeID() {
        return typeID;
    }

    /**
     * Set the id of the type of this attraction.
     * @param typeID the id to be set.
     */
    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }


    public String toString(HashMap<Integer, String> costMap) {
        String costDesc = costMap.get(this.costID);
        return "Name: " + this.name + "\nDescription: "
                + this.description + "\nCost: " + costDesc;
    }
}
