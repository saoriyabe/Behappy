package practice.saori.behappy.data;

import java.util.ArrayList;

import practice.saori.behappy.model.Quote;

public interface QuoteListAsyncResponse {
    void processFinished(ArrayList<Quote> quotes);
}
