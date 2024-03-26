package com.javarush.task.task18.task1816;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/* 
Английские буквы
*/
/*
public class Solution {
    public static void main(String[] args) throws IOException
    {
        String path = args[0];
        List<Character> English = new ArrayList<>();
        //Заполняем массив:
        for(char i = 'A'; i < 'z'; i++)English.add(i);
        English.remove(26);
        English.remove(26);
        English.remove(26);
        English.remove(26);
        English.remove(26);
        English.remove(26);
        int count_Eng = 0;
        try(FileReader reader = new FileReader(args[0]))
        {
            while (reader.ready())
            {
                char readChar = (char) reader.read();
                if (English.contains(readChar)) count_Eng++;
            }
        }
        System.out.println(count_Eng);
    }
}
*/
public class Solution {

    private final static Character[] ENGLISH_CHARACTERS_ARRAY = new Character[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L'
            , 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm'
            , 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private final static Set<Character> ENGLISH_CHARACTERS = new HashSet<>(Arrays.asList(ENGLISH_CHARACTERS_ARRAY));

    public static void main(String[] args) throws IOException {
        int countOfEnglishChars = 0;
        try (FileReader fileReader = new FileReader(args[0])) {
            while (fileReader.ready()) {
                char readChar = (char) fileReader.read();
                if (ENGLISH_CHARACTERS.contains(readChar)) countOfEnglishChars++;
            }
        }
        System.out.println(countOfEnglishChars);
    }
}

