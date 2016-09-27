import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Vector;

public class Utils {

	public static long[] extractCounts (String attValues) {
		
		String[] values = attValues.split(",");
		long[] counts = new long[6];
		
		counts[0] = Long.parseLong(values[0].substring(3, values[0].length()));	
		counts[1] = Long.parseLong(values[1].substring(3, values[1].length()));	
		counts[2] = Long.parseLong(values[2].substring(4, values[2].length()));	
		counts[3] = Long.parseLong(values[3].substring(4, values[3].length()));	
		counts[4] = Long.parseLong(values[4].substring(3, values[4].length()));	
		counts[5] = Long.parseLong(values[5].substring(3, values[5].length()));	
			
		return counts;
		
	}
	
	public static boolean checkConstant (String str) {
		if (str.contains("N1") || str.contains("N2")
				|| str.contains("|V|"))
			return true;
		return false;
	}	
	
	public static void buildTopPhrases (PhraseStatistics[] 
			topPhrases, PhraseStatistics curPhrase) {
		
		if (topPhrases[0] == null) {
			for (int i = 19; i >= 0; i--) {
				if (topPhrases[i] == null) {
					topPhrases[i] = curPhrase;
					
					if (i == 0) {
						Arrays.sort(topPhrases, new Comparator<PhraseStatistics>(){
							   @Override
							   public int compare(PhraseStatistics o1, PhraseStatistics o2) {
							     //TODO return 1 if rhs should be before lhs 
							     //     return -1 if lhs should be before rhs
							     //     return 0 otherwise
								   if (o1.totalScore > o2.totalScore)
									   return -1;
								   else if (o1.totalScore < o2.totalScore)
									   return 1;
												   
								   return 0;
							     }
							 });
					}
						
					return;
				}
			}
		}
		
		else {
			double prevScore = topPhrases[19].totalScore;
			double curScore = curPhrase.totalScore;
			
			if (curScore > prevScore) {
				int i;				
				for (i = 19; i >= 1; i--) {
					prevScore = topPhrases[i-1].totalScore;
					
					if (curScore > prevScore) 
						topPhrases[i] = topPhrases[i-1];
					else {
						topPhrases[i] = curPhrase;
						return;
					}		
				}
				
				if (i == 0)
					topPhrases[i] = curPhrase;
			}	
		}
		
		return;
	}
	
	public static void printStatistics (PhraseStatistics[] topPhrases) {
		
		try {
			BufferedWriter log = new BufferedWriter(new 
					  OutputStreamWriter(System.out));			
			for (PhraseStatistics phrase: topPhrases) {
				//log.write(phrase.phrase+"\t"+phrase.totalScore+"\n");				
				log.write(phrase.phrase+"\t"+phrase.totalScore+"\t"
						+phrase.phraseNess+"\t"+phrase.informativeNess+"\n");
			}
			log.close();
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error while printing phrases!");
		}
	}
}
