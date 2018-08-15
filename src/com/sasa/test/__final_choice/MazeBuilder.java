package com.sasa.test.__final_choice;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MazeBuilder
{
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

}//class MazeBuilder
