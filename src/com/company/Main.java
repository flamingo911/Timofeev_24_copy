package com.company;
import sun.security.util.Length;

import java.io.FileNotFoundException;
import java.io.*;
import java.util.*;

public class Main {
    /**
     * Field to store the graph
     */
    private static GRAPH myGraph = new GRAPH();

    /**
     *
     * @param someLine - Some string to check
     * @param number - Number of string at file
     * @return check status line
     */
    private static Boolean checkLine(String someLine, int number) {
        if (someLine.matches("[ ]*[\\d]*[ ]*[\\d]*[ ]*[\\d]*[ ]*[\\d]*[ ]*") && number > 0) {
            return true;
        } else {
            if(someLine.matches("[ ]*[\\d]*[ ]*") && number == 0) {
                someLine = someLine.replaceAll("\\s", "");
                myGraph.setGraphSize(Integer.parseInt(someLine));
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * Displays error if file is empty
     * @param number - Number of string
     */
    private static void checkEmptyFile (int number) {
        if (number == 0) System.err.println("ERROR! Empty file!");
    }

    /**
     * Reads graph from file
     * @param path Path to file
     * @return status of the file reading
     */
    private static Boolean readGraph(String path) {
        try {
            int i = 0;
            Scanner File = new Scanner(new File(path));
            String buffer;
            while ( true ) {
                try {
                    buffer = File.nextLine();
                }
                catch (NoSuchElementException e) {
                    break;
                }
                if (checkLine(buffer, i)) {
                    if (i > 0) {
                        myGraph.createEdge(buffer);
                    }
                } else {
                    System.err.println("ERROR! Invalid line " + i + " !");
                    return false;
                }
                i++;
            }
            checkEmptyFile(i);
        }
        catch (FileNotFoundException e) {
            System.err.println("ERROR! File not found!");
            return false;
        }
        return true;
    }

    /**
     * Display information to file
     * @param path - Path to output file.
     * @param matrix - Matrix of shortest paths.
     * @param arcs - Number of arcs from each vertex
     * @param minMax Lightest and heaviest edges.
     */
    private static void withdrawToFile(String path, String[] matrix, String[] arcs, String[] minMax) {
        try {
            FileWriter writeFile;
            writeFile = new FileWriter (new File( path ));
            writeFile.write("Length matrix\r\n");
            for (String someString : matrix) writeFile.write(someString + "\r\n");
            writeFile.write("\r\nArcs counts\r\n");
            for (String someString : arcs) writeFile.write(someString + "\r\n");
            writeFile.write("\r\nMaximum and minimum edges\r\n");
            for (String someString : minMax) writeFile.write(someString + "\r\n");
            writeFile.close();
        }
        catch (IOException e) {
            System.err.println("ERROR! Failed to create file!");
        }
    }
    //Testing basturd
    public static void main(String[] args) {
        if (args[0] != null) {
            if (readGraph( args[0] )) {
                if (args[1] != null) {
                    withdrawToFile(args[1], myGraph.getLengthsMatrix(), myGraph.countArc(), myGraph.getMinMaxEdges());
                } else {
                    System.err.println("ERROR! Unknown output file!");
                }
            }
        } else {
            System.err.println("ERROR! Unknown input file!");
        }
    }
}
