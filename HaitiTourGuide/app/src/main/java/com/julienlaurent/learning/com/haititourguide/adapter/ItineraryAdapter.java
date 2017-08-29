package com.julienlaurent.learning.com.haititourguide.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.julienlaurent.learning.com.haititourguide.R;
import com.julienlaurent.learning.com.haititourguide.model.Itinerary;

import java.util.List;


public class ItineraryAdapter extends ArrayAdapter<Itinerary>{
    Context mContext;

    public ItineraryAdapter(@NonNull Context context, @NonNull List<Itinerary> objects) {
        super(context, 0, objects);
        this.mContext = context;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Itinerary itinerary = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext)
                .inflate(R.layout.itinerary_content_layout_adapter, parent, false);
            viewHolder.start = convertView.findViewById(R.id.display_date_begin_itinerary);
            viewHolder.end = convertView.findViewById(R.id.display_date_end_itinerary);
            viewHolder.place = convertView.findViewById(R.id.display_place_name_itinerary);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (itinerary != null){
            viewHolder.place.setText(itinerary.getAccommodationName());
            viewHolder.start.setText(itinerary.getStartDate());
            viewHolder.end.setText(itinerary.getEndDate());
        }
        return convertView;
    }

    private static class ViewHolder{
        TextView start;
        TextView end;
        TextView place;
    }
}
