package com.smart.shortfilms.adapter;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.smart.shortfilms.MainActivity;
import com.smart.shortfilms.R;
import com.smart.shortfilms.vo.ShortFilm;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Purushotham on 04-11-2014.
 */
public class ImageListAdapter extends BaseAdapter {

    private final List<ShortFilm> list;
    private final Activity context;
    private final LayoutInflater inflater;
    private final SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");

    public ImageListAdapter(Activity context) {
        list = new ArrayList<ShortFilm>();
        this.context = context;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (list == null)
            return 0;
        return list.size();
    }

    public Activity getContext() {
        return context;
    }

    @Override
    public ShortFilm getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void setData(List<ShortFilm> films) {
        list.addAll(films);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ShortFilm shortFilm = list.get(position);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.image_item, parent, false);
        }

        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
        TextView titleView = (TextView) convertView.findViewById(R.id.title);
        TextView releaseDateView = (TextView) convertView.findViewById(R.id.releaseDate);
        TextView likesView = (TextView) convertView.findViewById(R.id.likesView);
        TextView dislikesView = (TextView) convertView.findViewById(R.id.dislikesView);
        TextView viewCountView = (TextView) convertView.findViewById(R.id.views);
        //TextView playTextView = (TextView) convertView.findViewById(R.id.playTextView);

        titleView.setTypeface(MainActivity.typeface);
        releaseDateView.setTypeface(MainActivity.typeface);
        likesView.setTypeface(MainActivity.typeface);
        dislikesView.setTypeface(MainActivity.typeface);
        viewCountView.setTypeface(MainActivity.typeface);
        //playTextView.setTypeface(typeface);

        Picasso.with(context).load(shortFilm.getUrl()).into(imageView);

        titleView.setText(shortFilm.getTitle().toUpperCase());
        String dateStr = format.format(new Date(shortFilm.getrDate()));
        releaseDateView.setText(dateStr);
        likesView.setText(context.getResources().getString(R.string.like)
                + NumberFormat.getNumberInstance(Locale.US).format(shortFilm.getLikes()));
        dislikesView.setText(context.getResources().getString(R.string.dislike)
                + NumberFormat.getNumberInstance(Locale.US).format(shortFilm.getDisLikes()));
        viewCountView.setText(context.getResources().getString(R.string.view)
                + NumberFormat.getNumberInstance(Locale.US).format(shortFilm.getViews()));

        return convertView;
    }
}
