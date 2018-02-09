package tbd;

/**
 * Created by sr250345 on 12/11/17.
 */
public class SlidingWindow {

    public static void main(String[] args)
    {
        int[] numbers = {1,2,3,4,5,6,7,8,9,10};

        int windowSize = 3;
        int first;
        int sum = 0;
        first = numbers[0];
        for(int c = 0; c < windowSize; c++)
        {
            sum = sum + numbers[c];
        }
        System.out.println("sum" + "------>" + sum);
        for(int c = windowSize; c < numbers.length; c++)
        {
            sum = sum + numbers[c];
            first = numbers[c-windowSize];
            sum = sum - first;

            System.out.println("sum" + "------>" + sum);
        }



    }


}
