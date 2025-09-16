public class LeetCode {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("teest"));
    }


    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        if (n == 0) return 0;

        int[] lastSeen = new int[Character.MAX_VALUE + 1];

        for (int i = 0; i < lastSeen.length; i++) {
            lastSeen[i] = -1;
        }

        int left = 0;
        int max = 0;

        for (int right = 0; right < n; right++) {
            char c = s.charAt(right);

            if (lastSeen[c] >= left) {
                // le caractère c est dans la fenêtre -> on décale left
                max = right - left;
                left = lastSeen[c] + 1;
            }

            lastSeen[c] = right; // on enregistre la dernière position de c

            //max = Math.max(max, right - left + 1);
        }
        if (n - left > max) max = n - left;
        return max;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] merged = new int[nums1.length + nums2.length];
        System.arraycopy(nums1, 0, merged, 0, nums1.length);
        System.arraycopy(nums2, 0, merged, nums1.length, nums2.length);

        // Appliquer quicksort
        quickSort(merged, 0, merged.length - 1);

        if (merged.length % 2 == 0)
            return (double) (merged[merged.length / 2] + merged[merged.length / 2 - 1]) / 2;
        else return merged[merged.length / 2];
    }

    private void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivot = partition(arr, low, high);

            quickSort(arr, low, pivot - 1);  // partie gauche
            quickSort(arr, pivot + 1, high); // partie droite
        }
    }

    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high]; // pivot = dernier élément
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                // swap arr[i] et arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // mettre le pivot à sa place finale
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

}