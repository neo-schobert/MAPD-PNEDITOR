package org.pneditor.petrinet.adapters.neo_paul;

import org.pneditor.petrinet.AbstractTransition;
import org.pneditor.petrinet.models.neo_paul.Transition;

/**
 * Adapter class for the Transition in the neo_paul model.
 * This class adapts the Transition class to the AbstractTransition interface.
 */
public class TransitionAdapter extends AbstractTransition {

    /**
     * Constructs a TransitionAdapter with the specified Transition and index.
     *
     * @param transition the Transition to adapt
     * @param index      the index of the transition
     */
    public TransitionAdapter(Transition transition, int index) {
        super("Transition_" + String.valueOf(index));
    }
}