package util;

public class StringUtil {
	
	public static int removeDot(float f){
		int i ;
		String s=Float.toString(f);
		if(s.indexOf(".")==-1)
			return Float.floatToIntBits(f);
		else
			return Integer.parseInt(s.substring(0,s.indexOf(".")));
	}
	
	public static int removeDot(double f){
		int i ;
		String s=Double.toString(f);
		if(s.indexOf(".")==-1)
			return (int)f;
		else
			return Integer.parseInt(s.substring(0,s.indexOf(".")));
	}

}
