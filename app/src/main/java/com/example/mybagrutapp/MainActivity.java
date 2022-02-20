package com.example.mybagrutapp;


import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    EditText searchBar;
    Button searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchBtn = findViewById(R.id.search_button);
        searchBar = findViewById(R.id.search_bar);
        searchBtn.setOnClickListener(this);

    }

    /*public void saveMessi()
    {

        URL urlWiki = null, urlInsta = null;

        try {
            urlWiki = new URL("https://en.wikipedia.org/wiki/Lionel_Messi");
            Log.d("Mine", "URL created: " + urlWiki);
            urlInsta = new URL("https://www.instagram.com/leomessi/");
            Log.d("Mine", "URL created: " + urlInsta);
        }
        catch (MalformedURLException e) {
            Log.d("Mine","Malformed URL: " + e.getMessage());
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("players");

        Player messi = new Player( "Lionel Messi", BitmapFactory.decodeResource(this.getResources(), R.drawable.promessi), "Lionel Andrés Messi", "24 June 1987", 34, "1.69", "Forward", "Paris Saint-Germain", 30, "Argentina", 758, 321, 80, "Career: Barcelona(2004-2021), PSG(2021-Today).",
                "Lionel Andrés Messi(born 24 June 1987), also known as Leo Messi, is an Argentine professional footballer who plays as a forward for Ligue 1 club Paris Saint-Germain and captains the Argentina national team." +
                        "Often considered the best player in the world and widely regarded as one of the greatest players of all time. Born and raised in central Argentina, Messi relocated to Spain to join Barcelona at age 13, for whom he made his competitive debut aged 17 in October 2004." +
                        " He established himself as an integral player for the club within the next three years, and in his first uninterrupted season in 2008–09 he helped Barcelona achieve the first treble in Spanish football, that year, aged 22, Messi won his first Ballon d'Or." +
                        " Three successful seasons followed, with Messi winning four consecutive Ballons d'Or, making him the first player to win the award four times and in a row. During the 2011–12 season, he set the La Liga and European records for most goals scored in a single season, while establishing himself as Barcelona's all-time top scorer." +
                        " The following two seasons, Messi finished second for the Ballon d'Or behind Cristiano Ronaldo (his perceived career rival), before regaining his best form during the 2014–15 campaign, becoming the all-time top scorer in La Liga and leading Barcelona to a historic second treble," +
                        " after which he was awarded a fifth Ballon d'Or in 2015. Messi assumed captaincy of Barcelona in 2018, and in 2019 he won a record sixth Ballon d'Or. Out of contract, he signed for Paris Saint-Germain in August 2021.",
                urlWiki, urlInsta);

        myRef.setValue("messi");

    }*/

    @Override
    public void onClick( View v )
    {

        if( searchBtn == v)
        {

            Intent intent = new Intent(this,PlayerDisplay.class);
            intent.putExtra( "searchResults", searchBar.getText().toString() );
            startActivity(intent);

        }

    }

}