import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class MessageUnigramCombiner {
	
	public static void main (String[] args) {
		
		String key;
		String previousKey = null;
		
		String unigramCounts = "";
		String requester = "";
		
		String[] tokens;
						
		try {
			 BufferedWriter log = new BufferedWriter(new 
					  OutputStreamWriter(System.out));
			BufferedReader reader = new BufferedReader(		
	            new InputStreamReader(System.in, Charset.defaultCharset())); 
	            String line;
	            while ((line = reader.readLine()) != null) {
	            	
			tokens = line.split("\\t");
	            	if (tokens.length == 2) {
	            		key = tokens[0];
	            		unigramCounts = tokens[1];
	            	}	
	            	else {
				tokens = tokens[0].split(",");	            		
				key = tokens[0];
				requester = tokens[1];
			}
	            	
	            	if (key.equals(previousKey)) {
	            		int pos = checkPositionInBigram(requester, key);
	            		if (pos == 0)
	            			log.write(requester +"\t"+unigramCounts+"\n");
	            		else if (pos == 1) 
	            			log.write(requester +"\t"+
	            					unigramCounts.replaceAll("x", "y")+"\n");
	            		//Generate both Bx, Cx and By, Cy for bigram
	            		//having same words
	            		else {
	            			log.write(requester +"\t"+unigramCounts+"\n");
	            			log.write(requester +"\t"+
	            					unigramCounts.replaceAll("x", "y")+"\n");
	            		}	
	            	}
	            	else {
	            		previousKey = key;
	            	}
	            }
		    log.close();
		    reader.close();
	        }
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("No Input from StdIn!");
		}
	}
	
	public static int checkPositionInBigram (String bigram, String word) {
		String[] words = bigram.split("\\s+");
		
		if (words[0].equals(words[1]))
			return -1;
		else if (words[0].equals(word))
			return 0;
		else
			return 1;
	}

}

