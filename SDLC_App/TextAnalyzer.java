// Jesus Sanchez-Rivera
// CEN 3024
// Prof Walauskis
// 9/18/2022

// Text Analyzer - read text from an HTML page
// removing any special characters and HTML tags
// and output a fixed amount of the top most
// frequent words found in the text.

//package application;

import java.net.URL;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.*;

public class TextAnalyzer
{
	// Function to sort a hashmap by value,
	// then by key and in descending order.
	public static Map<String, Integer> sortByVal(HashMap<String, Integer> w)
	{
		Map<String, Integer> unsorted = w;
		LinkedHashMap<String, Integer> descendingOrder = new LinkedHashMap<>();
		
		// Sort by descending value then alphabetical keys
		unsorted.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()
		.thenComparing(Map.Entry.comparingByKey())).forEach(x -> descendingOrder.put(x.getKey(), x.getValue()));
		
		return descendingOrder;
	}
	
	public static void replaceSpecialChar(StringBuilder sb, String text)
	{
		for (int x = 0; x < text.length(); x++)
		{
			if (text.charAt(x) > 64 && text.charAt(x) <= 122 || text.charAt(x) == 32)
			{
				// The text contains a valid character
				sb.append(text.charAt(x));
			}
			else if ((x + 2 < text.length()) && (x - 2 >= 0))
			{
				// Special character replace with apostrophe
				if (text.charAt(x - 2) > 64 && text.charAt(x - 2) <= 122)
				{

					if (text.charAt(x + 2) == 115)
						sb.append("'");
				}
				else if (text.charAt(x + 1) == 84)
				{
					
					if (text.charAt(x + 2) == 105 && text.charAt(x + 3) == 115)
						sb.append("'");
				}
			}
		}
		
		// Special case for the word
		// o'er
		if (sb.equals("oer"))
			sb.insert(1, "'");

	}
	
	public static void cleanText(HashMap<String, Integer> wordFreq, StringBuilder sb, String text)
	{
		List<String> wordArray = Arrays.asList(text.split(" "));
		ArrayList<String> wordList = new ArrayList<String>(wordArray);
		String temp = "";
		StringBuilder cleanWord = new StringBuilder();
		int cleanup = 0;
		int loc = -1;
		boolean mdash = false;
		
		// Function to remove the special character
		// mdash "--" from the text
		for (int x = 0; x < wordArray.size(); x++)
		{
			// Remove mdash character
			cleanWord.append(wordList.get(x));

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
		
		
		for (int x = 0; x < wordList.size(); x++)
		{
			cleanWord.delete(0, cleanWord.length());
			
			cleanWord.append(wordList.get(x).toLowerCase());
			
			replaceSpecialChar(sb, cleanWord.toString());
			
			// Add string as an ArrayList object instead of
			// string
			if (sb.length() > 0)
				wordFreq.merge(sb.toString(), 1, Integer::sum);
			
			// reset sb for next word
			sb.delete(0, sb.length());
		}
	}
	
	
	//public static void main(String[] args) throws IOException
	public static Map<String, Integer> analyzeText(String web) throws IOException
	{
		// Website URL pass as argument on command line
		// and check if there is no website provided
		String webpage = (web.length() > 0) ? web : "";
		if (webpage.equals(""))
		{
			System.out.println("No website provided. Enter website URL in command line.");
			System.out.println("Example: java TextAnalyzer www.example.com\n");
		}
		
		URL webtext = new URL(webpage);
		
		HashMap<String, Integer> wordFreq = new HashMap<>();
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(webtext.openStream())))
		{
			// How many words to print
			int counter = 0;
			
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
			
			//System.out.println("Word Frequency:");
			
			// Return hashmap sorted by value and alphabetical
			// keys.
			Map<String, Integer> sortedWordFreq = sortByVal(wordFreq);
			
			return sortedWordFreq;
			
			/*
			for (Map.Entry<String, Integer> set : sortedWordFreq.entrySet())
			{
				System.out.print((++counter) + ". " + set.getKey()
					+ " " + set.getValue());
				
				System.out.println();
			}
			*/
		}
	}
}
