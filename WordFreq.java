import java.io.*;
import java.util.*;

public class WordFreq{

    
    public static Map<String, Integer> countWordFrequency(String filename) {
        Map<String, Integer> wordCountMap = new HashMap<>();

        try {
            FileReader fr = new FileReader(filename);
            BufferedReader buff = new BufferedReader(fr);
            String line;

            
            while ((line = buff.readLine()) != null) {
               
                line = line.toLowerCase().replaceAll("[^a-z\\s]", " ").replaceAll("\\s+", " ");
                String[] words = line.split(" ");

                
                for (String word : words) {
                    if (!word.isBlank()) {
                        wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
                    }
                }
            }
            buff.close(); 
        } catch (FileNotFoundException e) {
            String m = e.getMessage();
            System.err.println("File " + filename + ": " + m);
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return wordCountMap;
    }

   
    public static List<Map.Entry<String, Integer>> getSortedWordList(Map<String, Integer> wordCountMap) {
        List<Map.Entry<String, Integer>> sortedWords = new ArrayList<>(wordCountMap.entrySet());

        
        sortedWords.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));
        return sortedWords;
    }

    // Main function
    public static void main(String[] args) {
        if (args.length < 1 || args.length > 2) {
            System.err.println("Usage: java WordFreq <filename> [k]");
            return;
        }

        String filename = args[0];
        int k = 10; 

        if (args.length == 2) {
            try {
                k = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.err.println("Invalid number for k: " + args[1]);
                return;
            }
        }

       
        Map<String, Integer> wordCountMap = countWordFrequency(filename);
        if (wordCountMap == null) {
            return; 
        }

        
        List<Map.Entry<String, Integer>> sortedWords = getSortedWordList(wordCountMap);

     
        System.out.println("Top " + k + " most frequent words:");
        for (int i = 0; i < Math.min(k, sortedWords.size()); i++) {
            Map.Entry<String, Integer> entry = sortedWords.get(i);
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
