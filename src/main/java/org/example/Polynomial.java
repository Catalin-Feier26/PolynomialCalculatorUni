package org.example;
import java.util.HashMap;

public class Polynomial {
    public HashMap<Integer,Double> polynom;
    public Polynomial(){
        polynom=new HashMap<>();
    }
    public void readPolynomial(String input)
    {
        input=input.replace("-","+-");
        input=input.replace(" ","");

        System.out.println(input);
        String[] splitInput=input.split("\\+");
        for(String arr: splitInput){
            System.out.printf(arr + "\t");
            String[] monomials=arr.split("x");
            for(String monom: monomials){
                System.out.printf(monom+" ");
            }
            System.out.println();
        }
    }
}
