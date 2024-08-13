package com.brendan.wordfinder.placer;

import com.brendan.wordfinder.WordPlacerResult;
import com.brendan.wordfinder.exception.IllegalGridLocationException;
import com.brendan.wordfinder.grid.Grid;

/**
 * A horizontal word placer.  Attempts to place the word horizontal left to right.
 * 
 * @author Brendan Douglas
 */
public class HorizonalWordPlacer implements WordPlacer {

	@Override
	public WordPlacerResult placeWord(Grid grid, String theWord, int row, int column)
			throws IllegalGridLocationException {

		// Check to see if the grid has enough column for the word starting at the
		// supplied column.
		if (column + theWord.length() > grid.getNumberOfColumns()) {
			return new WordPlacerResult(false);
		}

		// Check each character in the word to see if the grid location is either empty
		// or has a matching character
		for (int i = 0; i < theWord.length(); i++) {
			Character wordCharacter = theWord.charAt(i);
			Character gridCharacter = grid.getCellValue(row, column + i);

			if (gridCharacter != null && !gridCharacter.equals(wordCharacter)) {
				return new WordPlacerResult(false);
			}
		}

		// If we get here the word fits so place it into the grid.
		WordPlacerResult successResult = new WordPlacerResult(true);

		for (int i = 0; i < theWord.length(); i++) {
			grid.setCellValue(theWord.charAt(i), row, column + i);
			successResult.addCell(row, column + i);
		}

		return successResult;
	}
}
