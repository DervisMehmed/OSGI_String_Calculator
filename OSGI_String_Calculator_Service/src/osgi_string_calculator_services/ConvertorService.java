package osgi_string_calculator_services;

/*
 * Interface class for the service package.
 */

public interface ConvertorService 
{
	//  Converts string to integer
    int[] stringToInt(String s, String b, boolean flagEN);
    
    //  Converts integer to String
    String intToString(int n, boolean flagEN);

	void loadArrays(String lang);
}