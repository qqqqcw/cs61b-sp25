package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;
import org.knowm.xchart.XYChart;
import plotting.Plotter;

import java.util.ArrayList;
import java.util.List;

public class HistoryHandler extends NgordnetQueryHandler {
    NGramMap myMap;
    public HistoryHandler(NGramMap map) {
        myMap = map;
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        ArrayList<TimeSeries> lts = new ArrayList<>();
        for (String word : words) {
            lts.add(myMap.weightHistory(word, startYear, endYear));
        }
        XYChart chart = Plotter.generateTimeSeriesChart(words, lts);
        String encodedImage = Plotter.encodeChartAsString(chart);
        return encodedImage;
    }
}
