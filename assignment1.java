import sheffield.EasyReader;
import sheffield.EasyWriter;

public class Assignment1 {
	
	
	public static final float POUNDS_TO_PENNIES = 100.0f, 
		POUNDS_TO_OLD_PENNIES = 240.0f;
	public static final int SHILLINGS_TO_PENCE = 5;
	
	private static int pounds, pennies, shillings, oldPennies;
	private static EasyWriter writer;
	
	
	public static void main(String[] args) {
		// First Task
		EasyReader readerForCMD = new EasyReader();
		double firstAmount = readerForCMD.readDouble("What amount do you want "
			+"to convert? ");
		converting(firstAmount);
		writer = new EasyWriter();
		writer.prompt("That is £"+pounds+"."+shillings+"s."+oldPennies+"d\n");
			
		
		// Second Task
		writer.prompt("\n   New     L     s     d\n");
		addResults(firstAmount);
		
		
		// Third Task
		EasyReader readerForFile = new EasyReader("money.txt");
		double secondAmount = readerForFile.readDouble();
		converting(secondAmount);
		addResults(secondAmount);
		
		
		//Fourth Task
		String lastLine = readerForFile.readString();
		double thirdAmount = Double.valueOf(lastLine.substring(
			lastLine.indexOf("t")+2, lastLine.indexOf("to")-1));
		converting(thirdAmount);
		addResults(thirdAmount);
		
	}
	
	
	
	private static void converting(double amount) {
		pounds = (int)(amount);
		pennies = (int) ((amount-pounds) * POUNDS_TO_PENNIES);
		shillings = (int) (pennies / SHILLINGS_TO_PENCE);
		oldPennies = Math.round((pennies % SHILLINGS_TO_PENCE) 
			/ POUNDS_TO_PENNIES * POUNDS_TO_OLD_PENNIES);
	}
	
	
	/*** This method adds a new row showing the currency in modern  british 
	pounds defined by the variable amount and the conversion old british pounds
	spread across different columns named L for pounds, s for shillings and 
	d for old pennies
	***/
	private static void addResults(double amount) {
		writer.print(amount, 2, 6);
		writer.print(pounds, 6);
		writer.print(shillings, 6);
		writer.println(oldPennies, 6);
	}
}