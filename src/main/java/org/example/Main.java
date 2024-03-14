package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Polynomial polynomial1=new Polynomial();
        String input1 = "3x^2 - x^3 + 2x + 1";
        polynomial1.readPolynomial(input1);
        polynomial1.printPolynomial();
    }
}