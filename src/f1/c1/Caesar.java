package f1.c1;

public class Caesar {

    public static String preprocess(String args) {
        return args.toLowerCase().replaceAll("[^a-z]", "");
    }


    public static String crypt(String args, int key) {
        key = key % 26;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length(); i++) {
            char c = args.charAt(i);
            char newChar = (char) (((int) c + key) % ('z' + 1));
            newChar = newChar < 'a' ? (char) (newChar + 'a') : newChar;
            sb.append(newChar);
        }
        return sb.toString();
    }

    public static String decrypt(String args, int key) {
        key = key % 26;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length(); i++) {
            char c = args.charAt(i);
            char newChar = (char) (((int) c - key));
            newChar = newChar < 'a' ? (char) (newChar + 26) : newChar;
            sb.append(newChar);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        showOrder();
        System.out.println((int) '`');
        String text = "zz bbbbb ccc ddd eee fff ggg hhh iii jjj kkk lll mmm nnn ooo ppp qqq rrr sss ttt uuu vvv wwww xxxx yyyy zzzz";
        text = preprocess(text);
        String encrypted = crypt(text, 4);
        String decrypted = decrypt(encrypted, 4);
        System.out.println(text);
        System.out.println(encrypted);
        System.out.println(decrypted);
    }

    public static void showOrder() {
        for (int i = 0; i < 26; i++) {
            System.out.println((char) ('a' + i));
        }
    }
}