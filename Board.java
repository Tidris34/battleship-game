public class Board {

    private String[][] squares;

    public Board() {
        // Sets the default look of the board by setting every item in the array to "-"
        squares = new String[10][10];
        for (int r = 0; r < squares.length; r++) {
            for (int c = 0; c < squares[0].length; c++) {
                squares[r][c] = "-";
            }
        }
    }

    // Prints out all of the elements in the board
    public String toString() {
        String s = "";
        for (String[] row : squares) {
            for (String sp : row) {
                s += sp + " ";
            }
            s += "\n";
        }
        return s;
    }
    // method that will add a ship at a desired location
    // @param row is an integer that represents the starting row location
    // @param col is an integer that represents the starting column location
    // @param len is an integer that represents the desired length
    // @param horizontal is a boolean that checks if the use wants the ship
    // horizontal or vertical

    public boolean addShip(int row, int col, int len, boolean horizontal) {
        // checks to see that the inserted row and column values are valid
        if (row >= 10 || col >= 10)
            return false;
        if (row < 0 || col < 0) {
            return false;
        }
        // checks if the user wants the boat to be horizontal
        if (horizontal) {
            // checks if the boat wil fit
            if (squares[0].length - col >= len) {
                // iterates the 2d array column - heavy and checks that there isnt a ship in the
                // location
                for (int c = col; c < col + len; c++) {
                    if (squares[row][c].indexOf("b") > -1) {
                        return false;
                    }
                }
                // iterates again the same way but adds the ship
                for (int c = col; c < col + len; c++) {
                    squares[row][c] = "b";

                }
                return true;
            }
        }
        // if the user wants a vertical boat this code runs
        else {
            // checks if the boat fits
            if (squares.length - row >= len) {
                // iterates through the 2d array row-heavy and makes sure the area is clear for
                // a new boat
                for (int r = row; r < row + len; r++) {
                    if (squares[r][col].indexOf("b") > -1)
                        return false;

                }
                // iterates again the same way but adds the ship
                for (int r = row; r < row + len; r++) {
                    squares[r][col] = "b";
                }
                return true;
            }
        }
        return false;
    }

    // finds a ship of a desired length
    // @param len is an integer that represents the length to look for
    public boolean foundShip(int len) {
        // count variable to count the lenght of a ship
        int count = 0;
        // linear search of the array to find ships represented by "b" looks for
        // horizontal ships
        for (int r = 0; r < squares.length; r++) {
            for (int c = 0; c < squares[0].length; c++) {
                if (squares[r][c].equals("b"))
                    count++;
                // if the "b"s are not consecutive then the count resets
                else
                    count = 0;
                // if the algorithim pointer isn't at the max value checks if the count = len
                // and if the ship is the right length
                if (c < 9) {

                    if (count == len && !squares[r][c + 1].equals("b")) {

                        return true;

                    }
                }
                // if poninter is at max value checks if len is met
                else {
                    if (count == len)
                        return true;
                }

            }
        }
        // same process as above but this one checks for verticle ships while the other
        // checks for horizontal ships
        for (int c = 0; c < squares[0].length; c++) {
            for (int r = 0; r < squares.length; r++) {
                if (squares[r][c].equals("b"))
                    count++;

                else
                    count = 0;

                if (r < 9) {
                    if (count == len && !squares[r + 1][c].equals("b")) {

                        return true;

                    }
                } else {
                    if (count == len)
                        return true;
                }
            }
        }
        return false;
    }

    // the shooting part of the battleship game
    // @param row is an integer that represents the y cord
    // @param col is an integer that represents the x cord
    public int shoot(int row, int col) {
        // makes sure inserted parameters are valid
        if (row >= 10 || col >= 10) {
            return -1;
        }
        if (row < 0 || col < 0) {
            return -1;
        }
        // showcases the missed shots
        if (squares[row][col].equals("-")) {
            squares[row][col] = "m";
            return 0;
        }
        // showcases the hit shots
        if (squares[row][col].equals("b")) {
            squares[row][col] = "x";
            return 1;
        }
        // showcases the repeated shots
        if (squares[row][col].equals("x") || squares[row][col].equals("m")) {
            return 2;
        }
        return -1;
    }

    // method that checks if any ships are left if none left it ends the game
    public boolean gameOver() {
        // linear search for "b" which represents the ships
        for (String[] row : squares) {
            for (String s : row) {
                if (s.equals("b"))
                    return false;
            }
        }
        return true;
    }
}


