package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

//        Polynomial polynomial=new Polynomial();
//        String input="5";
//        System.out.println(input);
//        polynomial.readPolynomial(input);
//        polynomial.printPolynomial();

//        Polynomial polinom1=new Polynomial();
//        Polynomial polinom2=new Polynomial();
//        Polynomial result;
//
//        String input1= "5x^2 + 3x - 5";
//        String input2= "6x - 2";
//        polinom1.readPolynomial(input1);
//        polinom2.readPolynomial(input2);
//
//        result=Operation.addition(polinom1,polinom2);
//        result.printPolynomial();
//        System.out.println();
//        result=Operation.subtraction(polinom1,polinom2);
//        result.printPolynomial();
//        System.out.println();
//        Operation.integration(polinom2).printPolynomial();
//        System.out.println();
//        Operation.derivation(polinom1).printPolynomial();

        Polynomial polynomial1=new Polynomial();
        Polynomial polynomial2=new Polynomial();
        String input1 = "6x^3 - 2x + x + 5";
        String input2 = "x-5";
        polynomial1.readPolynomial(input1);
        polynomial2.readPolynomial(input2);
        polynomial2.printPolynomial();
        System.out.println();
        polynomial1.printPolynomial();
        System.out.println();
        Operation.multiplication(polynomial1,polynomial2).printPolynomial();
    }
}