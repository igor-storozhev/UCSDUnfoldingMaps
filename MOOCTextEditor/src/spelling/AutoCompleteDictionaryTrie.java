package spelling;

import java.util.List;
import java.util.Set;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
		this.size = 0;
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should convert the 
	 * string to all lower case before you insert it. 
	 * 
	 * This method adds a word by creating and linking the necessary trie nodes 
	 * into the trie, as described outlined in the videos for this week. It 
	 * should appropriately use existing nodes in the trie, only creating new 
	 * nodes when necessary. E.g. If the word "no" is already in the trie, 
	 * then adding the word "now" would add only one additional node 
	 * (for the 'w').
	 * 
	 * @return true if the word was successfully added or false if it already exists
	 * in the dictionary.
	 */
	public boolean addWord(String word)
	{
	    //TODO: Implement this method.

		// check input conditions
		if(word == null || word.equals("") == true) {
			return false;
		}
		// convert word to chars array
		char[] chars = word.toLowerCase().toCharArray();
		
		// locate node for word to insert, or locate that word is already in trie
		TrieNode curNode = root;
		boolean insertChild = false;
		for(char ch: chars) {
			// move to next node by ch key if it exists or create new
			if(curNode.getChild(ch) == null) {
				// node with ch is not exists, create it
				//System.out.println("insert ch=" + ch);
				curNode = curNode.insert(ch);
				insertChild = true;
			} else {
				// set curNode to node found in dictionary
				//System.out.println("find node ch=" + ch);
				curNode = curNode.getChild(ch);
			}
			// find if current node has this word
			if(curNode.getText().equals(word.toLowerCase())) {
				if(insertChild) {
					curNode.setEndsWord(true);
					this.size ++;
					//System.out.println("word " + word + " new word " + curNode.getText() + " size=" +this.size);
					return true;
				} else {
					//System.out.println("word " + word + " found " + curNode.getText());
					if(curNode.endsWord()) {
						return false; // already exist in dictionary
					} else {
						curNode.setEndsWord(true);
						this.size ++;
						return true;  // new word as shorter version previous word
					}
				}
			}
		}
		return true;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    //TODO: Implement this method
	    return this.size;
	}
	
	
	/** Returns whether the string is a word in the trie, using the algorithm
	 * described in the videos for this week. */
	@Override
	public boolean isWord(String s) 
	{
	    // TODO: Implement this method

		// check input conditions
		if(s == null || s.equals("") == true) {
			return false;
		}
		// convert word to chars array
		char[] chars = s.toLowerCase().toCharArray();
		
		// locate node for word to insert, or locate that word is already in trie
		TrieNode curNode = root;
		for(char ch: chars) {
			// next node
			if(curNode.getChild(ch) == null) {
				// next node is not exists, so word is not found
				return false;
			}
			// set curNode to node found in dictionary
			curNode = curNode.getChild(ch);
			// find if current node has this word
			if(curNode.endsWord() && curNode.getText().equals(s.toLowerCase()) == true) {
				return true; // exist in dictionary
			}
			
		}
		if(curNode.getText().equals(s.toLowerCase()) == true) {
			return true; // exists in dictionary
		}
		return false;    // not exists in dictionary
	}

	/** 
     * Return a list, in order of increasing (non-decreasing) word length,
     * containing the numCompletions shortest legal completions 
     * of the prefix string. All legal completions must be valid words in the 
     * dictionary. If the prefix itself is a valid word, it is included 
     * in the list of returned words. 
     * 
     * The list of completions must contain 
     * all of the shortest completions, but when there are ties, it may break 
     * them in any order. For example, if there the prefix string is "ste" and 
     * only the words "step", "stem", "stew", "steer" and "steep" are in the 
     * dictionary, when the user asks for 4 completions, the list must include 
     * "step", "stem" and "stew", but may include either the word 
     * "steer" or "steep".
     * 
     * If this string prefix is not in the trie, it returns an empty list.
     * 
     * @param prefix The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to numCompletions best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
    	 
         return null;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText() + " isWord:" + curr.endsWord());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}