package com.sasa.test.__final_choice;

import java.awt.*;

public class MazeSolver
{

    public int[][] maze1 = {
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0},
            {1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };
    public int[][] maze = null;
    private Point mStart;
    private Point mFinish;
    private int mRow = 0, mCol = 0;

    public MazeSolver(final Point start, final Point finish)
    {
        mStart = new Point(start);
        mFinish = new Point(finish);
        System.out.print("mCol: " + mCol + "; mRow: " + mRow);
    }

    public void SetMaze(int[][] maze)
    {
        this.maze=maze;
        mRow = this.maze.length;
        mCol = this.maze[0].length;
    }

    //===========================================================
    //  Prints the maze grid.
    //===========================================================
    public void print_maze()
    {

        System.out.println();

        for (int row = 0; row < maze.length; row++)
        {
            for (int column = 0; column < maze[row].length; column++)
            {
                System.out.print(maze[row][column]);
            }
            System.out.println();
        }

        System.out.println();

    }  // method print_maze


    //===========================================================
    //  Attempts to recursively traverse the maze.  It inserts
    //  special characters indicating locations that have been
    //  tried and that eventually become part of the solution.
    //===========================================================
    public void Solve()
    {
        if (solve(mStart.x, mStart.y))
        {
            System.out.println("Maze solved!");
        }
        else
        {
            System.out.println("No solution.");
        }
        maze[mStart.x][mStart.y] = 8;
        maze[mFinish.x][mFinish.y] = 9;
    }

    public boolean solve(int row, int column)
    {

        boolean done = false;

        if (valid(row, column))
        {

            maze[row][column] = 3;  // cell has been tried

            //if (row == maze.length - 1 && column == maze[0].length - 3)
            if (row == mFinish.x && column == mFinish.y)
            {
                //mFinish.x = row;
                //mFinish.y = column;//
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
                maze[row][column] = 7;
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
        if (row >= 0 && row < maze.length && column >= 0 && column < maze[0].length)

            //  check if cell is not blocked and not previously tried
            if (maze[row][column] == 0)
            {
                result = true;
            }

        return result;

    }  // method valid


    public static void main(String[] args)
    {
        ShowSolution v = new ShowSolution();
        MazeSolver maze = new MazeSolver(new Point(37, 10), new Point(8, 18));// new Point(7, 10));
        MazeBuilder mb = new MazeBuilder();
        maze.maze = mb.GetMaze();
        maze.print_maze();
        maze.Solve();

        v.maze = maze.maze;
        v.setVisible(true);
        maze.print_maze();

    }  // method main


}//class MazeSolver
