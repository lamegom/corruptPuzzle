package game.div.foradilma.util;

import com.divneg.storage.NumberNames;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
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

		BigDecimal number = new BigDecimal(output);
		return NumberNames.createString(number);

	}

}
