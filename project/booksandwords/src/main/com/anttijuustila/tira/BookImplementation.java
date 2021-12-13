package com.anttijuustila.tira;

import java.io.*;
import java.nio.charset.Charset;
import java.lang.String;

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

        Charset utf8 = Charset.forName("UTF-8");
        
        setIgnoredWords();

        try (BufferedReader file = new BufferedReader(new InputStreamReader(
            new FileInputStream(this.fileName), utf8))){
          
            int[] word = new int [100];      
            int specificCharacter; 
            int numberOfLetters = 0;
            String specificWord = "";
                             
            while ((specificCharacter = file.read()) != -1){
                if (Character.isLetter(specificCharacter)){
                    word[numberOfLetters] = specificCharacter; 
                    numberOfLetters++;
                } else {
                    specificWord = new String(word, 0, numberOfLetters).toLowerCase();
                    numberOfLetters = 0;
                    if (specificWord.length() <= 1){
                        continue;
                    }
                    if (!wordIsAnIgnoredWord(specificWord)){
                        addWordToTable(specificWord);          
                    } else {
                        totalNumberOfIgnoredWords++;
                    }   
                }    
            } 

        file.close();

        } catch (IOException e) {
            System.out.println("Virhe");
            e.printStackTrace();
            throw new IOException();
        }  catch (OutOfMemoryError e) {
            System.out.println("Virhe");
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

        String rivi;

        Charset utf8 = Charset.forName("UTF-8");
        try (BufferedReader file = new BufferedReader(new InputStreamReader(
                new FileInputStream(this.ignoredWordsFile), utf8))){   

            int numberOfWords = 0; 

            while (true){
                int wordsOnLine = 0;
                
                rivi = file.readLine();
                if (rivi == null) {
                    break;
                }
                
                String[] iwords = rivi.split(",");
                
                for (int i = 0; i < iwords.length; i++){
                    ignoredWords[numberOfWords+i] = iwords[i].toLowerCase();
                    wordsOnLine++;
                    numberOfUniqueIgnoredWords++;
                }
                
                numberOfWords = numberOfWords + wordsOnLine;
            } 
        
        file.close();

        } catch (IOException e) {
            System.out.println("Virhe");
            e.printStackTrace();
            throw new IOException();
        }  catch (OutOfMemoryError e) {
            System.out.println("Virhe");
            e.printStackTrace();
            throw new OutOfMemoryError();
        }       
    }

    @Override
    public void report() {
   
        if (sorted.length < 100){
            for (int i = 0; i < sorted.length; i++){
                System.out.println((i+1) + " " + sorted[i].word);
            }
        } else {
            for (int i = 0; i < 100; i++){
                System.out.println((i+1) + " " + sorted[i].word);
            }
        }
        System.out.println("The total number of words is " + totalNumberOfWords);
        System.out.println("The number of unique words is " + numberOfUniqueWords);
        System.out.println("The total number of ignored words is " + totalNumberOfIgnoredWords);
        System.out.println("The number of unique ignored words is " +  numberOfUniqueIgnoredWords);
        System.out.println("The number of collisions: " + collisionCount);
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

    private static int hashCode(String sana) {
        int hash = 7919;
        for (int i = 0; i < sana.length(); i++){
            hash = (7919 * hash + sana.charAt(i));
        }
        return hash;
    }

    public boolean wordIsAnIgnoredWord(String sana) {
        int indeksi = 0;
        while (indeksi < ignoredWords.length){
            if(ignoredWords[indeksi] == null) {
                break;
            }
            if (ignoredWords[indeksi].equals(sana)){
                return true;
            } else {
                indeksi++;
            }           
        }
        return false;
    }

    private void heapsort(WordCount[] words, int length){

        heapify(words, length);
        int end = length - 1;
        while (end > 0) {
           swap(words, end, 0);
           end -=1;
           siftDown(words, 0, end); 
        }
    }
  
    private void heapify(WordCount[] words, int count){

        int start = parent(count-1);
        while (start >= 0){
           siftDown(words, start, count - 1);
           start -=1; 
        }
    }
  
    private void siftDown(WordCount[] words, int start, int end) {

        int root = start;
        while (leftChild(root)<= end) { 
           int child = leftChild(root);
           int swap = root;
           if (words[swap].count > words[child].count) {
              swap = child;
           }
           if (child + 1 <= end && words[swap].count > words[child + 1].count) 
              swap = child + 1;
           if (swap == root){  
              return;   
           } else {
              swap(words, root, swap);
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
  
    private void swap(WordCount[] words, int a, int b) {
        WordCount temp = words[a];
        words[a] = words[b];
        words[b] = temp;
    }
     
    private void createSortedTable(){

        int counter = 0;

        for (int i = 0; i < words.length; i++){
            if (words[i] != null){
                counter++;
            }
        }

        sorted = new WordCount[counter];

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
