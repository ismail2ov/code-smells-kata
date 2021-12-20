package codesmells;

import lombok.Value;

@Value
public class Position {
    int x;
    int y;

    public static Position from(int x, int y) {
        return new Position(x, y);
    }
}
