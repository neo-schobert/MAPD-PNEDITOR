package org.pneditor.petrinet.models.neo_paul;

/**
 * Represents a Zero Arc, a type of arc that can only be fired if the place
 * contains no tokens.
 */
public class ZeroArc extends IncomingArc {

    /**
     * Constructor for a Zero Arc.
     * 
     * @param place      The associated place
     * @param transition The associated transition
     */
    public ZeroArc(Place place, Transition transition) {
        super(0, place, transition);
    }

    /**
     * Checks if this arc can be fired (if the place has no tokens).
     * 
     * @return True if the place has exactly zero tokens, false otherwise
     */
    @Override
    public boolean isFireable() {
        return getPlace().getTokens() == 0;
    }
}
