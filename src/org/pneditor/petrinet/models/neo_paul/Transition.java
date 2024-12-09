package org.pneditor.petrinet.models.neo_paul;

import java.util.ArrayList;

/**
 * Represents a Transition in the Petri net.
 */
public class Transition {

    private ArrayList<OutgoingArc> outgoingArcs; // List of outgoing arcs from the transition
    private ArrayList<IncomingArc> incomingArcs; // List of incoming arcs to the transition

    /**
     * Default constructor for a transition.
     */
    public Transition() {
        this.incomingArcs = new ArrayList<>();
        this.outgoingArcs = new ArrayList<>();
    }

    /**
     * Attempts to fire the transition if all conditions are met (incoming arcs
     * are fireable).
     */
    public void fireTransition() {
        boolean canFire = true;
        for (IncomingArc incomingArc : this.incomingArcs) {
            canFire = canFire && incomingArc.isFireable();
        }

        // If all incoming arcs can be fired, execute the transition
        if (canFire) {
            for (IncomingArc incomingArc : this.incomingArcs) {
                incomingArc.fire();
            }
            for (OutgoingArc outgoingArc : this.outgoingArcs) {
                outgoingArc.fire();
            }
        }
    }

    /**
     * Removes a place and its associated arcs from the transition.
     * 
     * @param place The place to remove
     */
    public void removePlace(Place place) {
        ArrayList<IncomingArc> updatedIncomingArcs = new ArrayList<>();
        ArrayList<OutgoingArc> updatedOutgoingArcs = new ArrayList<>();

        for (IncomingArc incomingArc : this.incomingArcs) {
            if (!incomingArc.getPlace().equals(place)) {
                updatedIncomingArcs.add(incomingArc);
            }
        }
        for (OutgoingArc outgoingArc : this.outgoingArcs) {
            if (!outgoingArc.getPlace().equals(place)) {
                updatedOutgoingArcs.add(outgoingArc);
            }
        }

        this.incomingArcs = updatedIncomingArcs;
        this.outgoingArcs = updatedOutgoingArcs;
    }

    /**
     * Adds an arc between a place and the transition.
     * 
     * @param place      The associated place
     * @param isIncoming Indicates if the arc is incoming
     * @param weight     The weight of the arc
     */
    public void addArc(Place place, boolean isIncoming, int weight) {
        // Replace the arc if it already exists
        removeArc(place, isIncoming);
        if (isIncoming) {
            IncomingArc newArc = new IncomingArc(weight, place, this);
            this.incomingArcs.add(newArc);
        } else {
            OutgoingArc newArc = new OutgoingArc(weight, place, this);
            this.outgoingArcs.add(newArc);
        }
    }

    /**
     * Adds a zero arc between a place and the transition.
     * 
     * @param place The associated place
     */
    public void addZeroArc(Place place) {
        IncomingArc newArc = new ZeroArc(place, this);
        this.incomingArcs.add(newArc);
    }

    /**
     * Adds a cleaning arc (arc that empties tokens) between a place and the
     * transition.
     * 
     * @param place The associated place
     */
    public void addCleaningArc(Place place) {
        IncomingArc newArc = new CleaningArc(place, this);
        this.incomingArcs.add(newArc);
    }

    /**
     * Removes an arc between a place and the transition.
     * 
     * @param place      The associated place
     * @param isIncoming Indicates if the arc is incoming or outgoing
     */
    public void removeArc(Place place, boolean isIncoming) {
        ArrayList<IncomingArc> updatedIncomingArcs = new ArrayList<>();
        ArrayList<OutgoingArc> updatedOutgoingArcs = new ArrayList<>();

        if (isIncoming) {
            updatedOutgoingArcs = this.outgoingArcs;
            for (IncomingArc incomingArc : this.incomingArcs) {
                if (!incomingArc.getPlace().equals(place)) {
                    updatedIncomingArcs.add(incomingArc);
                }
            }
        } else {
            updatedIncomingArcs = this.incomingArcs;
            for (OutgoingArc outgoingArc : this.outgoingArcs) {
                if (!outgoingArc.getPlace().equals(place)) {
                    updatedOutgoingArcs.add(outgoingArc);
                }
            }
        }

        this.incomingArcs = updatedIncomingArcs;
        this.outgoingArcs = updatedOutgoingArcs;
    }

    /**
     * Gets the list of incoming arcs.
     * 
     * @return The list of incoming arcs
     */
    public ArrayList<IncomingArc> getIncomingArcs() {
        return incomingArcs;
    }

    /**
     * Gets the list of outgoing arcs.
     * 
     * @return The list of outgoing arcs
     */
    public ArrayList<OutgoingArc> getOutgoingArcs() {
        return outgoingArcs;
    }

    public void setWeight(Place place, boolean isEntrant, int weight) {
        if (isEntrant) {
            for (IncomingArc incomingArc : this.incomingArcs) {
                if (incomingArc.getPlace().equals(place)) {
                    incomingArc.setWeight(weight);
                }
            }
        } else {
            for (OutgoingArc outgoingArc : this.outgoingArcs) {
                if (outgoingArc.getPlace().equals(place)) {
                    outgoingArc.setWeight(weight);
                }
            }
        }
    }
}
