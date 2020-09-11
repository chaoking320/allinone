package chaoking.java.WechatDemo;

import java.util.ArrayList;
import java.util.List;

public class LambdaDemo {

    public static void main(String[] args) {
        List<Doctor> doctors = new ArrayList<>();

        doctors.add(buildDoctor("钟南山", 83));
        doctors.add(buildDoctor("张文宏", 51));

        // 年龄总和
        Integer totalAge = 0;
        // 采用对象的方式
        TotalAge sum = new TotalAge();


        // 方式一
        doctors.forEach(t -> {
            //totalAge += t.getAge();
            sum.setSum(sum.getSum()+t.getAge());
        });

        //region 方式二
        for (Doctor doctor : doctors) {
            totalAge += doctor.getAge();
        }
        //endregion
    }

    private static class TotalAge{
        private Integer sum;

        public Integer getSum() {
            return sum;
        }

        public void setSum(Integer sum) {
            this.sum = sum;
        }
    }

    private static Doctor buildDoctor(String name,Integer age){
        Doctor  person = new Doctor();
        person.setAge(age);
        person.setName(name);
        return person;
    }

    private static class Doctor{
        private String name;
        private Integer age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}


