package f3;

class Rotor {
    private String wiring;
    private int position;
    private final String notch;
    private final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public Rotor(String wiring, String notch) {
        this.wiring = wiring;
        this.notch = notch;
        this.position = 0;
    }

    public void setPosition(int pos) {
        this.position = pos % 26;
    }

    public void rotate() {
        position = (position + 1) % 26;
    }

    public char forward(char c) {
        int index = (alphabet.indexOf(c) + position) % 26;
        return wiring.charAt(index);
    }

    public char backward(char c) {
        int index = wiring.indexOf(c);
        index = (index - position + 26) % 26;
        return alphabet.charAt(index);
    }

    public boolean atNotch() {
        return notch.indexOf(alphabet.charAt(position)) != -1;
    }
}

class Reflector {
    private String wiring;
    private final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public Reflector(String wiring) {
        this.wiring = wiring;
    }

    public char reflect(char c) {
        return wiring.charAt(alphabet.indexOf(c));
    }
}

class EnigmaMachine {
    private Rotor[] rotors;
    private Reflector reflector;

    public EnigmaMachine(Rotor r1, Rotor r2, Rotor r3, Reflector reflector) {
        this.rotors = new Rotor[]{r1, r2, r3};
        this.reflector = reflector;
    }

    private void stepRotors() {
        if (rotors[2].atNotch()) {
            rotors[1].rotate();
            if (rotors[1].atNotch()) {
                rotors[0].rotate();
            }
        }
        rotors[2].rotate();
    }

    public String encrypt(String text) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toUpperCase().toCharArray()) {
            if (!Character.isLetter(c)) continue;

            stepRotors();

            c = rotors[2].forward(c);
            c = rotors[1].forward(c);
            c = rotors[0].forward(c);

            c = reflector.reflect(c);

            c = rotors[0].backward(c);
            c = rotors[1].backward(c);
            c = rotors[2].backward(c);

            result.append(c);
        }
        return result.toString();
    }
}

public class Enigma {
    public static void main(String[] args) {
        Rotor r1 = new Rotor("EKMFLGDQVZNTOWYHXUSPAIBRCJ", "Q"); // 26
        Rotor r2 = new Rotor("AJDKSIRUXBLHWTMCQGZNPYFVOE", "E"); // 26
        Rotor r3 = new Rotor("BDFHJLCPRTXVZNYEIWGAKMUSQO", "V"); // 26
        Reflector ref = new Reflector("YRUHQSLDPXNGOKMIEBFZCWVJAT");

        EnigmaMachine enigma = new EnigmaMachine(r1, r2, r3, ref);

        String message = "HELLOWORLD";
        String encrypted = enigma.encrypt(message);
        String decrypted = enigma.encrypt(encrypted);

        System.out.println("Исходный текст: " + message);
        System.out.println("Зашифрованный текст: " + encrypted);
        System.out.println("Дешифрованный текст: " + decrypted);

        // 17.02.2025 не працює, чат ГПТ не впорався, поки нас не замінять

        // Хто зможе парахувати період?
    }
}
