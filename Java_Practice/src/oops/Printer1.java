package oops;

public class Printer1 {

	static {
		System.out.println("Static block in Printer1 class");	
	}
	
	public static  void display(String message) {
		System.out.println(message);
	}
}
