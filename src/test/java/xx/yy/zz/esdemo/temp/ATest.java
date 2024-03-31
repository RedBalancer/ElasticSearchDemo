package xx.yy.zz.esdemo.temp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Chunlong Zhang
 * @version 1.0.0
 * @ClassName ATest.java
 * @Description @TODO
 * @createTime 2024年03月14日 18:42:00
 */

@ExtendWith(MockitoExtension.class)
/* Dynamic Programming implementation in Java for longest bitonic
subsequence problem */
public class ATest {
    /* lbs() returns the length of the Longest Bitonic Subsequence in
    arr[] of size n. The function mainly creates two temporary arrays
    lis[] and lds[] and returns the maximum lis[i] + lds[i] - 1.

    lis[i] ==> Longest Increasing subsequence ending with arr[i]
    lds[i] ==> Longest decreasing subsequence starting with arr[i]
    */
    static int lbs( int arr[], int n )
    {
        int i, j;

		/* Allocate memory for LIS[] and initialize LIS values as 1 for
			all indexes */
        int[] lis = new int[n];
        Arrays.fill(lis, 1);
//        for (i = 0; i < n; i++)
//            lis[i] = 1;

        /* Compute LIS values from left to right */
        for (i = 1; i < n; i++)
            for (j = 0; j < i; j++)
                if (arr[i] > arr[j])
                    lis[i] = Math.max(lis[i], lis[j] + 1);

		/* Allocate memory for lds and initialize LDS values for
			all indexes */
        int[] lds = new int [n];
        for (i = 0; i < n; i++)
            lds[i] = 1;

        /* Compute LDS values from right to left */
        for (i = n-2; i >= 0; i--)
            for (j = n-1; j > i; j--)
                if (arr[i] > arr[j] && lds[i] < lds[j] + 1)
                    lds[i] = lds[j] + 1;


        /* Return the maximum value of lis[i] + lds[i] - 1*/
        int max = lis[0] + lds[0] - 1;
        int newMax = 0;
        for (i = 1; i < n; i++)
            if ( (newMax = lis[i] + lds[i] - 1) > max)
                max = newMax;

        return max;
    }

    @Test
    public void main1() {
        int arr[] = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5,
                13, 3, 11, 7, 15};
        int n = arr.length;
        System.out.println("Length of LBS is "+ lbs( arr, n ));
    }
    
    @Test
    void reverseInteger() {
        int i = 1234567709;
        String i1 = Integer.valueOf(i).toString();
        char[] result = new char[i1.length()];
        int pos = i1.length()-1;
        for (char c: i1.toCharArray() ) {
            result[pos--] = c;
        }
        System.out.println(String.valueOf(result));
    }

    @Test
    void matchBracket() {
        Stack<Character> brackets = new Stack();
        String bracketToValid = "[[[]]]";

        for (char c : bracketToValid.toCharArray()) {

        }
//        brackets.push();
    }

    class MyLinkedList<K, V> extends LinkedHashMap<K, V> {
        private int cacheSize;
        public MyLinkedList(int size) {
            super(size,(float)0.75, true);
            this.cacheSize = size;
        }
    }

    @Test
    void AGroupByAction() {
        List<Person> people = Arrays.asList(
                new Person("Alice", 25),
                new Person("Bob", 30),
                new Person("Charlie", 25),
                new Person("Diana", 30)
        );

        // 根据年龄分组
        Map<Integer, List<Person>> peopleByAge = people.stream()
                .collect(Collectors.groupingBy(Person::getAge));

        // 打印每个年龄组的人员列表
        peopleByAge.forEach((age, list) -> {
            System.out.println("Age: " + age);
            list.forEach(person -> System.out.println("   " + person.getName()));
        });
    }

    @Getter
    @AllArgsConstructor
    static class Person{
        String name;
        int age;
    };
}


