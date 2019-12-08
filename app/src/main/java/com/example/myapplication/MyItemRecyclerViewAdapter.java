package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.ItemFragment.OnListFragmentInteractionListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PictureItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {


    private final List<PictureItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyItemRecyclerViewAdapter(List<PictureItem> items, OnListFragmentInteractionListener listener) {
        mValues = items; //is this what changes?
        mListener = listener;

    }

    //created by me. This should update list based on contain
    public List<PictureItem> setList(String s) {
        return new ArrayList<PictureItem>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);

        final Button addTagButton = view.findViewById(R.id.add_tag_button);
        final EditText addTagEditText = view.findViewById(R.id.add_tag_editText);


        addTagButton.setOnClickListener(new View.OnClickListener() {
            //will add tag to
            public void onClick(View v) {
                //makes sure edittext has text to add
                if (addTagEditText.getText() == null || addTagEditText.getText().equals("")) {
                } else {

                    addTagButton.setText(addTagEditText.getText());
                }
            }
        });

        final ImageButton shareButton = view.findViewById(R.id.shareButton);



        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mImageView.setImageURI(mValues.get(position).uri);
        holder.mDateView.setText(mValues.get(position).date);

        holder.mTagView.setText("Tags: " + mValues.get(position).tag);


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mImageView;
        public final TextView mDateView;
        public final TextView mTagView;
        public PictureItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mImageView = view.findViewById(R.id.item_image_view);
            mDateView = view.findViewById(R.id.item_date_tv);
            mTagView = view.findViewById(R.id.tag);

        }
    }
}
