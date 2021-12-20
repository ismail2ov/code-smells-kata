package codesmells;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<Tile> _plays = new ArrayList<>();

    public Board() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                _plays.add(Tile.empty(Position.from(i, j)));
            }
        }
    }

    public Tile TileAt(int x, int y) {
        for (Tile t : _plays) {
            if (t.getPosition().equals(Position.from(x, y))) {
                return t;
            }
        }
        return null;
    }

    public void AddTileAt(Mark mark, int x, int y) {
        TileAt(x, y).setMark(mark);
    }

    Mark threeInRow() {
        for (int row = 0; row < 3; row++) {
            Mark board = validateRow(row);
            if (board != null) {
                return board;
            }
        }

        return Mark.NONE;
    }

    private Mark validateRow(int x) {
        if (isMarked(x, 0) && isMarked(x, 1) && isMarked(x, 2)) {
            if (TileAt(x, 0).getMark() ==
                    TileAt(x, 1).getMark() &&
                    TileAt(x, 2).getMark() == TileAt(x, 1).getMark()) {
                return TileAt(x, 0).getMark();
            }
        }
        return null;
    }

    boolean isMarked(int x, int y) {
        return TileAt(x, y).getMark() != Mark.NONE;
    }
}
