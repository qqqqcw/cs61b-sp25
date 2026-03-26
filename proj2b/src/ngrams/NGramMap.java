package ngrams;

import edu.princeton.cs.algs4.In;

import java.sql.Time;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;

import static ngrams.TimeSeries.MAX_YEAR;
import static ngrams.TimeSeries.MIN_YEAR;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {

    // TODO: Add any necessary static/instance variables.
    Map<String, TimeSeries> myNGramMap;
    TimeSeries totalCounts;
    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        // TODO: Fill in this constructor. See the "NGramMap Tips" section of the spec for help.
        myNGramMap = new HashMap<>();
        totalCounts = new TimeSeries();
        In inWords = new In(wordsFilename);
        while (!inWords.isEmpty()) {
            String nextLine = inWords.readLine();
            String[] words = nextLine.split("\t");
            String word = words[0];
            int year = Integer.parseInt(words[1]);
            double count = Double.parseDouble(words[2]);
            if (myNGramMap.containsKey(word)) {
                myNGramMap.get(word).put(year, count);
            } else {
                TimeSeries ts = new TimeSeries();
                ts.put(year, count);
                myNGramMap.put(words[0], ts);
            }
        }
        In incounts = new In(countsFilename);
        while (!incounts.isEmpty()) {
            String nextLine = incounts.readLine();
            String[] words = nextLine.split(",");
            int year = Integer.parseInt(words[0]);
            double totalCount = Double.parseDouble(words[1]);
            totalCounts.put(year, totalCount);
        }
    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        TimeSeries ts;
        if (myNGramMap.containsKey(word)) {
            ts = new TimeSeries(myNGramMap.get(word), startYear, endYear);
        } else {
            ts = new TimeSeries();
        }
        return ts;
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word) {
        // TODO: Fill in this method.
        TimeSeries ts;
        if (myNGramMap.containsKey(word)) {
            ts = new TimeSeries(myNGramMap.get(word), MIN_YEAR, MAX_YEAR);
        } else {
            ts = new TimeSeries();
        }
        return ts;
    }


    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        // TODO: Fill in this method.
        return new TimeSeries(totalCounts, MIN_YEAR, MAX_YEAR);
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        TimeSeries ts;
        ts = countHistory(word, startYear, endYear);
        ts = ts.dividedBy(totalCounts);
        return ts;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        // TODO: Fill in this method.
        TimeSeries ts;
        ts = countHistory(word);
        ts = ts.dividedBy(totalCounts);
        return ts;
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        // TODO: Fill in this method.
        TimeSeries ts = new TimeSeries();
        for (String word : words) {
            ts = ts.plus(weightHistory(word, startYear, endYear));
        }
        return ts;
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        // TODO: Fill in this method.
        TimeSeries ts = new TimeSeries();
        for (String word : words) {
            ts = ts.plus(weightHistory(word));
        }
        return ts;
    }

    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.
}
