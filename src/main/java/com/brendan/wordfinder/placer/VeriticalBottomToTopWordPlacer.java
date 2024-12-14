package com.brendan.wordfinder.placer;

import com.brendan.wordfinder.WordPlacerResult;
import com.brendan.wordfinder.exception.IllegalGridLocationException;
import com.brendan.wordfinder.grid.Grid;
import com.brendan.wordfinder.grid.GridLocation;

/**
 * A vertical bottom to top word placer.
 * 
 * @author Brendan Douglas
 */
public class VeriticalBottomToTopWordPlacer extends WordPlacer {

    @Override
    protected WordPlacerResult placeWord(Grid grid, String theWord, GridLocation gridLocation)
            throws IllegalGridLocationException {

        // Check to see if the grid has enough column for the word starting at the
        // supplied column.
        if ((gridLocation.getRow() + 1) - theWord.length() < 0) {
            return new WordPlacerResult(false);
        }

        // Check each character in the word to see if the grid location is either empty
        // or has a matching character
        for (int i = 0; i < theWord.length(); i++) {
            Character wordCharacter = theWord.charAt(i);
            Character gridCharacter = grid.getCellValue(gridLocation.getRow() - i, gridLocation.getColumn());

            if (gridCharacter != null && !gridCharacter.equals(wordCharacter)) {
                return new WordPlacerResult(false);
            }
        }

        // If we get here the word fits so place it into the grid.
        WordPlacerResult successResult = new WordPlacerResult(true);

        for (int i = 0; i < theWord.length(); i++) {
            grid.setCellValue(theWord.charAt(i), gridLocation.getRow() - i, gridLocation.getColumn());
            successResult.addCell(gridLocation.getRow() - i, gridLocation.getColumn());
        }

        return successResult;
    }
}
