package org.pneditor.petrinet.models.neo_paul;

/**
 * Represents a place in the Petri net, where tokens are stored.
 */
public class Place {

    private int tokens; // Number of tokens in the place

    /**
     * Default constructor that initializes the place with 0 tokens.
     */
    public Place() {
        this.tokens = 0;
    }

    /**
     * Gets the number of tokens in the place.
     * 
     * @return The number of tokens
     */
    public int getTokens() {
        return this.tokens;
    }

    /**
     * Adds a certain number of tokens to the place.
     * 
     * @param nbTokens The number of tokens to add
     */
    public void addToken(int nbTokens) {
        if (nbTokens >= 0) {
            this.tokens += nbTokens;
        }
    }

    /**
     * Removes a certain number of tokens from the place.
     * 
     * @param nbTokens The number of tokens to remove
     */
    public void removeToken(int nbTokens) {
        if (canSupply(nbTokens)) {
            this.tokens -= nbTokens;
        }
    }

    /**
     * Checks if the place can supply a certain number of tokens.
     * 
     * @param nbTokens The number of tokens to check
     * @return True if the place can supply the tokens, false otherwise
     */
    public boolean canSupply(int nbTokens) {
        return nbTokens <= this.tokens && nbTokens >= 0;
    }
}
