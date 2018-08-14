package com.sasa.test;

import com.sasa.test.depth_first.Viewer;
import com.sasa.test.graphics.Grayscale;
import com.sasa.test.lee_algorithm.MainLee;
import com.sasa.test.maze_other.Maze;
import com.sasa.test.maze_search.Maze_Search;
import com.sasa.test.maze_solver.MazeGenerator;
import com.sasa.test.maze_solver.MazeSolver;
import com.sasa.test.maze_zero.MazeZero;
import com.sasa.test.path_finding.Grid2d;

import java.io.IOException;

public class Main
{

    public static void main(String[] args)
    {
        //Viewer
        boolean zoo = true;
        if (zoo)
        {
            //Maze
            //MazeGenerator
            //Maze_Search
            //Viewer
            //MainLee
            Grid2d
                    .main(args);
        }
        else
        {

            try
            {
                MazeZero.main(args);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }


   /*         try
            {
                //
                MazeSolver.main(args);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }*/
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
