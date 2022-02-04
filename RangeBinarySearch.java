
import java.util.Comparator;

public class RangeBinarySearch {

    // Returns the index of the *first* element in `a` that equals the search key,
    // according to the given comparator, or -1 if there is no matching element.
    // Precondition: `a` is sorted according to the given comparator.
    // Complexity: O(log N) comparisons where N is the length of `a`
    public static<T> int firstIndexOf(T[] a, T key, Comparator<T> comparator) {
        // TODO
        int lo=0;
        int hi= a.length-1;
        int answer=0;

        while (lo<=hi){
            int mid= (lo+hi)/2;
            int result = comparator.compare(a[mid],key);
            if( result == -1 ) {
                lo= mid+1;
            } else if (result == 1) {
                hi = mid-1;
            } else if (result == 0) {
                answer = mid;
                hi = mid -1 ;
            }
        }
        return answer;
    }

    // Returns the index of the *last* element in `a` that equals the search key,
    // according to the given comparator, or -1 if there are is matching element.
    // Precondition: `a` is sorted according to the given comparator.
    // Complexity: O(log N) comparisons where N is the length of `a`
    public static<T> int lastIndexOf(T[] a, T key, Comparator<T> comparator) {
        // TODO
        int lo=0;
        int hi= a.length-1;
        int answer=0;

        while (lo<=hi){
            int mid= (lo+hi)/2;
            int result = comparator.compare(a[mid],key);
            if( result == -1 ) {
                lo= mid+1;
            } else if (result == 1) {
                hi = mid-1;
            } else if (result == 0) {
                answer = mid;
                lo = mid +1;
            }
        }
        return answer;
    }

    public static final Comparator<String> alphabetically = new Comparator<String>() {
        @Override

        public int compare(String o1, String o2) {
            return o1.compareToIgnoreCase(o2);
        }
    } ;

    // For testing purposes.
    public static void main(String[] args) {
        // Here you can write some tests if you want.
        String[] T = {"a","a","b","b","c","c","c"};
        int answer= firstIndexOf(T,"b", alphabetically);
        System.out.println(answer);

        int secondAnswer= lastIndexOf(T, "c", alphabetically);
        System.out.println(secondAnswer);
    }
}
