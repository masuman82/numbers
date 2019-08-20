package com.assignment;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestNumberToWordConverter {

    private NumberToWordConverter converter;

    @Before
    public void init(){
        converter = new NumberToWordConverter();
    }

    @Test
    public void testInvalidInputs() {

        try {
            converter.convertToWords(-1);
        } catch (Exception e) {
            assertEquals(e.getMessage(), NumberToWordConverter.EXCEPTION_MESSAGE);
        }
        try {
            converter.convertToWords(1000000000);
        } catch (Exception e) {
            assertEquals(e.getMessage(), NumberToWordConverter.EXCEPTION_MESSAGE);
        }

    }
    @Test
    public void testNumbersBelowHundred() throws Exception{
        assertEquals(converter.convertToWords(0), "zero");
        assertEquals(converter.convertToWords(5), "five");
        assertEquals(converter.convertToWords(10), "ten");
        assertEquals(converter.convertToWords(11), "eleven");
        assertEquals(converter.convertToWords(19), "nineteen");
        assertEquals(converter.convertToWords(20), "twenty");
        assertEquals(converter.convertToWords(35), "thirty five");
        assertEquals(converter.convertToWords(99), "ninety nine");
    }

    @Test
    public void testNumbersTillTenThousand() throws Exception{
        assertEquals(converter.convertToWords(100), "one hundred");
        assertEquals(converter.convertToWords(117), "one hundred and seventeen");
        assertEquals(converter.convertToWords(255), "two hundred and fifty five");
        assertEquals(converter.convertToWords(1000), "one thousand");
        assertEquals(converter.convertToWords(1011), "one thousand eleven");
        assertEquals(converter.convertToWords(3999), "three thousand nine hundred and ninety nine");
        assertEquals(converter.convertToWords(7877), "seven thousand eight hundred and seventy seven");
        assertEquals(converter.convertToWords(8002), "eight thousand two");
        assertEquals(converter.convertToWords(8010), "eight thousand ten");
        assertEquals(converter.convertToWords(9999), "nine thousand nine hundred and ninety nine");
    }

    @Test
    public void testNumbersTillMillion() throws Exception{
        assertEquals(converter.convertToWords(10000), "ten thousand");
        assertEquals(converter.convertToWords(10115), "ten thousand one hundred and fifteen");
        assertEquals(converter.convertToWords(19999), "nineteen thousand nine hundred and ninety nine");
        assertEquals(converter.convertToWords(20000), "twenty  thousand");
        assertEquals(converter.convertToWords(38744), "thirty eight thousand seven hundred and forty four");
        assertEquals(converter.convertToWords(99999), "ninety nine thousand nine hundred and ninety nine");
        assertEquals(converter.convertToWords(100000), "one hundred thousand");
        assertEquals(converter.convertToWords(105980), "one hundred and five thousand nine hundred and eighty");
        assertEquals(converter.convertToWords(127859), "one hundred and twenty seven thousand eight hundred and fifty nine");
        assertEquals(converter.convertToWords(999999), "nine hundred and ninety nine thousand nine hundred and ninety nine");
    }

    @Test
    public void testNumbersOverMillion() throws Exception{
        assertEquals(converter.convertToWords(1000000), "one million");
        assertEquals(converter.convertToWords(1000105), "one million one hundred and five");
        assertEquals(converter.convertToWords(1138799), "one million one hundred and thirty eight thousand seven hundred and ninety nine");
        assertEquals(converter.convertToWords(177338295), "one hundred and seventy seven million three hundred and thirty eight thousand two hundred and ninety five");
        assertEquals(converter.convertToWords(500000000), "five hundred million");
        assertEquals(converter.convertToWords(999999999), "nine hundred and ninety nine million nine hundred and ninety nine thousand nine hundred and ninety nine");
    }
}
