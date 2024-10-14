package chaoking.java.allinone.tools;

import java.util.Random;

/**
 * Created by chao_w on 2024/10/14.
 */
public class mathematics {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 50; i++) {
            Result result;
            if (i % 3 == 0) {
                result = calc("*-", 0);
                System.out.println(result.line);
            } else {
                result = calc("*+", 0);
                System.out.println(result.line);
            }
            sb.append(String.format("第%s题答案%s：", i + 1, result.answer)).append("\n");
        }
        System.out.println(sb.toString());
    }

    // region 计算策略

    /**
     *
     * @param calcStrategy 计算策略，如（*-、*+）
     * @param count 几个数字
     * @return
     */
    private static Result calc(String calcStrategy, Integer count) {
        Result result = new Result();

        String line = "";
        Random random = new Random();
        // 三位数
        Integer num1 = getNum1(random) ;
        Integer num2 = getNum2(random);
        if (calcStrategy == "*-") {
            Integer num3 = getNum3_subtract(random,num1, num2);
            line = num1 + " x " + num2 + " - " + num3;
            result.setLine(line);
            result.setAnswer(num1 * num2 - num3);
        } else if (calcStrategy == "*+") {
            Integer num3 = getNum3_plus(random);
            line = num1 + " x " + num2+ " + " + num3;
            result.setLine(line);
            result.setAnswer(num1 * num2 + num3);
        }
        return result;
    }

    private static Integer getNum1(Random random){
        return random.nextInt(900) + 100;
    }

    private static Integer getNum2(Random random){
        Integer num2 = random.nextInt(9)+1;
        if(num2==1)
            return 2;
        return num2;
    }

    private static Integer getNum3_plus(Random random){
        return random.nextInt(500);

    }

    private static Integer getNum3_subtract(Random random,Integer num1 ,Integer num2){
        return random.nextInt(num1 * num2);
    }

    // endregion

    static class Result{
        String line;
        Integer answer;

        public String getLine() {
            return line;
        }

        public void setLine(String line) {
            this.line = line;
        }

        public Integer getAnswer() {
            return answer;
        }

        public void setAnswer(Integer answer) {
            this.answer = answer;
        }
    }
}
