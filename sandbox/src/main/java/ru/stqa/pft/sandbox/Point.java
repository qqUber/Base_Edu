package ru.stqa.pft.sandbox;

public class Point {

  double x;
  double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double distance(Point p2){
    double u;
    u = Math.pow((p2.x- this.x), 2)+Math.pow((p2.y- this.y), 2);
    //u = ((p2.x - this.x) * (p2.x - this.x) + (p2.y - this.y) * (p2.y - this.y));
    return Math.sqrt(u);
  }
}