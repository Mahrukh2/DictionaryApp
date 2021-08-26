package com.example.dictionary;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DefinitionActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definition);

        String word=getIntent().getStringExtra("WORD");
        String definition=getIntent().getStringExtra("DEFINITION");

        TextView wordText = findViewById(R.id.wordtext);
        TextView definitionText= findViewById(R.id.definitiontext);

        wordText.setText(word);
        definitionText.setText(definition);
    }

}