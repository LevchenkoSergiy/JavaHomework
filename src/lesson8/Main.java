package lesson8;

public class Main {
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

        ShapePrinter.printShapeName(circle);
        ShapePrinter.printShapeName(quad);
        ShapePrinter.printShapeName(triangle);
        ShapePrinter.printShapeName(pentagon);
        ShapePrinter.printShapeName(hexagon);
    }
}
