package osgi_string_calculator_imp;

import java.util.ArrayList;

import osgi_string_calculator_services.ConvertorService;

/*
 * This class includes the essential functions for the conversions
 */

public class ConvertorServiceImp implements ConvertorService
{
	int thousand = 1000;
    int million = 1000000;
    int billion = 1000000000;
    int multiplier  = 0;
    
    ArrayList<String> numbersTR;
    ArrayList<String> decimalsTR;
    ArrayList<String> digitsTR;
            
    ArrayList<String> numbersEN;
    ArrayList<String> teensEN;
    ArrayList<String> decimalsEN;
    ArrayList<String> digitsEN;
    
    ArrayList<String> numbers;
    ArrayList<String> teens;
    ArrayList<String> decimals;
    ArrayList<String> digits;
    
    String firstNum = "";
    String secondNum= "";
    String resultNum= "";
    
    //	Loads the arrays language accordingly
    @Override
    public void loadArrays(String lang)
    {
        if(lang.equals("tr"))
        {
            numbersTR   = new ArrayList<>();
            decimalsTR  = new ArrayList<>();
            digitsTR    = new ArrayList<>();
        
            numbersTR.add("bir");
            numbersTR.add("iki");
            numbersTR.add("üç");
            numbersTR.add("dört");
            numbersTR.add("beş");
            numbersTR.add("altı");
            numbersTR.add("yedi");
            numbersTR.add("sekiz");
            numbersTR.add("dokuz");
            
            decimalsTR.add("on");
            decimalsTR.add("yirmi");
            decimalsTR.add("otuz");
            decimalsTR.add("kırk");
            decimalsTR.add("elli");
            decimalsTR.add("altmış");
            decimalsTR.add("yetmiş");
            decimalsTR.add("seksen");
            decimalsTR.add("doksan");
            
            digitsTR.add("yüz");
            digitsTR.add("bin");
            digitsTR.add("milyon");
            digitsTR.add("milyar");
        }
        else
        {
            numbersEN   = new ArrayList<>();
            teensEN     = new ArrayList<>();
            decimalsEN  = new ArrayList<>();
            digitsEN    = new ArrayList<>();
            
            numbersEN.add("one");
            numbersEN.add("two");
            numbersEN.add("three");
            numbersEN.add("four");
            numbersEN.add("five");
            numbersEN.add("six");
            numbersEN.add("seven");
            numbersEN.add("eight");
            numbersEN.add("nine");
            
            teensEN.add("eleven");
            teensEN.add("twelve");
            teensEN.add("thirteen");
            teensEN.add("fourteen");
            teensEN.add("fifteen");
            teensEN.add("sixteen");
            teensEN.add("seventeen");
            teensEN.add("eighteen");
            teensEN.add("nineteen");
            
            decimalsEN.add("ten");
            decimalsEN.add("twenty");
            decimalsEN.add("thirty");
            decimalsEN.add("forty");
            decimalsEN.add("fifty");
            decimalsEN.add("sixty");
            decimalsEN.add("seventy");
            decimalsEN.add("eighty");
            decimalsEN.add("ninty");
            
            digitsEN.add("hundred");
            digitsEN.add("thousand");
            digitsEN.add("million");
            digitsEN.add("billion");
        }
    }
    
    //	Function for converting two strings into integers
	@Override
	public int[] stringToInt(String s, String b, boolean flagEN) {
		int firstNumInt;
        int[] nums = new int[2];
        String[] num = new String[2];
        
        if(!flagEN)
        {
            numbers = new ArrayList<>(numbersTR);
            decimals = new ArrayList<>(decimalsTR);
            digits = new ArrayList<>(digitsTR);
        }
        else
        {
            numbers = new ArrayList<>(numbersEN);
            teens   = new ArrayList<>(teensEN);
            decimals = new ArrayList<>(decimalsEN);
            digits = new ArrayList<>(digitsEN);
        }
        
        num[0]  = s;
        num[1]  = b;
        
        for (int j = 0; j < num.length; j++)
        {
            firstNumInt = 0;
            String[] words1 = num[j].split("\\s+");
            
            for (int i = words1.length-1; i >= 0 ;i--)
            {
                //  Digit Seen
                if(digits.contains(words1[i]))
                {   //  Hundred Seen
                    if ((digits.indexOf(words1[i]) == 0)) 
                    {   //  Before Hundred if Number?
                        if( i-1 >= 0 && numbers.contains(words1[i-1]))
                        {
                            firstNumInt = (firstNumInt + ((numbers.indexOf(words1[i-1])+1) * 100));
                            i = i-1;
                        }
                        else
                        {
                            firstNumInt = (firstNumInt + 100);
                        }   
                    } else{
                        if((digits.indexOf(words1[i]) == 1))
                            multiplier = thousand;
                        else if (digits.indexOf(words1[i]) == 2)
                            multiplier = million;
                        else
                            multiplier = billion;
                        
                        // numbers multiplier
                        if(i-1 >= 0 && numbers.contains(words1[i-1]))
                        {   // tens numbers multiplier
                            if(i-2 >= 0 && decimals.contains(words1[i-2]))
                            {   // hundred tens numbers multiplier
                                if(i-3 >= 0 && digits.contains(words1[i-3]) && digits.indexOf(words1[i-3]) == 0)
                                {
                                    if(i-4 >= 0 && numbers.contains(words1[i-4])) 
                                    {   // (numbers) hundred tens number multiplier
                                        firstNumInt = firstNumInt + ((numbers.indexOf(words1[i-4])+1) * 100 + ((decimals.indexOf(words1[i-2])+1) * 10) + (numbers.indexOf(words1[i-1]) + 1)) * multiplier;
                                        i = i - 4;
                                        continue;
                                    } else {
                                        firstNumInt = firstNumInt + (100 + ((decimals.indexOf(words1[i-2])+1) * 10) + (numbers.indexOf(words1[i-1]) + 1)) * multiplier;
                                        i = i - 3;
                                        continue;
                                    }
                                } else {
                                    firstNumInt = firstNumInt + (((decimals.indexOf(words1[i-2])+1) * 10) + numbers.indexOf(words1[i-1]) + 1) * multiplier;
                                    i = i - 2;
                                    continue;
                                }
                            } else if(i-2 >= 0 && digits.contains(words1[i-2]) && digits.indexOf(words1[i-2]) == 0)
                            {   //  (number) hundred numbers multiplier
                                if(i-3 >= 0 && numbers.contains(words1[i-3]))
                                {
                                    firstNumInt = firstNumInt + ((numbers.indexOf(words1[i-3])+1) * 100 + (numbers.indexOf(words1[i-1]) + 1)) * multiplier;
                                    i = i - 3;
                                    continue;
                                } else
                                {
                                    firstNumInt = firstNumInt + ((100 + numbers.indexOf(words1[i-1]) + 1)) * multiplier;
                                    i = i - 2;
                                    continue;
                                }
                            } else
                            {
                                firstNumInt = (firstNumInt + ((numbers.indexOf(words1[i-1])+1) * multiplier));
                                i--;
                            }
                        } else if (flagEN && i-1 >= 0 && teens.contains(words1[i-1]))
                        {   //  For English: ... eleven hundred/thousand/million
                            if(i-2 >= 0 && digits.contains(words1[i-2]) && digits.indexOf(words1[i-2]) == 0)
                            {   // (two) hundred tens multiplier
                                if(i-3 >= 0 && numbers.contains(words1[i-3]))
                                {   
                                    firstNumInt = (firstNumInt + ((numbers.indexOf(words1[i-3])+1) * 100 * multiplier + ((teens.indexOf(words1[i-1])+1) + 10) * multiplier));
                                    i = i - 3;
                                    continue;
                                } else
                                {   
                                    firstNumInt = (firstNumInt + (100 * multiplier + ((teens.indexOf(words1[i-1])+1) + 10) * multiplier));
                                    i = i -2;
                                    continue;
                                }
                            } else 
                            {   //  eleven multiplier
                                firstNumInt = (firstNumInt + (((teens.indexOf(words1[i-1])+1) + 10) * multiplier));
                                i--;
                                continue;
                            }
                        } else if (i-1 >= 0 && decimals.contains(words1[i-1]))
                        {   //  ten multiplier
                            if(i-2 >= 0 && digits.contains(words1[i-2]) && digits.indexOf(words1[i-2]) == 0)
                            {
                                if(i-3 >= 0 && numbers.contains(words1[i-3]))
                                {   // (number) hundred ten multiplier
                                    firstNumInt = (firstNumInt + ((numbers.indexOf(words1[i-3])+1) * 100 * multiplier + (decimals.indexOf(words1[i-1])+1) * 10 * multiplier));
                                    i = i - 3;
                                    continue;
                                } else
                                {   
                                    firstNumInt = (firstNumInt + (100 * multiplier + (decimals.indexOf(words1[i-1])+1) * 10 * multiplier));
                                    i = i -2;
                                    continue;
                                }
                            } else 
                            {   // tens multiplier
                                firstNumInt = (firstNumInt + ((decimals.indexOf(words1[i-1])+1) * 10 * multiplier));
                                i--;
                                continue;
                            }
                        } else if (i-1 >= 0 && digits.contains(words1[i-1]) && digits.indexOf(words1[i-1]) == 0)
                        {   // (two) hundred multiplier
                            if( i-2 >= 0 && numbers.contains(words1[i-2]))
                            {
                                firstNumInt = (firstNumInt + ((numbers.indexOf(words1[i-2])+1) * 100 * multiplier));
                                i = i - 2;
                                continue;
                            } else {
                                firstNumInt = (firstNumInt + ( 100 * multiplier));
                                i = i - 1;
                                continue;
                            }
                        }else {
                            firstNumInt = (firstNumInt + multiplier);
                        }
                    }
                }
                //  Numbers
                if(numbers.contains(words1[i]))
                {
                    if(firstNumInt == 0)
                    {
                        firstNumInt = firstNumInt + numbers.indexOf(words1[i])+1;
                    }
                }
                // Tens
                if(decimals.contains(words1[i]))
                {
                    firstNumInt = firstNumInt + (decimals.indexOf(words1[i])+1) * 10;
                }
            } // for ends
            if(words1[0].equals("minus") || words1[0].equals("eksi"))
                firstNumInt = firstNumInt * (-1);
            nums[j] = firstNumInt;
        }
        return nums;
	}

	//	Converts incoming n number into String and returns it
	@Override
	public String intToString(int n, boolean flagEN) 
	{
		String hundredD;
        String thousandD;
        String millionD;
        String billionD;
        String[] units;
        String[] tens;
        
        if(flagEN){
            units = new String[]{
                "", "one", "two", "three", "four", "five", "six", "seven",
                "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen",
                "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
            };

            tens = new String[]{"", "", "twenty", "thirty", "forty", "fifty", 
            		"sixty", "seventy", "eighty", "ninety"};
            
            hundredD = " hundred";
            thousandD= " thousand";
            millionD = " million";
            billionD = " billion";
        }
        else {
            units = new String[]{
                "", "bir", "iki", "üç", "dört", "beş", "altı", "yedi",
                "sekiz", "dokuz", "on", "on bir", "on iki", "on üç", "on dört",
                "on beş", "on altı", "on yedi", "on sekiz", "on dokuz"
            };

            tens = new String[]{ "", "", "yirmi", "otuz", "kırk", "elli", 
            		"altmış", "yetmiş", "seksen", "doksan"};
            
            hundredD = " yüz";
            thousandD= " bin";
            millionD = " milyon";
            billionD = " milyar";
        }
        
        if (n < 0) 
        {
            if(flagEN)
                return "minus " + intToString(-n, flagEN);
            else
                return "eksi " + intToString(-n, flagEN);
        }
        
        if (n < 20) 
        {
            return units[n];
        }

        if (n < 100) 
        {
        	if(n % 10 != 0)
        		return tens[n / 10] + " " + units[n % 10];
        	else
        		return tens[n / 10] + "" + units[n % 10];
        }

        if (n < 1000) 
        {
        	if(n % 100 != 0)
        		return units[n / 100] + hundredD + " " + intToString(n % 100, flagEN);
        	else
        		return units[n / 100] + hundredD + "" + intToString(n % 100, flagEN);
        }

        if (n < 1000000) 
        {
        	if(n % 1000 != 0)
        		return intToString(n / 1000, flagEN) + thousandD + " " + intToString(n % 1000, flagEN);
        	else
        		return intToString(n / 1000, flagEN) + thousandD + "" + intToString(n % 1000, flagEN);
        }

        if (n < 1000000000) 
        {
        	if(n % 1000000 != 0)
        		return intToString(n / 1000000, flagEN) + millionD + " "+ intToString(n % 1000000, flagEN);
        	else
        		return intToString(n / 1000000, flagEN) + millionD + "" + intToString(n % 1000000, flagEN);
        }

        if(n % 1000000000 != 0)
        	return intToString(n / 1000000000, flagEN) + billionD  + " " + intToString(n % 1000000000, flagEN);
        else
        	return intToString(n / 1000000000, flagEN) + billionD  + "" + intToString(n % 1000000000, flagEN);
	}
}