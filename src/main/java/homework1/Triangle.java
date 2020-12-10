package homework1;

public class Triangle implements ShapeFunctionality{
    double[] sides;
    double[] angles;
    String message;

    public Triangle(double[] sides, double[] angles){
        if(isCorrect(sides, angles)){
            this.sides = sides;
            this.angles = angles;
        }
        this.sides = null;
        this.angles = null;
    }

    public Triangle(double sideA, double sideB, double sideC, float angleA,float angleB,float angleC){
        sides = new double[]{sideA, sideB, sideC};
        angles = new double[]{angleA, angleB, angleC};

        if(!isCorrect(sides, angles)){
            this.sides = null;
            this.angles = null;
        }
    }

    @Override
    public double getArea() {
        return 0;
    }

    @Override
    public double getPerimeter() {
        return 0;
    }

    @Override
    public double getCenter() {
        return 0;
    }

    @Override
    public double resize() {
        return 0;
    }

    @Override
    public void draw() {

    }

    private boolean isCorrect(double[] sides, double[] angles){
        boolean isCorrect = true;
        // is it correct data for triangle
        // if isn't return the message
        if(!isCorrect){
            message = "Incorrect data";
            return isCorrect;
        }
        return isCorrect;
    }
}
