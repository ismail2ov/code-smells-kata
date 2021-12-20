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
        } else if (isMarked(x, y)) {
            throw new Exception("Invalid position");
        }

        updateGameState(symbol, x, y);
    }

    private void updateGameState(char symbol, int x, int y) {
        _lastSymbol = symbol;
        _board.AddTileAt(symbol, x, y);
    }

    private boolean isMarked(int x, int y) {
        return _board.TileAt(x, y).Symbol != ' ';
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

    public char Winner() {
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
            if (_board.TileAt(x, 0).Symbol ==
                    _board.TileAt(x, 1).Symbol &&
                    _board.TileAt(x, 2).Symbol == _board.TileAt(x, 1).Symbol) {
                return _board.TileAt(x, 0).Symbol;
            }
        }
        return null;
    }
}

