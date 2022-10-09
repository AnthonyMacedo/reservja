package Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class testeDate {

	public static void main(String[] args) {
			
		Calendar c = Calendar.getInstance();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		DateFormat data = DateFormat.getInstance();
		
		Date data2 = new Date();
		
		if(data2.after(c.getTime()) || data2.equals(c.getTime())) {
			System.out.println("passou");
		}else {
			System.out.println(c.getTime());
			System.out.println(data2.after(c.getTime()));
		}
		
		


	}

}
