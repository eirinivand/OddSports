package com.evandorou.oddsports.model;

import lombok.Getter;

/**
 * The enum Sport.
 * <br>
 * Refers to the sport of a match.
 */
@Getter
public enum Sport {

    /**
     * Football sport.
     */
    FOOTBALL(1, "Football"),

    /**
     * Basketball sport.
     */
    BASKETBALL(2, "Basketball"),
    /**
     * Basketball sport.
     */
    SOCCER(3, "Soccer");


    /**
     * -- GETTER --
     *  Gets id.
     *  <br>
     *  This references <code>match.sport</code> in the DB.
     *
     * @return the id
     */
    // Identifier matching the db at match.sport
    private final int id;

    /**
     * -- GETTER --
     *  Gets name.
     *  <br>
     *  This is the displayed name of the Sport.
     *
     * @return the name
     */
    // Displayed name
    private final String name;

    Sport(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Sport fromId(int value) {
        for (Sport status : Sport.values()) {
            if (status.getId() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown status value: " + value);
    }

}
