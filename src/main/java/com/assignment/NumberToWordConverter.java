package com.assignment;

public class NumberToWordConverter {

    private static final String[] UNITS = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    private static final String[] TENS = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen",
            "eighteen", "nineteen"};
    private static final String[] TENS_2 = {"twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety",
            "eighteen", "nineteen"};

    private static final String MILLION = " million ";
    private static final String THOUSAND = " thousand ";
    private static final String HUNDRED_AND = " hundred and ";
    private static final String HUNDRED = " hundred";

    static final String EXCEPTION_MESSAGE = "Number should be between 0 and 999,999,999 included.";

    public String convertToWords(long number) throws Exception {
        if (!validateNumber(number))
            throw new Exception(EXCEPTION_MESSAGE);
        return convertToWords(number, new StringBuffer()).toString().trim();
    }

    private boolean validateNumber(long number) {
        return !(number < 0 || number > 999999999);
    }

    private StringBuffer convertToWords(long number, final StringBuffer words) {
        StringBuffer emptyBuffer = new StringBuffer();
        if (number >= 10000000 && number <= 999999999) {
            words.append(getWords(number, 6, true, MILLION, emptyBuffer));
        }
        if (number >= 1000000 && number <= 99999999) {
            words.append(getWords(number, 6, false, MILLION, emptyBuffer));
        }
        if (number >= 100000 && number <= 999999) {
            words.append(getWords(number, 3, true, THOUSAND, emptyBuffer));
        }
        if (number >= 1000 && number <= 99999) {
            words.append(getWords(number, 3, false, THOUSAND, emptyBuffer));
        }
        if (number >= 100 && number <= 999) {
            words.append(getHundreds((int) number, emptyBuffer));
        } else if (number > 0 && number < 100)
            words.append(getTens((int) number, emptyBuffer));
        else
            words.append(words.length() == 0 ? "zero" : "");

        return words;
    }

    private StringBuffer getWords(long number, int power, boolean isHundreds, String text, StringBuffer words) {
        int divisor = (int) Math.pow(10, power);
        int quotient = (int) number / divisor;
        int reminder = (int) number % divisor;
        StringBuffer children = isHundreds ? getHundreds(quotient, new StringBuffer()) : getTens(quotient, new StringBuffer());
        if (reminder > 0)
            convertToWords(number % divisor, words.append(children).append(text));
        else
            return words.append(children).append(text);
        return words;
    }

    private StringBuffer getTens(int number, StringBuffer words) {
        if (number >= 20 && number <= 99) {
            int quotient = number / 10;
            convertToWords(number % 10, words.append(TENS_2[quotient - 2]).append(" "));
        } else if (number >= 10 && number <= 19) {
            int reminder = number % 10;
            words.append(TENS[reminder]);
        } else if (number > 0 && number < 10) {
            words.append(UNITS[number - 1]);
        }
        return words;
    }

    private StringBuffer getHundreds(int number, StringBuffer words) {
        int quotient = number / 100;
        int reminder = number % 100;
        if (reminder > 0)
            words.append(UNITS[quotient - 1]).append(HUNDRED_AND).append(getTens(number % 100, new StringBuffer()));
        else
            return words.append(UNITS[quotient - 1]).append(HUNDRED);
        return words;
    }

    public static void main(String[] args) throws Exception {
        NumberToWordConverter converter = new NumberToWordConverter();
        System.out.println(converter.convertToWords(999999999));
    }

}
