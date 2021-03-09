package com.divneg.storage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.text.DecimalFormat;

public class NumberNames
{
    public static void main(String[] args)
    {
        test("100", "Nearly nothing");
        test("1000", "1 Thousand");
        test("1230", "1.23 Thousand");
        test("1000000", "1 Millhão");
        test("1435234", "1.43 Millhão");
        test("350000000", "350 Millhão");
        test("1000000000", "1 Billhão");
        test("1765000000", "1.76 Billhão");
        test("1000000000000", "1 Trillhão");
        test("1345342345000", "1.34 Trillhão");
        test("1000000000000000", "1 Quadrillhão");
        test("100000000000000000", "100 Quadrillhão");
        test("1230000000000000000000000000000000000000000000000000000000000000", "1.23 Vigintillhão");
    }

    private static void test(String numberString, String string)
    {
        BigDecimal number = new BigDecimal(numberString);
        System.out.println(number+" is "+createString(number)+" should be "+string);
    }



    private static final String NAMES[] = new String[]{
            "Mil",
            "Milhão",
            "Bilhão",
            "Trilhão",
            "Quadrillhão",
            "Quintillhão",
            "Sextillhão",
            "Septillhão",
            "Octillhão",
            "Nonillhão",
            "Decillhão",
            "Undecillhão",
            "Duodecillhão",
            "Tredecillhão",
            "Quattuordecillhão",
            "Quindecillhão",
            "Sexdecillhão",
            "Septendecillhão",
            "Octodecillhão",
            "Novemdecillhão",
            "Vigintillhão",
    };
    public static final BigDecimal THOUSAND = BigDecimal.valueOf(1000);
    public static final NavigableMap<BigDecimal, String> MAP;
    static
    {
        MAP = new TreeMap<BigDecimal, String>();
        for (int i=0; i<NAMES.length; i++)
        {
            MAP.put(THOUSAND.pow(i+1), NAMES[i]);
        }
    }

    public static String createString(BigDecimal number)
    {
        Entry<BigDecimal, String> entry = MAP.floorEntry(number);
        if (entry == null)
        {
            return number + "";
        }
        BigDecimal key = entry.getKey();
        BigDecimal d = key.divide(THOUSAND, 8, RoundingMode.DOWN);
        BigDecimal m = number.divide(d, 8, RoundingMode.DOWN);
        m = m.setScale(2, RoundingMode.DOWN);
        float f = m.floatValue() / 1000.0f;
        float rounded = ((int)(f * 100.0))/100.0f;
        BigDecimal banana = new BigDecimal(rounded);
        banana = banana.setScale(2, RoundingMode.DOWN);
		
		DecimalFormat formatter = new DecimalFormat("###,###,###.00");

        return formatter.format(banana.doubleValue()) + " " + entry.getValue();
    }
}
