package demos;

class Persons {
	private String name;
	public Persons(String n) {
		this.name = n;
	}
	public String toString() {
		return name;
	}
}

class Students extends Persons {
	private int id;
	public Students(String n, int id) {
		super(n);
		this.id = id;
	}
	public String toString() {
		return super.toString() + ", " + id;
	}
}

public class ArrayLocation {

	private double coords[];
	
	public ArrayLocation(double[] coords) {
		this.coords = coords;
	}

	public static void main(String[] args) {
		double coords[] = {5.0, 0.0};
		ArrayLocation accra = new ArrayLocation(coords);
		coords[0] = 32.9;
		coords[1] = -117.2;
		System.out.println(accra.coords[0]);
		
		Persons s = new Students("Jose", 12345);
		System.out.println(s);
	}

}
