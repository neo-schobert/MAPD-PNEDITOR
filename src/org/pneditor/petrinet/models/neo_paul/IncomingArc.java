package org.pneditor.petrinet.models.neo_paul;

/**
 * Represents an incoming arc (from place to transition).
 */
public class IncomingArc extends Arc {

    /**
     * Default constructor for an incoming arc.
     * 
     * @param weight     The weight of the arc
     * @param place      The associated place
     * @param transition The associated transition
     */
    public IncomingArc(int weight, Place place, Transition transition) {
        super(weight, place, transition);
    }

    /**
     * Performs the action of firing the arc (removes tokens from the place).
     */
    public void fire() {
        Place place = getPlace();
        place.removeToken(getWeight());
    }

    /**
     * Checks if this arc can be fired (based on the number of tokens).
     * 
     * @return True if the arc can be fired, false otherwise
     */
    public boolean isFireable() {
        return getPlace().canSupply(this.getWeight());
    }
}
