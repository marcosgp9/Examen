package com.example.examen;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private LinkedList<Contacte> mWordList;
    private LayoutInflater mInflater;


    public WordListAdapter(Context context, LinkedList<Contacte> mWorldLinked) {
        mInflater = LayoutInflater.from(context);
        this.mWordList = mWorldLinked;
    }


    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType){ // Inflate an item view.
        View mItemView = mInflater.inflate(R.layout.row, parent, false);
        return new WordViewHolder(mItemView, this);

    }


    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {

        Contacte contacto = mWordList.get(position);
        holder.wordItemId.setText(contacto.getId());
        holder.wordItemNombre.setText(contacto.getNombre());
        holder.wordItemTelefono.setText(contacto.getCompletado());
    }

    @Override
    public int getItemCount() {

        return mWordList.size();
    }

    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView wordItemView;
        private TextView wordItemId, wordItemNombre, wordItemTelefono;
        private WordListAdapter mAdapter;

        public WordViewHolder(View itemView, WordListAdapter adapter) {
            super(itemView);
            wordItemId = (TextView) itemView.findViewById(R.id.consultaId);
            wordItemNombre = (TextView) itemView.findViewById(R.id.consultaNombre);
            wordItemTelefono = (TextView) itemView.findViewById(R.id.consultaTelefono);
            //wordItemView = (TextView) itemView.findViewById(R.id.consultaTelefono);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}

