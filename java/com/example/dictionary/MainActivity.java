package com.example.dictionary;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionary.adapters.Word_Adapter;
import com.example.dictionary.utils.DatabaseHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
public class MainActivity extends AppCompatActivity {
    private RecyclerView rvWord;
    private Word_Adapter word_adapter;
    private DatabaseHelper dh;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvWord = findViewById(R.id.rvWord);
        rvWord.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL);
        rvWord.addItemDecoration(itemDecoration);
        dh = new DatabaseHelper(this);
        File database = getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
       if (database.exists() == false)
       {
            dh.getReadableDatabase();
            if (copyDatabase(this))
            {
                Toast.makeText(getApplicationContext(), "Copy success", Toast.LENGTH_SHORT).show();
            } else
                {
                Toast.makeText(getApplicationContext(), "Copy Failed", Toast.LENGTH_SHORT).show();
                return;
               }
        }
        word_adapter = new Word_Adapter();
        rvWord.setAdapter(word_adapter);

         searchView= findViewById(R.id.searchView);
         searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
         {
             @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                searchWord(newText);
                return false;
            }
         });
    }
    private void searchWord(String wordSearch)
    {

      word_adapter.setCursor(dh.getListWord(wordSearch));
     rvWord.setAdapter(word_adapter);
    }
    private boolean copyDatabase(Context context){

        try{
            InputStream inputStream=context.getAssets().open(DatabaseHelper.DBNAME);
            String outFileName=DatabaseHelper.DBLOCATION +DatabaseHelper.DBNAME;
            OutputStream outputStream=new FileOutputStream(outFileName);
            byte[] buff=new byte[1024];
            int length=0;
            while ((length=inputStream.read(buff))>0){
                outputStream.write(buff,0,length);
            }
            outputStream.flush();
            outputStream.close();
            Log.w("Database","CopySuccess");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}

