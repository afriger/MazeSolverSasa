package com.sasa.test.graphics;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Grayscale
{
    private final String mPath;

    public Grayscale(final String path)
    {
        mPath = path;
    }

    protected BufferedImage ReadImg(final String file)
    {
        BufferedImage img = null;
        File f = null;

        //read image
        try
        {
            f = new File(mPath + "/" + file);
            img = ImageIO.read(f);
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
        return img;
    }

    protected void WriteImage(final String file, final BufferedImage img)
    {
        File f = null;
        //write image
        try
        {
            f = new File(mPath + "/" + file);
            ImageIO.write(img, "png", f);
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }

    public int[][] GetMaze()
    {
        BufferedImage img = ReadImg("my-blueprint.png");
        int width = img.getWidth();
        int height = img.getHeight();
        int k = 20;
        int s = k / 2;
        int[][] maze = new int[height / k][ width/ k];
        {
            int i = 0, j = 0;
            for (int y = s; y < height; y += k)
            {
                if(i>=40)
                    break;
                for (int x = s; x < width; x += k)
                {
                    if(j>=40)
                        break;
                    int p = img.getRGB(x, y);
/*
                    int a = (p >> 24) & 0xff;
                    int r = (p >> 16) & 0xff;
                    int g = (p >> 8) & 0xff;
                    int b = p & 0xff;
*/

                    if(p==-1)// (r == 255 && g == 255)
                    {
                        maze[i][j] = 0;
                    }
                    else
                    {
                        maze[i][j] = 1;
                        System.out.print(i+";"+j+"\n");
                    }
                    j++;
                }
                j=0;
                i++;
            }
        }


        return maze;
    }

    public void LinedSheet()
    {
        BufferedImage img = ReadImg("map-0.png");
        if (img == null)
        {
            return;
        }
        int k = 20;
        int width = img.getWidth();
        int height = img.getHeight();
        Graphics g = img.getGraphics();
        g.setColor(Color.BLACK);
        for (int y = 0; y < height; y += 20)
        {
            g.drawLine(0, y, width, y);
        }
        for (int x = 0; x < width; x += 20)
        {
            g.drawLine(x, 0, x, height);
        }
        WriteImage("map-l.png", img);
    }

    public BufferedImage Proc(final BufferedImage out)
    {
        BufferedImage img = out;
        //get image width and height
        int width = img.getWidth();
        int height = img.getHeight();
        int[] c = new int[4];
        c[0] = c[1] = c[2] = c[3] = -777;
        //convert to grayscale
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                int p = img.getRGB(x, y);
                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;
                int rgb = (a << 24) | (255 << 16) | (255 << 8) | 255;
                if (r != 255 && g != 255)
                {
                    rgb = (a << 24) | (255 << 16) | (255 << 8) | 255;
                }
                else
                {
                    rgb = (a << 24) | (123 << 16) | (123 << 8) | 123;
                }
                img.setRGB(x, y, rgb);
            }
        }
        return img;
    }

    public static void main(String args[]) throws IOException
    {
        Grayscale gs = new Grayscale("C:/Users/afrig/Downloads");
        BufferedImage img = gs.ReadImg("in.png");
        BufferedImage out = gs.Proc(img);
        gs.WriteImage("out.png", out);
    }


}//class ends here