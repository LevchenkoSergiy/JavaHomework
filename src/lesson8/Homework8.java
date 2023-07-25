package lesson8;

abstract class Shape {
    public abstract String getName();
}

// Класи фігур, що наслідуються від Shape
class Circle extends Shape {

    public String getName() {
        return "Circle";
    }
}

class Quad extends Shape {

    public String getName() {
        return "Quad";
    }
}

class Triangle extends Shape {

    public String getName() {
        return "Triangle";
    }
}

class Pentagon extends Shape {

    public String getName() {
        return "Pentagon";
    }
}

class Hexagon extends Shape {

    public String getName() {
        return "Hexagon";
    }
}

class ShapePrinter {
    public void printShapeName(Shape shape) {
        System.out.println("Shape name: " + shape.getName());
    }
}

class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    public void run() {
        Shape circle = new Circle();
        Shape quad = new Quad();
        Shape triangle = new Triangle();
        Shape pentagon = new Pentagon();
        Shape hexagon = new Hexagon();

        ShapePrinter shapePrinter = new ShapePrinter();

        shapePrinter.printShapeName(circle);
        shapePrinter.printShapeName(quad);
        shapePrinter.printShapeName(triangle);
        shapePrinter.printShapeName(pentagon);
        shapePrinter.printShapeName(hexagon);
    }
}


