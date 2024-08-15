package com.example.firstapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CategoryAdapter extends BaseAdapter {
    private Context context;
    private final String[] categories;
    private final int[] images;

    public CategoryAdapter(Context context, String[] categories, int[] images) {
        this.context = context;
        // Ensure only first 6 categories are used
        this.categories = new String[6];
        System.arraycopy(categories, 0, this.categories, 0, 6);
        // Ensure only first 6 images are used
        this.images = new int[6];
        System.arraycopy(images, 0, this.images, 0, 6);
    }

    @Override
    public int getCount() {
        return categories.length; // Only show 6 categories
    }

    @Override
    public Object getItem(int position) {
        return categories[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.category_item, parent, false);
            holder = new ViewHolder();
            holder.imageView = convertView.findViewById(R.id.categoryImageView);
            holder.textView = convertView.findViewById(R.id.categoryTextView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.imageView.setImageResource(images[position]);
        holder.textView.setText(categories[position]);

        return convertView;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView textView;
    }
}
