package org.pneditor.petrinet.models.neo_paul;

/**
 * Represents a Cleaning Arc, a type of arc that completely empties a place.
 */
public class CleaningArc extends IncomingArc {

    /**
     * Constructor for a Cleaning Arc.
     * 
     * @param place      The place to be emptied
     * @param transition The associated transition
     */
    public CleaningArc(Place place, Transition transition) {
        super(0, place, transition);
    }

    /**
     * Checks if this arc can be fired (if the place has tokens).
     * 
     * @return True if the place contains at least one token, false otherwise
     */
    @Override
    public boolean isFireable() {
        return getPlace().getTokens() > 0;
    }

    /**
     * Fires the arc by removing all tokens from the place.
     */
    @Override
    public void fire() {
        Place place = getPlace();
        place.removeToken(place.getTokens());
    }
}
