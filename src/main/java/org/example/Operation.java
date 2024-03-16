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
    public static Polynomial multiplication(Polynomial poli1, Polynomial poli2) {

        HashMap<Integer, Double> mult = new HashMap<>();
        for (int power1 : poli1.getPolynom().keySet())
        {
            double coefficient1 = poli1.getPolynom().get(power1);
            for (int power2 : poli2.getPolynom().keySet())
            {
                double coefficient2 = poli2.getPolynom().get(power2);
                double coefficient = coefficient1 * coefficient2;
                int newPower = power1 + power2;
                mult.put(newPower, mult.getOrDefault(newPower, 0.0) + coefficient);
                if(mult.get(newPower)==0.0){
                    mult.remove(newPower);
                }
            }
        }
        Polynomial result = new Polynomial();
        result.setPolynom(mult);
        return result;
    }

    public static Polynomial[] division(Polynomial poli1, Polynomial poli2)
    {
        Polynomial[] result = new Polynomial[2];
        HashMap<Integer, Double> quotient=new HashMap<>();
        HashMap<Integer, Double> remainder=new HashMap<>();


        result[0].setPolynom(quotient);
        result[1].setPolynom(remainder);
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
