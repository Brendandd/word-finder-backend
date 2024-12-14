package com.brendan.wordfinder.placer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.brendan.wordfinder.WordPlacerResult;
import com.brendan.wordfinder.exception.IllegalGridLocationException;
import com.brendan.wordfinder.grid.Grid;
import com.brendan.wordfinder.grid.GridLocation;

/**
 * Base class for all the word placers.
 * 
 * @author Brendan Douglas
 */
public abstract class WordPlacer {

    /**
     * Attempt to place the word onto the grid at the supplied location.
     * 
     * @param grid
     * @param theWord
     * @param gridLocation
     * @return
     * @throws IllegalGridLocationException
     */
    protected abstract WordPlacerResult placeWord(Grid grid, String theWord, GridLocation gridLocation)
            throws IllegalGridLocationException;

    /**
     * Attempt to place the word onto the grid. All locations are attempted until a
     * suitable place is found.
     * 
     * @param grid
     * @param theWord
     * @return
     * @throws IllegalGridLocationException
     */
    public WordPlacerResult placeWord(Grid grid, String theWord) throws IllegalGridLocationException {
        List<GridLocation> notAttemptedGridLocations = new ArrayList<>();

        Random random = new Random();

        // Initialise the list of not tried grid locations. Once a location is tried and
        // fails it is removed from the list.
        for (int i = 0; i < grid.getNumberOfRows(); i++) {
            for (int j = 0; j < grid.getNumberOfColumns(); j++) {
                notAttemptedGridLocations.add(new GridLocation(i, j));
            }
        }

        boolean placed = false;

        // Keep attempting to place the word until no more locations are available or
        // the word is placed.
        while (!placed && notAttemptedGridLocations.size() > 0) {
            int gridLocationToAttempt = random.nextInt(notAttemptedGridLocations.size());
            GridLocation selectedGridLocation = notAttemptedGridLocations.get(gridLocationToAttempt);
            notAttemptedGridLocations.remove(gridLocationToAttempt);

            WordPlacerResult result = placeWord(grid, theWord, selectedGridLocation);

            if (result.isWordPlaced()) {
                return result;
            }
        }

        return new WordPlacerResult(false);
    }
}
