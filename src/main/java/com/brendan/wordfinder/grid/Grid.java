package com.brendan.wordfinder.grid;

import java.util.Random;

import org.json.JSONArray;

import com.brendan.wordfinder.WordPlacerResult;
import com.brendan.wordfinder.exception.IllegalGridLocationException;
import com.brendan.wordfinder.placer.WordPlacer;

/**
 * The word finder grid.
 * 
 * @author Brendan Douglas
 */
public class Grid {
	private Character[][] theGrid;
	int numberOfRows;
	int numberOfColumns;

	private Grid() {
	}

	/**
	 * Initialises the grid with the supplied number of rows and columns.
	 * 
	 * @param numberOfRows
	 * @param numberOfColumns
	 */
	public Grid(int numberOfRows, int numberOfColumns) {
		this.numberOfColumns = numberOfColumns;
		this.numberOfRows = numberOfRows;

		this.theGrid = new Character[numberOfRows][numberOfColumns];
	}

	/**
	 * Returns the number of rows in this word finder grid.
	 * 
	 * @return The number of rows.
	 */
	public int getNumberOfRows() {
		return numberOfRows;
	}

	/**
	 * Returns the number of columns in this word finder grid.
	 * 
	 * @return The number of columns.
	 */
	public int getNumberOfColumns() {
		return numberOfColumns;
	}

	/**
	 * Is the supplied location a valid grid location.
	 * 
	 * @param row
	 * @param column
	 * @return True if the supplied grid location is valid, otherwise false.
	 */
	public boolean validateGridLocation(int row, int column) {
		if (row > numberOfRows) {
			return false;
		}

		if (column > numberOfColumns) {
			return false;
		}

		return true;
	}

	/**
	 * Returns true if the cell is empty (null), or false if not empty.
	 * 
	 * @param row
	 * @param column
	 * @return
	 * @throws IllegalGridLocationException
	 */
	public boolean isCellEmpty(int row, int column) throws IllegalGridLocationException {
		if (!validateGridLocation(row, column)) {
			throw new IllegalGridLocationException("Ilegal grid location supplied", row, column);
		}

		return theGrid[row][column] == null;
	}

	/**
	 * Returns the character at the supplied location.
	 * 
	 * @param row
	 * @param column
	 * @return
	 * @throws IllegalGridLocationException
	 */
	public Character getCellValue(int row, int column) throws IllegalGridLocationException {
		if (!validateGridLocation(row, column)) {
			throw new IllegalGridLocationException("Ilegal grid location supplied", row, column);
		}

		return theGrid[row][column];
	}

	/**
	 * Sets the supplied character at the specified cell location.
	 * 
	 * @param theChar
	 * @param row
	 * @param column
	 * @throws IllegalGridLocationException
	 */
	public void setCellValue(Character theChar, int row, int column) throws IllegalGridLocationException {
		if (!validateGridLocation(row, column)) {
			throw new IllegalGridLocationException("Ilegal grid location supplied", row, column);
		}

		theGrid[row][column] = theChar;
	}

	/**
	 * Attempts to place a word on the grid using the supplied word placer.
	 * 
	 * @param theWord
	 * @param row
	 * @param column
	 * @param wordPlacer
	 * @return
	 */
	public WordPlacerResult placeWord(String theWord, int row, int column, WordPlacer wordPlacer)
			throws IllegalGridLocationException {
		if (!validateGridLocation(row, column)) {
			throw new IllegalGridLocationException("Ilegal grid location supplied", row, column);
		}

		return wordPlacer.placeWord(this, theWord, row, column);
	}

	/**
	 * Returns the grid as JSON string.
	 * 
	 * @return
	 * @throws IllegalGridLocationException
	 */
	public String asJson() throws IllegalGridLocationException {

		// fill the empty cells with a random letter.
		for (int i = 0; i < numberOfRows; i++) {
			for (int j = 0; j < numberOfColumns; j++) {

				if (isCellEmpty(i, j)) {
					char[] letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

					Random random = new Random();
					setCellValue(letters[random.nextInt(letters.length)], i, j);
				}
			}
		}

		// Convert to a JSON string.
		JSONArray result = new JSONArray(theGrid);

		return result.toString();
	}
}
