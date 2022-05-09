
import java.time.*;
import java.time.temporal.ChronoUnit;

public class Product {
	

	private String bezeichnung;
	private int  qualität;
	private String verfallsdatum;
	private final double preise = 0;
	
	
	static String[][] productArr = {{"Bezeichnung","Qualitaet","Verfallsdatum","Preise"},
									{"Kaese","25", "2022-05-09","9"},
									{"Wine","10", "0001-01-01","5"}};
	
	
	public void GetProductsInfo() {
		// Get info of all products and check the new quality value
		System.out.println("Produkte Info:"); 
		
		//Print headers
		for (int row = 0; row < 1; row++) { 
            for (int col = 0; col < 4; col++) { 
            System.out.print(productArr[row][col] + "    \t"); 
            } 
            	System.out.println(); 
        } 
		
	    //Print rows
		for (int row = 1; row < productArr.length; row++) { 
            for (int col = 0; col < 4; col++) { 
            	
                 System.out.print(productArr[row][col] + "\t\t");  
            } 
            
            	System.out.println(); 
        } 
		
		System.out.println("\n##### Check Qualität #####");
		CheckQualitaet(); 
		
		System.out.println("##### Check Verfallsdatum #####\n");
		CheckVerfallsdatum();
		
		System.out.println("##### Check Tagesaktuelles Preise ######\n");
		SetTagesPreise();

	} //End GetProductsInfo
	
	
	private static void CheckVerfallsdatum() {
		String verfallsdatum;
		String bezeihnung;

		boolean valid;
		LocalDate currentDate = LocalDate.now();	     


		
		for (int row = 1; row < productArr.length; row++) { 
            for (int col = 2; col < 3; col++) {
            	verfallsdatum = productArr[row][col];
        		LocalDate expiryDate = LocalDate.parse(verfallsdatum);
        		valid = currentDate.isEqual(expiryDate); 
            	bezeihnung = GetBezeihnung(row);
            	if(valid) {
            		System.out.println("Achtung!!! Prodct: "+ bezeihnung +" muss ausgeräumt werden " + 
            	    "Verfallsdatum: " + verfallsdatum + "\n");
            	}

            }
		}
		
	}//end CheckVerfallsdatum
	
	
	private static String GetBezeihnung(int rowIn) {
		
		String  bezeihnung; 
		
		bezeihnung = productArr[rowIn][0]; 
		
		return bezeihnung;
		
		}//End GetBezeihnung
	
	 private static void SetTagesPreise() {
		String  preise ; 
		String  bezeihnung; 
		double  preiseDouble;
		double  tagesPreise;
		double  qualitaet = 0 ; 
		ChangeQualitaet();// update quality value
	  //Get prices 
		 for (int row = 1; row < productArr.length; row++) { 
	            for (int col = 3; col < 4; col++) { 
	            	
	           preise = productArr[row][col]; 
	           preiseDouble = Double.parseDouble(preise); // convert to double
	           qualitaet = GetQualitaet(row); //Get quality for each product
	           bezeihnung = GetBezeihnung(row);//Get label for each product
	           tagesPreise = preiseDouble + (0.1 * qualitaet);  //Tagespreis = Grundpreis + 0,1€ * Qualität
	           System.out.println("Tages Preis für " + bezeihnung +" : "+ tagesPreise +"\n");
	          
	            } 
	        } 
		
     
	 }//End SetTagesPreise
	 
	 
	 private static void ChangeQualitaet() { 
		 
       String herstellungsdatum = "2022-05-01"; //assumption
	   LocalDate currentDate = LocalDate.now();	
	   LocalDate hsDatum = LocalDate.parse(herstellungsdatum);
	   long   difference = ChronoUnit.DAYS.between(hsDatum, currentDate);
	   double newQualitaet;
	   String test;
	   
	    for (int row = 1; row < productArr.length; row++) { 
	    	
	 	         double qualitaet  = GetQualitaet(row);
		  	     String bezeihnung = GetBezeihnung(row);
		  	     if (bezeihnung.equalsIgnoreCase("wine") && qualitaet < 50){
		 //Das sollte passieren nur wenn Wine im Lager und nicht auf Dem Regal im Verkaufsraum ausgestellt is
		//neue spalte in Product array kann hinzugefügt werden(Status:ja/nein) zum checken ob product ist eingeräumt oder nicht
		  		            newQualitaet = qualitaet + difference;
		  			    	//System.out.println("newQualitaet "+ bezeihnung +" "+ newQualitaet); //debug
		  			    	String newQualitaetStr = Double.toString(newQualitaet);
		  			        productArr[row][1] = newQualitaetStr;
		  		  }else{
		  		    	    newQualitaet = qualitaet - difference;
		  		  	        //System.out.println("newQualitaet "+ bezeihnung +" "+ newQualitaet); //debug
		  		  	        String newQualitaetStr = Double.toString(newQualitaet);
		  		  	        productArr[row][1]= newQualitaetStr;
		  		  	        
		  		   }//end if condition
		  	     
           }//end for loop

	 }//end ChangeQualitaet
	 
	 
	 private static double GetQualitaet(int rowIn) {
			
			String  qualitaet; 
			double  qualitaetDouble = 0 ;
			
			qualitaet = productArr[rowIn][1]; 
		    qualitaetDouble = Double.parseDouble(qualitaet);
		    
			return qualitaetDouble;
			
			}//End GetQualitaet
	 
	 private static void CheckQualitaet() {
		 double qualitaet;
		 String bezeihnung;
			
		 for (int row = 1; row < productArr.length; row++) { 
	            for (int col = 1; col < 2; col++) { 
	            	
	            	 qualitaet  = GetQualitaet(row);
		     	     bezeihnung = GetBezeihnung(row);
		     	     if(bezeihnung.equalsIgnoreCase("kaese") && qualitaet < 30){
		     	    	 
		     	    	 System.out.println("\nAchtung!!! Käse muss sofort aus dem Regal ausgeräumt werden\n");
		     	    	 
					 }else if(qualitaet < 0) {
						 
						 System.out.println("\nAchtung!!! Qualitlät von Produckt: " + bezeihnung +" muss großer/gleich 0 sein\n");
						 //hier sollten wir bei produckt info Eingabe keine negative Werte erlauben 
						 
	                 }//end if
	            } 
	       } 
	 }//End CheckQualitaet
	 
}
