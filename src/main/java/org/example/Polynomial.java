package org.example;
import java.util.HashMap;
import java.util.regex.*;
public class Polynomial
{
    private HashMap<Integer,Double> polynom;
    private int degree;
    public Polynomial()
    {
        polynom= new HashMap<>();
        degree=0;
    }
    public int getDegree(){return degree;}
    public HashMap<Integer,Double> getPolynom()
    {
        return polynom;
    }

    //sets the hashMap of a polynomial
    public void setPolynom(HashMap<Integer, Double> polynom)
    {
        this.polynom = polynom;
        degree=getMaxPower();
    }
    //Reading the polynomial from a string
    public void readPolynomial(String input) {
        HashMap<Integer,Double> readPolynom=new HashMap<>();
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
             readPolynom.put(power,readPolynom.getOrDefault(power, 0.0) + coefficient);
        }
        this.polynom=readPolynom;
        degree=getMaxPower();
    }
    // toString method for a more elegant way to display the polynomial
    public String toString(){
        String result="";
        HashMap<Integer, Double> aux= new HashMap<>(this.polynom);
        if(!aux.isEmpty()) {
            while (!aux.isEmpty()) {
                int power=getMax(aux);
                double coefficient = aux.get(power);
                if (power == 0) {
                    if (result.isEmpty())
                        result = result.concat(checkCoefficient(coefficient));
                    else {
                        if (coefficient > 0)
                            result = result.concat("+" + checkCoefficient(coefficient));
                        else
                            result = result.concat(checkCoefficient(coefficient));
                    }
                } else if (power == 1) {
                    if (result.isEmpty())
                        if (!checkCoefficient(coefficient).equals("1") && !checkCoefficient(coefficient).equals("-1"))
                            result = result.concat(checkCoefficient(coefficient) + "x");
                        else if (checkCoefficient(coefficient).contains("-"))
                            result = result.concat("-x");
                        else
                            result = result.concat("x");
                    else {
                        if (coefficient == 1)
                            result = result.concat("+x");
                        if (coefficient == -1)
                            result = result.concat("-x");
                        if (coefficient > 0 && coefficient != 1)
                            result = result.concat("+" + checkCoefficient(coefficient) + "x");
                        if (coefficient < 0 && coefficient != -1)
                            result = result.concat(checkCoefficient(coefficient) + "x");
                    }
                } else {
                    if (result.isEmpty())
                        if (!checkCoefficient(coefficient).equals("1") && !checkCoefficient(coefficient).equals("-1"))
                            result = result.concat(checkCoefficient(coefficient) + "x^" + power);
                        else if (checkCoefficient(coefficient).contains("-"))
                            result = result.concat("-x^" + power);
                        else
                            result = result.concat("x^" + power);
                    else {
                        if (coefficient == 1)
                            result = result.concat("+x^" + power);
                        if (coefficient == -1)
                            result = result.concat("-x^" + power);
                        if (coefficient > 0 && coefficient != 1)
                            result = result.concat("+" + checkCoefficient(coefficient) + "x^" + power);
                        if (coefficient < 0 && coefficient != -1)
                            result = result.concat(checkCoefficient(coefficient) + "x^" + power);
                    }
                }
                aux.remove(power);
            }
        }
        else
            result="0";
        return result;
    }
    //Getting the maximum power from a polynomial hashMap
    private int getMax(HashMap<Integer,Double> map)
    {
        int max=0;
        for(int pow: map.keySet())
            if(pow>=max)
                max=pow;
        return max;
    }
    //Transforms a double like 1.0 into 1, used for the toString method.
    private String checkCoefficient(double c)
    {
        if(c==0)
            return "";
        if(c==(int) c)
            return String.valueOf((int) c);
        return String.valueOf(c);
    }
    //Validates the input via a regex check
    public static Boolean checkValidInput(String input)
    {
        input=input.replace(" ","");
        String pat="([+-]?\\d*(\\.\\d*)?(x)?(\\^\\d+)?)+";
        Pattern pattern= Pattern.compile(pat);
        Matcher matcher= pattern.matcher(input);
        return matcher.matches();
    }
    //Getting the degree of the polynomial
    public Integer getMaxPower()
    {
        Integer maxPower=0;
        for(Integer power: polynom.keySet())
            if(power>=maxPower && polynom.get(power)!=0.0)
                maxPower=power;
        return maxPower;
    }
    //Method to check if the polynomial is 0. (used to check for division);
    public Boolean isZero()
    {
        Boolean assumption = true;
        for(Double coefficient: polynom.values())
            if(coefficient!=0.0)
                assumption=false;
        return assumption;
    }
}
