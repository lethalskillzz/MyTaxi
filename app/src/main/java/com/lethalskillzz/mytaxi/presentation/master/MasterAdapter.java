package com.lethalskillzz.mytaxi.presentation.master;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.lethalskillzz.mytaxi.R;
import com.lethalskillzz.mytaxi.data.model.Placemark;
import com.lethalskillzz.mytaxi.presentation.base.BaseViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ibrahimabdulkadir on 05/08/2017.
 */

public class MasterAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private Callback mCallback;
    private List<Placemark> mPlacemarks;

    public MasterAdapter(List<Placemark> placemarks) {
        setPlacemarks(placemarks);
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_placemark, parent, false);

        return new ViewHolder(view);
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if (mPlacemarks == null) {
            return 0;
        }
        return mPlacemarks.size();
    }


    private void setPlacemarks(@NonNull List<Placemark> placemarks) {
        mPlacemarks = placemarks;
    }

    public void refreshPlacemarks(@NonNull List<Placemark> placemarks) {
        setPlacemarks(placemarks);
        notifyDataSetChanged();
    }

    class ViewHolder extends BaseViewHolder{

        @BindView(R.id.item_placemark_imageView)
        ImageView imageView;
        @BindView(R.id.item_placemark_check_icon)
        ImageView checkIcon;
        @BindView(R.id.item_placemark_name_textView)
        TextView nameTextView;
        @BindView(R.id.item_placemark_address_textView)
        TextView addressTextView;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        protected void clear() {
            imageView.setImageDrawable(null);
            checkIcon.setImageDrawable(null);
            nameTextView.setText("");
            addressTextView.setText("");
        }

        public void onBind(int position) {
            super.onBind(position);

            final Placemark placemark = mPlacemarks.get(position);
            String name = placemark.name();
            String address = placemark.address();
            int fuel = placemark.fuel();

            nameTextView.setText(name);
            addressTextView.setText(address);

            int color = 0;
            if(fuel>=75)
                color = Color.parseColor("#039BE5");
            else if(fuel>=50 && fuel<75)
                color = Color.parseColor("#43A047");
            else if (fuel>=25 && fuel<50)
                color = Color.parseColor("#F4511E");
            else
                color = Color.parseColor("#E53935");

            TextDrawable drawable = TextDrawable.builder()
                    .buildRound(String.valueOf(fuel), color);

            imageView.setImageDrawable(drawable);

            itemView.setOnClickListener(v -> {
                if (mCallback != null)
                    mCallback.onPlacemarkClick(placemark);
            });
        }

    }

    public interface Callback {
        void onPlacemarkClick(Placemark placemark);
    }

}
