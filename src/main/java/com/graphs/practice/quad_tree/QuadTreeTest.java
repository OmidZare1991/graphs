package com.graphs.practice.quad_tree;

import javax.sound.midi.Soundbank;
import java.util.List;

public class QuadTreeTest {
    public static void main(String[] args) {
        Region area = new Region(0, 0, 400, 400);
        QuadTree quadTree = new QuadTree(area);
        float[][] points = new float[][]{{21, 25}, {55, 53}, {70, 318}, {98, 302},
                {49, 229}, {135, 229}, {224, 292}, {206, 321}, {197, 258}, {245, 238}};

        for (int i = 0; i < points.length; i++) {
            Point point = new Point(points[i][0], points[i][1]);
            quadTree.addPoint(point);
        }

        Region searchArea = new Region(200, 200, 250, 250);
        List<Point> result = quadTree.search(searchArea, null);
        System.out.println(result);

        // So, if we're given a point and asked to find the nearest n neighbors, we could define a suitable search area where the given point is at the center.
        //Then, from all the resulting points of the search operation,
        // we can calculate the Euclidean distances between the given points and sort them to get the nearest neighbors.


        quadTree.search(new Point(245, 238));
        quadTree.search(new Point(21, 25));
        quadTree.search(new Point(21, 26));

    }
}
