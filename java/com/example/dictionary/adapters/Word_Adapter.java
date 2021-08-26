package com.example.dictionary.adapters;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionary.DefinitionActivity;
import com.example.dictionary.R;

public class Word_Adapter extends RecyclerView.Adapter<Word_Adapter.ViewHolder>
{
    public Cursor cursor;
    public Word_Adapter(){}
    public void setCursor(Cursor cursor)
    {
        this.cursor=cursor;
        notifyDataSetChanged();
    }
    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType)
    {
       Context context =parent.getContext();
       LayoutInflater inflater=LayoutInflater.from(context);
       View wordView=inflater.inflate(R.layout.word_item,parent,false);
       ViewHolder viewHolder=new ViewHolder(wordView,context);
       return viewHolder;
    }
    @Override
    public void onBindViewHolder( Word_Adapter.ViewHolder holder, int position)
    {
               cursor.moveToPosition(position);
               holder.wordText.setText(cursor.getString(1));
    }

   @Override
    public int getItemCount()
   {
        if(cursor==null) return 0;
        return cursor.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
       public Context context;
       public TextView wordText;
       public ViewHolder(View itemView, Context context)
       {
            super(itemView);
            this.context=context;
            wordText= itemView.findViewById(R.id.wordtext);

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view){
                   int position=getAdapterPosition();
                   cursor.moveToPosition(position);

                    Intent intent=new Intent(context, DefinitionActivity.class);
                    intent.putExtra("WORD", cursor.getString(1));
                    intent.putExtra("DEFINITION", cursor.getString(2));
                    context.startActivity(intent);
                }
            });
       }
    }
}
