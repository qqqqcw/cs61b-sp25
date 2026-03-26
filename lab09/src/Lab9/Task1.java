package Lab9;

import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;

/**
 * Draws a world initially full of trees.
 */
public class Task1 {
    /**
     * Fills the entire 2D world with the Tileset.TREE tile.
     */
    private static final int TER_WIDTH = 30;
    private  static final int TER_HEIGHT = 20;
    private static final int WORLD_WIDTH = 30;
    private  static final int WORLD_HEIGHT = 15;

    private static void fillWithTrees(TETile[][] world)
    {
        for (int i = 0; i < WORLD_WIDTH; i++) {
            for (int j = 0; j < WORLD_HEIGHT; j++) {
                world[i][j] = Tileset.TREE;
            }
        }
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(TER_WIDTH, TER_HEIGHT);
        TETile[][] world = new TETile[WORLD_WIDTH][WORLD_HEIGHT];
        fillWithTrees(world);
        ter.renderFrame(world);
    }
}