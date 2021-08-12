package chaoking.java.allinone.others;


/**
 * final 的作用是禁止变更，
 *  1）不单单用在类变量的声明上，
 *  2）也可以用在方法的参数上
 */
public class FinalTest {

    private static final String name = "jason";

    public static void main(String[] args) {

       /* if(Strings.isNullOrEmpty(name))
            // 这里不允许修改，因为有final
            name  = "chao";*/

        test("hello world.");
    }

    private static void test(final String code){

        /*if (Strings.isNullOrEmpty(code))
            // 这里不允许修改，因为有final
            code= "改变";*/

        System.out.println(code);
    }

}
