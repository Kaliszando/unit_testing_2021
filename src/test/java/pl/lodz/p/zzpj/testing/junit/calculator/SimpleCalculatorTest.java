package pl.lodz.p.zzpj.testing.junit.calculator;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junitparams.JUnitParamsRunner.$;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnitParamsRunner.class)
public class SimpleCalculatorTest {

    SimpleCalculator sut;

    @Before
    public void setUp() {
        sut = new SimpleCalculator();
    }

    @Test
    @Parameters(method = "getTwoNumberSet")
    public void shouldAddTwoNumbers(double a, double b) {
        assertEquals(Double.sum(a, b), sut.add(a, b), 0);
    }

    public Object[] getTwoNumberSet() {
        Object[] $ = $(
                $(3, 4),
                $(-23, 231),
                $(414, -2_535)
        );
        return $;
    }

    @Test
    @Parameters(method = "getTwoNumberSet")
    public void shouldSubtractTwoNumbers(double a, double b) {
        assertEquals(Double.sum(a, -b), sut.subtract(a, b), 0);
    }

    @Test
    @Parameters(method = "getTwoNumberSet")
    public void shouldDivideTwoNumbers(double a, double b) throws CannotDivideByZeroException {
        assertEquals(a / b, sut.divide(a, b), 0);
    }

    @Test
    @Parameters({"3", "4", "5", "1235", "2", "53"})
    public void shouldCalculateSquareRoot(double number) throws CannotCalculateSquareRootOfNegativeNumber {
        assertEquals(Math.sqrt(number), sut.calculateSquareRoot(number), 0);
    }

    @Test(expected = CannotCalculateSquareRootOfNegativeNumber.class)
    @Parameters({"-1", "-0.01", "-0.0001"})
    public void shouldThrowCannotCalculateSquareRootOfNegativeNumberException(double number)
            throws CannotCalculateSquareRootOfNegativeNumber {
        sut.calculateSquareRoot(number);
    }

    @Test
    @Parameters({"3", "7", "73"})
    public void shouldCheckIfNumberIsPrime(int prime) {
        assertTrue(sut.isPrime(prime));
    }

    @Test
    @Parameters(method = "getModuloTestSet")
    public void shouldCalculateModuloOfTwoNumbers(int a, int b) {
        assertEquals(a % b, sut.modulo(a, b), 0);
    }

    public Object[] getModuloTestSet() {
        Object[] $ = $(
          $(4, 5),
          $(20, 10),
          $(7, 3),
          $(7, 7)
        );
        return $;
    }

    @Test
    @Parameters(method = "getTestSet")
    public void shouldCheckIfNumberIsPrimeOrNot(int prime, boolean resultFlag) {
        assertEquals(resultFlag, sut.isPrime(prime));
    }

    public Object[] getTestSet() {
        Object[] $ = $(
                $(3, true),
                $(5, true),
                $(21, false)
        );
        return $;
    }

    @Test(expected = CannotDivideByZeroException.class)
    public void shouldThrowAnException() throws CannotDivideByZeroException {
        sut.divide(5, 0);
    }

    @Test
    @Parameters({"-13", "-1", "-0.00001"})
    public void shouldReturnNumberThatCausedException(double negativeNumber) {
        try {
            sut.calculateSquareRoot(negativeNumber);
        } catch (CannotCalculateSquareRootOfNegativeNumber e) {
            assertEquals(negativeNumber, e.getNumberThatCausedException(), 0);
        }
    }
}