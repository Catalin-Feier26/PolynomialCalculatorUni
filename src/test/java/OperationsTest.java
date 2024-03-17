import org.example.Operation;
import org.example.Polynomial;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
public class OperationsTest {
    Polynomial poli1=new Polynomial();
    Polynomial poli2=new Polynomial();
    @Test
    public void addTest(){
        poli1.readPolynomial("x^2-3");
        poli2.readPolynomial("x^6+3x+5");
        assertEquals(Operation.addition(poli1,poli2).toString(),"x^6+x^2+3x+2");

        poli1.readPolynomial("x-2");
        poli2.readPolynomial("-2");
        assertEquals(Operation.addition(poli1,poli2).toString(),"x-4");
    }
    @Test
    public void subTest()
    {
        poli1.readPolynomial("x^3+3x-2");
        poli2.readPolynomial("x^3+2");
        assertEquals(Operation.subtraction(poli1,poli2).toString(),"3x-4");

        poli1.readPolynomial("-x^7+3x");
        poli2.readPolynomial("-6.7x^9+4.5x-1.2");
        assertEquals(Operation.subtraction(poli1,poli2).toString(),"6.7x^9-x^7-1.5x+1.2");
    }
    @Test
    public void multTest()
    {
        poli1.readPolynomial("x^7-3x+1.2");
        poli2.readPolynomial("0.5x");
        assertEquals(Operation.multiplication(poli1,poli2).toString(),"0.5x^8-1.5x^2+0.6x");

        poli1.readPolynomial("-9.2x+3x^7-15");
        poli2.readPolynomial("0");
        assertEquals(Operation.multiplication(poli1,poli2).toString(),"0");

        poli1.readPolynomial("x^2-2");
        poli2.readPolynomial("x^2+2");
        assertEquals(Operation.multiplication(poli1,poli2).toString(),"x^4-4");
    }
    @Test
    public void divisionTest()
    {
        poli1.readPolynomial("x^2-2x+1");
        poli2.readPolynomial("x-1");
        assertEquals(Operation.division(poli1,poli2)[0].toString(),"x-1");
        assertEquals(Operation.division(poli1,poli2)[1].toString(),"0");

        poli1.readPolynomial("x^5+x^3+   2x");
        poli2.readPolynomial("-0.5x^2");
        assertEquals(Operation.division(poli1,poli2)[0].toString(),"-2x^3-2x");
        assertEquals(Operation.division(poli1,poli2)[1].toString(),"2x");
    }
    @Test
    public void testDivisionWithZero()
    {
        poli1.readPolynomial("x^2+3");
        poli2.readPolynomial("0");
        ArithmeticException exception= assertThrows(ArithmeticException.class,()->{
            Operation.division(poli1,poli2);
        });

        assertEquals("Expect a denominator different from 0",exception.getMessage());
    }
    @Test public void wrongDegreeDivision()
    {
        poli1.readPolynomial("x^7+2x^3");
        poli2.readPolynomial("-x^20+15x^2");
        IllegalArgumentException exception=assertThrows(IllegalArgumentException.class,()->{
            Operation.division(poli1,poli2);
        });

        assertEquals("Wrong degree",exception.getMessage());
    }
    @Test
    public void derivationTest()
    {
        poli1.readPolynomial("x^2-2x+1");
        poli2.readPolynomial("8x^7-5x+0.5x^4");
        assertEquals(Operation.derivation(poli1).toString(),"2x-2");
        assertEquals(Operation.derivation(poli2).toString(),"56x^6+2x^3-5");
    }
    @Test
    public void integrationTest()
    {
        poli1.readPolynomial("3x^2-2x+1");
        poli2.readPolynomial("8x^7-5x+0.5x^4");
        assertEquals(Operation.integration(poli1).toString(),"x^3-x^2+x");
        assertEquals(Operation.integration(poli2).toString(),"x^8+0.1x^5-2.5x^2");
    }
}
