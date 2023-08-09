import java.util.List;

class Counter {

    public static boolean checkTheSameNumberOfTimes(int elem, List<Integer> list1, List<Integer> list2) {
        // implement the method

        long count1 = list1.stream().filter(num -> num == elem).count();
        long count2 = list2.stream().filter(num -> num == elem).count();
        return count1 == count2;
    }
}