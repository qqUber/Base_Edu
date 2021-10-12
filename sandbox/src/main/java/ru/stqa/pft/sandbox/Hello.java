package ru.stqa.pft.sandbox;

class Hello {
    public static void main(String[] args) {
        Point s1 = new Point(-1,3);
        Point s2 = new Point(6,2);
        System.out.println(s1.distance(s2));
    }

    public static double distance(Point p1, Point p2){
        double u;
        u = ((p2.x- p1.x)*2+(p2.y- p1.y)*2)*2;//((p1) * (p2.x - this.x) + (p2.y - this.y) * (p2.y - this.y));
        return Math.sqrt(u);
    }
}
