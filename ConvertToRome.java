import java.util.ArrayList;
import java.util.List; 
import java.util.stream.Collectors; 
import java.util.stream.Stream; 

public class ConvertToRome {

    
    
    private StringBuilder sb;
    public ConvertToRome () {
        sb = new StringBuilder();
    }

    public String romanToArab(String expression){
        int priority;
        boolean flag = false;
        String current = "";
        String left = "";
        String right = "";
        String sign = "";
    
        for(int i = 0; i < expression.length(); i++) {
            priority = getPriority(expression.charAt(i));

            if (priority == 0) {
                if(!flag) {
                    current += expression.charAt(i);
                } else {
                    right += expression.charAt(i);
                }       
            }
            if (priority > 1) {    
                left += current;
                sign += expression.charAt(i);
                flag = true; 
            }                
        }
        
        return romanToArabic(left) + sign + romanToArabic(right);
    }

    private int getPriority(char token){                  
        if (token == '*' || token == '/') return 3;
        else if (token == '+' || token == '-') return 2;
        else return 0;
    }
 
    public  int romanToArabic(String input) {
        String romanNumeral = input.toUpperCase();
        int result = 0;

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;

        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            RomanNumeral symbol = romanNumerals.get(i);
             if (romanNumeral.startsWith(symbol.name())) {
                 result += symbol.getValue();
                 romanNumeral = romanNumeral.substring(symbol.name().length());
             } else {
                 i++;
             }
        }

        if (romanNumeral.length() > 0) {
             throw new IllegalArgumentException(input + " cannot be converted to a Roman Numeral");
        }

        return result;
    }
 
    public  String arabicToRoman(int number) {
        if ((number <= 0) || (number > 4000)) {
            throw new IllegalArgumentException(number + " is not in range (0,4000]");
        }

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        
       
        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
       }

        return sb.toString();
    }


}