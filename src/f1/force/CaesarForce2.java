package f1.force;

import f1.c1.Caesar;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class CaesarForce2 {

    private static String key = "In addition to the header, txt_book ebooks contain more metadata at the end of the file. The decision to put these at the end of the file was made because some of these lines are optional and thus would make it necessary to migrate bookmark positions after adding or removing metadata. The footer metadata block is starting with a mandatory line that indicates the txt_book version used in the file after two empty lines following the end of the books contents. This";

    public static void main(String[] args) {
        Random random = new Random();
        var processedKey = Caesar.preprocess(key);

        String encryptedText = Caesar.crypt(processedKey, random.nextInt(26));

        System.out.println("encrypted: \n" + encryptedText + "\n");

        magic(encryptedText);
    }

    public static void magic(String encryptedText) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            list.add(Caesar.crypt(encryptedText, i));
        }

        List<Map<Character, Integer>> listMap = new ArrayList<>();
        for (String s : list) {
            Map<Character, Integer> map = mapFriq(s);
            listMap.add(map);
        }

        String text = getTrainingText();

        Map<Character, Integer> map = mapFriq(text);
        //map.forEach((k, v) -> System.out.println(k + " " + v));
        String trainData = fromMapFriq(map);

        PriorityQueue<Map.Entry<Map<Character, Integer>, String>> queue = new PriorityQueue<>(Comparator.comparingInt(a -> distance(a.getKey(), map)));
        for (int i = 0; i < listMap.size(); i++) {
            queue.add(Map.entry(listMap.get(i), list.get(i)));
        }

        System.out.println("trainDataOrder: " + trainData);
        while (!queue.isEmpty()) {
            var s = queue.poll();
            System.out.println("N1 " + distance(s.getKey(), map));
            System.out.println(s.getValue() + " ");
            //  System.out.println(s.getKey());
            System.out.println("____________");
        }
    }

    public static int distance(Map<Character, Integer> a, Map<Character, Integer> b) {
        int s1 = a.values().stream().mapToInt(Integer::intValue).sum();
        int s2 = b.values().stream().mapToInt(Integer::intValue).sum();

        int res = 0;
        for (Character c : a.keySet()) {
            double a1 = (double) a.get(c) / s1;
            double b1 = (double) b.get(c) / s2;

            System.out.println(c + " " + a1 + " " + b1);
            if (Math.abs(a1 - b1) < 0.01) res++;

        }
        return res;
    }

    public static String fromMapFriq(Map<Character, Integer> map) {
        PriorityQueue<Map.Entry<Character, Integer>> queue = new PriorityQueue<>((a, b) -> b.getValue().compareTo(a.getValue()));
        queue.addAll(map.entrySet());
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            Map.Entry<Character, Integer> entry = queue.poll();
            sb.append(entry.getKey());
        }
        return sb.toString();
    }

    public static Map<Character, Integer> mapFriq(String text) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            map.put((char) ('a' + i), 0);
        }
        for (char c : text.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        return map;
    }

    public static String getTrainingText() {
        String text = readFile("data.txt");
        String processed = Caesar.preprocess(text);
        return processed;
    }

    public static String readFile(String path) {
        String text = "";
        try {
            text = new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    // javac src/1/force/CaesarianaeForce.java && java src.1.force.CaesarianaeForce
}