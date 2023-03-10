package com.anttijuustila.tira;

import java.io.*;
import java.nio.charset.Charset;
import java.lang.String;
import java.nio.charset.StandardCharsets;
import java.util.Locale;


public class BookImplementation implements Book {

    private class WordCount {

        String word;
        int count; 

        WordCount() {
            word = "";
            count = 0;
        }

        WordCount(String word) {
            this.word = word;
            count = 1;
        }
    }

    private String ignoredWordsFile;
    private String fileName;
    private String[] ignoredWords = new String[100];
    private WordCount[] words = new WordCount[4500000];
    private int collisionCount = 0; 
    private int totalNumberOfWords = 0;
    private int numberOfUniqueWords = 0;
    private int totalNumberOfIgnoredWords = 0;
    private int numberOfUniqueIgnoredWords = 0;
    private WordCount[] sorted;
  

    @Override
    public void setSource(String fileName, String ignoreWordsFile) throws FileNotFoundException{

        File file = new File(fileName);
        
        if (file.isFile()){
            System.out.println(file + " exists");
        } else {
            throw new FileNotFoundException();
        }

        file = new File(ignoreWordsFile);
        
        if (file.isFile()){
            System.out.println(file + " exists");
        } else {
            throw new FileNotFoundException();
        }

        this.fileName = fileName;
        this.ignoredWordsFile = ignoreWordsFile;

    }

    @Override
    public void countUniqueWords() throws IOException, OutOfMemoryError {
        
        setIgnoredWords();

        try (FileReader reader = new FileReader(fileName, StandardCharsets.UTF_8)){ 
            
            int[] word = new int [100];      
            int specificCharacter; 
            int numberOfLetters = 0;
            String specificWord = "";
                             
            while ((specificCharacter = reader.read()) != -1){
                if (Character.isLetter(specificCharacter)){
                    word[numberOfLetters] = specificCharacter; 
                    numberOfLetters++;
                } else {
                    specificWord = new String(word, 0, numberOfLetters).toLowerCase();
                    numberOfLetters = 0;
                    if (specificWord.length() == 0){
                        continue;
                    }
                    if (specificWord.length() == 1){
                        totalNumberOfIgnoredWords++;
                        continue;
                    }
                    if (!wordIsIgnoredWord(specificWord)){
                        addWordToTable(specificWord);          
                    } else {
                        totalNumberOfIgnoredWords++;
                    }   
                }    
            } 

        reader.close();

        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
            throw new IOException();
        }  catch (OutOfMemoryError e) {
            System.out.println("Error");
            e.printStackTrace();
            throw new OutOfMemoryError();
        }
     
        createSortedTable();
    }

    private void addWordToTable(String sana) {

        int i = 0;
        int hashValue = hashCode(sana);
        int index = ((hashValue + i) & 0x7fffffff) % words.length;

        while (true){                        
                               
            if (words[index] == null){
                words[index] = new WordCount(sana);
                numberOfUniqueWords++;
                totalNumberOfWords++;
                break;         
            } else if (words[index] != null){
                if (words[index].word.equals(sana)){
                    words[index].count++;
                    totalNumberOfWords++;
                    break;
                } else {
                    collisionCount++;
                    i++;
                    index = ((hashValue + i) & 0x7fffffff) % words.length;
                }
            }       
        }
    }

    private void setIgnoredWords() throws IOException, OutOfMemoryError {

        String line;

        Charset utf8 = Charset.forName("UTF-8");

        try (BufferedReader file = new BufferedReader(new InputStreamReader(
               new FileInputStream(this.ignoredWordsFile), utf8))){   
           
            int numberOfWords = 0; 

            while (true){
                int wordsOnLine = 0;
                line = file.readLine();

                if (line == null) {
                    break;
                }
                
                String[] iwords = line.split(",");
                
                for (int i = 0; i < iwords.length; i++){
                    ignoredWords[numberOfWords+i] = iwords[i].toLowerCase();
                    wordsOnLine++;
                    numberOfUniqueIgnoredWords++;
                }              
                numberOfWords = numberOfWords + wordsOnLine;
            } 
        
        file.close();

        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
            throw new IOException();
        }  catch (OutOfMemoryError e) {
            System.out.println("Error");
            e.printStackTrace();
            throw new OutOfMemoryError();
        }       
    }

    @Override
    public void report() {
   
        for (int i = 0; i < Math.min(sorted.length, 100); i++){
            System.out.println((i+1) + ". " + sorted[i].word + " " + sorted[i].count);
        }
      
        System.out.println("The total number of words is " + totalNumberOfWords);
        System.out.println("The number of unique words is " + numberOfUniqueWords);
        System.out.println("The total number of ignored words is " + totalNumberOfIgnoredWords);
        System.out.println("The number of unique ignored words is " +  numberOfUniqueIgnoredWords);
        System.out.println("The number of collisions is " + collisionCount);
    }

    @Override
    public void close() {

        sorted = null;
        
    }

    @Override
    public int getUniqueWordCount() {
        
        return numberOfUniqueWords;
    }

    @Override
    public int getTotalWordCount() {
        
        return totalNumberOfWords;
    }

    
    @Override
    public String getWordInListAt(int position) {
        
        if (position >= 100){
            return null;
        }
        if (position >= sorted.length){
            return null;
        }
        if (position < 0){
            return null;            
        }    
        if (sorted.length == 0){
            return null;
        }

        return sorted[position].word;
    }

    @Override
    public int getWordCountInListAt(int position) {
        
        if (position >= 100){
            return -1;
        }
        if (position >= sorted.length){
            return -1;
        }
        if (position < 0){
            return -1;            
        }    
        if (sorted.length == 0){
            return -1;
        }

        return sorted[position].count;
    }

    private static int hashCode(String word) {

        int hash = 7919;
        for (int i = 0; i < word.length(); i++){
            hash = (7919 * hash + word.codePointAt(i));
        }
        return hash;
    }

    public boolean wordIsIgnoredWord(String word) {

        int indeksi = 0;
        while (indeksi < ignoredWords.length){
            if(ignoredWords[indeksi] == null) {
                break;
            }
            if (ignoredWords[indeksi].equals(word)){
                return true;
            } else {
                indeksi++;
            }           
        }
        return false;
    }

    private void heapsort(WordCount[] A, int length){

        heapify(A, length);
        int end = length - 1;
        while (end > 0) {
           swap(A, end, 0);
           end -=1;
           siftDown(A, 0, end); 
        }
    }
  
    private void heapify(WordCount[] A, int count){

        int start = parent(count-1);
        while (start >= 0){
           siftDown(A, start, count - 1);
           start -=1; 
        }
    }
  
    private void siftDown(WordCount[] A, int start, int end) {

        int root = start;
        while (leftChild(root)<= end) { 
           int child = leftChild(root);
           int swap = root;
           if (A[swap].count > A[child].count) {
              swap = child;
           }
           if (child + 1 <= end && A[swap].count > A[child + 1].count) 
              swap = child + 1;
           if (swap == root){  
              return;   
           } else {
              swap(A, root, swap);
              root = swap;
           }
        }   
    }
  
    private int parent(int i){
        return (int)Math.floor((i-1)/2);
    }
  
    private int leftChild(int i){
        return 2 * i + 1;
    }
   
    private int rightChild(int i){
        return 2 * i + 2;
    }
  
    private void swap(WordCount[] A, int a, int b) {
        WordCount temp = A[a];
        A[a] = A[b];
        A[b] = temp;
    }
     
    private void createSortedTable(){

        sorted = new WordCount[numberOfUniqueWords];

        int j = 0;

        for (int i = 0; i < words.length; i++){
            if (words[i] != null){
                sorted[j] = words[i]; 
                j++;
            }
        }

        heapsort(sorted, sorted.length);
    }
}
