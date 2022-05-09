import java.util.Scanner;

public class ProductTester {
	
	
	 static void ProgramStart() {
			Scanner options = new Scanner(System.in);
			Product product = new Product();
			
			System.out.println("################### HauptMenü ###################");
			System.out.println("[1]- Alle Produkte sehen");
			System.out.println("[2]- Progarmm beenden?");
			
			int selected = options.nextInt();
				
			switch(selected) {
			
				case 1:		
					product.GetProductsInfo();
					ProgramStart(); // show main menu again
					
					break;
				
				case 2:
					
					break;
					
				default:
					System.out.println("Sie sollen 1 oder 2 eingeben");
				}
		 }

	public static void main(String[] args) {
		
		ProgramStart(); //start the program
		
	}

}
