package com.sasa.test;

import com.sasa.test.depth_first.Viewer;
import com.sasa.test.maze_solver.MazeGenerator;
import com.sasa.test.maze_solver.MazeSolver;

import java.io.IOException;

public class Main
{
  /*  enum Kaka
    {
        Z(5);
        int  z;
        Kaka(int z)
        {
            this.z=z;
        }

    }*/

    public static void main(String[] args)
    {
        //Viewer
        boolean zoo = false;
        if (zoo)
        {
            MazeGenerator.main(args);
        }
        else
        {
            try
            {
                //
                MazeSolver.main(args);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

    }
}
