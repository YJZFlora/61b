package hw3.hash;

import java.util.ArrayList;
import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        List<List>  lists = new ArrayList<>(M);
        for (int k = 0; k < M; k++) {
            List list = new ArrayList();
            lists.add(list);
        }
        int N = oomages.size();
        for (int i = 0; i < N; i++) {
            int bucketNum = (oomages.get(i).hashCode() & 0x7FFFFFFF) % M;
            lists.get(bucketNum).add(oomages);
        }
        for (int j = 0; j < M; j++) {
            int length = lists.get(j).size();
            if (length < N / 50 || length > N / 2.5) {
                return false;
            }
        }
        return true;
    }
}
