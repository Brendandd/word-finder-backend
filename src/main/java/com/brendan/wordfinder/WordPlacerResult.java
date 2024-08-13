package com.brendan.wordfinder;

import java.util.ArrayList;
import java.util.List;

import com.brendan.wordfinder.grid.GridLocation;

/**
 * The result of attempting to placer a word in the grid.
 * 
 * @author Brendan Douglas
 */
public class WordPlacerResult {
	private List<GridLocation> placedWordCells = new ArrayList<>();
	private boolean result;

	public WordPlacerResult(boolean result) {
		this.result = result;
	}

	public void addCell(int row, int column) {
		placedWordCells.add(new GridLocation(row, column));
	}

	public List<GridLocation> getPlacedWordCells() {
		return placedWordCells;
	}

	public boolean isWordPlaced() {
		return result;
	}
}
