package com.javarush.task.task19.task1923;

import java.io.*;

/* 
Слова с цифрами
*/

public class Solution {
    public static void main(String[] args) throws IOException
    {
        String file1 = args[0];
        String file2 = args[1];
        try (BufferedReader reader = new BufferedReader(new FileReader(file1));
             FileWriter writer = new FileWriter(file2))
        {
            String s;
            //boolean haveCount = false;
            while ((s = reader.readLine()) != null)
            {
                String[] splitedS = s.split(" ");
                for (String word: splitedS)
                {
                    if (word.matches(".*[0-9].*"))
                        writer.write(word + " ");
                }
//                char[] word = s.toCharArray();
//                for (int i = 0; i < word.length; i++)
//                {
//                    if (word[i] < 58 && word[i] > 47)
//                    {
//                        writer.write(s);
//                        writer.write(" ");
//                        break;
//                    }
//                }
            }

        }
    }
}
