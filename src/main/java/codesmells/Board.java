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

    public Tile getTile(Position position) {
        return tiles.stream().filter(t -> t.getPosition().equals(position)).findFirst().orElse(null);
    }

    public void markTileAt(Mark mark, Position position) {
        getTile(position).setMark(mark);
    }

    boolean threeInRow() {
        return validateRow(0) || validateRow(1) || validateRow(2);
    }

    private boolean validateRow(int x) {
        if (isMarked(Position.from(x, 0))) {
            return isThreeInRow(x, getTile(Position.from(x, 0)));
        } else {
            return false;
        }
    }

    private boolean isThreeInRow(int x, Tile firstTile) {
        return getTile(Position.from(x, 1)).hasSameMarkAs(firstTile) && getTile(Position.from(x, 2)).hasSameMarkAs(firstTile);
    }

    boolean isMarked(Position position) {
        return getTile(position).getMark() != Mark.NONE;
    }
}
