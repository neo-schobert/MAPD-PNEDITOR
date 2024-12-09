package org.pneditor.petrinet.models.neo_paul;

/**
 * Represents an outgoing arc (from transition to place).
 */
public class OutgoingArc extends Arc {

    /**
     * Default constructor for an outgoing arc.
     * 
     * @param weight     The weight of the arc
     * @param place      The associated place
     * @param transition The associated transition
     */
    public OutgoingArc(int weight, Place place, Transition transition) {
        super(weight, place, transition);
    }

    /**
     * Performs the action of firing the arc (adds tokens to the place).
     */
    public void fire() {
        Place place = getPlace();
        place.addToken(getWeight());
    }
}
