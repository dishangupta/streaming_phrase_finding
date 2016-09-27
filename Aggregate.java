import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class Aggregate {
	
	public static void main (String[] args) {
		
		if (Integer.parseInt(args[0]) == 0)
			processUnigrams();
		else
			processBigrams();
			
	}
	
	public static void processUnigrams() {
		// MergeCounts
		String previousKey = null;
		
		String[] tokens;
		int year, count;
		long fgCount = 0, bgCount = 0;
		//long fgTokens = 0, bgTokens = 0;
		//long vocabSize = 0;
				
		try {
			 BufferedWriter log = new BufferedWriter(new 
					  OutputStreamWriter(System.out));
			BufferedReader reader = new BufferedReader(		
	            new InputStreamReader(System.in, Charset.defaultCharset())); 
	            String line;
	            while ((line = reader.readLine()) != null) {
	              tokens = line.split("\\s+");
		       	              
	              //Discard phrases with stopwords
	                            	              
	              if (Configuration.STOPWORDS.contains(tokens[0]))
	            	  continue;
	              	              
	              year = Integer.parseInt(tokens[1]);
	              count = Integer.parseInt(tokens[2]);
	              	              
	              //Collect foreground and background frequencies
	              if (tokens[0].equals(previousKey)) 
	            		bgCount = bgCount + count;
		    	
		      else {
	            	if (previousKey != null)
	            		log.write(previousKey + "\t" + "Bx=" + 
	            				(bgCount-fgCount) + "," + "Cx=" + fgCount + "\n");

			//bgTokens = bgTokens + bgCount - fgCount;
			//fgTokens = fgTokens + fgCount;					

			if (year == 1960)
	            		fgCount = count;
	            	else 
	            		fgCount = 0;
	            	
	            	bgCount = count;
	            	previousKey = tokens[0];
			//vocabSize++;
			}
		    }
	            log.write(previousKey + "\t" + "Bx=" + 
        				(bgCount-fgCount) + "," + "Cx=" + fgCount+"\n");
		    
		    /*bgTokens = bgTokens + bgCount - fgCount;
		    fgTokens = fgTokens + fgCount;	
			
		    log.write("N1:" + bgTokens + "\n");
		    log.write("N2:" + fgTokens + "\n");
		    log.write("|V|:" + vocabSize + "\n");*/	            
		
		    log.close();	   
	            reader.close();
        	}
		catch (Exception e) {
			e.printStackTrace();
			//System.out.println("No Input from StdIn!");
		}
	}

	public static void processBigrams() {
		// MergeCounts
		String key;		
		String previousKey = null;
		
		String[] tokens;
		int year, count;
		long fgCount = 0, bgCount = 0;
		//long fgTokens = 0, bgTokens = 0;
		//long vocabSize = 0;
						
		try {
			 BufferedWriter log = new BufferedWriter(new 
					  OutputStreamWriter(System.out));
			BufferedReader reader = new BufferedReader(		
	            new InputStreamReader(System.in, Charset.defaultCharset())); 
	            String line;
	            while ((line = reader.readLine()) != null) {
	              tokens = line.split("\\s+");
		       	              
	              //Discard phrases with stopwords
	                            	              
	              if (Configuration.STOPWORDS.contains(tokens[0]) || 
				Configuration.STOPWORDS.contains(tokens[1]))
	            	  continue;
	              	              
		      key = tokens[0] + " " + tokens[1];	              
		      year = Integer.parseInt(tokens[2]);
	              count = Integer.parseInt(tokens[3]);
	              	              
		      //Collect foreground and background frequencies
	              if (key.equals(previousKey)) 
	            		bgCount = bgCount + count;
				
		      else {
	            	if (previousKey != null)
	            		log.write(previousKey + "\t" + "Bxy=" + 
	            				(bgCount-fgCount) + "," + "Cxy=" + fgCount + "\n");
			
			//bgTokens = bgTokens + bgCount - fgCount;
			//fgTokens = fgTokens + fgCount;			 	            		            		            	
	            	
			if (year == 1960)
	            		fgCount = count;
	            	else 
	            		fgCount = 0;
	            	
	            	bgCount = count;
	            	previousKey = key;
			//vocabSize++;
			}
			
	            }
	            log.write(previousKey + "\t" + "Bxy=" + 
        				(bgCount-fgCount) + "," + "Cxy=" + fgCount+"\n");
	                
		    /*bgTokens = bgTokens + bgCount - fgCount;
		    fgTokens = fgTokens + fgCount;	
			
		    log.write("N1:" + bgTokens + "\n");
		    log.write("N2:" + fgTokens + "\n");
		    log.write("|V|:" + vocabSize + "\n");*/
      
	            log.close();
		    reader.close();
        	}
		catch (Exception e) {
			e.printStackTrace();
			//System.out.println("No Input from StdIn!");
		}
		
		
	
	}
}
