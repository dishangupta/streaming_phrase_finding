import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Vector;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class MessageGenerator {
	
	public static void main (String[] args) {
				
		String[] tokens;
				
		try {
			 BufferedWriter log = new BufferedWriter(new 
					  OutputStreamWriter(System.out));
			BufferedReader reader = new BufferedReader(		
	            new InputStreamReader(System.in, Charset.defaultCharset())); 
	            String line;
	            while ((line = reader.readLine()) != null) {
	            	
	            	tokens = line.split("\\s+");
			
			// Generate one message for bigram with same words
			if (tokens[0].equals(tokens[1])) {
				log.write(tokens[0] + "," + tokens[0] + 
					" " + tokens[1] + "\n");
				continue;			
			}
	            	
			log.write(tokens[0] + "," + tokens[0] + " " + tokens[1] + "\n"); 
			log.write(tokens[1] + "," + tokens[0] + " " + tokens[1] + "\n");           		            	
	            	
	            }
		    log.close();
		    reader.close();
	        }
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("No Input from StdIn!");
		}
	}
	
}
