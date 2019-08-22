package com.andri.juni.mylibrary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class LibraryAdapter extends RecyclerView.Adapter<LibraryAdapter.ListViewHOlder> {
    private ArrayList<Library> listLibrary;
    private static OnItemClickCallback onItemClickCallback;

    public LibraryAdapter(ArrayList<Library> list) {
        this.listLibrary = list;
    }

    @NonNull
    @Override
    public ListViewHOlder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_library, viewGroup,false);
        return new ListViewHOlder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHOlder holder, int position) {
        final Library library = listLibrary.get(position);

        Glide.with(holder.itemView.getContext())
                .load(library.getPhoto())
                .centerCrop()
                .into(holder.img_Photo);

        holder.tv_Name.setText(library.getName());
        holder.tv_Location.setText(library.getLocation());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickCallback.onItemClicked(listLibrary.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listLibrary.size();
    }

    class ListViewHOlder extends RecyclerView.ViewHolder {
        ImageView img_Photo;
        TextView tv_Name, tv_Location;
        ListViewHOlder(@NonNull View itemView) {
            super(itemView);
            img_Photo = itemView.findViewById(R.id.img_item);
            tv_Name = itemView.findViewById(R.id.tv_name);
            tv_Location = itemView.findViewById(R.id.tv_location);
        }
    }

    static void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        LibraryAdapter.onItemClickCallback = onItemClickCallback;
    }

    public interface OnItemClickCallback {
        void onItemClicked(Library data_Name);
    }
}
