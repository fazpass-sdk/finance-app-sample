package com.fazpass.finance.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fazpass.finance.R;
import com.fazpass.finance.object.Overview;

import java.util.List;

public class MainRVOverviewAdapter extends RecyclerView.Adapter<MainRVOverviewAdapter.ViewHolder> {
    private List<Overview> overviews;

    public MainRVOverviewAdapter(List<Overview> overviews) {
        this.overviews = overviews;
    }

    public List<Overview> getOverviews() {
        return overviews;
    }

    public void setOverviews(List<Overview> overviews) {
        this.overviews = overviews;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vh_overview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final Overview overview = overviews.get(position);
        viewHolder.title.setText(overview.getTitle());
        viewHolder.subtitle.setText(overview.getSubtitle());
        MainRVOverviewDetailAdapter detailsAdapter = new MainRVOverviewDetailAdapter(overview.getDetails());
        viewHolder.details.setLayoutManager(new LinearLayoutManager(viewHolder.itemView.getContext()));
        viewHolder.details.setAdapter(detailsAdapter);
    }

    @Override
    public int getItemCount() {
        return overviews.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView subtitle;
        RecyclerView details;

        protected ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.o_title);
            subtitle = itemView.findViewById(R.id.o_subtitle);
            details = itemView.findViewById(R.id.o_details);
        }
    }
}
