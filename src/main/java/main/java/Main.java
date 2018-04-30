package main.java;

import Dictionaries.WordsInAFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Класс Main
 * Считывание слов из файла и выбор желаемой операции над словами
 *
 * @see Dictionaries.WordsInAFile
 *
 * @author Natalia Litvinova
 */

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        // Открытие файла
        Scanner myValue = new Scanner(new File("D:\\example.txt"));

        WordsInAFile myWords = new WordsInAFile();

        // Считывание слов из файла
        while (myValue.hasNext()) {
            String word = myValue.useDelimiter("\\s+").next();

            // Добавление слов в объект-словарь
            myWords.addAWord(word);
        }

        myValue.close();

        System.out.println("All words were read from the file.");

        myValue = new Scanner(System.in);

        while (true) {
            // Вывод меню выбора задачи
            System.out.println("\nWhat do you want to do?");
            System.out.println("1. Check all words that were found");
            System.out.println("2. Check statistics");
            System.out.println("3. Find the most used words");
            System.out.println("4. Find the words that were used N times");
            System.out.println("5. Exit");

            int choice = myValue.nextInt();

            switch (choice) {
                case 1:
                    // Вывод списка всех слов в файле
                    System.out.println("\nThe list of all words in a file:\n");
                    myWords.printAllWords();
                    break;
                case 2:
                    // Вывод статистики о словах в файле: слово и сколько раз оно встречается
                    System.out.println("\nStatistics of the words in a file:\n");
                    myWords.printStatistics();
                    break;
                case 3:
                    // Поиск слов, которые были использованы максимальное количество раз
                    int maxUsage = myWords.findTheMostUsage();
                    System.out.println("\nThe most used words were used " + maxUsage + " times.\n");
                    myWords.printUsageOfWords(maxUsage);
                    break;
                case 4:
                    // Вывод слов, которые встречаются количество раз, заданное пользователем
                    System.out.println("\nEnter N (number of usage of word):");
                    int usage = myValue.nextInt();
                    System.out.println();
                    myWords.printUsageOfWords(usage);
                    break;
                case 5:
                    // Выход из программы
                    System.out.println("\nGoodbye");
                    return;
                default:
                    // Вывод ошибки некорректной команды
                    System.out.println("\nWrong command");
            }
        }
    }
}
