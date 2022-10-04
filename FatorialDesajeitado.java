import static java.lang.String.valueOf;

public class FatorialDesajeitado {
    public static int countOperation = 0;

    public static void main(String[] args) {
        int n = 10; // int n = Integer.parseInt(new Scanner(System.in).nextLine());

        if(n <= 0) System.out.println(0);
        else System.out.println("Result: " + operation(n));

    }

    public static int operation(int n) {
        String operationString = operationStringBuilder(n); // operationStringBuilder(int n) ->
        System.out.println(operationString);                //          devolve uma String com a equacao.
        operationString = calc(operationString, '*'); // calc(String stringDaEquacao, char sinalDasOperações)->
        System.out.println(operationString);                  //             devolve uma string com a equacao resolvida
        operationString = calc(operationString, '/'); //                       nas partes com o sinal passado.
        System.out.println(operationString);
        operationString = calc(operationString, '+');
        System.out.println(operationString);
        return Integer.parseInt(operationString);
    }

    public static char[] calcStrings(String a, String b, char operation) {
        int intA = Integer.parseInt(a);
        int intB = Integer.parseInt(b);
        int result = 0;
        if (operation == '/') result = intA / intB;
        if (operation == '*') result = intA * intB;
        if (operation == '+') result = intA + intB;
        if (operation == '-') result = intA - intB;
        return (Integer.toString(result)).toCharArray();
    }

    public static String calc(String operationString, char operation) {
        String calcString = operationString.replaceAll("\\s", "");

        for (int i = 1; i < calcString.length(); i++) {
            if (calcString.charAt(i) == operation || calcString.charAt(i) == '-' && operation == '+') {
                char[] calcCharArray = calcString.toCharArray();
                //set valor1
                String valor1 = "";
                int valor1Size = 0;
                for (int k = 1; k <= 10; k++) {
                    if (k == i || !Character.isDigit(calcCharArray[i - k])) {
                        if (k == i) valor1Size = k;
                        else valor1Size = k - 1;
                        System.out.println("valor1Size " + valor1Size);
                        for (int l = valor1Size; l > 0; l--) {
                            System.out.println("calcCharArray[i - l] " + calcCharArray[i - l]);
                            valor1 += calcCharArray[i - l];
                            System.out.println("valor 1: " + valor1);
                        }
                        break;
                    }
                }
                System.out.println("valor 1: " + valor1);
                //set valor2
                String valor2 = "";
                int valor2Size = 0;
                //valor2 = Integer.toString(Integer.parseInt(valor1) - 1);
                for (int k = 1; k <= 10; k++) {
                    if (k == calcCharArray.length - 1 - i || !Character.isDigit(calcCharArray[i + k])) {
                        if (k == calcCharArray.length - 1 - i) valor2Size = k;
                        else valor2Size = k - 1;
                        System.out.println("valor2Size " + valor2Size);
                        for (int l = 1; l <= valor2Size; l++) {
                            System.out.println("calcCharArray[i - l] " + calcCharArray[i - l]);
                            valor2 += calcCharArray[i + l];
                            System.out.println("valor 2: " + valor2);
                        }
                        break;
                    }
                }


                System.out.println("valor 1: " + valor1 + " valor 2: " + valor2);
                //calculate valor1 operation valor2
                char[] stringCalc = calcStrings(valor1, valor2, calcString.charAt(i));
                System.out.println("stringCalc: " + valueOf(stringCalc));
                //limpa array e bota o stringCalc a partir do primeiro espaço do array limpo
                int modArraySize = valor1.length() + valor2.length() + 1;
                for (int j = 0; j < modArraySize; j++) {
                    int firstCharOfOperation = (i - valor1.length()) + j;
                    if (stringCalc.length > j) {
                        calcCharArray[firstCharOfOperation] = stringCalc[j];
                    } else calcCharArray[firstCharOfOperation] = ' ';
                }

                System.out.println("calcCharArray: " + valueOf(calcCharArray));
                calcString = valueOf(calcCharArray);
                calcString = calcString.replaceAll("\\s", "");
                i = 0;
            }
        }

        return calcString;
    }

    public static String getOperationSignal() {
        String op = "";
        if (countOperation == 4) countOperation = 0;
        if (countOperation == 0) op = "*";
        if (countOperation == 1) op = "/";
        if (countOperation == 2) op = "+";
        if (countOperation == 3) op = "-";
        countOperation++;
        return op;
    }

    public static String operationStringBuilder(int n) {
        String operationString = "";
        for (int i = n; i > 1; i--) {
            operationString += Integer.toString(i);
            operationString += getOperationSignal();
        }
        operationString += 1;
        return operationString;
    }
}
