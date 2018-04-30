package Dictionaries;

import java.util.*;

/**
 * Класс WordsInAFile
 * Описывает работу со словарем: словами, имеющимися во входном файле
 * Осуществляет добавление слов, обработку некорректно заданных слов и вывод словаря в консоль
 *
 * @author Natalia Litvinova
 */

public class WordsInAFile {
    private TreeMap<String, Integer> words;

    /**
     * Конструктор без параметров
     * Создает новый пустой словарь
     */
    public WordsInAFile() {
        this.words = new TreeMap<>();
    }

    /**
     * Конструктор с заданными параметрами
     *
     * @param words Заданный словарь
     */
    public WordsInAFile(TreeMap<String, Integer> words) {
        this.words = words;
    }

    /**
     * Геттеры и сеттеры
     */

    public TreeMap<String, Integer> getWords() {
        return words;
    }

    public void setWords(TreeMap<String, Integer> words) {
        this.words = words;
    }

    /**
     * Метод добавления слова в словарь
     *
     * @param word Слово
     */
    public void addAWord(String word) {
        // Символ "-" не обрабатывается при корректировании, так как может быть дефисом в сложном слове
        if (!word.equals("-")) {
            TreeMap<String, Integer> words = this.getWords();

            // Удаление спецсимволов из считанного слова
            word = this.correctAWord(word);

            // Получение текущего количества использования слова в файле
            Integer count = words.get(word);

            // Задание начального значения, если данное слово не было встречено в файле ранее
            if (count == null) {
                count = 0;
            }

            // Добавление слова в словарь
            words.put(word, ++count);

            this.setWords(words);
        }
    }

    /**
     * Метод корректирования полученного слова
     * Удаляет из слова спецсимволы
     *
     * @param word Слово
     * @return Откорректированное слово
     */
    private String correctAWord(String word) {
        // Понижает регистр слова
        word = word.toLowerCase();

        // Удаляет скобки из слова
        word = word.replace("(", "");
        word = word.replace(")", "");

        // Удаляет разделители частей предложения
        word = word.replace(",", "");
        word = word.replace(";", "");

        // Удаляет символы конца предложения из слова
        word = word.replace(".", "");
        word = word.replace("?", "");
        word = word.replace("!", "");

        // Удаляет кавычки из слова
        word = word.replace("\"", "");

        return word;
    }

    /**
     * Метод вывода статистики слов в файле: какое слово сколько раз встречалось
     * При выводе разделяет все слова на группы по первой букве
     */
    public void printStatistics() {
        TreeMap<String, Integer> words = this.getWords();

        // Задание начальных значений переменных
        int isLetterUsed = 0; // Было ли встречено слово, начинающееся с текущей буквы
        char letter = '1'; // Текущая первая буква слова

        for (Map.Entry<String, Integer> entry : words.entrySet()) {
            String key = entry.getKey();

            // Проверка того, была ли выведена первая буква текущего слова
            if (isLetterUsed == 0) {
                letter = key.charAt(0);
                System.out.println(key.toUpperCase().charAt(0) + "\n");
                isLetterUsed = 1;
            } else if (letter != key.charAt(0)) {
                letter = key.charAt(0);
                System.out.println("\n\n" + key.toUpperCase().charAt(0) + "\n");
                isLetterUsed = 1;
            }

            System.out.println("\"" + entry.getKey() + "\" was used " +
                    entry.getValue() + " times");
        }
    }

    /**
     * Метод вывода списка всех слов в файле
     * При выводе разделяет все слова на группы по первой букве
     */
    public void printAllWords() {
        TreeMap<String, Integer> words = this.getWords();

        int isLetterUsed = 0;
        char letter = '1';

        for (String key : words.keySet()) {
            if (isLetterUsed == 0) {
                letter = key.charAt(0);
                System.out.println(key.toUpperCase().charAt(0) + "\n");
                isLetterUsed = 1;
            } else if (letter != key.charAt(0)) {
                letter = key.charAt(0);
                System.out.println("\n\n" + key.toUpperCase().charAt(0) + "\n");
                isLetterUsed = 1;
            }

            System.out.println(key);
        }
    }

    /**
     * Метод поиска максимального значения использования слова в файле
     *
     * @return Максимальное использование слова
     */
    public Integer findTheMostUsage() {
        TreeMap<String, Integer> words = this.getWords();

        int maxFound = 0;

        for (Integer found : words.values()) {
            if (found > maxFound)
                maxFound = found;
        }

        return maxFound;
    }

    /**
     * Метод поиска слов, встретившихся в файле заданное количество раз
     *
     * @param usage Значение количества использования слова
     * @return Список слов, использованных заданное количество раз
     */
    private ArrayList<String> findUsageOfWords(Integer usage) {
        TreeMap<String, Integer> words = this.getWords();
        ArrayList<String> foundWords = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : words.entrySet()) {
            if (entry.getValue().equals(usage))
                foundWords.add(entry.getKey());
        }

        return foundWords;
    }

    /**
     * Вывод списка слов, встретившихся в файле заданное количество раз
     *
     * @param usage Значение количества использования слова
     */
    public void printUsageOfWords(Integer usage) {
        ArrayList<String> foundWords = this.findUsageOfWords(usage);

        System.out.println("Words that were used " + usage + " times:\n");

        if (foundWords.isEmpty()) {
            System.out.println("Ooops! No words found!");
        } else
            for (String word : foundWords) {
                System.out.println(word);
            }
    }
}
