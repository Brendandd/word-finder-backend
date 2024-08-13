package com.brendan.wordfinder.rest;

import java.util.ArrayList;
import java.util.List;

/**
 * The REST service request.
 * 
 * @author Brendan Douglas
 */
public class WordFinderRequest {
	private int rows;
	private int columns;
	private List<String> words = new ArrayList<>();

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	public List<String> getWords() {
		return words;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public void setWords(List<String> words) {
		this.words = words;
	}
}
