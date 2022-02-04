import java.util.ArrayList;
import java.util.Arrays;

public class Autocompleter {
    private final Term[] dictionary;

    // Initializes the dictionary from the given array of terms.
    public Autocompleter(Term[] dictionary) {
        this.dictionary = dictionary;
        sortDictionary();
    }

    // Sorts the dictionary in *case-insensitive* lexicographic order.
    // Complexity: O(N log N) where N is the number of dictionary terms
    private void sortDictionary() {
        // TODO
        Arrays.parallelSort(dictionary, Term.byLexicographicOrder);
    }

    // Returns the number of terms that start with the given prefix.
    // Precondition: the internal dictionary is in lexicographic order.
    // Complexity: O(log N) where N is the number of dictionary terms
    public int numberOfMatches(String prefix) {
        // TODO
        int lo=0;
        int hi=0;
        Term prefix2 = new Term(prefix,0);

        lo = RangeBinarySearch.firstIndexOf(dictionary,prefix2,Term.byPrefixOrder(prefix.length()));
        hi = RangeBinarySearch.lastIndexOf(dictionary,prefix2,Term.byPrefixOrder(prefix.length()));
        System.out.println(lo);
        System.out.println(hi);
        return (hi - lo) + 1;
    }

    // Returns all terms that start with the given prefix, in descending order of weight.
    // Precondition: the internal dictionary is in lexicographic order.
    // Complexity: O(log N + M log M) where M is the number of matching terms
    public Term[] allMatches(String prefix) {
        // TODO
        int numberOfMatches = numberOfMatches(prefix);
        Term[] result = new Term[numberOfMatches];

        int lo=0;
        int hi=0;
        Term prefix2 = new Term(prefix,0);

        lo = RangeBinarySearch.firstIndexOf(dictionary,prefix2,Term.byPrefixOrder(prefix.length()));
        hi = RangeBinarySearch.lastIndexOf(dictionary,prefix2,Term.byPrefixOrder(prefix.length()));

        int i=0;
        for (int j=lo; lo<hi; lo++) {
            result[i] = dictionary[j];
            i++;
        }

        Arrays.parallelSort(result, Term.byReverseWeightOrder);

        return result;

    }
}
