package f1.force;

import f1.c1.Caesar;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;



public class CaesarForce {

    private static String key = "Filch took them down to Professor McGonagall's study on the first floor,\n" +
            "where they sat and waited without saying a word to each other. Hermione\n" +
            "was trembling. Excuses, alibis, and wild cover- up stories chased each\n" +
            "other around Harry's brain, each more feeble than the last. He couldn't\n" +
            "see how they were going to get out of trouble this time. They were\n" +
            "cornered. How could they have been so stupid as to forget the cloak?\n" +
            "There was no reason on earth that Professor McGonagall would accept for\n" +
            "their being out of bed and creeping around the school in the dead of\n" +
            "night, let alone being up the tallest astronomy tower, which was\n" +
            "out-of-bounds except for classes. Add Norbert and the invisibility\n" +
            "cloak, and they might as well be packing their bags already.";




    public static void main(String[] args) {
        Random random = new Random();
        var preprocessedKey = Caesar.preprocess(key);

        String encryptedText = Caesar.crypt(preprocessedKey, random.nextInt(26));

        System.out.println("encrypted"+encryptedText);

        magic(encryptedText);
    }




    public static void magic(String encryptedText) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            list.add(Caesar.crypt(encryptedText, i));
        }

        List<String> listSortedOfFriq = new ArrayList<>();
        for (String s : list) {
            Map<Character, Integer> map = mapFriq(s);
            listSortedOfFriq.add(fromMapFriq(map));
        }

        String text = getTrainingText();

        Map<Character, Integer> map = mapFriq(text);
        //map.forEach((k, v) -> System.out.println(k + " " + v));
        String trainData = fromMapFriq(map);

        PriorityQueue<String[]> queue = new PriorityQueue<>((a, b) -> Integer.compare(distance(a[0], trainData), distance(b[0], trainData)));
        for (int i = 0; i < listSortedOfFriq.size(); i++) {
            queue.add(new String[]{listSortedOfFriq.get(i), list.get(i)});
        }

        System.out.println("trainDataOrder: "+ trainData);
        int index =0;
        while (!queue.isEmpty()) {
            String[] s = queue.poll();
            System.out.println("N1 " + distance(s[0], trainData));
            System.out.println(s[1] + " ");
            System.out.println(s[0]);
            System.out.println("____________");
        }
    }

    public static int distance(String a, String b) {
        int count = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) count++;
        }
        return count;
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
        for (int i=0;i< 26;i++) {
            map.put((char)('a'+i),0);
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