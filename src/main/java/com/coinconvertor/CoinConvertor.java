package com.coinconvertor;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CoinConvertor {

    public static Map<Float, String> rupeedenomination = new HashMap<>();
    public static Map<Float, String> dollardenomination = new HashMap<>();
    public static Map<Float, String> eurodenomination = new HashMap<>();

    static void loadRupeeDenomination() {
        rupeedenomination.put(10f, "Ten Rupees");
        rupeedenomination.put(5f,"Five Rupees");
        rupeedenomination.put(2f, "Two Rupees");
        rupeedenomination.put(1f, "One Rupee");
        rupeedenomination.put(0.5f, "Fifty Paise");
        rupeedenomination.put(0.25f, "Twenty Five Paise");
        rupeedenomination.put(0.2f, "Twenty Paise");
        rupeedenomination.put(0.1f, "Ten Paise");
        rupeedenomination.put(0.05f, "Five Paise");
        rupeedenomination.put(0.01f, "One Paise");
    }
    static void loadDollarDenomination() {

        dollardenomination.put(1f, "Dollar");
        dollardenomination.put(0.25f, "Quartor");
        dollardenomination.put(0.1f, "Dime");
        dollardenomination.put(0.05f, "Nickle");
        dollardenomination.put(0.01f, "Pennies");
    }

    static void loadEuroDenomination() {

        eurodenomination.put(2f, "Two Euros");
        eurodenomination.put(1f, "One Euro");
        eurodenomination.put(0.5f, "Fifty Cents");
        eurodenomination.put(0.2f, "Twenty Cents");
        eurodenomination.put(0.1f, "Ten Cents");
        eurodenomination.put(0.05f, "Five Cents");
        eurodenomination.put(0.02f, "Two Cents");
        eurodenomination.put(0.01f, "One Cents");
    }



    public String convertToCoins(String input) {

        if(input.indexOf(".") != input.lastIndexOf("."))
        {
            return "Invalid input with multiple points";
        }
        String currencytype = input.substring(0,1);
        Double currency = Double.parseDouble((input.substring(1,input.length())).trim());

        Map<Float, String> coindenomination;
        if(currencytype.equalsIgnoreCase("D")) {
            coindenomination=dollardenomination;
        }else if(currencytype.equalsIgnoreCase("R")) {
            coindenomination=rupeedenomination;
        }else if(currencytype.equalsIgnoreCase("E")) {
            coindenomination=eurodenomination;
        }else{
            return "Invalid  Currency type, allowed currencies are Rupee, Dollar and Euro.";
        }

        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        if(currency>0.0f) {
            StringBuilder sb = new StringBuilder();
            for(Float coin : coindenomination.keySet())
            {
                if(currency>=coin)
                {

                    if(currency%coin==0)
                    {

                        sb.append(" ").append((int) (currency / coin)).append(" "+coindenomination.get(coin));
                        break;
                    }else {

                        int quotient = (int) (currency/coin);
                        sb.append(" "+(int)(currency/coin)).append(" "+coindenomination.get(coin));
                        currency =Double.parseDouble(df.format(currency-(quotient*coin)));
                        sb.append("," );
                    }

                }
            }
            if(sb.toString().endsWith("and")) {
                return sb.substring(0,sb.length()-4).concat(" Coin(s).");
            }else {
                return sb.append(" Coin(s).").toString();
            }
        }else {
            return "Please enter  currency greater than 0.";
        }


    }

    public static void main(String[] args)
    {
        loadDollarDenomination();
        loadEuroDenomination();
        loadRupeeDenomination();
        Scanner scanner = new Scanner(System.in);
        System.out.println(" Enter the currency Type and currency : Ex.D2.87");
        String currency =  scanner.next();
        System.out.println(new CoinConvertor().convertToCoins(currency.trim()));

    }

}

