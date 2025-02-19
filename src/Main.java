public class Main {
    public static void main(String[] args) {
        int a = 60;    // 60 = 0011 1100
        int b = 13;    // 13 = 0000 1101
        System.out.println("a = " + a + " = " + String.format("%8s", Integer.toBinaryString(a)).replace(' ', '0'));
        System.out.println("b = " + b + " = " + String.format("%8s", Integer.toBinaryString(b)).replace(' ', '0'));
        // Побитовое И (AND)
        int c = a & b; // 12 = 0000 1100
        System.out.println("a & b = " + c + " = " + String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0'));
        
        // Побитовое ИЛИ (OR) 
        c = a | b;     // 61 = 0011 1101
        System.out.println("a | b = " + c + " = " + String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0'));
        
        // Побитовое исключающее ИЛИ (XOR)
        c = a ^ b;     // 49 = 0011 0001
        System.out.println("a ^ b = " + c + " = " + String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0'));
        
        // Побитовое НЕ (NOT)
        c = ~a;        // -61 = 1100 0011
        System.out.println("~a = " + c + " = " + String.format("%8s", Integer.toBinaryString(c & 0xFF)).replace(' ', '0'));
        
        // Сдвиг влево
        c = a << 2;    // 240 = 1111 0000
        System.out.println("a << 2 = " + c + " = " + String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0'));
        
        // Сдвиг вправо
        c = a >> 2;    // 15 = 0000 1111
        System.out.println("a >> 2 = " + c + " = " + String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0'));
        
        // Беззнаковый сдвиг вправо
        c = a >>> 2;   // 15 = 0000 1111
        System.out.println("a >>> 2 = " + c + " = " + String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0'));
    }
    //a = 60 =      00111100
    //b = 13 =      00001101

    //a & b = 12 =  00001100
    //a | b = 61 =  00111101
    //a ^ b = 49 =  00110001
    //~a = -61 =    11000011
    //a << 2 =240 = 11110000
    //a >> 2 = 15 = 00001111
    //a >>> 2 = 15= 00001111
}