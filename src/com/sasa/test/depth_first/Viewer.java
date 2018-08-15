package com.sasa.test.depth_first;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * @author leo
 */
public class Viewer extends JFrame
{

    /**
     * Conventions:
     * <p>
     * maze[row][col]
     * <p>
     * Values: 0 = not-visited node
     * 1 = wall (blocked)
     * 2 = visited node
     * 9 = target node
     * <p>
     * borders must be filled with "1" to void ArrayIndexOutOfBounds exception.
     */
    private int[][] maze =
            {
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1},
                    {1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 9, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1},
                    {1, 0, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1},
                    {1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1},
                    {1, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}

/*
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1},
                    {1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 9, 1},
                    {1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1},
                    {1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1},
                    {1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1},
                    {1, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1},
                    {1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
*/


            };


    //private int[][] maze = null;


    private final int k = 20;
    private final List<Integer> path = new ArrayList<Integer>();
    private int pathIndex;

    public Viewer()
    {
        setTitle("Simple Maze Solver");
        setSize(640, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DepthFirst.searchPath(maze, 1, 1, path);

        pathIndex = path.size() - 2;
    }

    public Viewer(int x0, int y0, int x1, int y1)
    {
        setTitle("Simple Maze Solver 2");
        setSize(1000, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        maze = GetMaze();
        maze[8][18] = 9;
        DepthFirst.searchPath(maze, 10, 37, path);

        pathIndex = path.size() - 2;
    }

    protected BufferedImage ReadImg(final String file)
    {
        BufferedImage img = null;
        File f = null;

        //read image
        try
        {
            f = new File("C:/Users/afrig/Downloads" + "/" + file);
            img = ImageIO.read(f);
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
        return img;
    }

    public int[][] GetMaze()
    {
        BufferedImage img = ReadImg("my-blueprint.png");
        int width = img.getWidth();
        int height = img.getHeight();
        int k = 20;
        int s = k / 2;
        int[][] maze = new int[height / k][width / k];
        {
            int i = 0, j = 0;
            for (int y = s; y < height; y += k)
            {
                if (i >= 40)
                {
                    break;
                }
                for (int x = s; x < width; x += k)
                {
                    if (j >= 40)
                    {
                        break;
                    }
                    int p = img.getRGB(x, y);
                    if (p == -1)// white
                    {
                        maze[i][j] = 0;
                    }
                    else
                    {
                        maze[i][j] = 1;
                    }
                    j++;
                }
                j = 0;
                i++;
            }
        }
        return maze;
    }


    @Override
    public void paint(Graphics g)
    {
        super.paint(g);

        g.translate(50, 50);
        int endX = 0, endY = 0;
        // draw the maze
        for (int row = 0; row < maze.length; row++)
        {
            for (int col = 0; col < maze[0].length; col++)
            {
                Color color;
                switch (maze[row][col])
                {
                    case 1:
                        color = Color.BLACK;
                        break;
                    case 9:
                        endX = col;
                        endY = row;
                        color = Color.RED;
                        break;
                    default:
                        color = Color.WHITE;
                }
                g.setColor(color);
                g.fillRect(k * col, k * row, k, k);
                g.setColor(Color.BLACK);
                g.drawRect(k * col, k * row, k, k);
            }
        }

        // draw the path list
        for (int p = 0; p < path.size(); p += 2)
        {
            int pathX = path.get(p);
            int pathY = path.get(p + 1);
            g.setColor(Color.GREEN);
            g.fillRect(pathX * k + 1, pathY * k + 1, k - 2, k - 2);
        }

        // draw the ball on path
        int pathX = path.get(pathIndex);
        int pathY = path.get(pathIndex + 1);
        g.setColor(Color.RED);
        g.fillOval(pathX * k, pathY * k, k, k);
        g.setColor(Color.BLUE);
        g.fillOval(endX * k, endY * k, k, k);
    }

    @Override
    protected void processKeyEvent(KeyEvent ke)
    {
        if (ke.getID() != KeyEvent.KEY_PRESSED)
        {
            return;
        }
        if (ke.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            pathIndex -= 2;
            if (pathIndex < 0)
            {
                pathIndex = 0;
            }
        }
        else if (ke.getKeyCode() == KeyEvent.VK_LEFT)
        {
            pathIndex += 2;
            if (pathIndex > path.size() - 2)
            {
                pathIndex = path.size() - 2;
            }
        }
        repaint();
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
              Viewer view = new Viewer();
//                Viewer view = new Viewer(1, 1, 11, 2);
                view.setVisible(true);
            }
        });
    }

}
