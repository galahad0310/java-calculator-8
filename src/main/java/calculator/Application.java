package calculator;

import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        String str = input();
        long sum = calculator(str); // 👈 try-catch 없이 바로 호출
        System.out.println("결과 : " + sum);
    }

    private static String input() {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        return Console.readLine();
    }

    private static long calculator(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }

        ExtractionResult result = extractCustomDelimiters(str);
        String[] numbers = result.textToCalculate().split("[" + result.delimiters() + "]");
        long sum = 0;

        for (String numStr : numbers) {
            if (!numStr.isEmpty()) {
                try {
                    long num = Long.parseLong(numStr);
                    if (num < 0) {
                        throw new IllegalArgumentException("음수는 입력할 수 없습니다.");
                    }
                    sum += num;
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("문자열에 숫자가 아닌 값이 포함되어 있습니다.");
                }
            }
        }
        return sum;
    }

    private static ExtractionResult extractCustomDelimiters(String str) {
        StringBuilder delimiterBuilder = new StringBuilder(",:");
        String textToCalculate = str;

        while (textToCalculate.startsWith("//")) {
            int newLineIndex = textToCalculate.indexOf("\\n");
            if (newLineIndex == -1) {
                throw new IllegalArgumentException("커스텀 구분자 형식이 올바르지 않습니다.(\\n이 없음)");
            }
            delimiterBuilder.append(textToCalculate, 2, newLineIndex);
            textToCalculate = textToCalculate.substring(newLineIndex + 2);
        }
        return new ExtractionResult(delimiterBuilder.toString(), textToCalculate);
    }

    private record ExtractionResult(String delimiters, String textToCalculate) {
    }
}