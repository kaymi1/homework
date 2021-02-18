package homework1test;


import BinarySearchAndHierarchy.BinarySearch;

public class TestBinarySearch {
    public static void main(String[]args){

        BinarySearch bs = new BinarySearch();
        int[] data = new int[100];
        for (int i = 0; i < data.length; i++) {
            data[i] = i*i;
        }
        int target = 25;

        bs.setTarget(target);
        bs.setData(data);
        test(bs);

        for (int i = 0; i < data.length/2; i++) {
            data[2*i] = i;
        }
        target = 46;

        bs.setTarget(target);
        bs.setData(data);
        test(bs);
    }

    public static void test(BinarySearch bs){
        System.out.println("Index " + bs.solve() + " has " + bs.getTarget());
    }
}

