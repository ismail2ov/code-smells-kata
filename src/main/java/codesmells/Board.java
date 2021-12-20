package codesmells;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<Tile> _plays = new ArrayList<>();

    public Board() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Tile tile = new Tile();
                tile.X = i;
                tile.Y = j;
                tile.Symbol = ' ';
                _plays.add(tile);
            }
        }
    }

    public Tile TileAt(int x, int y) {
        for (Tile t : _plays) {
            if (t.X == x && t.Y == y) {
                return t;
            }
        }
        return null;
    }

    public void AddTileAt(char symbol, int x, int y) {
        TileAt(x, y).Symbol = symbol;
    }

    char threeInRow() {
        for (int row = 0; row < 3; row++) {
            Character board = validateRow(row);
            if (board != null) {
                return board;
            }
        }

        return ' ';
    }

    private Character validateRow(int x) {
        if (isMarked(x, 0) && isMarked(x, 1) && isMarked(x, 2)) {
            if (TileAt(x, 0).Symbol ==
                    TileAt(x, 1).Symbol &&
                    TileAt(x, 2).Symbol == TileAt(x, 1).Symbol) {
                return TileAt(x, 0).Symbol;
            }
        }
        return null;
    }

    boolean isMarked(int x, int y) {
        return TileAt(x, y).Symbol != ' ';
    }
}
