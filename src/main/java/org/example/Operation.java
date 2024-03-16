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
            if(additionResult.get(power)==0)
                additionResult.remove(power);
        }
        for(int power: poli2.getPolynom().keySet())
        {
            double coefficient=poli2.getPolynom().get(power);
            additionResult.put(power,additionResult.getOrDefault(power,0.0) + coefficient);
            if(additionResult.get(power)==0)
                additionResult.remove(power);
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
            if(subtractionResult.get(power)==0.0)
                subtractionResult.remove(power);
        }
        for(int power: poli2.getPolynom().keySet())
        {
            double coefficient=poli2.getPolynom().get(power);
            subtractionResult.put(power,subtractionResult.getOrDefault(power, 0.0) - coefficient);
            if(subtractionResult.get(power)==0.0)
                subtractionResult.remove(power);
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

    public static Polynomial[] division(Polynomial numerator, Polynomial denominator)
    {
        Polynomial quotient= new Polynomial();
        Polynomial remainder= new Polynomial();
        if(numerator.getDegree()<denominator.getDegree())
            return new Polynomial[] {quotient,remainder};

        Polynomial copyNumerator= new Polynomial();
        copyNumerator=numerator;
            while (copyNumerator.getDegree() >= denominator.getDegree()) {
                if (copyNumerator.toString().equals("0"))
                    break;
                int powerNum = copyNumerator.getMaxPower();
                int powerdDenom = denominator.getMaxPower();

                double coeffNum = copyNumerator.getPolynom().get(powerNum);
                double coeffDenom = denominator.getPolynom().get(powerdDenom);

                double coeffQuo = coeffNum / coeffDenom;
                int powerQuo = powerNum - powerdDenom;

                Polynomial auxQuotient = new Polynomial();
                auxQuotient.getPolynom().put(powerQuo, coeffQuo);
                quotient = addition(quotient, auxQuotient);

                Polynomial quoDenomProduct = new Polynomial();
                quoDenomProduct = multiplication(denominator, auxQuotient);
                copyNumerator = subtraction(copyNumerator, quoDenomProduct);
            }
            remainder = copyNumerator;
            return new Polynomial[]{quotient, remainder};
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
