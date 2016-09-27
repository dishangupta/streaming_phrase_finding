import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class PhraseGenerator {
	
	
	public static void main (String[] args) {
		PhraseStatistics[] topPhrases = new PhraseStatistics[20];
		
		int lineNum = 0;
		double phraseNess = 0.0;
		double informativeNess = 0.0;
						
		String attValues = "";
		String[] temp;
				
		PhraseStatistics curPhrase;
								
		try {
			BufferedReader reader = new BufferedReader(		
	            new InputStreamReader(System.in, Charset.defaultCharset())); 
	            String line;
	            while ((line = reader.readLine()) != null) {
	            	
	            	lineNum++;
	            	            	
	            	temp = line.split("\\t");
	            	attValues = attValues + temp[1] + ",";
	            		            	
	            	if (lineNum %3 == 0) {
	            		attValues = attValues.substring(0, attValues.length()-1);
	            		long[] featureCounts = Utils.extractCounts(attValues);
	            		
	            		phraseNess = phraseNess(featureCounts);
	            		informativeNess = informativeNess(featureCounts);
	            		
	            		curPhrase = new PhraseStatistics();
	            		curPhrase.phrase = temp[0];
	            		curPhrase.phraseNess = phraseNess;
	            		curPhrase.informativeNess = informativeNess;
	            		curPhrase.totalScore = phraseNess + informativeNess;
	            		            		
	            		Utils.buildTopPhrases(topPhrases, curPhrase);
	            		attValues = "";
	            	}
	            	
	            	
	            }
	            	            
	            Utils.printStatistics(topPhrases);
		    reader.close();
	        }
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("No Input from StdIn!");
		}
		
	}
	
	public static double phraseNess (long[] featureCounts) {
		double pfgXY, pfgX, pfgY, phraseNess;
		
		//FeatureCounts Format: Bx, Cx, Bxy, Cxy, By, Cy
		//Constants Format: N1_bi, N1_uni, N2_bi, N2_uni,|V|_bi, |V|_uni 
		/*pfgXY = (double) (featureCounts[3]+1)/(1285628143L + 25441701L);
		pfgX = (double) (featureCounts[1]+1)/(5287762535L + 2443107L);
		pfgY = (double) (featureCounts[5]+1)/(5287762535L + 2443107L);*/
		
		pfgXY = (double) (featureCounts[3]+1)/(3647529L + 14997L);
		pfgX = (double) (featureCounts[1]+1)/(3829099428L + 12036L);
		pfgY = (double) (featureCounts[5]+1)/(3829099428L + 12036L);
						
		phraseNess = (pfgXY)*Math.log(pfgXY/(pfgX*pfgY)); 
		
		return phraseNess;
	}
	
	public static double informativeNess (long[] featureCounts) {
		
		double pfgXY, pbgXY, informativeNess;
		
		//FeatureCounts Format: Bx, Cx, Bxy, Cxy, By, Cy
		//Constants Format: N1_bi, N1_uni, N2_bi, N2_uni,|V|_bi, |V|_uni 
		/*pfgXY = (double) (featureCounts[3]+1)/(1285628143L + 25441701L);
		pbgXY = (double) (featureCounts[2]+1)/(5312602963L + 25441701L);*/
		
		pfgXY = (double) (featureCounts[3]+1)/(3647529L + 14997L);
		pbgXY = (double) (featureCounts[2]+1)/(11579626L + 14997L);
					
		informativeNess = (pfgXY)*Math.log(pfgXY/pbgXY); 
		
		return informativeNess;
			
	}
}
