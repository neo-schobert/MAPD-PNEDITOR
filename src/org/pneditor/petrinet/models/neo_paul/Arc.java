package org.pneditor.petrinet.models.neo_paul;

/**
 * Represents an Arc in the Petri net.
 * An arc connects a place to a transition, or a transition to a place.
 */
public class Arc {

    private int weight; // The weight of the arc (number of tokens to move)
    final private Place place; // The place associated with this arc
    final private Transition transition; // The transition associated with this arc

    /**
     * Default constructor for an Arc.
     * 
     * @param weight     The weight of the arc
     * @param place      The place associated with the arc
     * @param transition The transition associated with the arc
     */
    public Arc(int weight, Place place, Transition transition) {
        this.weight = weight;
        this.place = place;
        this.transition = transition;
    }

    /**
     * Gets the place associated with this arc.
     * 
     * @return The place of this arc
     */
    public Place getPlace() {
        return this.place;
    }

    /**
     * Gets the weight of the arc.
     * 
     * @return The weight of the arc
     */
    public int getWeight() {
        return this.weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * Gets the transition associated with this arc.
     * 
     * @return The transition of this arc
     */
    public Transition getTransition() {
        return this.transition;
    }
}
