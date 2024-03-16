package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Polynomial polynomial1=new Polynomial();
        Polynomial polynomial2=new Polynomial();
        String input1 = "5x^2+3x-2";
        String input2="x+1";
        polynomial1.readPolynomial(input1);
        polynomial2.readPolynomial(input2);

        Polynomial[] arr=Operation.division(polynomial1,polynomial2);
        arr[0].printPolynomial();
        arr[1].printPolynomial();
    }
}