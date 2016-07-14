package textgen;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		// TODO: Implement this method
		
		// split text to words. yes it is not so effective. 
		List<String> words = this.getTokens("[a-zA-Z.,']+", sourceText);
		if(words.size() < 1) {
			return;			               // no any words in input sourceText
		}
		this.starter = words.remove(0);    // set "starter" to be the first word in the text
		String prevWord = this.starter;    // set "prevWord" to be starter
		ListNode node;
		for(String word: words) {       
			//System.out.print(word + "-");
			if((node = getWordInListNode(prevWord)) != null) { // check to see if "prevWord" is already a node in the list
				node.addNextWord(word);                        //  add "w" as a nextWord to the "prevWord" node
			} else {
				node = new ListNode(prevWord);                 // add a node to the list with "prevWord" as the node's word
				node.addNextWord(word);                        // add "w" as a nextWord to the "prevWord" node
				wordList.add(node);
			}
			prevWord = word;                                   // set "prevWord" = "w" 
		}
		if((node = getWordInListNode(prevWord)) != null) {     // add starter to be a next word for the last word in the source text
			node.addNextWord(this.starter);                
		} else {
			node = new ListNode(prevWord);                 
			node.addNextWord(this.starter);                        
			wordList.add(node);
		}	
	}
	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
	    // TODO: Implement this method
		
		if(this.starter == null || numWords < 1) {                // not trained yet or number of words incorrect
			return "";
		}
		String currWord = this.starter;   // set "currWord" to be the starter word
		String output = new String("");   // set "output" to be ""
		output += currWord;               // add "currWord" to output
		ListNode node;
		String word;
		for(int i = 1; i < numWords; i++) {                        // while you need more words
			if((node = getWordInListNode(currWord)) != null) {     // find the "node" corresponding to "currWord" in the list
				word = node.getRandomNextWord(rnGenerator);        // select a random word "w" from the "wordList" for "node"
				output += " " + word;                              // add "w" to the "output"
				currWord = word;                                   // set "currWord" to be "w" 
			}
		}
		return output;
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		// TODO: Implement this method.
		this.starter = new String("");                 // initiate class variables
		this.wordList = new LinkedList<ListNode>();
		this.train(sourceText);
	}
	
	// TODO: Add any private helper methods you need here.
	
	/** Returns the tokens that match the regex pattern from the document 
	 * text string.
	 * @param pattern A regular expression string specifying the 
	 *   token pattern desired
	 * @return A List of tokens from the document text that match the regex 
	 *   pattern
	 */
	protected List<String> getTokens(String pattern, String text)
	{
		ArrayList<String> tokens = new ArrayList<String>();
		Pattern tokSplitter = Pattern.compile(pattern);
		Matcher m = tokSplitter.matcher(text);
		
		while (m.find()) {
			tokens.add(m.group());
		}
		
		return tokens;
	}
	
	/** Check if word already exists in ListNode
	 *  @param word
	 *  @return word node if exits, null if not exist
	 */
	protected ListNode getWordInListNode(String word) {
		for(ListNode node: this.wordList) {
			if(node.getWord().equals(word)) {
				return node;
			}
		}
		return null;
	}
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		//String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		String textString = "hi there hi Leo";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(10));
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		
		//String text = document.DocumentBenchmarking.getStringFromFile("data/warAndPeace.txt", 500000);
		//gen.retrain(text);
		//System.out.println(gen);
		//System.out.println(gen.generateText(50));
		
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		// TODO: Implement this method
	    // The random number generator should be passed from 
	    // the MarkovTextGeneratorLoL class
		
		// from http://stackoverflow.com/questions/363681/generating-random-integers-in-a-specific-range
		// int randomNum = rand.nextInt((max - min) + 1) + min;
		int randomIndex = generator.nextInt((this.nextWords.size() - 1 - 0) + 1) + 0;
		//System.out.println("nextWord.size()=" + this.nextWords.size() + " randomIndex=" + randomIndex);
	    return nextWords.get(randomIndex); // return random word form list
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}


