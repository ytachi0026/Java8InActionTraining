package java8.in.action.chap5;
import java8.in.action.chap4.Dish;
import lambdasinaction.chap4.*;
import org.junit.Test;

import java.util.stream.*;
import java.util.*;

public class NumericStreams{

    public static void main(String...args){
    
        List<Integer> numbers = Arrays.asList(3,4,5,1,2);

        Arrays.stream(numbers.toArray()).forEach(System.out::println);
        int calories = Dish.menu.stream()
                           .mapToInt(Dish::getCalories)
                           .sum();
        System.out.println("Number of calories:" + calories);


        // max and OptionalInt
        OptionalInt maxCalories = Dish.menu.stream()
                                      .mapToInt(Dish::getCalories)
                                      .max();

        int max;
        if(maxCalories.isPresent()){
            max = maxCalories.getAsInt();
        }
        else {
            // we can choose a default value
            max = 1;
        }
        System.out.println(max);

        // numeric ranges
        IntStream evenNumbers = IntStream.rangeClosed(1, 100)
                                 .filter(n -> n % 2 == 0);

        System.out.println(evenNumbers.count());

        Stream<int[]> pythagoreanTriples =
               IntStream.rangeClosed(1, 100).boxed()
                        .flatMap(a -> IntStream.rangeClosed(a, 100)
                                               .filter(b -> Math.sqrt(a*a + b*b) % 1 == 0).boxed()
                                               .map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}));       

        pythagoreanTriples.forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2])); 

    }
   
    public static boolean isPerfectSquare(int n){
        return Math.sqrt(n) % 1 == 0;
    }

    @Test
    public void pythagoreanTriples(){
        Stream<int[]> pythagoreanTriples =
                IntStream.rangeClosed(1, 100).boxed()
                        .flatMap(a -> IntStream.rangeClosed(a, 100)
                                .filter(b -> Math.sqrt(a*a + b*b) % 1 == 0).boxed()
                                .map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}));

        pythagoreanTriples.forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));


        Stream<int[]> result = IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(  a -> IntStream.rangeClosed(a , 100)
                    .filter(b -> Math.sqrt(a*a + b*b )  % 1 == 0)
                    .boxed()
                    .map(b -> new int[] { a, b, (int) Math.sqrt(a*a + b*b)  }) );

        result.limit(5)
                .forEach(r -> System.out.println(r[0] + "," + r[1] + "=" + r[2]));


    }

    @Test
    public void testIntStream(){
        OptionalInt result = IntStream.rangeClosed(1, 100)
                .reduce(Integer::sum);

        int result1 = IntStream.rangeClosed(1,100)
                .sum();
        System.out.println(result);
        System.out.println(result1);
        result = IntStream.rangeClosed(1, 100)
                .reduce((a,b) -> a + b);
        System.out.println(result);
    }

}
