package main.java.model.board;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * This is the class responsible for dealing with the game board related things, such as
 * the tiles which are occupied, what pieces are on there and what the winning lines are.
 * Use this class to store and retrieve data from the appropriate arraylists.
 * @version 1.0
 * @author Rodžers Ušackis
 * @author Ruben Brouwers
 */

public class Board {
    private ArrayList<Integer> piecesOnBoard = new ArrayList<>();
    private int[] pieceStatus = new int[16];
    private ArrayList<ArrayList<Integer>> usedTiles = new ArrayList<>();
    // 16 - 0's representing an empty board, if a piece is added n is changed to 1
    private ArrayList<Integer> boardStatus = new ArrayList<>(Collections.nCopies(16,0));
    private final ArrayList<ArrayList<Integer>> winningLines = new ArrayList<>();
    private int[][] boardRepresentation = new int[4][4];
    private final int[][] validFirstMoves = {{0,0}, {1,1}, {2,2}, {3,3}, {3,0}, {2,1}, {1,2}, {0,3}}; // positions that fit in a diagonal winning line

    private ArrayList<ArrayList<Integer>> remainingSpots = new ArrayList<>();

    private ArrayList<Integer> simulatedBoardStatus = new ArrayList<>();

    /**
     * Fills the winningLines arraylist.
     * Use case is to compare the winning lines to the active game (boardStatus), to identify if the game is won.
     */
    public void fillWinningLines() {
        for (String line : lines) {
            ArrayList<Integer> temporary = new ArrayList<Integer>();
            for (int j = 0; j < line.length(); j++) {
                if (Integer.parseInt(String.valueOf(line.charAt(j))) == 1) {

                    temporary.add(j);
                }
            }
            winningLines.add(temporary);
        }

        System.out.println("Winning lines: \n"+winningLines);
    }

    public void fillRemainingSpots() {
        for (int i = 0; i<4; i++) {
            for (int j = 0; j<4; j++) {
                ArrayList<Integer> temporary = new ArrayList<Integer>();
                temporary.add(i);
                temporary.add(j);
                remainingSpots.add(temporary);
            }
        }
        System.out.println("Remaining spots created: "+remainingSpots+"\n");
    }

    public void removeRemainingSpots(int xCoord, int yCoord) {
        for (int j=0; j<remainingSpots.size(); j++) {
            if(remainingSpots.get(j).get(0)==xCoord && remainingSpots.get(j).get(1)==yCoord) {
                remainingSpots.remove(j);
            }
        }
    }


    String lines[] = {
            // diagonals
            "1000010000100001",
            "0001001001001000",
            // rows
            "1111000000000000",
            "0000111100000000",
            "0000000011110000",
            "0000000000001111",
            // columns
            "1000100010001000",
            "0100010001000100",
            "0010001000100010",
            "0001000100010001"
};


    //Getters
    public int[] getPieceStatus() { return pieceStatus; }
    public ArrayList<Integer> getPiecesOnBoard() { return piecesOnBoard; }
    public ArrayList<ArrayList<Integer>> getUsedTiles() { return usedTiles; }
    public ArrayList<Integer> getBoardStatus() { return boardStatus; }
    public ArrayList<ArrayList<Integer>> getWinningLines() { return winningLines; }
    public ArrayList<ArrayList<Integer>> getRemainingSpots() { return remainingSpots; }
    public int[][] getValidFirstMoves() { return validFirstMoves; }

    public ArrayList<Integer> getSimulatedBoardStatus() { return simulatedBoardStatus; }

    //Setters
    public void setPieceStatus(int[] pieceStatus) {
        this.pieceStatus = pieceStatus;
    }
    public void setPiecesOnBoard(int id) {
        piecesOnBoard.add(id);
    }

    public void setBoardRepresentation(int y, int x) { boardRepresentation[x][y]=1;
        System.out.println("Board representation: "+Arrays.deepToString(boardRepresentation)); } ;

    public void setSimulatedBoardStatus(ArrayList<Integer> simulatedBoardStatus) { this.simulatedBoardStatus = simulatedBoardStatus; }
}




/*
Er, a note first -- some of this advice may not transfer over to the advanced version (2x2 square sharing the same property being a winning move in addition to a line of 4). I haven't won consistently at that level.

Rule 1. Realize that every piece is (potentially) a winning piece. Don't focus on such things as "this piece is tall," because one tends to forget that that same piece is also light, square, and solid.
For any piece, there are:
4 pieces that share exactly 3 properties
6 pieces that share exactly 2 properties
4 pieces that share exactly 1 property, and only
1 piece that shares no properties.

Meaning that for any single piece, there are 14 others that share at least 1 property.
Make sure to keep track of all 4 properties. It is really easy to remember 3 of them, and forget about the 4th.

Rule 2. Know always where properties can potentially win. For the basic game, there are 10 winning areas (4 rows, 4 columns, 2 diagonals). For the advanced game, there are an additional 9 square areas. In the basic game, each piece will be in 2 or 3 of these areas, while in the advanced game it is 4 or 7 areas. As soon as 2 pieces are in the same area, identify all the ways that something could possibly win there. This is critical to know when there are 3 pieces in an area (a property being set up to win).

Rule 3. Know how many pieces *cannot* win. For example, say lights and solids are set up to win (3 in an area already). Don't count how many darks there are, and add it to how many hollows there are, as you'll include light hollows and dark solids. Only count how many non-light non-solids there are (or, said another way, *both* dark and solid). One wants to be able to give the last non-winning piece to the opponent, forcing them to give a winning piece back.

Rule 4. You have one move at a time, not a place the piece move followed by a chose a piece move. IE, whenever you place a piece, you should already know there is at least one piece available that you can give that cannot win. It is disheartening to realize that your placement set up both lights and darks, or that it set up both circles and hollows (and the only pieces left are either circular or hollow).

OK, I'll admit, these are more mechanics of the game rather than strategy.
As far as that goes, I've only discovered that it is better to play to not lose than it is to play to win. Know that if an area is set up to win, and you get the last non-winning piece, you must use that piece to block the win. Don't get caught in a situation where there are 2 winning areas, and you need to block both of them with only one piece. When it is your opponent's move, still be analyzing the board. Figure out what the winning properties are for each place that they could put the piece, and if that would guarantee you a win no matter what piece they gave you. Then, don't count on them putting it there....

OK. Long winded to get to the point of saying "I don't know a winning strategy." But in a nutshell, 2 tenants that I play by:
1 -- Keep repeating all 4 properties, to not lose track of one of them. Is it light/dark? solid/hollow? tall/short? circle/square? Know this for each area and piece.
2 -- Play not to lose, rather than to win.

https://ourpastimes.com/win-quarto-2325455.html

uarto is a deceptively tricky game for two players. There is a four by four board, and sixteen pieces. And all you have to do to win is to get four in a row of the same shape, or color, or size, or hollowness. Which wouldn't be that hard if you got to choose which pieces you played. But since your opponent will be choosing your pieces for you, the game gets a little trickier, and it's not just tic-tac-toe.

Familiarize yourself with the pieces. When looking at a piece you should instantly think of its four attributes. Keep looking at pieces on your own time until you can immediately see all attributes of any piece you glance at.

Hand your opponent lots of pieces of the same attribute until she places two in the same line. This will allow you to partially fill the board with your chosen attribute.

Count the number of pieces remaining that are not in your chosen attribute. For example, if you have been handing your opponent white pieces, as soon as he places two white pieces in the same line, you should count how many black pieces are left.

Hand your opponent another piece of the chosen attribute if the number of remaining opposite pieces is even. For example, if you've been handing her white pieces and there are four black pieces remaining, hand her another white piece. If you've been handing her white pieces and there are only three black pieces remaining, hand her a black piece instead.

Continue handing your opponent off-attribute pieces once he has made three in a row of your chosen attribute. If you have counted correctly, he will eventually have to hand you a piece of your chosen attribute, which you can play in the row to make four unless it becomes blocked.

Always watch the board to keep track of all attributes of pieces on the board. Remember, even if you are focusing on color, your opponent may be trying to win with shape or height.

Pay particular attention to any row or column with three pieces in it. You should immediately figure out if any type of piece could be a scoring piece in the remaining space. If so, you must be sure not to give your opponent any pieces of that type.
*/
