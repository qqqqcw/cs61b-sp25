package Lab9;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;
import utils.RandomUtils;

import java.awt.*;
import java.io.File;
import java.util.Random;

/**
 * Draws a world initially full of trees.
 */
public class Task4 {
    /**
     * Fills the entire 2D world with the Tileset.TREE tile.
     */
    private static final int TER_WIDTH = 30;
    private  static final int TER_HEIGHT = 20;
    private static final int WORLD_WIDTH = 30;
    private  static final int WORLD_HEIGHT = 15;
    private static final long SEED = 999;
    private static Random RANDOM = new Random(SEED);

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
        drawSquare(world, randomX, randomY, randomSize, randomTile(rand));
    }
    private static TETile randomTile(Random rand) {
        int tile = rand.nextInt(3);
        return switch (tile) {
            case 0 -> Tileset.WALL;
            case 1 -> Tileset.FLOWER;
            default -> Tileset.WATER;
        };
    }
    private static int load (TETile[][] world, int SquareNum) {
        String filename = "state.txt";
        File file = new File(filename);
        if (file.exists()) {
            In in = new In(file);
            if (in.hasNextLine()) {
                int savedNum = Integer.parseInt(in.readLine());
                RANDOM =  new Random(SEED);
                fillWithTrees(world);
                for (int i = 0; i < savedNum; i++) {
                    addRandomSquare(world, RANDOM);
                }
                in.close();
                return savedNum;
            } else {
                System.out.println("You have not saved.");
                in.close();
            }
        }
        return SquareNum;
    }
    private static void save (int SquareNum) {
        String filename = "state.txt";
        Out out = new Out(filename);
        out.println(SquareNum);
        out.close();
    }


    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(TER_WIDTH, TER_HEIGHT);
        TETile[][] world = new TETile[WORLD_WIDTH][WORLD_HEIGHT];
        fillWithTrees(world);
        char c;
        int SquareNum = 0;
        while (true) {
            while (StdDraw.hasNextKeyTyped()) {
                c = StdDraw.nextKeyTyped();
                c = Character.toLowerCase(c);
                switch (c) {
                    case 'n':
                        SquareNum++;
                        addRandomSquare(world, RANDOM);
                        break;
                    case 's':
                        save(SquareNum);
                        break;
                    case 'l':
                        SquareNum = load(world, SquareNum);
                        break;
                    case 'q':
                        System.exit(0);
                        break;
                    default:
                        break;
                };
            }

            String text = "Number of squares:" + SquareNum;
            ter.drawTiles(world);
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.textLeft(1, 17, text);
            StdDraw.show();
        }
    }
}