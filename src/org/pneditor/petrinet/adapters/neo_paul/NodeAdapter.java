package org.pneditor.petrinet.adapters.neo_paul;

import org.pneditor.petrinet.AbstractNode;

/**
 * The NodeAdapter class is an extension of AbstractNode.
 * It represents a node in a Petri net with a specific label.
 */
public class NodeAdapter extends AbstractNode {
    private final String label;

    /**
     * Constructor for the NodeAdapter class.
     *
     * @param label The label of the node.
     */
    public NodeAdapter(String label) {
        super(label);
        this.label = label;
    }

    /**
     * Checks if the node is a place.
     *
     * @return true if the label contains "Place", otherwise false.
     */
    @Override
    public boolean isPlace() {
        return this.label.contains("Place");
    }
}