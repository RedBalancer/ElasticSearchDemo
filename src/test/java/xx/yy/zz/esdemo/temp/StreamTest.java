package xx.yy.zz.esdemo.temp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

/**
 * @author Chunlong Zhang
 * @version 1.0.0
 * @ClassName StreamTest.java
 * @Description @TODO
 * @createTime 2024年03月22日 10:21:00
 */
@ExtendWith(MockitoExtension.class)
public class StreamTest {
    record Person(String name, int age, boolean gender) {
        @Override
        public Person clone() {
            return new Person(name, age, gender);
        }
    }

    private ArrayList<Person> personList = new ArrayList<>();

    @BeforeEach
    public void init() {
        Person t = new Person( "张三", 20, true);
        personList.add(t);
        t = new Person("李四", 30, true);
        personList.add(t);
        t = new Person("小李", 30, false);
        personList.add(t);
        t = new Person("小宋", 29, true);
        personList.add(t);
        t = new Person("大黄", 35, false);
        personList.add(t);
        t = new Person("一一", 40, true);
        personList.add(t);

    }

    @Test
    void testSteamMap() {
        personList.stream().map(person -> {
            Person p = person.clone();
            return p;
        })
                .forEach(p->System.out.println(p.gender));
                //.forEach(person -> System.out.println(person.name+person.age));
    }
}
