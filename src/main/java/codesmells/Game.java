package codesmells;

public class Game {
    private char _lastSymbol = ' ';
    private Board _board = new Board();

    public void Play(char symbol, int x, int y) throws Exception {
        if (isFirstMove()) {
            if (isPlayerO(symbol)) {
                throw new Exception("Invalid first player");
            }
        } else if (isRepeatedPlay(symbol)) {
            throw new Exception("Invalid next player");
        } else if (_board.isMarked(x, y)) {
            throw new Exception("Invalid position");
        }

        updateGameState(symbol, x, y);
    }

    private void updateGameState(char symbol, int x, int y) {
        _lastSymbol = symbol;
        _board.AddTileAt(symbol, x, y);
    }

    private boolean isRepeatedPlay(char symbol) {
        return symbol == _lastSymbol;
    }

    private boolean isPlayerO(char symbol) {
        return symbol == 'O';
    }

    private boolean isFirstMove() {
        return _lastSymbol == ' ';
    }

    public char winner() {
        return _board.threeInRow();
    }

}

