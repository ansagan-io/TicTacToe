abstract class Shape {

    abstract double getPerimeter();

    abstract double getArea();
}

class Triangle extends Shape {
    protected double a;

    protected double b;

    protected double c;

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    double getPerimeter() {
        return (a + b + c);
    }

    @Override
    double getArea() {
        double s = this.getPerimeter() / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }
}

class Rectangle extends Shape {
    protected double a;

    protected double b;

    public Rectangle(double a, double b) {
        this.a = a;
        this.b = b;
    }

    @Override
    double getPerimeter() {
        return (a + b) * 2;
    }

    @Override
    double getArea() {
        return a * b;
    }
}

class Circle extends Shape {
    private final double r;

    public Circle(double r) {
        this.r = r;
    }

    @Override
    double getPerimeter() {
        return 2 * Math.PI * r;
    }

    @Override
    double getArea() {
        return r * r * Math.PI;
    }
}