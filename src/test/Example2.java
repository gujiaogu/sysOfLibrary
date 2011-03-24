package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class Example2 {
	
	public static void main(String args[])throws Exception{
			URL url = new URL("http://www.cdu.edu.cn");
			InputStreamReader isr = new InputStreamReader(url.openStream());
			BufferedReader bufr = new BufferedReader(isr);
			String s = bufr.readLine();
			if(s != null && !"".equals(s)){
				System.out.println(s);
			}
			isr.close();
			bufr.close();
	}
}
