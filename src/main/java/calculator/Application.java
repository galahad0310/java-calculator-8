package calculator;
import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        String str = input();
        long sum = calculator(str);
        System.out.println("결과 : " + sum);
    }

    private static long calculator(String str){
        if(str == null || str.isEmpty()) return 0;
        long sum = 0;

        for(String num : str.split("[,:]")){
            sum += Long.parseLong(num);
        }

        return sum;
    }

    private static String input(){
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        return Console.readLine();
    }
}
