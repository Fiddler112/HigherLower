package com.example.jonat.higher_lower;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.security.interfaces.DSAKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int mScore;
    private int mHighscore;
    private Random randomizer;
    private ImageView mImageView;
    private FloatingActionButton mButtonUp;
    private FloatingActionButton mButtonDown;
    private List<Integer> mScoreList;
    private ArrayAdapter mAdapter;
    private ListView mListview;
    private int[] mDiceNames;
    private TextView Tscore;
    private TextView Hscore;
    private int currentDice = 0;
    private int x;
    public Snackbar mySnackbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = findViewById(R.id.imageView);
        mButtonUp = findViewById(R.id.buttonUp);
        mButtonDown = findViewById(R.id.buttonDown);
        mScoreList = new ArrayList<Integer>();
        mListview = findViewById(R.id.list_view);
        Tscore = findViewById(R.id.Score);
        Hscore = findViewById(R.id.Highscore);
        randomizer = new Random();
        mySnackbar = Snackbar.make(findViewById(R.id.myConstraintLayout), "Highscore achieved!", Snackbar.LENGTH_SHORT);

        mScoreList.add(1);
        update();
        mDiceNames = new int[]{R.drawable.d1, R.drawable.d2, R.drawable.d3, R.drawable.d4, R.drawable.d5, R.drawable.d6};

        mButtonUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x = mScoreList.get(mScoreList.size() - 1);
                ThrowDice();
                System.out.println(x);
                if(currentDice >= x) {
                    mScore++;
//                    Toast.makeText(MainActivity.this, "Correct!",Toast.LENGTH_SHORT).show();
                    highscore();
                    update();
                } else {
                    mScore = 0;
                    Toast.makeText(MainActivity.this, "Wrong!!",Toast.LENGTH_SHORT).show();
                    update();
                }
            }
        });
        mButtonDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x = mScoreList.get(mScoreList.size() - 1);
                ThrowDice();
                System.out.println(x);
                if(currentDice <= x) {
                    mScore++;
//                    Toast.makeText(MainActivity.this, "Correct!",Toast.LENGTH_SHORT).show();
                    highscore();
                    update();
                } else {
                    mScore = 0;
                    Toast.makeText(MainActivity.this, "Wrong!!",Toast.LENGTH_SHORT).show();
                    update();
                }
            }
        });

    }
    public void ThrowDice() {
        currentDice = randomizer.nextInt(6);
        mScoreList.add(currentDice + 1);
        mImageView.setImageResource(mDiceNames[currentDice]);
    }

    public void highscore() {
        if(mScore > mHighscore) {
            mHighscore = mScore;

            mySnackbar.show();
        }
    }

    public void update() {

        Tscore.setText("Score: " + mScore);
        Hscore.setText("Highscore: " + mHighscore);
        if (mAdapter == null) {

            mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mScoreList);
            mListview.setAdapter(mAdapter);
        } else {
            // When the adapter is not null, it has to know what to do when our dataset changes, when a change happens we need to call this method on the adapter so that it will update internally.
            mAdapter.notifyDataSetChanged();
        }
    }

    }

