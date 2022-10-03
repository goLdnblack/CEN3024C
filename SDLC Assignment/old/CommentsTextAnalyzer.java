import java.net.URL;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.*;

public class TextAnalyzer
{
	public static Map<String, Integer> sortByVal(HashMap<String, Integer> w)
	{
		Map<String, Integer> unsorted = w;
		LinkedHashMap<String, Integer> descendingOrder = new LinkedHashMap<>();
		unsorted.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
		.forEachOrdered(x -> descendingOrder.put(x.getKey(), x.getValue()));
		
		return descendingOrder;
	}
	
	public static void replaceSpecialChar(StringBuilder sb, String text)
	{
		for (int x = 0; x < text.length(); x++)
		{
			if (text.charAt(x) > 64 && text.charAt(x) <= 122 || text.charAt(x) == 32)
			{
				
				
				
				sb.append(text.charAt(x));
			}
			else if ((x + 2 < text.length()) && (x - 2 >= 0))
			{
				// Special character
				if (text.charAt(x - 2) > 64 && text.charAt(x - 2) <= 122)
				{

					if (text.charAt(x + 2) == 115)
					{
						sb.append("'");
					}
				}
				else if (text.charAt(x + 1) == 84)
				{
					
					if (text.charAt(x + 2) == 105 && text.charAt(x + 3) == 115)
						sb.append("'");
				}
			}
		}
		
		if (sb.equals("oer"))
			sb.insert(1, "'");
		
		//sb.append(" ");

	}
	
	public static void cleanText(HashMap<String, Integer> wordFreq, StringBuilder sb, String text)
	{
		//String[] wordArray = text.split(" ");
		List<String> wordArray = Arrays.asList(text.split(" "));
		ArrayList<String> wordList = new ArrayList<String>(wordArray);
		String temp = "";
		StringBuilder cleanWord = new StringBuilder();
		int cleanup = 0;
		int loc = -1;
		boolean mdash = false;
		
		for (int x = 0; x < wordArray.size(); x++)
		{
			// Remove mdash character
			cleanWord.append(wordList.get(x));
			//System.out.println(cleanWord);
			while ((cleanup = cleanWord.indexOf("&mdash")) != -1)
			{
				cleanWord.delete(cleanup, cleanup + 7);
				wordList.set(x, cleanWord.toString());
				loc = cleanup;
				mdash = true;
			}
			
			// Check if there's multiple words
			if (loc + 1 < cleanWord.length() && mdash == true)
			{
				temp = cleanWord.substring(loc);
				wordList.add(temp);
				cleanWord.delete(loc, cleanWord.length());
				wordList.set(x, cleanWord.toString());
				mdash = false;
				loc = -1;
			}
			
			cleanWord.delete(0, cleanWord.length());
		}
		
		// After m dash
		
		for (int x = 0; x < wordList.size(); x++)
		{
			cleanWord.delete(0, cleanWord.length());
			//System.out.println(wordList.get(x));
			cleanWord.append(wordList.get(x).toLowerCase());
			
			//System.out.println(cleanWord);
			
			/*
			// Remove mdash character
			cleanWord.append(wordList.get(x));
			while ((cleanup = cleanWord.indexOf("&mdash;")) != -1)
			{
				cleanWord.delete(cleanup, cleanup + 7);
				loc = cleanup;
			}
			
			// Check if there's multiple words
			if (loc + 1 < cleanWord.length())
			{
				
			}
			*/
			replaceSpecialChar(sb, cleanWord.toString());
			//sb.append(" ");
			//System.out.print(sb);
			
			// Add string as an ArrayList object instead of
			// string
			if (sb.length() > 0)
			{
				//ArrayList<String, Integer> nextWord;
				wordFreq.merge(sb.toString(), 1, Integer::sum);
				//if (wordFreq.containsKey(sb.toString()))
				//{
					
				//	int freq = wordFreq.get(sb.toString());
				//	wordFreq.put(sb.toString(), freq + 1);
				//}
				//else
				//{
				//	wordFreq.put(sb.toString(), 1);
					//nextWord = new ArrayList<String>();
					//nextWord.add(1);
					//wordFreq.put(sb.toString(), nextWord);
					//wordFreq.merge(sb.toString(), 1, Integer::sum);
				//}
			}
			
			// reset sb for next word
			sb.delete(0, sb.length());
		}
		
		//System.out.println(sb);
	}
	
	
	public static void main(String[] args) throws IOException
	{
		// Webpage URL pass as argument on command line
		URL webtext = new URL(args[0]);
		
		HashMap<String, Integer> wordFreq = new HashMap<>();
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(webtext.openStream())))
		{
			// How many words to print
			int counter = 0;
			int wordCount = 20;
			
			// Set to find text to read on website
			String readInit = "START OF THE PROJECT GUTENBERG EBOOK THE RAVEN";
			String readEnd = "END OF THE PROJECT GUTENBERG EBOOK THE RAVEN";
			
			StringBuilder sb = new StringBuilder();
			String text;
			// Poem assignment: https://www.gutenberg.org/files/1065/1065-h/1065-h.htm
			
			while ((text = br.readLine()) != null)
			{
				if (text.contains(readInit))
				{
					// Set to skip to the text on website val 19
					int skipLines = 19;
					
					for (int x = 0; x<skipLines; x++)
						text = br.readLine();
				
					do
					{
						// Remove any HTML tags from text
						text = text.replaceAll("\\<.*?\\>", "");
						
						cleanText(wordFreq, sb, text);

						sb.delete(0, sb.length());

						text = br.readLine();
					} while (text != null && !text.contains(readEnd));
					
					// End reading website
				}
			}
			
			System.out.println("Top 20 Most Frequent Words:");
			
			Map<String, Integer> sortedWordFreq = sortByVal(wordFreq);
			
			for (Map.Entry<String, Integer> set : sortedWordFreq.entrySet())
			{
				System.out.print((counter + 1) + ". " + set.getKey() + " " + set.getValue());
				System.out.println();
				
				counter++;
				
				if (counter == wordCount)
					break;
			}
		}
	}
}