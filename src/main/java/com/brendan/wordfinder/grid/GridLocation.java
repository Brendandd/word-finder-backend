package com.brendan.wordfinder.grid;

/**
 * A location with the word finder grid.
 * 
 * @author Brendan Douglas
 */
public class GridLocation {

    private int row;
    private int column;

    public GridLocation(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
