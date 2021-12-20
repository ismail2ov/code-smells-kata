package codesmells;

public class Game {
    private Mark _lastSymbol = Mark.NONE;
    private Board _board = new Board();

    public void Play(Mark mark, int x, int y) throws Exception {
        if (isFirstMove()) {
            if (isPlayerO(mark)) {
                throw new Exception("Invalid first player");
            }
        } else if (isRepeatedPlay(mark)) {
            throw new Exception("Invalid next player");
        } else if (_board.isMarked(x, y)) {
            throw new Exception("Invalid position");
        }

        updateGameState(mark, x, y);
    }

    private void updateGameState(Mark mark, int x, int y) {
        _lastSymbol = mark;
        _board.AddTileAt(mark, x, y);
    }

    private boolean isRepeatedPlay(Mark mark) {
        return mark == _lastSymbol;
    }

    private boolean isPlayerO(Mark mark) {
        return mark.equals(Mark.O);
    }

    private boolean isFirstMove() {
        return _lastSymbol == Mark.NONE;
    }

    public Mark winner() {
        return _board.threeInRow();
    }

}

