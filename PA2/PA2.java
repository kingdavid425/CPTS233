import java.util.*;
import java.io.*;

public class PA2 {

	//PA #2 TODO: finds the smallest tree in a given forest, allowing for a single skip
	//Finds the smallest tree (by weight) in the supplied forest.  
	//Note that this function accepts a second optional parameter of an index to skip.  
	//Use this index to allow your function to also find the 2nd smallest tree in the 
	//forest.
	//DO NOT change the first findSmallestTree function. Only work in the second one!
   public static int findSmallestTree(List<HuffmanTree<Character>> forest) {
      return findSmallestTree(forest, -1); //find the real smallest 
   }
   
   public static int findSmallestTree(List<HuffmanTree<Character>> forest, int index_to_ignore) {
   
      boolean hasSmallest = false;
      int smallest = 0;
      HuffmanTree<Character> tree;
      
      //iterates through the given forest and returns the index of the tree with the smallest weight
      //ignoring the tree at the index to ignore      
      for(int i = 0; i < forest.size(); i++) {
         if(i != index_to_ignore) {
            if(!hasSmallest) {
               hasSmallest = true;
               smallest = i;
            } else {
               tree = forest.get(i);
               if(tree.getWeight() < forest.get(smallest).getWeight()) {
                  smallest = i;
               }
            }     
         }
      }
      
      return smallest; //find the smallest except the index to ignore.
   }
   
   //huffmanTreeFromText: converts a text file into a tree containing the data from the text file
   //Parameters:
   //    - data: list that contains lines of the data from the text file
   //Returns: tree containing the same data as from the list
   public static HuffmanTree<Character> huffmanTreeFromText(List<String> data) {
      
      HuffmanTree<Character> dataTree = new HuffmanTree<Character>(null);
      boolean treeInitialized = false;
      
      Hashtable<Character, Integer> dataTable = new Hashtable<Character, Integer>();
      List<HuffmanTree<Character>> forest = new ArrayList<HuffmanTree<Character>>();
      
      int smallest, secondSmallest, value;
      String line;
      
      //create table that pairs a letter (key) with its frequency (value)
      for(int i = 0; i < data.size(); i++) {
         line = data.get(i);
         for(char c : line.toCharArray()) {
            if(dataTable.containsKey(c)) {
               //if the data table already contains the key we just want to increase the weight
               //by 1
               value = dataTable.get(c);
               value++;
               dataTable.remove(c);
               dataTable.put(c, value);   
            } else {
               //if the data table doesnt yet contain the letter then we add it to the table with 
               //weight = 1
               dataTable.put(c, 1);
            }
         }       
      }
      
      //create a list of huffman tress from the data in the table
      for(char c : dataTable.keySet()) {
         forest.add(new HuffmanTree(c, dataTable.get(c)));   
      }
      
      while(forest.size() > 1) {
         //retrieve smallest node
         smallest = findSmallestTree(forest);
         
         //retrieve second smallest node 
         secondSmallest = findSmallestTree(forest, smallest);
         
         //merge smallest and second smallest trees by weight
         dataTree = new HuffmanTree<Character>(forest.get(smallest), forest.get(secondSmallest));
         
         //replace smallest node with newly generated tree
         forest.set(smallest, dataTree);
         
         //remove second smallest node
         forest.remove(secondSmallest);  
      }
         
      return dataTree;
   }

	//huffmanTreeFromMap: converts a given map into a tree containing the same data
   //Parameters:
   //    - huffmanMap: map containg the data to be converted into a tree
   //Returns: tree containing the data from the given map
   public static HuffmanTree<Character> huffmanTreeFromMap(Map<Character, String> huffmanMap) {
      
      //points to the root of the tree 
      HuffmanInternalNode<Character> root = new HuffmanInternalNode<Character>(null, null);
      
      //points to the current spot of the tree at the present point of iteration
      HuffmanInternalNode<Character> current = root;
      
      //code containing the path taken to reach the data
      String code;      
      
      //iterates through the keys in the map
      for(char c : huffmanMap.keySet()) {
         //c||code
         code = huffmanMap.get(c);
         
         //if code length is 0, we know it is time to get a new code from the huffmanMap
         while(code.length() > 0) {
            //if code length is 1, we need to create a leafNode at either the left or right branch
            //of the node
            if(code.length() == 1) {
               if(code.charAt(0) == '0') {
                  current.setLeftChild(new HuffmanLeafNode(c, 0));      
               } else { //code.charAt(0) == '1'
                  current.setRightChild(new HuffmanLeafNode(c, 0));
               }
            //if code length > 1 then we need to continue traversing the tree
            } else { //code.length() > 1
            //situation: either the path has already been created (an internal node exists) or we
            //           need to create the path
               if(code.charAt(0) == '0') {
                  //create path if next spot in the iteration doesnt exist
                  if(current.getLeftChild() == null) {
                     current.setLeftChild(new HuffmanInternalNode(null, null));
                  }
                  //iterate to next spot in the tree
                  current = (HuffmanInternalNode<Character>) current.getLeftChild();
               } else { //code.charAt(0) == '1'
                  //create path if next spot in the iteration doesnt exist
                  if(current.getRightChild() == null) {
                     current.setRightChild(new HuffmanInternalNode(null, null));
                  }
                  //iterate to next spot in the tree
                  current = (HuffmanInternalNode<Character>) current.getRightChild();
               }
            }
            //shorten code by 1
            code = code.substring(1);
         }
         //return to the root before grabbing the next code
         current = root;              
      }
        	
      return new HuffmanTree<Character>(root);
   }

   //huffmanEncodingMapFromTree: converts a given tree into a map containing the same data
   //Parameters:
   //    - tree: data tree to be converted into a map
   //Returns: map containing the data from the given tree
   public static Map<Character, String> huffmanEncodingMapFromTree(HuffmanTree<Character> tree) {
      
      Map<Character, String> result = new HashMap<Character, String>();
            
      treeTraverser(result, tree.getRoot(), "");
               
      return result;
   }
   
   //recursive helper function for huffmanEncodingMapFromTree that traverses the given and converts
   //the data enclosed into a map 
   public static void treeTraverser(Map<Character, String> map, HuffmanNode<Character> node, String path) {
      //if its reached a leaf node, we want to extract the data enclosed in the node and its info
      //into the map
      if(node.isLeaf()) {
         HuffmanLeafNode<Character> leafNode = (HuffmanLeafNode<Character>) node;
         map.put(leafNode.getValue(), path);
         return;
      } else { //is internalNode
         //if it hasnt reached a leaf node we want to continue to recurse through the tree
         HuffmanInternalNode<Character> internalNode = (HuffmanInternalNode<Character>) node;
         
         treeTraverser(map, internalNode.getLeftChild(), path + "0");
         treeTraverser(map, internalNode.getRightChild(), path + "1");
      }         
   }

   //writeEncodingMapToFile: converts a map into a readable text file
   //Parameters: 
   //    - huffmanMap: map containing the data to be written to the file
   //    - file_name: name of the file to write map to
   public static void writeEncodingMapToFile(Map<Character, String> huffmanMap, String file_name) throws IOException {
   
      try {
         File file = new File(file_name);
         file.createNewFile();
         FileWriter fw = new FileWriter(file);
         BufferedWriter bw = new BufferedWriter(fw);
         
         Boolean firstLine = true;
         
         //iterates through a set of keys and writes the key with its corresponding value to
         //the file
         for(char c : huffmanMap.keySet()) {
            //to avoid having an empty line at the end of the file
            if(!firstLine) {
               //used lineSeparator to avoid cross platform compatability problems 
               bw.write(System.lineSeparator());
            }
            //writes: character||path to file
            bw.write(c + "||" + huffmanMap.get(c));
            firstLine = false; 
         }
         
         bw.flush();
         bw.close();
      } catch(IOException e) {
         System.out.println(file_name + " does not exist.");
      }
   }

	//readEncodingMapFromFile: creates a map containing the data from a compressed (.pa2c) file
   //Parameters:
   //    - file_name: the name of the file to be decompressed
   //Returns: map containing the data given by the inputted file
   public static Map<Character, String> readEncodingMapFromFile(String file_name) throws FileNotFoundException, StringIndexOutOfBoundsException {
   	
      Map<Character, String> result = new HashMap<>();
      String line, code;
      char character;
      String[] lineArray;
      
      try {
         Scanner sc = new Scanner(new File(file_name));   
         
         while(sc.hasNextLine()) {
            line = sc.nextLine();
            
            //every line of the file to decompress is of the form:
            //character||code     
            //        a||01010101 (not the actual code)
            if(line.contains("||")) {  
               lineArray = line.split("\\|\\|");
               
               character = lineArray[0].charAt(0);              
               code = lineArray[1].trim();
               
               //add character and corresponding code to the map
               result.put(character, code);
            }
         }
      //if the file to read from doesnt exist, this exception will be thrown           
      } catch(FileNotFoundException e) {
         System.out.println("The file you entered does not exist");
      }
      
      return result;
   }

	//decodeBits: converts the list of bools back into readable text
   //Parameters:
   //    - bits: each boolean represents directions to iterate through a huffmanTree
   //    - huffmanMap: contains the information to construct the huffmanTree that corresponds with
   //                  the list of bits 
   //Returns: String that contains the information encoded in the list of bools and the map
   public static String decodeBits(List<Boolean> bits, Map<Character, String> huffmanMap) {
   	//Uses the supplied Huffman Map to convert the list of bools (bits) back into text.
   	//To solve this problem, I converted the Huffman Map into a Huffman Tree and used 
   	//tree traversals to convert the bits back into text.
   	
   	//Use a StringBuilder to append results. 
      StringBuilder result = new StringBuilder();    
      
      HuffmanLeafNode<Character> currentLeaf;
      HuffmanInternalNode<Character> currentInternal;
      
      HuffmanTree<Character> dataTree = huffmanTreeFromMap(huffmanMap);
      HuffmanNode<Character> current = dataTree.getRoot();
      
      boolean goRight;
      char c;
      
      for(int i = 0; i < bits.size(); i++) {
         if(current.isLeaf()) {
            currentLeaf = (HuffmanLeafNode<Character>) current;
            c = currentLeaf.getValue();
            result.append(c);
            current = (HuffmanNode<Character>) dataTree.getRoot();
            i--;
         } else {
            //since TRUE indicates going right, goRight is true if the tree traversal goes to the right
            goRight = bits.get(i);
            
            //if the current node is not a leaf, it can be asusmed to be an internal node
            currentInternal = (HuffmanInternalNode<Character>) current;
            
            if(goRight) {               
               current = currentInternal.getRightChild();
            } else { //goLeft
               current = currentInternal.getLeftChild();   
            }
         }                 
      }
      
      currentLeaf = (HuffmanLeafNode<Character>) current;
      c = currentLeaf.getValue();
      result.append(c);
      
      return result.toString();
   }

   //toBinary: converts a list of strings into a list of boolean values
   //Parameters:
   //    - text: representation of a .txt file where each line is a spot on the list
   //    - huffmanMap: contains the corresponding huffman Codes for each ccharacter contained in
   //                  the .txt file
   //Returns: List<Boolean> where each boolean represents directions to iterate through a
   //         huffmanTree
   public static List<Boolean> toBinary(List<String> text, Map<Character, String> huffmanMap) {
      List<Boolean> result = new ArrayList<>();      
      String value;
      
      //iterates through each string in the list
      for(String line : text) {
         for(char c : line.toCharArray()) {
            //value holds the path that corresponds to the letter
            //ex:   c||value
            //      A||000110101
            value = huffmanMap.get(c);
            for(char code : value.toCharArray()) {
               //System.out.print(code + " ");
               if(code == '0') {
                  result.add(false);
               } else {
                  result.add(true);
               }   
            } 
         }
      }
            
      return result;
   }
}