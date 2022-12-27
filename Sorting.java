import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Static class with some sorting algorithms we learned in Data Structures but never actually implimented.
 * @author: William Freedman
 * Implemented Algorithms: counting sort
 * Known bugs: Counting sort involves subtracting large integers, which can cause overflow issues if the numbers are
 * near the max int size
 */
public class Sorting {
    

    /**
     * A wrapper for countingSort, runs the method with an arraylist
     * @param arr The array to be sorted
     * @return The sorted array
     */
    public static int[] countingSort(int[] arr)
    {
        return countingSort(arr,true);
    }

    /**
     * Implements the coutning sort algorithm on an array of ints.
     * @param arr The array to be sorted
     * @param al Whether an array list should be used. If false, a linked list will be used, but this is slower
     * @return The sorted array
     */
    public static int[] countingSort(int[] arr, boolean al)
    {
        List<Integer> storage;
        if (al)
        {
            storage = new ArrayList<>();
        }
        else
        {
            storage = new LinkedList<>();
        }
        int offset = arr[findMin(arr)];
        for (Integer i: arr)
        {
            if (i-offset<storage.size())
            {
                Integer found = storage.get(i-offset);
                if (found == null || found.equals(0))
                {
                    storage.set(i-offset,1);
                }
                else
                {
                    storage.set(i-offset,found+1);
                }
            }
            else
            {
                while (i-offset>=storage.size())
                {
                    storage.add(0);
                }
                storage.set(i-offset,1);
            }
        }


        int[] toReturn = new int[arr.length];

        int idx = 0;
        for (int i=0;i<storage.size();i++)
        {
            for (int j=0;j<storage.get(i);j++)
            {
                toReturn[idx] = i+offset;
                idx ++;
            }
        }

        return toReturn;
    }

    /**
     * A helper method to find the minimum value of an array. Used in determining the offset for countingSort
     * @param arr The array to be searched
     * @return The index of the lowest element in the array
     */
    private static int findMin(int[] arr)
    {
        int found = arr[0];
        int idx = 0;
        for (int i=0;i<arr.length;i++)
        {
            if (arr[i]<found)
            {
                idx = i;
                found = arr[i];
            }
        }
        return idx;

    }

}
