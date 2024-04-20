package oops;

interface VehicleType{
	void type();
}


abstract class Looks{
abstract void color();
		
}	
	
class Vehicle extends Looks implements VehicleType {
    void drive()
    {
        System.out.println("drive() method of base class");
        System.out.println("driving the Car.");
    }
    
    public void type() {
    	System.out.println("petrol");
    }
    void color() {
    	System.out.println("Red");
    }
}
class Car extends Vehicle {
    void drive()
    {
        System.out.println("drive() method of derived class");
        System.out.println("Car is driving.");
    }
    
    public void type() {
    	System.out.println("Electric");
    }
    
    public void color() {
    	System.out.println("blue");
    }
}
class GFG {
    public static void main(String[] args)
    {
        Car c1 = new Car();
        Vehicle v1 = new Vehicle();
        c1.drive();
        v1.drive();
        Vehicle vehicle = new Car();
        // drive() method of Vehicle class is overridden by
        // Car class drive()
        vehicle.drive();
        
      Car rp = new Car();
        rp.type();
        rp.drive();
        
        Looks look = new Vehicle();
        look.color();
    }
}
