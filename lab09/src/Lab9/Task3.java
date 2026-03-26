package Lab9;

import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;
import utils.RandomUtils;
import edu.princeton.cs.algs4.StdDraw;


import java.util.Random;

/**
 * Draws a world initially full of trees.
 */
public class Task3 {
    /**
     * Fills the entire 2D world with the Tileset.TREE tile.
     */
    private static final int TER_WIDTH = 30;
    private  static final int TER_HEIGHT = 20;
    private static final int WORLD_WIDTH = 30;
    private  static final int WORLD_HEIGHT = 15;
    private static final long SEED = 999;
    private static final Random RANDOM = new Random(SEED);

    private static void fillWithTrees(TETile[][] world)
    {
        for (int i = 0; i < WORLD_WIDTH; i++) {
            for (int j = 0; j < WORLD_HEIGHT; j++) {
                world[i][j] = Tileset.TREE;
            }
        }
    }

    private static void drawSquare(TETile[][] world, int startX, int startY, int size, TETile tile) {
        for (int i = startX; i < startX + size; i++) {
            for (int j = startY; j > startY - size; j--) {
                if (i >=0 && i < WORLD_WIDTH && j >= 0 && j < WORLD_HEIGHT) {
                    world[i][j] = tile;
                }
            }
        }
    }
    private static void addRandomSquare(TETile[][] world, Random rand) {
        int randomX = RandomUtils.uniform(rand, 0, WORLD_WIDTH);
        int randomY = RandomUtils.uniform(rand, 0, WORLD_HEIGHT);
        int randomSize = RandomUtils.uniform(rand, 3, 8);
        drawSquare(world, randomX, randomY, randomSize, randomTile());
    }
    private static TETile randomTile() {
        int tile = RANDOM.nextInt(3);
        return switch (tile) {
            case 0 -> Tileset.WALL;
            case 1 -> Tileset.FLOWER;
            default -> Tileset.WATER;
        };
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(TER_WIDTH, TER_HEIGHT);
        TETile[][] world = new TETile[WORLD_WIDTH][WORLD_HEIGHT];
        fillWithTrees(world);
        char c;
        int SquareNum = 0;
        while (true) {
            String text = "Number of squares:" + SquareNum;
            while (StdDraw.hasNextKeyTyped()) {
                c = StdDraw.nextKeyTyped();
                c = Character.toLowerCase(c);
                switch (c) {
                    case 'n':
                        SquareNum++;
                        addRandomSquare(world, RANDOM);
                        break;
                    case 'q':
                        System.exit(0);
                        break;
                    default:
                        break;
                };
            }

            ter.drawTiles(world);
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.textLeft(1, 17, text);
            StdDraw.show();
        }
    }
}