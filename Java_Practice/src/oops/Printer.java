package oops;

public class Printer {

	static {
		System.out.println("Static block in Printer class");	
	}
	
	public static  void display(String message) {
		System.out.println(message);
	}
}
