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
import com.brendan.wordfinder.placer.DiagonalTopLeftToBottomRightWordPlacer;
import com.brendan.wordfinder.placer.DiagonalTopRightToBottomLeftWordPlacer;
import com.brendan.wordfinder.placer.HorizonalLeftToRightWordPlacer;
import com.brendan.wordfinder.placer.HorizontalRightToLeftWordPlacer;
import com.brendan.wordfinder.placer.VeriticalBottomToTopWordPlacer;
import com.brendan.wordfinder.placer.VerticalTopToBottomWordPlacer;
import com.brendan.wordfinder.placer.WordPlacer;

/**
 * The word finder app. Controls the creation of the grid.
 * 
 * @author Brendan Douglas
 */
public class WordFinder {
    private List<WordPlacer> notAttemptedWordPlacer = new ArrayList<>();
    private Grid grid;

    private Map<String, List<GridLocation>> placedWords = new HashMap<String, List<GridLocation>>();
    private List<String> notPlacedWords = new ArrayList<String>();

    private WordFinder() {
    }

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


            Random random = new Random();

            // The available word placers. Once a word placer is tried and failed it
            // is then removed from the list so another can be tried.
            notAttemptedWordPlacer.add(new HorizonalLeftToRightWordPlacer());
            notAttemptedWordPlacer.add(new VerticalTopToBottomWordPlacer());
            notAttemptedWordPlacer.add(new HorizontalRightToLeftWordPlacer());
            notAttemptedWordPlacer.add(new VeriticalBottomToTopWordPlacer());
            notAttemptedWordPlacer.add(new DiagonalTopLeftToBottomRightWordPlacer());
            notAttemptedWordPlacer.add(new DiagonalTopRightToBottomLeftWordPlacer());

            WordPlacerResult result = new WordPlacerResult(false);
            
            while (!result.isWordPlaced() && notAttemptedWordPlacer.size() > 0) {
                int randomWordPlacer = random.nextInt(notAttemptedWordPlacer.size());
                WordPlacer selectedWordPlacer = notAttemptedWordPlacer.get(randomWordPlacer);
                notAttemptedWordPlacer.remove(randomWordPlacer);

                result = grid.placeWord(word, selectedWordPlacer);
            }
            
            if (result.isWordPlaced()) {
                placedWords.put(word, result.getPlacedWordCells()); 
            } else {
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
