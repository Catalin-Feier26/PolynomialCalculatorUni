package org.example;
import java.util.HashMap;
import java.util.regex.*;
public class Polynomial
{
    public HashMap<Integer,Double> polynom;
    public Polynomial(){
        polynom=new HashMap<>();
    }
    public void readPolynomial(String input)
    {
        input=input.replace(" ","");
        if(!checkValidInput(input))
            throw new RuntimeException("Invalid input");

        input=input.replace("-","+-");
        String[] splitInput=input.split("\\+");

        for(String arr: splitInput)
        {
            String[] monomials=arr.split("\\^");

            double coefficient;
            int power;

            if(monomials.length==1)
            {
                if(monomials[0].contains("x"))
                {
                    monomials[0] = monomials[0].replace("x","");
                    coefficient =  Double.parseDouble(monomials[0]);
                    power=1;
                }
                else
                {
                    coefficient = Double.parseDouble(monomials[0]);
                    power = 0;
                }
            }
            else
            {
                monomials[0] = monomials[0].replace("x","");
                coefficient=Double.parseDouble(monomials[0]);
                power=Integer.parseInt(monomials[1]);
            }
             polynom.put(power,coefficient);
        }
    }

    public void printPolynomial()
    {
        for(int power: polynom.keySet())
        {
            double coeff=polynom.get(power);
            if(power==0)
            {
                if(coeff>0)
                    System.out.print("+" + coeff );
                else
                    System.out.print(coeff);
            }
            else if(power == 1)
            {
                if(coeff>0)
                    System.out.print("+" + coeff+"x");
                else
                    System.out.print(coeff+"x");
            }
            else
            {
                if(coeff>0)
                    System.out.print("+" + coeff+"x^" + power );
                else
                    System.out.print(coeff+"x^" + power);
            }
        }
    }
    private Boolean checkValidInput(String input)
    {
        String polynomPat= "([+-]?\\d*(x)?(\\^\\d+)?)+";
        Pattern pattern= Pattern.compile(polynomPat);
        Matcher matcher= pattern.matcher(input);

        return matcher.matches();
    }
}
