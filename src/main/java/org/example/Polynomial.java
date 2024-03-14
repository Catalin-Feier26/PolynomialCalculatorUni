package org.example;
import java.util.HashMap;
import java.util.regex.*;
public class Polynomial
{
    private HashMap<Integer,Double> polynom;
    public Polynomial()
    {
        polynom=new HashMap<>();
    }
    public HashMap<Integer,Double> getPolynom()
    {
        return polynom;
    }

    public void setPolynom(HashMap<Integer, Double> polynom)
    {
        this.polynom = polynom;
    }

    public void readPolynomial(String input) {
        input=input.replace(" ","");
        if(!checkValidInput(input))
            throw new RuntimeException("Invalid input");
        input=input.replace("-","+-");
        String[] splitInput=input.split("\\+");
        for(String arr: splitInput)
        {
            if(arr.isEmpty())
                continue;
            String[] monomials=arr.split("\\^");
            double coefficient;
            int power;
            if(monomials.length==1)
            {
                if(monomials[0].contains("x"))
                {
                    if(!monomials[0].equals("x") && !monomials[0].equals("-x")) {
                        monomials[0] = monomials[0].replace("x", "");
                        coefficient = Double.parseDouble(monomials[0]);
                    } else {
                        if(monomials[0].equals("x"))
                            coefficient=1.0;
                        else
                            coefficient=-1.0;
                    }
                    power = 1;
                } else {
                    coefficient = Double.parseDouble(monomials[0]);
                    power = 0;
                }
            } else {
                if(monomials[0].equals("x")) {
                    coefficient=1;
                }else if(monomials[0].equals("-x"))
                    coefficient=-1.0;
                else {
                    monomials[0] = monomials[0].replace("x", "");
                    coefficient = Double.parseDouble(monomials[0]);
                }
                power=Integer.parseInt(monomials[1]);
            }
             polynom.put(power,polynom.getOrDefault(power, 0.0) + coefficient);
        }
    }

    public void printPolynomial()
    {
        System.out.println(this);
    }
    public String toString(){
        String result="";
        for(Integer power: this.polynom.keySet())
        {
            double coefficient=polynom.get(power);
            if(power==0){
                if(result.isEmpty())
                    result=result.concat(checkCoefficient(coefficient));
                else{
                    if(coefficient>0)
                        result=result.concat("+"+checkCoefficient(coefficient));
                    else
                        result=result.concat(checkCoefficient(coefficient));
                }
            }
            else if(power==1){
                if(result.isEmpty())
                    if(!checkCoefficient(coefficient).equals("1") && !checkCoefficient(coefficient).equals("-1"))
                        result=result.concat(checkCoefficient(coefficient)+"x");
                    else
                        if(checkCoefficient(coefficient).contains("-"))
                            result=result.concat("-x");
                        else
                            result=result.concat("x");
                else{
                    if(coefficient==1)
                        result=result.concat("+x");
                    if(coefficient==-1)
                        result=result.concat("-x");
                    if(coefficient > 0 && coefficient != 1)
                        result=result.concat("+"+checkCoefficient(coefficient)+"x");
                    if(coefficient<0 && coefficient != -1)
                        result=result.concat(checkCoefficient(coefficient)+"x");
                }
            }else{
                if(result.isEmpty())
                    if(!checkCoefficient(coefficient).equals("1") && !checkCoefficient(coefficient).equals("-1"))
                        result=result.concat(checkCoefficient(coefficient)+"x^"+power);
                    else
                    if(checkCoefficient(coefficient).contains("-"))
                        result=result.concat("-x^"+power);
                    else
                        result=result.concat("x^"+power);
                else{
                    if(coefficient==1)
                        result=result.concat("+x^"+power);
                    if(coefficient==-1)
                        result=result.concat("-x^"+power);
                    if(coefficient>0 && coefficient!=1)
                        result=result.concat("+"+checkCoefficient(coefficient)+"x^"+power);
                    if(coefficient<0 && coefficient!=-1)
                        result=result.concat(checkCoefficient(coefficient)+"x^"+power);
                }
            }
        }
        return result;
    }
    private String checkCoefficient(double c)
    {
        if(c==0)
            return "";
        if(c==(int) c)
            return String.valueOf((int) c);
        return String.valueOf(c);
    }
    private Boolean checkValidInput(String input)
    {
        String polynomPat= "([+-]?\\d*(x)?(\\^\\d+)?)+";
        String pat="([+-]?\\d*(\\.\\d*)?(x)?(\\^\\d+)?)+";
        Pattern pattern= Pattern.compile(pat);
        Matcher matcher= pattern.matcher(input);

        return matcher.matches();
    }
}
