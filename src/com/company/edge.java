package com.company;

/**
 * Created by Iliya on 22.12.2014.
 */
public class edge {
    //Number of first vertex
    private int numbOfFirstVertex;
    //Number of second vertex
    private int numbOfSecondVertex;
    //Length of edge
    private int length;
    //Weight of edge
    private int weight;

    /**
     * @return parameters of edge
     */
    public int[] getParameters() {
        int[] parameters = new int[4];
        parameters[0] = numbOfFirstVertex;
        parameters[1] = numbOfSecondVertex;
        parameters[2] = length;
        parameters[3] = weight;
        return parameters;
    }

    public Boolean setParameters(int[] parameters) {
        if (parameters.length == 4) {
            numbOfFirstVertex = parameters[0];
            numbOfSecondVertex = parameters[1];
            weight = parameters[2];
            length = parameters[3];
            return true;
        } else return false;
    }
}
