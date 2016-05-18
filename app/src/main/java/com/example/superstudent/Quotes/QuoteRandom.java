package com.example.superstudent.Quotes;

import java.util.Random;

/**
 * Created by ABeeR on 04-May-16.
 */
public class QuoteRandom {

    public String Quotes[] = {

            "“Live as if you were to die tomorrow. Learn as if you were to live forever.”\n– Gandhi",

            "“Do not wait to strike till the iron is hot; but make it hot by striking.”\n– William Butler Yeats",

            "“Learning is not a spectator sport.”\n–D. Blocher",

            "“It is wiser to find out than to suppose.”\n– Mark Twain",

            "“Education is what survives when what has been learned has been forgotten.”\n – B. F. Skinner",

            "“I think, therefore I am. (Cogito, ergo sum.)”\n– Rene Descartes",

            "“Experience: that most brutal of teachers. But you learn, my God do you learn.”\n – C.S. Lewis",

            "“Learn from yesterday, live for today, hope for tomorrow.”\n – Albert Einstein"
    };


    public String getQuote() {
        String quote = "";
        Random random = new Random();
        int x = random.nextInt(Quotes.length);
        quote = Quotes[x];
        return quote;
    }

}

