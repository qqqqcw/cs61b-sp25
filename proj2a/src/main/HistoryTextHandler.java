package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;

import java.util.List;

public class HistoryTextHandler extends NgordnetQueryHandler {
    NGramMap myMap;
    public HistoryTextHandler(NGramMap map) {
        myMap = map;
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        String response = "";
        for (String word : words) {
            response += word + ": {";
            TimeSeries ts = myMap.weightHistory(word, startYear, endYear);
            List<Integer> years = ts.years();
            boolean firstFlag = true;
            for (int year : years) {
                if (firstFlag) {
                    firstFlag = false;
                } else {
                    response += ", ";
                }
                response += year + "=" + ts.get(year);
            }
            response += "}\n";
        }
        return response;
    }
}
