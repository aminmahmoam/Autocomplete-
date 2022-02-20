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
        Arrays.sort(this.dictionary,Term.byLexicographicOrder);
    }

    // Returns the number of terms that start with the given prefix.
    // Precondition: the internal dictionary is in lexicographic order.
    // Complexity: O(log N) where N is the number of dictionary terms
    public int numberOfMatches(String prefix) {
        // TODO
        int firstMatch;
        int lastMatch;
        Term prefix2 = new Term(prefix,0);

        firstMatch = RangeBinarySearch.firstIndexOf(dictionary,prefix2,Term.byPrefixOrder(prefix.length()));
        lastMatch = RangeBinarySearch.lastIndexOf(dictionary,prefix2,Term.byPrefixOrder(prefix.length()));
        if (firstMatch == -1 || lastMatch == -1){
            return 0;
        }
        return (lastMatch - firstMatch) + 1;
    }

    // Returns all terms that start with the given prefix, in descending order of weight.
    // Precondition: the internal dictionary is in lexicographic order.
    // Complexity: O(log N + M log M) where M is the number of matching terms
    public Term[] allMatches(String prefix) {
        // TODO
        int numberOfMatches = numberOfMatches(prefix);
        Term[] result = new Term[numberOfMatches];

        int lo;
        int hi;
        Term prefix2 = new Term(prefix,0);

        lo = RangeBinarySearch.firstIndexOf(dictionary,prefix2,Term.byPrefixOrder(prefix.length()));
        hi = RangeBinarySearch.lastIndexOf(dictionary,prefix2,Term.byPrefixOrder(prefix.length()));
        if (lo > -1 || hi > -1){
            int i = 0;
            for (int j = lo; lo <= hi; j++) {
                result[i] = dictionary[j];
                lo++;
                i++;
            }
            Arrays.sort(result, Term.byReverseWeightOrder);
        }
        return result;
    }
}
