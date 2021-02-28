package game.div.foradilma.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class Common {
	
	public static String convertNum(String num, boolean currency){
		DecimalFormat formatter = (DecimalFormat) NumberFormat.getCurrencyInstance(Locale.US);
		DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
		
		formatter.setDecimalFormatSymbols(symbols);
		String output = "";
		output += formatter.format(Double.valueOf(num));
//		if(!currency) {
			
//			symbols.setCurrencySymbol(""); // Don't use null.
		String output2 = output.replaceAll("$", "");

			
//		}

	      return output2;
	}
	
	public static String milTrilConverter(double output, boolean currency) {

	      
	      String num =  output+"";
	      String out = "";
	      String type = "";
		
		if (num.length() > 6) {

			if (num.length() >= 33) {
//				num = num.substring(0, num.length() - 32);
				num = (Double.valueOf(num) / 1000000000000000000000000000000000.00)+"";
			
				type += " Nonalhões";
			} else if (num.length() >= 30) {
//				num = num.substring(0, num.length() - 29);
				num = (Double.valueOf(num) / 1000000000000000000000000000.00)+"";
				type += " Octiliões";
			} else if (num.length() >= 27) {
//				num = num.substring(0, num.length() - 26);
				num = (Double.valueOf(num) / 1000000000000000000000000.00)+"";
				type += " Septillion";
			} else if (num.length() >= 24) {
//				num = num.substring(0, num.length() - 23);
				num = (Double.valueOf(num) / 1000000000000000000000.00)+"";
				type += " Sextilhões";
			} else if (num.length() >= 21) {
//				num = num.substring(0, num.length() - 20);
				num = (Double.valueOf(num) / 1000000000000000000.00)+"";
				type += " Quintilhões";
			} else if (num.length() >= 18) {
//				num = num.substring(0, num.length() - 17);
				num = (Double.valueOf(num) / 1000000000000000.00)+"";
				type += " Quadrilhões";
			} else if (num.length() >= 15) {
//				num = num.substring(0, num.length() - 14);
				num = (Double.valueOf(num) / 1000000000000.00)+"";
				type += " Trilhões";

			} else if (num.length() >= 12) {
//				num = num.substring(0, num.length() - 11);
				num = (Double.valueOf(num) / 1000000000.00)+"";
				
				type += " Bilhões";
			} else if (num.length() >= 9) {
//				num = num.substring(0, num.length() - 8);
				num = (Double.valueOf(num) / 1000000.00)+"";
				
				type += " Milhões";
			}
		}
		
		//out = convertNum(num, currency);
		
		return num + type;

	}
	

}
