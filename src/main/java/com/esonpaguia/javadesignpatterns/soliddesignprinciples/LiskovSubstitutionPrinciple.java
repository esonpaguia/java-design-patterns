package com.esonpaguia.javadesignpatterns.soliddesignprinciples;

/**
 * LSP - Liskov Substitution Principle
 * @author Eson Paguia
 */
class LiskovSubstitutionPrinciple {

    static void useItOld(Rectangle r) {
        int width = r.getWidth();
        r.setHeight(10);
        System.out.println("useItOld: Expected area of " +
                (width * 10) +
                ", got " +
                r.getArea());
    }

    static void useItSolution1(Rectangle r) {
        int width = r.getWidth();
        int height = width;
        if (!r.isSquare()) { // Solution 1: band-aid solution
            height = 10;
            r.setHeight(height);
        }
        System.out.println("Solution 1: Expected area of " +
                (width * height) +
                ", got " +
                r.getArea());
    }

    public static void main(String[] args) {
        System.out.println("A normal Rectangle");
        Rectangle rc = new Rectangle(2, 3);
        useItOld(rc);

        System.out.println("A normal Square");
        Rectangle sq = new Square();
        sq.setHeight(5);
        useItOld(sq);
        useItSolution1(sq);

        System.out.println("Solution 2: A Factory Square");
        sq = RectangleFactory.newSquare(5);
        useItOld(sq);

    }
}

class Rectangle {
    protected int width, height;

    public Rectangle() {
    }

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getArea() {
        return width * height;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }

    // Solution 1: band-aid solution
    public boolean isSquare() {
        return width == height;
    }

}

class Square extends Rectangle {

    public Square() {
    }

    public Square(int size) {
        width = height = size;
    }

    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        super.setHeight(width);
    }

    @Override
    public void setHeight(int height) {
        super.setHeight(height);
        super.setWidth(height);
    }
}

// Solution 2
class RectangleFactory {

    public static Rectangle newSquare(int size) {
        return new Rectangle(size, size);
    }

    public static Rectangle newRectangle(int width, int height) {
        return new Rectangle(width, height);
    }

}
