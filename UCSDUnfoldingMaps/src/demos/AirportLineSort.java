package demos;

public class AirportLineSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
	public static String findAirportCode3(String toFind, Airport[] airports) 
	{
		for(Airport airport: airports) {
			if(airport.getName().equals(toFind)) {
				return airport.getCode3();
			}
		}
		return null;
	}
	public static String findAirportCode4(String toFind, Airport[] airports) 
	{
		int index = 0;
		while(index < airports.length){
			Airport airport = airports[index];
			if(airport.getName().equals(toFind)) {
				return airport.getCode4();
			}
			index++;
		}
		return null;
	}

}
