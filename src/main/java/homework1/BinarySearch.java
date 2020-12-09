package homework1;

public class BinarySearch {
    private int[] data;
    private int target;

    public BinarySearch(int[] data, int target){
        this.setData(data);
        this.setTarget(target);
    }

    public BinarySearch(){

    }

    public int solve(){
        if(getData() == null && getTarget() == 0){
            return -1;
        }
        bubbleSort(getData());

        int min = 0;
        int max = getData().length - 1;

        while(min <= max){
            int mid = (min + max)/2;

            if(getTarget() < getData()[mid]){
                max = mid - 1;
            } else if (getTarget() > getData()[mid]){
                min = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;
    }

    public static void bubbleSort(int[] data){
        boolean notSorted = true;

        while(notSorted){
            notSorted = false;
            for (int i = 1; i < data.length - 1; i++) {
                if(data[i] < data[i-1]){
                    int temp = data[i];
                    data[i] = data[i-1];
                    data[i-1] = temp;

                    notSorted = true;
                }
            }
        }
    }

    public int[] getData() {
        return data;
    }

    public void setData(int[] data) {
        this.data = data;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }
}
