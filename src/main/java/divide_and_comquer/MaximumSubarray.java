package divide_and_comquer;

public class MaximumSubarray {

    public static void main(String[] args) {
        int[] arr = {-3, 1, 9, 9, -19, 5 - 1, 8, -9};
        int left = 0;
        int right = 0;
        int sum = arr[0] ;
        int maxsum = arr[0];

        for (int i = 1; i < arr.length; i++) {
            sum = sum + arr[i];
            int temp2 = arr[i] + arr[i - 1];
            if (sum >= maxsum && sum >= temp2 && sum >= arr[i]) {
                maxsum = sum;
                right = i;
            } else if (temp2 >= maxsum && temp2 >= sum && temp2 >= arr[i]) {
                maxsum = temp2;
                left = i - 1;
                right = i;
            } else if (arr[i] >= maxsum && arr[i] >= sum && arr[i] >= temp2) {
                left = i;
                right = i;
                maxsum = arr[i];
            }

        }

        System.out.println(left);
        System.out.println(right);
        System.out.println(maxsum);


    }
}
