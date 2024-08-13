package com.brendan.wordfinder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.json.JSONObject;

import com.brendan.wordfinder.exception.IllegalGridLocationException;
import com.brendan.wordfinder.grid.Grid;
import com.brendan.wordfinder.grid.GridLocation;
import com.brendan.wordfinder.placer.HorizonalWordPlacer;
import com.brendan.wordfinder.placer.ReverseHorizontalWordPlacer;
import com.brendan.wordfinder.placer.ReverseVeriticalWordPlacer;
import com.brendan.wordfinder.placer.VerticalWordPlacer;
import com.brendan.wordfinder.placer.WordPlacer;

/**
 * The word finder app.  Controls the creation of the grid.
 * 
 * @author Brendan Douglas
 */
public class WordFinder {
	private List<GridLocation> notAttemptedGridLocations = new ArrayList<>();
	private List<WordPlacer> notAttemptedWordPlacer = new ArrayList<>();
	private Grid grid;

	private Map<String, List<GridLocation>> placedWords = new HashMap<String, List<GridLocation>>();
	private List<String> notPlacedWords = new ArrayList<String>();

	private WordFinder() {}
	
	public WordFinder(int numberOfRows, int numberOfColumns) {
		grid = new Grid(numberOfRows, numberOfColumns);
	}

	/**
	 * Generates the word finder grid.
	 * 
	 * @param words
	 * @return
	 * @throws IllegalGridLocationException
	 */
	public String generate(List<String> words) throws IllegalGridLocationException {

		// Attempt to add each of the words.
		for (String word : words) {
			word = word.toUpperCase();
			
			boolean placed = false;
			Random random = new Random();

			// The available word placers. Once a word placer is tried and failed it
			// is then removed from the list so another can be tried.
			notAttemptedWordPlacer.add(new HorizonalWordPlacer());
			notAttemptedWordPlacer.add(new VerticalWordPlacer());
			notAttemptedWordPlacer.add(new ReverseHorizontalWordPlacer());
			notAttemptedWordPlacer.add(new ReverseVeriticalWordPlacer());

			while (!placed && notAttemptedWordPlacer.size() > 0) {
				int randomWordPlacer = random.nextInt(notAttemptedWordPlacer.size());
				WordPlacer selectedWordPlacer = notAttemptedWordPlacer.get(randomWordPlacer);
				notAttemptedWordPlacer.remove(randomWordPlacer);

				// Initialise the list of not tried grid locations. Once a location is tried and fails it
				// is removed from the list.
				for (int i = 0; i < grid.getNumberOfRows(); i++) {
					for (int j = 0; j < grid.getNumberOfColumns(); j++) {
						notAttemptedGridLocations.add(new GridLocation(i, j));
					}
				}

				// For the randomly selected word placer we try each location until placed
				// or we have tried all locations.
				while (!placed && notAttemptedGridLocations.size() > 0) {
					int gridLocationToAttempt = random.nextInt(notAttemptedGridLocations.size());
					GridLocation selectedGridLocation = notAttemptedGridLocations.get(gridLocationToAttempt);
					notAttemptedGridLocations.remove(gridLocationToAttempt);

					WordPlacerResult result = grid.placeWord(word, selectedGridLocation.getRow(), selectedGridLocation.getColumn(),selectedWordPlacer);

					// The word is placed in the grid. The location of the word is retained.
					if (result.isWordPlaced()) {
						placedWords.put(word, result.getPlacedWordCells());
						placed = true;
						break;
					}
				}
			}

			if (!placed) {
				notPlacedWords.add(word);
			}
		}

		// Returns the result as a JSON string.
		JSONObject json = new JSONObject();
		json.put("theGrid", grid.asJson());
		json.put("placedWords", placedWords);
		json.put("notPlacedWords", notPlacedWords);

		return json.toString();

	}
}
