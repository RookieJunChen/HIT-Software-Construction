/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P2.turtle;

import java.util.List;
import java.util.Set;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class TurtleSoup {

    /**
     * Draw a square.
     * 
     * @param turtle the turtle context
     * @param sideLength length of each side
     */
    public static void drawSquare(Turtle turtle, int sideLength) {
    	int i;
    	for(i = 0 ; i < 4 ; i++) //四条边用四次循环并转向即可。
    	{
    		turtle.forward(sideLength);
    		turtle.turn(90);
    	}    	
        
    }

    /**
     * Determine inside angles of a regular polygon.
     * 
     * There is a simple formula for calculating the inside angles of a polygon;
     * you should derive it and use it here.
     * 
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360
     */
    public static double calculateRegularPolygonAngle(int sides) {
        return ((double)sides - 2) * 180 / sides; //由边求多边形的角公式。
    }

    /**
     * Determine number of sides given the size of interior angles of a regular polygon.
     * 
     * There is a simple formula for this; you should derive it and use it here.
     * Make sure you *properly round* the answer before you return it (see java.lang.Math).
     * HINT: it is easier if you think about the exterior angles.
     * 
     * @param angle size of interior angles in degrees, where 0 < angle < 180
     * @return the integer number of sides
     */
    public static int calculatePolygonSidesFromAngle(double angle) {
        int m = (int)(360/(180-angle));
        double n = 360/(180-angle);
        if(n - m > 0.5)
        	return m + 1;
        else
        	return m;
    }

    /**
     * Given the number of sides, draw a regular polygon.
     * 
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
     * 
     * @param turtle the turtle context
     * @param sides number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
        int i;
        double jd = 180 - calculateRegularPolygonAngle(sides); //求应转角度。
        for(i = 0 ; i < sides ; i++) //进行多边形边数次循环以绘图。
        {
        	turtle.forward(sideLength);
        	turtle.turn(jd);
        }
    }

    /**
     * Given the current direction, current location, and a target location, calculate the Bearing
     * towards the target point.
     * 
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing at angle currentBearing. The angle must be expressed in
     * degrees, where 0 <= angle < 360. 
     *
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     * 
     * @param currentBearing current direction as clockwise from north
     * @param currentX current location x-coordinate
     * @param currentY current location y-coordinate
     * @param targetX target point x-coordinate
     * @param targetY target point y-coordinate
     * @return adjustment to Bearing (right turn amount) to get to target point,
     *         must be 0 <= angle < 360
     */
    public static double calculateBearingToPoint(double currentBearing, int currentX, int currentY,
                                                 int targetX, int targetY) {
    	
        int dx = currentX - targetX; //两点横坐标之差
        int dy = currentY - targetY;//两点纵坐标之差
        double jd1 = 90 - Math.atan2((double)-dy,(double) -dx)/Math.PI*180; //利用atan2函数求出其角度。
        double jd2;
        if(jd1 > currentBearing) //分为三种情况讨论得出最后的结果。
           jd2 = jd1 - currentBearing;
        else if(jd1 == currentBearing)
            jd2 = 0;
        else
            jd2 = 360.0 + jd1 - currentBearing;
        return jd2;
        
    }

    /**
     * Given a sequence of points, calculate the Bearing adjustments needed to get from each point
     * to the next.
     * 
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
     * For each subsequent point, assumes that the turtle is still facing in the direction it was
     * facing when it moved to the previous point.
     * You should use calculateBearingToPoint() to implement this function.
     * 
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of Bearing adjustments between points, of size 0 if (# of points) == 0,
     *         otherwise of size (# of points) - 1
     */
    public static List<Double> calculateBearings(List<Integer> xCoords, List<Integer> yCoords) { //以队列形式利用calculateBearingToPoint函数求值。
        
        List<Double> bearings = new ArrayList<>();
        int i;
        double temp;
        for(i = 0 ; i < xCoords.size() - 1; i++)
        {
        	if(i == 0)
        		temp = 0;
        	else
        		temp = bearings.get(i - 1);
        	bearings.add(i,calculateBearingToPoint(temp, xCoords.get(i), yCoords.get(i), xCoords.get(i+1), yCoords.get(i+1)));
        }
        return bearings;
        
    }
    
    /**
     * Given a set of points, compute the convex hull, the smallest convex set that contains all the points 
     * in a set of input points. The gift-wrapping algorithm is one simple approach to this problem, and 
     * there are other algorithms too.
     * 
     * @param points a set of points with xCoords and yCoords. It might be empty, contain only 1 point, two points or more.
     * @return minimal subset of the input points that form the vertices of the perimeter of the convex hull
     */
    public static Set<Point> convexHull(Set<Point> points) {
        Set<Point> hull = new HashSet<Point>();
        int i = 1 , j;
        int n = points.size() + 1;
        Point[] mypoints = new Point[n];
        boolean[] vis = new boolean[n];
        for(Point p : points)
        {
        	mypoints[i] = p;
        	i++;
        }
        if(n > 1) //避免产生数组访问越界问题。
        {	//x优先级最高，y优先级其次的排序（找出最左边的点作为起始点）
        	Point temp;
        	for(i = 1 ; i < n ; i++) 
        	{
        		for(j = i ; j < n ; j++)
        		{
        			if(mypoints[j].x() < mypoints[i].x())
        			{
        				temp = mypoints[i];
        				mypoints[i] = mypoints[j];
        				mypoints[j] = temp;
        			}
        		}
        	}
        	for(i = 1 ; i < n ; i++)
        	{
        		for(j = i ; j < n ; j++)
        		{
        			if(mypoints[j].y() < mypoints[i].y() && mypoints[j].x() == mypoints[i].x())
        			{
        				temp = mypoints[i];
        				mypoints[i] = mypoints[j];
        				mypoints[j] = temp;
        			}
        		}
        	}
            vis[1] = true;//将起始点放入凸包点集中。
            hull.add(mypoints[1]);
        }
        int in = 1;
        while(true) //随机遍历所有点利用向量外积性质寻找凸包上的点。
        {
        	int not = -1; 
        	for (i = 1; i < n ; i++) 
        	 {
                 if (!vis[i]) 
                 {
                     not = i;
                     break;
                 }
        	 }
        	if(not == -1)
        		break;
        	for(i = 1; i < n ; i++)
        	{
        		if ((cross(mypoints[in], mypoints[i], mypoints[not]) > 0) //向量方向为逆时针旋转关系（第二个向量在第一个向量左侧）
                        || (cross(mypoints[in], mypoints[i], mypoints[not]) == 0) //向量共线。
                        && (distance(mypoints[in], mypoints[i]) > distance(mypoints[in], mypoints[not])))//共线是in点距离更远。
                    		not = i; //说明第i个点较原来的not点更符合凸包上的点的性质，因此进行交换。
        	}
        	if(vis[not])
        		break;
        	hull.add(mypoints[not]);
        	vis[not] = true;
        	in = not;
        }	
        return hull;
    }
    
    /**
     * Draw your personal, custom art.
     * 
     * Many interesting images can be drawn using the simple implementation of a turtle.  For this
     * function, draw something interesting; the complexity can be as little or as much as you want.
     * 
     * @param turtle the turtle context
     */
    public static void drawPersonalArt(Turtle turtle) {
        PenColor color = PenColor.RED;
        int i;
        turtle.color(color);//转换颜色。
        for(i = 0 ; i <= 180 ; i+=1) //利用前进与旋转角度的关系画一个半圆。
        {
        	turtle.forward(1);
        	turtle.turn(1);
        }
        for(i = 0 ; i <= 45; i+=1) //画八分之一个圆。
        {
        	turtle.forward(2);
        	turtle.turn(1);
        }
        for(i = 0 ; i <= 37; i+=1) //直线前进。
        {
        	turtle.forward(3);
        }
        turtle.turn(90);
        for(i = 0 ; i <= 37; i+=1) //进行上述操作的对称操作，最后画出一个爱心。
        {
        	turtle.forward(3);
        }
        for(i = 0 ; i <= 45 ; i+=1)
        {
        	turtle.forward(2);
        	turtle.turn(1);
        }
        for(i = 0 ; i <= 180 ; i+=1)
        {
        	turtle.forward(1);
        	turtle.turn(1);
        }
        i = 3;
        for(PenColor c : PenColor.values()) //每次循环幻颜色画多边形。
        {
        	turtle.color(c);
        	drawRegularPolygon(turtle, i, 35);
        	i++;
        }
        turtle.forward(35);
        turtle.turn(180);
        i = 3;
        for(PenColor c : PenColor.values())
        {
        	turtle.color(c);
        	drawRegularPolygon(turtle, i, 35);
        	i++;
        }
        
}


    /**
     * Main method.
     * 
     * This is the method that runs when you run "java TurtleSoup".
     * 
     * @param args unused
     */
    public static void main(String args[]) {
        DrawableTurtle turtle = new DrawableTurtle();

        drawPersonalArt(turtle);

        // draw the window
        turtle.draw();
    }
    public static  double cross(Point c, Point a, Point b)  //向量外积的计算函数。
    {
        return (c.x() - a.x()) * (a.y() - b.y()) - (c.y() - a.y()) * (a.x() - b.x());
    }
    public static double distance(Point p1, Point p2) { //求两点距离的函数。
        return (Math.hypot((p1.x() - p2.x()), (p1.y() - p2.y())));
    }
}
