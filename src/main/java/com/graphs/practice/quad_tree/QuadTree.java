package com.graphs.practice.quad_tree;

import java.util.ArrayList;
import java.util.List;

public class QuadTree {
    private static final int MAX_POINTS = 3;
    private Region region;
    private List<Point> points = new ArrayList<>();
    private List<QuadTree> quadTrees = new ArrayList<>();

    public QuadTree(Region region) {
        this.region = region;
    }

    public static int getMaxPoints() {
        return MAX_POINTS;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public List<QuadTree> getQuadTrees() {
        return quadTrees;
    }

    public void setQuadTrees(List<QuadTree> quadTrees) {
        this.quadTrees = quadTrees;
    }

    // we need to check if the point is contained within the boundary of the QuadTree instance.
    // We also need to ensure that the QuadTree instance has not reached the capacity of MAX_POINTS points.
    public boolean addPoint(Point point) {
        if (this.region.containsPoint(point)) {
            if (this.points.size() < MAX_POINTS) {
                this.points.add(point);
                return true;
            } else {
                if (this.quadTrees.isEmpty()) {
                    createQuadrants();
                }
                return addPointToOneQuadrant(point);
            }
        }
        return false;
    }

    // On the other hand, if we've reached the MAX_POINTS value, then we need to add the new point to one of the sub-quadrants.
    // For this, we loop through the child quadTrees list and call the same addPoint method which will return a true value on successful addition.
    // Then we exit the loop immediately as a point needs to be added exactly to one quadrant.
    private boolean addPointToOneQuadrant(Point point) {
        boolean isPointAdded;
        for (int i = 0; i < 4; i++) {
            isPointAdded = this.quadTrees.get(i)
                    .addPoint(point);
            if (isPointAdded)
                return true;
        }
        return false;
    }

    // Additionally, let's have a handy method createQuadrants to subdivide the current quadtree into four quadrants:
    // We'll call this method to create quadrants only if we're no longer able to add any new points.
    // This ensures that our data structure uses optimum memory space.
    private void createQuadrants() {
        Region region;
        for (int i = 0; i < 4; i++) {
            region = this.region.getQuadrant(i);
            quadTrees.add(new QuadTree(region));
        }
    }


    public List<Point> search(Region searchRegion, List<Point> matches) {
        if (matches == null) {
            matches = new ArrayList<>();
        }
        if (!this.region.doesOverlap(searchRegion)) {
            return matches;
        } else {
            for (Point point : points) {
                if (searchRegion.containsPoint(point)) {
                    matches.add(point);
                }
            }
            if (!this.quadTrees.isEmpty()) {
                for (int i = 0; i < 4; i++) {
                    quadTrees.get(i)
                            .search(searchRegion, matches);
                }
            }
        }
        return matches;
    }


    public void search(Point point) {
        if (this.region.containsPoint(point)) {
            for (Point p : points) {
                if ((p.getX() == point.getX()) && (p.getY() == point.getY())) {
                    System.out.println("found in region: " + region);
                    return;
                }
            }
        }
        if (!this.quadTrees.isEmpty()) {
            for (int i = 0; i < 4; i++) {
                quadTrees.get(i)
                        .search(point);
            }
        }
    }

    public double calculateDistanceBetweenPoints(
            double x1,
            double y1,
            double x2,
            double y2) {
        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }
}
