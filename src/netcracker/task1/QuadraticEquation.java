package netcracker.task1;

import java.util.Scanner;

/**
 * Class of quadratic equation.
 * It includes three double coefficients: a, b, c - to show a quadratic equation like a*x^2 + b*x + c
 * and the root of the discriminant.
 */

public class QuadraticEquation {
    private final double a, b, c;
    private final Discriminant discriminant;

    public QuadraticEquation(){
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.discriminant = new Discriminant(a, b, c);
    }

    public QuadraticEquation(double a, double b, double c){
        this.a = a;
        this.b = b;
        this.c = c;
        this.discriminant =  new Discriminant(a, b, c);
    }

    /**
     * Static nested class of Discriminant.
     * It includes the absolute value of the root of the discriminant - absDiscr
     * and shows if it is complex or not.
     * This class helps to operate with complex roots.
     */
    private static class Discriminant {
        private final double absDiscr;
        private boolean complex = false;

        public Discriminant(double a, double b, double c){
            double d = b*b - 4*a*c;
            absDiscr = Math.sqrt(Math.abs(d));
            if ((d) < 0){
                complex = true;
            }
        }
    }

    /**
     * @return a coefficient
     */
    public double getA() {
        return a;
    }

    /**
     * @return b coefficient
     */
    public double getB() {
        return b;
    }

    /**
     * @return c coefficient
     */
    public double getC() {
        return c;
    }

    /**
     * @return Equation in common form
     */
    public String getEquation(){
        return (a)+"x^2 + "+(b)+"x + "+(c)+"=0";
    }

    /**
     * @return Roots of the quadratic equation (including complex)
     * or shows that there are no solutions or an infinite number of them
     */
    public String result(){
        if (a == 0){
            if (b == 0){
                if (c == 0){
                    return "An infinite number of solutions";
                }
                return "There are no solutions";
            }
            return "x = "+((-c)/(b));
        }
        if (discriminant.absDiscr == 0){
            return "x = "+((-b)/(2*a));
        }
        if (!discriminant.complex){
            return "x1 = "+((-b+discriminant.absDiscr)/(2*a))+"\nx2 = "+((-b-discriminant.absDiscr)/(2*a));
        }
        return "x1 = "+(-b)+" + "+(discriminant.absDiscr)+"i / "+(2*a)+"\nx2 = "+(-b)+" - "+(discriminant.absDiscr)+"i / "+(2*a);
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter coefficients of your equation (a*x^2 + b*x +c = 0):");
        System.out.println("a = ");
        double myA = scanner.nextDouble();
        System.out.println("b = ");
        double myB = scanner.nextDouble();
        System.out.println("c = ");
        double myC = scanner.nextDouble();
        QuadraticEquation myEquation = new QuadraticEquation(myA, myB, myC);
        System.out.println("Your equation:");
        System.out.println(myEquation.getEquation());
        System.out.println("Result:");
        System.out.println(myEquation.result());
    }
}
