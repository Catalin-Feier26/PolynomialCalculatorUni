package org.example;
import java.util.HashMap;
public class Operation {
    public static Polynomial addition(Polynomial poli1, Polynomial poli2)
    {
        HashMap<Integer,Double> additionResult=new HashMap<>();
        for(int power: poli1.getPolynom().keySet())
        {
            double cuff=poli1.getPolynom().get(power);
            additionResult.put(power, additionResult.getOrDefault(power, 0.0) + cuff);
        }
        for(int power: poli2.getPolynom().keySet())
        {
            double coefficient=poli2.getPolynom().get(power);
            additionResult.put(power,additionResult.getOrDefault(power,0.0) + coefficient);
        }
        Polynomial result=new Polynomial();
        result.setPolynom(additionResult);
        return result;
    }
    public static Polynomial subtraction(Polynomial poli1, Polynomial poli2)
    {
        HashMap<Integer,Double> subtractionResult=new HashMap<>();
        for(int power: poli1.getPolynom().keySet())
        {
            double coefficient=poli1.getPolynom().get(power);
            subtractionResult.put(power,subtractionResult.getOrDefault(power, 0.0) + coefficient);
        }
        for(int power: poli2.getPolynom().keySet())
        {
            double coefficient=poli2.getPolynom().get(power);
            subtractionResult.put(power,subtractionResult.getOrDefault(power, 0.0) - coefficient);
        }
        Polynomial result=new Polynomial();
        result.setPolynom(subtractionResult);
        return result;
    }
    public static Polynomial multiplication(Polynomial poli1, Polynomial poli2)
    {
        Polynomial result= new Polynomial();
        return result;
    }
    public static Polynomial division(Polynomial poli1, Polynomial poli2)
    {
        Polynomial result= new Polynomial();
        return result;
    }
    public static Polynomial integration(Polynomial poli)
    {
        HashMap<Integer,Double> integrate=new HashMap<>();
        for(Integer power: poli.getPolynom().keySet())
        {
            double coefficient=poli.getPolynom().get(power);
            coefficient= coefficient/(power+1);
            integrate.put(power+1,coefficient);
        }
        Polynomial result= new Polynomial();
        result.setPolynom(integrate);
        return  result;
    }
    public static Polynomial derivation(Polynomial poli)
    {
        HashMap<Integer,Double> derivate=new HashMap<>();
        for(Integer power: poli.getPolynom().keySet())
        {
            if(power!=0)
            {
                double coefficient=poli.getPolynom().get(power);
                coefficient=coefficient*power;
                derivate.put(power-1,coefficient);
            }
        }
        Polynomial result = new Polynomial();
        result.setPolynom(derivate);
        return result;
    }
}
