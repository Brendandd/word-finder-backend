package com.brendan.wordfinder.placer;

import com.brendan.wordfinder.WordPlacerResult;
import com.brendan.wordfinder.exception.IllegalGridLocationException;
import com.brendan.wordfinder.grid.Grid;

/**
 * Interface for all the word placers.
 * 
 * @author Brendan Douglas
 */
public interface WordPlacer {

    /**
     * Place the word onto the grid at the supplied location.
     * 
     * @param theWord
     * @param grid
     * @param row
     * @param column
     * @return
     */
    WordPlacerResult placeWord(Grid grid, String theWord, int row, int column) throws IllegalGridLocationException;

}
