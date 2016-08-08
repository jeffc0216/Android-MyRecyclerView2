package com.cax.jeffchen.myrecyclerview;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SpotFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_spot, container, false);
        RecyclerView rvSpots = (RecyclerView) view.findViewById(R.id.rvSpots);
        if(rvSpots != null){
            rvSpots.setLayoutManager(new LinearLayoutManager(getActivity()));
            rvSpots.setAdapter(new SpotAdapter(getActivity(), getSpotList()));
        }
        return view;
    }

    private class SpotAdapter extends RecyclerView.Adapter {
        Context context;
        List<Spot> spotlist;
        public SpotAdapter(Context context, List<Spot> spotList) {
            this.context = context;
            this.spotlist = spotList;
        }

        private class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView ivSpot;
            TextView tvName, tvAddress;
            public MyViewHolder(View itemView) {
                super(itemView);
                ivSpot = (ImageView) itemView.findViewById(R.id.ivSpot);
                tvName = (TextView) itemView.findViewById(R.id.tvName);
                tvAddress = (TextView) itemView.findViewById(R.id.tvAddress);
            }
        }

        @Override
        public int getItemCount() {
            return spotlist.size();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            //間接依存false, 直接依存true
            View itemView = layoutInflater.inflate(R.layout.spot_item, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int index) {
            final Spot spot = spotlist.get(index);
            MyViewHolder viewHolder = ((MyViewHolder) holder);
            viewHolder.ivSpot.setImageResource(spot.getImageId());
            viewHolder.tvName.setText(spot.getName());
            viewHolder.tvAddress.setText(spot.getAddress());
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), SpotDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("spot", spot);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }
    }

    private List<Spot> getSpotList() {
        List<Spot> spotList = new ArrayList<>();
        spotList.add(new Spot(R.drawable.memorial, "中正紀念堂", "台北市中正紀念堂", "02-23455678"));
        spotList.add(new Spot(R.drawable.taipei101, "台北101", "台北市台北101", "02-23455677"));
        spotList.add(new Spot(R.drawable.pagodas, "龍虎塔", "高雄市龍虎塔", "08-23455679"));
        return spotList;
    }
}
