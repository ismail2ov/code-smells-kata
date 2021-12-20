package codesmells;

public class Game {
    private Mark lastMark = Mark.NONE;
    private final Board board = new Board();

    public void Play(Mark mark, Position position) throws Exception {
        if (isFirstMove()) {
            if (isPlayerO(mark)) {
                throw new Exception("Invalid first player");
            }
        } else if (isRepeatedPlay(mark)) {
            throw new Exception("Invalid next player");
        } else if (board.isMarked(position)) {
            throw new Exception("Invalid position");
        }

        lastMark = mark;
        board.markTileAt(mark, position);
    }

    private boolean isRepeatedPlay(Mark mark) {
        return mark == lastMark;
    }

    private boolean isPlayerO(Mark mark) {
        return mark.equals(Mark.O);
    }

    private boolean isFirstMove() {
        return lastMark == Mark.NONE;
    }

    public Mark winner() {
        if (board.threeInRow()) {
            return lastMark;
        }
        return Mark.NONE;
    }

}

