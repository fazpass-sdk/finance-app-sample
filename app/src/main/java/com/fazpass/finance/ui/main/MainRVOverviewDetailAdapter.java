package com.fazpass.finance.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fazpass.finance.R;
import com.fazpass.finance.object.OverviewDetail;

import java.util.List;

public class MainRVOverviewDetailAdapter extends RecyclerView.Adapter<MainRVOverviewDetailAdapter.ViewHolder> {
    private List<OverviewDetail> overviewDetails;

    public MainRVOverviewDetailAdapter(List<OverviewDetail> overviewDetails) {
        this.overviewDetails = overviewDetails;
    }

    public List<OverviewDetail> getOverviewDetails() {
        return overviewDetails;
    }

    public void setOverviewDetails(List<OverviewDetail> overviews) {
        this.overviewDetails = overviews;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vh_overview_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final OverviewDetail detail = overviewDetails.get(position);
        viewHolder.title.setText(detail.getTitle());
        viewHolder.subtitle.setText(detail.getSubtitle());
        viewHolder.value.setText(detail.getValue());
    }

    @Override
    public int getItemCount() {
        return overviewDetails.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView subtitle;
        TextView value;
        
        protected ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.od_title);
            subtitle = itemView.findViewById(R.id.od_subtitle);
            value = itemView.findViewById(R.id.od_value);
        }
    }
}
