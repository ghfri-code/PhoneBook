package com.example.sara.ghafarian;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAdapterNote extends BaseAdapter
{
    private List<Note> Note;
    private Context context;

    public MyAdapterNote(Context context, List<Note> note) {
        this.context = context;
        Note = note;
    }



    @Override
    public int getCount() {
        return Note.size();
    }

    @Override
    public Object getItem(int position) {
        return Note.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent)
    {    if (v == null){
        LayoutInflater in = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = in.inflate(R.layout.notelistview,parent,false); }
        TextView note = (TextView) v.findViewById(R.id.textViewlvnote);
        note.setText(Note.get(position).getNotetext());
        
      /*  Log.d("noteee",Note.get(position).getNotetext());
        Log.d("myyyyyyy",(String) note.getText());*/
        
        return v;
    }
}
