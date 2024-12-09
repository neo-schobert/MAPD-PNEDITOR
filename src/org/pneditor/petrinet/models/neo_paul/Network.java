package org.pneditor.petrinet.models.neo_paul;

import java.util.ArrayList;

/**
 * Represents a Petri net, consisting of places and transitions.
 */
public class Network {

    private final ArrayList<Place> places; // List of places in the Petri net
    private final ArrayList<Transition> transitions; // List of transitions in the Petri net

    /**
     * Default constructor for an empty Petri net.
     */
    public Network() {
        this.places = new ArrayList<>();
        this.transitions = new ArrayList<>();
    }

    /**
     * Attempts to fire a transition in the Petri net.
     * 
     * @param transition The transition to fire
     * @throws Error if the transition is not part of the Petri net
     */
    public void fireTransition(Transition transition) {
        if (transitions.contains(transition)) {
            transition.fireTransition();
        } else {
            throw new Error("Transition not in Petrinet");
        }
    }

    /**
     * Creates a new place and adds it to the Petri net.
     */
    public void createPlace() {
        Place newPlace = new Place();
        this.places.add(newPlace);
    }

    /**
     * Creates a new transition and adds it to the Petri net.
     */
    public void createTransition() {
        Transition newTransition = new Transition();
        this.transitions.add(newTransition);
    }

    /**
     * Removes a place and its associated arcs from the Petri net.
     * 
     * @param place The place to remove
     */
    public void removePlace(Place place) {
        if (places.contains(place)) {
            for (Transition transition : transitions) {
                transition.removePlace(place);
            }
            this.places.remove(place);
        }
    }

    /**
     * Removes a transition from the Petri net.
     * 
     * @param transition The transition to remove
     */
    public void removeTransition(Transition transition) {
        if (transitions.contains(transition)) {
            this.transitions.remove(transition);
        }
    }

    /**
     * Adds a certain number of tokens to a place.
     * 
     * @param place   The target place
     * @param nbToken The number of tokens to add
     */
    public void addToken(Place place, int nbToken) {
        place.addToken(nbToken);
    }

    /**
     * Removes a certain number of tokens from a place.
     * 
     * @param place   The target place
     * @param nbToken The number of tokens to remove
     */
    public void removeToken(Place place, int nbToken) {
        place.removeToken(nbToken);
    }

    /**
     * Adds an arc between a place and a transition.
     * 
     * @param place      The associated place
     * @param transition The associated transition
     * @param isIncoming Indicates if the arc is incoming
     * @param weight     The weight of the arc
     */
    public void addArc(Place place, Transition transition, boolean isIncoming, int weight) {
        if (weight > 0 && places.contains(place) && transitions.contains(transition)) {
            transition.addArc(place, isIncoming, weight);
        }
    }

    /**
     * Adds a zero arc between a place and a transition.
     * 
     * @param place      The associated place
     * @param transition The associated transition
     */
    public void addZeroArc(Place place, Transition transition) {
        if (places.contains(place) && transitions.contains(transition)) {
            transition.addZeroArc(place);
        }
    }

    /**
     * Adds a cleaning arc (CleaningArc) between a place and a transition.
     * 
     * @param place      The associated place
     * @param transition The associated transition
     */
    public void addCleaningArc(Place place, Transition transition) {
        if (places.contains(place) && transitions.contains(transition)) {
            transition.addCleaningArc(place);
        }
    }

    /**
     * Removes an arc between a place and a transition.
     * 
     * @param place      The associated place
     * @param transition The associated transition
     * @param isEntrant  Indicates if the arc is incoming or outgoing
     */
    public void removeArc(Place place, Transition transition, boolean isEntrant) {
        if (places.contains(place) && transitions.contains(transition)) {
            transition.removeArc(place, isEntrant);
        }
    }

    /**
     * Clears the entire Petri net by removing all places and transitions.
     */
    public void clearNetwork() {
        this.places.clear();
        this.transitions.clear();
    }

    /**
     * Gets the list of places in the Petri net.
     * 
     * @return The list of places
     */
    public ArrayList<Place> getPlaces() {
        return places;
    }

    /**
     * Gets the list of transitions in the Petri net.
     * 
     * @return The list of transitions
     */
    public ArrayList<Transition> getTransitions() {
        return transitions;
    }

    public void setWeight(Place place, Transition transition, boolean isEntrant, int weight) {
        if (places.contains(place) && transitions.contains(transition)) {
            transition.setWeight(place, isEntrant, weight);
        }
    }
}
