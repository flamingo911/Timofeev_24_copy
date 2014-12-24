package com.company;

import java.util.ArrayList;

/**
 * Created by Iliya on 23.12.2014.
 */
public class GRAPH {
    /**
     * Size of graph
     */
    private int graphSize = 0;
    /**
     * Dynamic array that contains list of edges
     */
    private ArrayList<edge> setOfEdges = new ArrayList<edge>();

    /**
     * Sets size of graph
     * @param size - Size of graph
     * @return Status set size of graph
     */
    public Boolean setGraphSize(int size) {
        if (size > 0) {
            graphSize = size;
            return true;
        } else return false;
        }

    /**
     * Creates new edge.
     * @param line - Parameter string edge.
     */
    public void createEdge(String line) {
        int[] parametres = new int[4];
        String buffer = "";
        edge newEdge = new edge();
        line = line.replaceAll("[\\s]+", " ");
        line = line.replaceAll("[\\s]+", "");
        for (int i = 0, k = 0; i < line.length(); i++) {
            if (line.charAt(i) != ' ') {
                buffer += line.charAt(i);
            } else {
                parametres[k] = Integer.parseInt(buffer);
                buffer = "";
                k++;
            }
        }

        for (int i = 0; i < 4; i++) {
            parametres[i] = Character.getNumericValue(buffer.charAt(i));
        }
        newEdge.setParameters(parametres);
        setOfEdges.add(newEdge);
    }

    /**
     * Counts number of outgoing arcs from each vertex.
     * @return
     */
    public String[] countArc() {
        int[] counterMatrix = new int[graphSize];
        for (edge someEdge : setOfEdges) {
            counterMatrix[someEdge.getParameters()[0] - 1]++;
        }
        String[] output = new String[graphSize];
        for (int index = 0; index < graphSize; index++) {
            output[index] = "From " + (index + 1) + " vertex comes " + counterMatrix[index] + " arcs.";
        }
        return output;
        }

    /**
     *
     * @return most light/heavy edge.
     */
    public String[] getMinMaxEdges() {
        String[] output = new String[2];
        int min = 500, max = -500;
        for (edge someEdge : setOfEdges) {
            int[] bufferParameters = someEdge.getParameters();
            if (bufferParameters[3] < min) {
                output[0] = "Edge " + bufferParameters[0] + "-" + bufferParameters[1] + " has smallest weight: " + bufferParameters[3];
                min = bufferParameters[3];
            }
            if (bufferParameters[3] > max) {
                output[1] = "Edge " + bufferParameters[0] + "-" + bufferParameters[1] + " has biggest weight: " + bufferParameters[3];
                max = bufferParameters[3];
            }
        }
        return output;
    }

    /**
     *
     * @return neighborhood matrix for graph.
     */
    private int[][] makeMatrix() {
        int[][] output = new int[graphSize][graphSize];
        for (int i = 0; i < graphSize; i++) {
            for (int j = 0; j < graphSize; j++) {
                if (i == j) {
                    output[i][i] = 0;
                } else {
                    output[i][j] = 999;
                }
            }
        }
        for (edge someEdge : setOfEdges) {
            int[] buffer = someEdge.getParameters();
            output[buffer[0] - 1][buffer[1] - 1] = buffer[2];
        }
        return output;
    }

    /**
     * Floyd's algorithm
     * @param matrix
     * @return matrix of shortest paths
     */
    private int[][] floydMatrix(int[][] matrix) {
            for (int k = 0; k < graphSize; k++)
                for (int i = 0; i < graphSize; i++)
                    for (int j = 0; j < graphSize; j++) {
                        if (matrix[i][k] + matrix[k][j] < matrix[i][j]) matrix[i][j] = matrix[i][k] + matrix[k][j];
                    }
        return matrix;
    }

    /**
     *
     * @return matrix of minimal paths in string-view
     */
    public String[] getLengthsMatrix() {
        String[] output = new String[graphSize];
        for (int i = 0; i < output.length; i++) output[i] = "";
        int[][] matrix = floydMatrix(makeMatrix());
        for (int i = 0; i < graphSize; i++) {
            for(int j = 0; j< (graphSize); j++) {
                if(matrix[i][j] != 999) {
                    output[i] += matrix[i][j] + "\t";
                } else {
                    output[i] += "X\t";
                }
            }
        }
        return output;
    }
}





