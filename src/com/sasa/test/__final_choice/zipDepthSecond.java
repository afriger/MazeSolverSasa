package com.sasa.test.__final_choice;

import java.awt.*;

public class zipDepthSecond
{

    //===========================================================
    //  Creates a new maze, prints its original form, attempts
    //  to solve it, and prints out its final form.
    //===========================================================
    public static void main(String[] args)
    {
        ShowSolution v = new ShowSolution();
        Maze labyrinth = new Maze(0, 0);

        labyrinth.print_maze();

        if (labyrinth.solve(0, 0))
        {
            System.out.println("Maze solved!");
        }
        else
        {
            System.out.println("No solution.");
        }
        labyrinth.SetStart();
        labyrinth.SetFinish();
        v.maze = labyrinth.grid;
        v.setVisible(true);
        labyrinth.print_maze();

    }  // method main

}//class DepthSecond

//-------------------------------------------------------------------
//
//  Class Maze represents a maze of characters.  The goal is to get
//  from the top left corner to the bottom right, following a path
//  of 1's.
//
//  Methods:
//
//     public void print_maze ()
//     public boolean solve (int row, int column)
//     private boolean valid (int row, int column)
//
//-------------------------------------------------------------------

class Maze
{

    public int[][] grid = {
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0},
            {1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    public Point mStart = new Point();
    public Point mFinish = new Point();

    public Maze(int x, int y)
    {
        mStart.x = x;
        mStart.y = y;
    }


    //===========================================================
    //  Prints the maze grid.
    //===========================================================
    public void print_maze()
    {

        System.out.println();

        for (int row = 0; row < grid.length; row++)
        {
            for (int column = 0; column < grid[row].length; column++)
            {
                System.out.print(grid[row][column]);
            }
            System.out.println();
        }

        System.out.println();

    }  // method print_maze

    public void SetStart()
    {
        grid[mStart.x][mStart.y] = 8;
    }
    public void SetFinish()
    {
        grid[mFinish.x][mFinish.y] = 9;
    }

    //===========================================================
    //  Attempts to recursively traverse the maze.  It inserts
    //  special characters indicating locations that have been
    //  tried and that eventually become part of the solution.
    //===========================================================
    public boolean solve(int row, int column)
    {

        boolean done = false;

        if (valid(row, column))
        {

            grid[row][column] = 3;  // cell has been tried

            if (row == grid.length - 1 && column == grid[0].length - 3)
            {
                mFinish.x=row;
                mFinish.y=column;//
                done = true;  // maze is solved
            }
            else
            {
                done = solve(row + 1, column);  // down
                if (!done)
                {
                    done = solve(row, column + 1);  // right
                }
                if (!done)
                {
                    done = solve(row - 1, column);  // up
                }
                if (!done)
                {
                    done = solve(row, column - 1);  // left
                }
            }
            if (done)  // part of the final path
            {
                grid[row][column] = 7;
            }
        }

        return done;

    }  // method solve

    //===========================================================
    //  Determines if a specific location is valid.
    //===========================================================
    private boolean valid(int row, int column)
    {

        boolean result = false;

        // check if cell is in the bounds of the matrix
        if (row >= 0 && row < grid.length && column >= 0 && column < grid[0].length)

            //  check if cell is not blocked and not previously tried
            if (grid[row][column] == 0)
            {
                result = true;
            }

        return result;

    }  // method valid

}  // class Maze