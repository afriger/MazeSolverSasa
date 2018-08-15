package com.sasa.test.__final_choice;

import javax.swing.*;
import java.awt.*;

public class ShowSolution extends JFrame
{
    public int[][] maze = null;
    private final int k = 20;

    public ShowSolution()
    {
        setTitle("Simple Maze Solver");
        setSize(1000, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);

        g.translate(50, 50);
        int endX = 0, endY = 0;
        int startX = 0, startY = 0;
        // draw the maze
        for (int row = 0; row < maze.length; row++)
        {
            for (int col = 0; col < maze[0].length; col++)
            {
                Color color = Color.WHITE;
                switch (maze[row][col])
                {
                    case 1:
                        color = Color.BLACK;
                        break;
                    case 3:
                        color = Color.YELLOW;
                        break;
                    case 7:
                        color = Color.GREEN;
                        break;
                    case 9:
                        endX = col;
                        endY = row;
                        break;
                    case 8:
                        startX = col;
                        startY = row;
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
        g.setColor(Color.RED);
        g.fillOval(startX * k, startY * k, k, k);
        g.setColor(Color.BLUE);
        g.fillOval(endX * k, endY * k, k, k);
    }
}
