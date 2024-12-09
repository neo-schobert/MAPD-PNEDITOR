package org.pneditor.petrinet.adapters.neo_paul;

import org.pneditor.petrinet.AbstractPlace;
import org.pneditor.petrinet.models.neo_paul.Place;

/**
 * Adapter class for the Place in the neo_paul model.
 * This class adapts the Place class to the AbstractPlace interface.
 */
public class PlaceAdapter extends AbstractPlace {
    private final Place place;

    /**
     * Constructs a PlaceAdapter with the specified Place and index.
     *
     * @param place the Place to adapt
     * @param index the index of the place
     */
    public PlaceAdapter(Place place, int index) {
        super("Place_" + String.valueOf(index));
        this.place = place;
    }

    /**
     * Adds a token to the place.
     */
    @Override
    public void addToken() {
        place.addToken(1);
    }

    /**
     * Removes a token from the place.
     */
    @Override
    public void removeToken() {
        place.removeToken(1);
    }

    /**
     * Returns the number of tokens present in the place.
     *
     * @return the number of tokens
     */
    @Override
    public int getTokens() {
        return place.getTokens();
    }

    /**
     * Sets the number of tokens in the place.
     *
     * @param tokens the number of tokens to set
     */
    @Override
    public void setTokens(int tokens) {
        int currentTokens = place.getTokens();
        place.removeToken(currentTokens);
        place.addToken(tokens);
    }
}