package com.sasa.test;


import com.sasa.test.__final_choice.MazeSolver;
import com.sasa.test.depth_first.Viewer;
import com.sasa.test.graphics.Grayscale;
import com.sasa.test.maze_solver.MazeGenerator;
import com.sasa.test.maze_solver.MazeSolverTest;

import java.io.IOException;

public class Main
{
    public static void main(String[] args)
    {
        Viewer


        //MazeSolver
        .main(args);
        System.out.println("Ok");
    }

    public static void main1(String[] args)
    {
        //Viewer
        boolean zoo = true;
        if (zoo)
        {
            //Maze
            MazeGenerator
                    //Maze_Search
                    //Viewer
                    //MainLee
                    //Grid2d
                    .main(args);
        }
        else
        {
/*
            try
            {
                MazeZero.main(args);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

*/
            try
            {
                //
                MazeSolverTest.main(args);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

    }

    public static void main_1(String[] args)
    {
        try
        {
            Grayscale.main(args);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
