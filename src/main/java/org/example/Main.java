package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Polynomial polynomial=new Polynomial();
        String input="2x+2";
        System.out.println(input);
        polynomial.readPolynomial(input);
        polynomial.printPolynomial();
    }
}