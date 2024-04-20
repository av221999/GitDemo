package oops;

public class Tester {
	
	public static int sampleVariable =0;
	
	static {
		System.out.println("Static block in Tester Class");
	}
	{
		System.out.println("adds");
	}
	
	public static void main(String args[]) {
		
		sampleVariable++;
		Printer p=null;
		System.out.println(sampleVariable);
		Printer p1 = null;
		System.out.println("in Main");
		System.out.println(sampleVariable);
		p1.display("The value of sample variable is: "+ sampleVariable);
		new Tester();
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	public static int var;
//	
//	Tester(int var){
//		System.out.println(this.var);
//	}
//	
//	public static void main(String[] args){
//		Tester sc = new Tester(20);
//		System.out.println( Tester.var);
//		
//	}
	

}
