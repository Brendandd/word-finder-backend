package com.brendan.wordfinder.placer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brendan.wordfinder.WordPlacerResult;
import com.brendan.wordfinder.exception.IllegalGridLocationException;
import com.brendan.wordfinder.grid.Grid;
import com.brendan.wordfinder.grid.GridLocation;

/**
 * A top right to bottom left diagonal word placer.
 * 
 * @author Brendan Douglas
 */
public class DiagonalTopRightToBottomLeftWordPlacer extends WordPlacer {
    public static String NAME = "Diagonal Top Right To Bottom Left Word Placer";
    
    private static final Logger logger = LoggerFactory.getLogger(DiagonalTopRightToBottomLeftWordPlacer.class);

    @Override
    protected WordPlacerResult placeWord(Grid grid, String theWord, GridLocation gridLocation)
            throws IllegalGridLocationException {

        // Check to see if the grid has enough column for the word starting at the
        // supplied column.
        if ((gridLocation.getColumn() + 1) - theWord.length() < 0) {
            return new WordPlacerResult(false);
        }

        // Check to see if the grid has enough rows for the word starting at the
        // supplied row.
        if (gridLocation.getRow() + theWord.length() > grid.getNumberOfRows()) {
            return new WordPlacerResult(false);
        }

        // Check each character in the word to see if the grid location is either empty
        // or has a matching character
        for (int i = 0; i < theWord.length(); i++) {
            Character wordCharacter = theWord.charAt(i);
            Character gridCharacter = grid.getCellValue(gridLocation.getRow() + i, gridLocation.getColumn() - i);

            if (gridCharacter != null && !gridCharacter.equals(wordCharacter)) {
                return new WordPlacerResult(false);
            }
        }

        // If we get here the word fits so place it into the grid.
        WordPlacerResult successResult = new WordPlacerResult(true);

        for (int i = 0; i < theWord.length(); i++) {
            grid.setCellValue(theWord.charAt(i), gridLocation.getRow() + i, gridLocation.getColumn() - i);
            successResult.addCell(gridLocation.getRow() + i, gridLocation.getColumn() - i);
        }

        return successResult;
    }
    
    
    @Override
    public String getName() {
        return NAME;
    }
    
    @Override
    public Logger getLogger() {
        return logger;
    }
}
