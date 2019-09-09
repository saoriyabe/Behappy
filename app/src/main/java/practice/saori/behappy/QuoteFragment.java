package practice.saori.behappy;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.ThreadLocalRandom;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuoteFragment extends Fragment {


    public QuoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View quoteView = inflater.inflate(R.layout.fragment_quote, container, false);
        TextView quoteText = quoteView.findViewById(R.id.quoteText);
        TextView authorText = quoteView.findViewById(R.id.byAuthor);
        CardView cardView = quoteView.findViewById(R.id.cardView);

        final String quote = getArguments().getString("quote");
        final String author = getArguments().getString("author");

        int colors[] = new int[] {R.color.amber_900, R.color.blue_800, R.color.cyan_800, R.color.green_800, R.color.orange_700, R.color.grey_800};

        cardView.setBackgroundResource(getRandomQuote(colors));
        quoteText.setText(quote);
        authorText.setText("-" + author);

        Button shareButton = quoteView.findViewById(R.id.shareButton);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.setType("text/plain");
;               intent.putExtra(Intent.EXTRA_TEXT, quote + "\n -" + author);
                intent.putExtra(Intent.EXTRA_SUBJECT, "quote");
                startActivity(intent);
            }
        });

        return quoteView;
    }

    private int getRandomQuote(int[] colorArray) {
        int color;
        int quotesArrayLen = colorArray.length;
        int randomNum = ThreadLocalRandom.current().nextInt(quotesArrayLen);
        color = colorArray[randomNum];
        return color;
    }

    public static final QuoteFragment newInstance(String quote, String author) {
        QuoteFragment fragment = new QuoteFragment();
        Bundle bundle = new Bundle();
        bundle.putString("quote", quote);
        bundle.putString("author", author);
        fragment.setArguments(bundle);
        return fragment;
    }

}
