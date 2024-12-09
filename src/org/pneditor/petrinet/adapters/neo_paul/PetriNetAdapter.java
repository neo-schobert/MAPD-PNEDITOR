package org.pneditor.petrinet.adapters.neo_paul;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.AbstractPlace;
import org.pneditor.petrinet.AbstractTransition;
import org.pneditor.petrinet.PetriNetInterface;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.models.neo_paul.IncomingArc;
import org.pneditor.petrinet.models.neo_paul.Network;
import org.pneditor.petrinet.models.neo_paul.Place;
import org.pneditor.petrinet.models.neo_paul.Transition;

/**
 * The PetriNetAdapter class is an adapter for the PetriNetInterface.
 * It adapts the functionality of the Network class to the PetriNetInterface.
 */
public class PetriNetAdapter extends PetriNetInterface {

    private final Network network; // Actual instance of Network
    private final ArrayList<PlaceAdapter> places; // List of places
    private final ArrayList<TransitionAdapter> transitions; // List of transitions
    private final ArrayList<ArcAdapter> arcs; // List of arcs

    /**
     * Constructor for the PetriNetAdapter class.
     * Initializes the network and the lists of places, transitions, and arcs.
     */
    public PetriNetAdapter() {
        this.network = new Network();
        this.places = new ArrayList<>();
        this.transitions = new ArrayList<>();
        this.arcs = new ArrayList<>();
    }

    /**
     * Gets the network instance.
     *
     * @return The network instance.
     */
    public Network getNetwork() {
        return network;
    }

    /**
     * Gets the list of arcs.
     *
     * @return The list of arcs.
     */
    public ArrayList<ArcAdapter> getArcs() {
        return arcs;
    }

    /**
     * Gets the set of places.
     *
     * @return The set of places.
     */
    @Override
    public Set<AbstractPlace> getPlaces() {
        return new HashSet<>(places);
    }

    /**
     * Gets the list of places.
     *
     * @return The list of places.
     */
    public ArrayList<PlaceAdapter> getPlacesList() {
        return places;
    }

    /**
     * Gets the list of transitions.
     *
     * @return The list of transitions.
     */
    public ArrayList<TransitionAdapter> getTransitionsList() {
        return transitions;
    }

    /**
     * Gets the set of transitions.
     *
     * @return The set of transitions.
     */
    @Override
    public Set<AbstractTransition> getTransitions() {
        return new HashSet<>(transitions);
    }

    /**
     * Adds a new place to the network.
     *
     * @return The added place.
     */
    @Override
    public AbstractPlace addPlace() {
        network.createPlace();
        ArrayList<Place> networkPlaces = this.network.getPlaces();
        Place place = networkPlaces.get(networkPlaces.size() - 1);

        PlaceAdapter placeAdapter = new PlaceAdapter(place, networkPlaces.size() - 1);
        this.places.add(placeAdapter);
        return placeAdapter;
    }

    /**
     * Adds a new transition to the network.
     *
     * @return The added transition.
     */
    @Override
    public AbstractTransition addTransition() {
        network.createTransition();
        ArrayList<Transition> networkTransitions = this.network.getTransitions();
        Transition transition = networkTransitions.get(networkTransitions.size() - 1);

        TransitionAdapter transitionAdapter = new TransitionAdapter(transition, networkTransitions.size() - 1);
        this.transitions.add(transitionAdapter);
        return transitionAdapter;
    }

    /**
     * Adds a regular arc between a source node and a destination node.
     *
     * @param source      The source node.
     * @param destination The destination node.
     * @return The added arc.
     */
    @Override
    public AbstractArc addRegularArc(AbstractNode source, AbstractNode destination) {
        boolean isSourcePlace = source.isPlace();
        if (isSourcePlace) {
            int indexSource = this.places.indexOf(source);
            int indexDestination = this.transitions.indexOf(destination);
            Place place = network.getPlaces().get(indexSource);
            Transition transition = network.getTransitions().get(indexDestination);
            PlaceAdapter placeAdapter = this.places.get(indexSource);
            TransitionAdapter transitionAdapter = this.transitions.get(indexDestination);
            network.addArc(place, transition, true, 1);
            ArcAdapter arcAdapter = new ArcAdapter(placeAdapter, transitionAdapter, "regular", this);
            this.arcs.add(arcAdapter);
            return arcAdapter;

        } else {
            int indexSource = this.transitions.indexOf(source);
            int indexDestination = this.places.indexOf(destination);
            Transition transition = network.getTransitions().get(indexSource);
            Place place = network.getPlaces().get(indexDestination);
            TransitionAdapter transitionAdapter = this.transitions.get(indexSource);
            PlaceAdapter placeAdapter = this.places.get(indexDestination);
            network.addArc(place, transition, false, 1);
            ArcAdapter arcAdapter = new ArcAdapter(transitionAdapter, placeAdapter, "regular", this);
            this.arcs.add(arcAdapter);
            return arcAdapter;
        }
    }

    /**
     * Adds an inhibitory arc between a place and a transition.
     *
     * @param place      The place.
     * @param transition The transition.
     * @return The added arc.
     */
    @Override
    public AbstractArc addInhibitoryArc(AbstractPlace place, AbstractTransition transition) {
        int indexPlace = this.places.indexOf(place);
        int indexTransition = this.transitions.indexOf(transition);
        Place networkPlace = network.getPlaces().get(indexPlace);
        Transition networkTransition = network.getTransitions().get(indexTransition);
        PlaceAdapter placeAdapter = this.places.get(indexPlace);
        TransitionAdapter transitionAdapter = this.transitions.get(indexTransition);
        network.addZeroArc(networkPlace, networkTransition);
        ArcAdapter arcAdapter = new ArcAdapter(placeAdapter, transitionAdapter, "inhibitory", this);
        this.arcs.add(arcAdapter);
        return arcAdapter;
    }

    /**
     * Adds a reset arc between a place and a transition.
     *
     * @param place      The place.
     * @param transition The transition.
     * @return The added arc.
     */
    @Override
    public AbstractArc addResetArc(AbstractPlace place, AbstractTransition transition) {
        int indexPlace = this.places.indexOf(place);
        int indexTransition = this.transitions.indexOf(transition);
        Place networkPlace = network.getPlaces().get(indexPlace);
        Transition networkTransition = network.getTransitions().get(indexTransition);
        PlaceAdapter placeAdapter = this.places.get(indexPlace);
        TransitionAdapter transitionAdapter = this.transitions.get(indexTransition);
        network.addCleaningArc(networkPlace, networkTransition);
        ArcAdapter arcAdapter = new ArcAdapter(placeAdapter, transitionAdapter, "reset", this);
        this.arcs.add(arcAdapter);
        return arcAdapter;
    }

    /**
     * Removes a place from the network.
     *
     * @param abstractPlace The place to be removed.
     */
    @Override
    public void removePlace(AbstractPlace abstractPlace) {
        int index = this.places.indexOf(abstractPlace);
        Place place = network.getPlaces().get(index);
        this.places.remove(index);
        network.removePlace(place);
    }

    /**
     * Removes a transition from the network.
     *
     * @param abstractTransition The transition to be removed.
     */
    @Override
    public void removeTransition(AbstractTransition abstractTransition) {
        int index = this.transitions.indexOf(abstractTransition);
        Transition transition = network.getTransitions().get(index);
        this.transitions.remove(index);
        network.removeTransition(transition);
    }

    /**
     * Removes an arc from the network.
     *
     * @param arc The arc to be removed.
     */
    @Override
    public void removeArc(AbstractArc arc) {
        int indexArc = this.arcs.indexOf(arc);

        if (arc.isSourceAPlace()) {
            AbstractPlace abstractPlace = (AbstractPlace) arc.getSource();
            AbstractTransition abstractTransition = (AbstractTransition) arc.getDestination();
            int indexPlace = this.places.indexOf(abstractPlace);
            Place place = network.getPlaces().get(indexPlace);
            int indexTransition = this.transitions.indexOf(abstractTransition);
            Transition transition = network.getTransitions().get(indexTransition);
            network.removeArc(place, transition, true);

        } else {
            AbstractPlace abstractPlace = (AbstractPlace) arc.getDestination();
            AbstractTransition abstractTransition = (AbstractTransition) arc.getSource();
            int indexPlace = this.places.indexOf(abstractPlace);
            Place place = network.getPlaces().get(indexPlace);
            int indexTransition = this.transitions.indexOf(abstractTransition);
            Transition transition = network.getTransitions().get(indexTransition);
            network.removeArc(place, transition, false);

        }
        this.arcs.remove(indexArc);
    }

    /**
     * Checks if a transition is enabled.
     *
     * @param abstractTransition The transition to check.
     * @return true if the transition is enabled, otherwise false.
     * @throws ResetArcMultiplicityException if there is an issue with arc
     *                                       multiplicity.
     */
    @Override
    public boolean isEnabled(AbstractTransition abstractTransition) throws ResetArcMultiplicityException {
        int index = this.transitions.indexOf(abstractTransition);
        Transition transition = network.getTransitions().get(index);
        for (IncomingArc arc : transition.getIncomingArcs()) {
            if (!arc.isFireable()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Fires a transition.
     *
     * @param abstractTransition The transition to fire.
     * @throws ResetArcMultiplicityException if there is an issue with arc
     *                                       multiplicity.
     */
    @Override
    public void fire(AbstractTransition abstractTransition) throws ResetArcMultiplicityException {
        int index = this.transitions.indexOf(abstractTransition);
        Transition transition = network.getTransitions().get(index);
        network.fireTransition(transition);
    }
}