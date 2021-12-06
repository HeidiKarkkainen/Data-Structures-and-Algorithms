package com.anttijuustila.tira;

import java.io.*;
import java.nio.charset.Charset;
import java.io.File;

public class BookImplementation implements Book {

    private String ignoredWords;
    private String bookFile;


    public void setSource(String fileName, String ignoreWordsFile) throws FileNotFoundException{

        File file = new File(fileName);
        
        if (file.isFile()){
            System.out.println(file + "exists");
        } else {
            throw new FileNotFoundException();
        }

        file = new File(ignoreWordsFile);
        
        if (file.isFile()){
            System.out.println(file + "exists");
        } else {
            throw new FileNotFoundException();
        }

        bookFile = fileName;
        ignoredWords = ignoreWordsFile;

    }

    public void dummy() {
        String d = this.bookFile;
    }
  

}
