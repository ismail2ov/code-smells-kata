package codesmells;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final List<Tile> tiles = new ArrayList<>();

    public Board() {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                tiles.add(Tile.empty(Position.from(row, column)));
            }
        }
    }

    public Tile getTile(int x, int y) {
        return tiles.stream().filter(t -> t.getPosition().equals(Position.from(x, y))).findFirst().orElse(null);
    }

    public void markTileAt(Mark mark, int x, int y) {
        getTile(x, y).setMark(mark);
    }

    Mark threeInRow() {
        for (int row = 0; row < 3; row++) {
            Mark mark = validateRow(row);
            if (mark != null) {
                return mark;
            }
        }

        return Mark.NONE;
    }

    private Mark validateRow(int x) {
        if (isMarked(x, 0) && isMarked(x, 1) && isMarked(x, 2)) {
            if (getTile(x, 0).getMark() == getTile(x, 1).getMark() && getTile(x, 2).getMark() == getTile(x, 1).getMark()) {
                return getTile(x, 0).getMark();
            }
        }
        return null;
    }

    boolean isMarked(int x, int y) {
        return getTile(x, y).getMark() != Mark.NONE;
    }
}
