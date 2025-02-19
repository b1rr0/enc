package f6;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class RC4 {
    private byte[] S = new byte[256];
    private int x = 0;
    private int y = 0;


    public RC4(byte[] key) {
        keySchedule(key);
    }

    private void keySchedule(byte[] key) {
        int keyLength = key.length;
        for (int i = 0; i < 256; i++) {
            S[i] = (byte) i;
        }


        int j = 0;
        for (int i = 0; i < 256; i++) {
            j = (j + S[i] + key[i % keyLength]) & 0xFF;
            swap(i, j);
        }
    }
   // 11111
    // & 0xFF == 255 ->  % 256

    private void swap(int i, int j) {
        byte temp = S[i];
        S[i] = S[j];
        S[j] = temp;
    }

    public byte[] encryptDecrypt(byte[] data) {
        byte[] output = new byte[data.length];
        for (int i = 0; i < data.length; i++) {
            output[i] = (byte) (data[i] ^ keyStream());
        }
        return output;
    }

    private byte keyStream() {
        x = (x + 1) & 0xFF;
        y = (y + S[x]) & 0xFF;
        swap(x, y);
        return S[(S[x] + S[y]) & 0xFF];
    }

    public static void main(String[] args) {
        String key = "keyForAllDay";
        String plaintext = "Hello, RC4!";

        RC4 rc4 = new RC4(key.getBytes(StandardCharsets.UTF_8));
        byte[] encrypted = rc4.encryptDecrypt(plaintext.getBytes(StandardCharsets.UTF_8));
        System.out.println("Encrypted: " + new String(encrypted));

        RC4 rc4Decrypt = new RC4(key.getBytes(StandardCharsets.UTF_8));
        byte[] decrypted = rc4Decrypt.encryptDecrypt(encrypted);
        System.out.println("Decrypted: " + new String(decrypted));



        System.out.println("255! = ");
        BigInteger bigInteger = BigInteger.ONE;
        BigInteger b = BigInteger.valueOf(255);
        for (int i = 0; i < 255; i++) {
            bigInteger = bigInteger.multiply(b);
            b = b.subtract(BigInteger.ONE);
        }
        System.out.println(bigInteger);
        System.out.println("10^ "+ bigInteger.toString().length());
    }
}
