package org.pneditor.petrinet.adapters.neo_paul;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.models.neo_paul.Network;

/**
 * Adapter class for arcs in a Petri net.
 */
public class ArcAdapter extends AbstractArc {
    private final AbstractNode source;
    private final AbstractNode destination;
    private int multiplicity;
    private final boolean isReset;
    private final boolean isInhibitory;
    private final boolean isRegular;
    private final PetriNetAdapter petrinet;

    /**
     * Constructs an ArcAdapter with the specified source, destination, type, and
     * Petri net adapter.
     *
     * @param source          the source node of the arc
     * @param destination     the destination node of the arc
     * @param type            the type of the arc (reset, inhibitory, or regular)
     * @param petriNetAdapter the Petri net adapter
     */
    public ArcAdapter(AbstractNode source, AbstractNode destination, String type, PetriNetAdapter petriNetAdapter) {
        super();
        this.source = source;
        this.destination = destination;
        this.multiplicity = 1;
        this.isReset = type.equals("reset");
        this.isInhibitory = type.equals("inhibitory");
        this.isRegular = type.equals("regular");
        this.petrinet = petriNetAdapter;
    }

    /**
     * Returns the source node of the arc.
     *
     * @return the source node
     */
    @Override
    public AbstractNode getSource() {
        return source;
    }

    /**
     * Returns the destination node of the arc.
     *
     * @return the destination node
     */
    @Override
    public AbstractNode getDestination() {
        return destination;
    }

    /**
     * Checks if the arc is a reset arc.
     *
     * @return true if the arc is a reset arc, false otherwise
     */
    @Override
    public boolean isReset() {
        return isReset;
    }

    /**
     * Checks if the arc is a regular arc.
     *
     * @return true if the arc is a regular arc, false otherwise
     */
    @Override
    public boolean isRegular() {
        return isRegular;
    }

    /**
     * Checks if the arc is an inhibitory arc.
     *
     * @return true if the arc is an inhibitory arc, false otherwise
     */
    @Override
    public boolean isInhibitory() {
        return isInhibitory;
    }

    /**
     * Returns the multiplicity of the arc.
     *
     * @return the multiplicity of the arc
     * @throws ResetArcMultiplicityException if the arc is a reset arc
     */
    @Override
    public int getMultiplicity() throws ResetArcMultiplicityException {
        return multiplicity;
    }

    /**
     * Sets the multiplicity of the arc.
     *
     * @param multiplicity the new multiplicity
     * @throws ResetArcMultiplicityException if the arc is a reset arc
     */
    @Override
    public void setMultiplicity(int multiplicity) throws ResetArcMultiplicityException {
        this.multiplicity = multiplicity;
        // We Had to change our implementation to make it work with the new (we added
        // the setWeight method in Network in Transition and in Arc because our first
        // implementation couldn't handle the setMultiplicity method)

        Network network = petrinet.getNetwork();
        if (source.isPlace()) {
            int indexPlace = petrinet.getPlacesList().indexOf(source);
            int indexTransition = petrinet.getTransitionsList().indexOf(destination);
            network.setWeight(network.getPlaces().get(indexPlace), network.getTransitions().get(indexTransition), true,
                    multiplicity);
        } else {
            int indexPlace = petrinet.getPlacesList().indexOf(destination);
            int indexTransition = petrinet.getTransitionsList().indexOf(source);
            network.setWeight(network.getPlaces().get(indexPlace), network.getTransitions().get(indexTransition), false,
                    multiplicity);
        }
    }

    /**
     * Sets the multiplicity of the arc only on the adapter.
     *
     * @param multiplicity the new multiplicity
     */
    public void setMultiplicityOnAdapterOnly(int multiplicity) {
        this.multiplicity = multiplicity;
    }

}