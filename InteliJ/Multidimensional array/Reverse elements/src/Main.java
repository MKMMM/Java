class ArrayOperations {

    public static void reverseElements(int[][] twoDimArray) {
        // write your code here
        //Iterate over each row of the twoDim Array
        for (int i = 0; i < twoDimArray.length; i++) {

            //Initialize first and last elements
            int start = 0;
            int end = twoDimArray[i].length - 1;

            //From side to side swap elements towards the center
            while (start < end) {
                // Swap the elements and move the pointers towards the center
                int temp = twoDimArray[i][start];
                twoDimArray[i][start] = twoDimArray[i][end];
                twoDimArray[i][end] = temp;

                // Increment the pointers
                start++;
                end--;
            }
        }
    }
}