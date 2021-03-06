import java.util.Scanner;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;

public class FractionCalculator {
	
	private static String getOperation(Scanner scanner) {
		String s;
		Set<String> operation = new HashSet<String>();
		operation.add("+");
		operation.add("-");
		operation.add("*");
		operation.add("/");
		operation.add("=");
		operation.add("q");
		operation.add("Q");
		
		System.out.print("Please enter an operation (+, -, /, *, = or Q to quit): ");
		s = scanner.nextLine();
		while (!operation.contains(s)) {
			System.out.print("Invalid input (+, -, /, *, = or Q to quit): ");
			s = scanner.nextLine();
		}
		return s;
	}
	
	
	private static boolean validFraction(String input) {

		int x=0;
		if(input.charAt(0) == '-') {
			x=1;
		}
		if (input.contains("/")) {

			int index = input.indexOf("/");
		
			
			return isNumber(input.substring(x,index)) && isNumber(input.substring(index+1,input.length()));
		}

			
		return isNumber(input.substring(x,input.length()));
	}
	
	public static boolean isNumber(String input) {
		int count = 0;
		if(input.length()==0) {
			return false;
		}
		for (int i = 0; i<input.length();i++) {
			if ((int)input.charAt(i) < 58 && (int)input.charAt(i)>47) {
				count++;
			}
		}
		return count==input.length();
	}
	
	
	static int[] fractionNumDen (String input) {
		int[] result = {0,0};
		int x = 0;
		int index = input.indexOf('/');

		if(input.charAt(0) == '-') {
			x=1;
		}
		if ( index >= 0) {
			result[0] = Integer.parseInt(input.substring(x,index));
			result[1] = Integer.parseInt(input.substring(index+1,input.length()));
		}
		else {
			result[0] = Integer.parseInt(input.substring(x,input.length()));
			result[1] = 1;
		}
		if (x==1)
			result[0]*=(-1);
		return result;
	}
	
	
	public static void main(String args[]) {
		System.out.println("This program is a fraction calculator\n"
				+ "it will add, subtract, multiply, and divide fractions until you type Q to quit.\n"
				+ "Please enter your fractions in the form a/b, where a and b are integers.\n"
				+ "-------------------------------------------------------------------------------");
		String operation ="";
		Scanner scanner = new Scanner(System.in);
		while (!operation.equalsIgnoreCase("Q")) {
			operation = getOperation(scanner);
			if (!operation.equalsIgnoreCase("Q")) {
				List<Fraction> fracList = new ArrayList<Fraction>();
				boolean validate = false;
				
				for (int i=0;i<2;i++) {
					while(!validate) {
						System.out.print("Please enter a fraction (a/b) or (a): ");
						String in = scanner.nextLine();
						validate = validFraction(in);
						if (validate) {
							int[] number = fractionNumDen(in);
							Fraction frac = new Fraction(number[0],number[1]);
							fracList.add(frac);	
						}
					}
					validate = false;
				}
				Fraction result = new Fraction();
				String resultTrue = " false";
				System.out.print(fracList.get(0).toString() + " " + operation + " " + fracList.get(1).toString() + " = ");
				switch (operation){
					case "+":
						result = fracList.get(0).add(fracList.get(1));
						result.toLowestTerms();
						System.out.println(result.toString());
						break;
						
					case "-":
						result = fracList.get(0).subtract(fracList.get(1));
						result.toLowestTerms();
						System.out.println(result.toString());
						break;
						
					case "*":
						result = fracList.get(0).multiply(fracList.get(1));
						result.toLowestTerms();
						System.out.println(result.toString());
						break;
						
					case "/":
						result = fracList.get(0).divide(fracList.get(1));
						result.toLowestTerms();
						System.out.println(result.toString());
						break;
						
					case "=":
						if (fracList.get(0).equals(fracList.get(1))){
							resultTrue = "true";
						}
						System.out.println(resultTrue);
						break;
						
					default:
						break;
				}
				System.out.println("-------------------------------------------------------------------------------");
				
			}
		}
		

		
		


		
		
		
	}

}

	
