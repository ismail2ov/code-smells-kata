package codesmells;

import lombok.Data;

@Data
public class Tile {
    public int X;
    public int Y;
    private Mark mark;
}
