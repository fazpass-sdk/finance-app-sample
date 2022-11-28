package com.fazpass.finance.object;

import android.graphics.drawable.Drawable;

import androidx.annotation.Nullable;

import java.util.List;

public class Overview {
    private String title;
    private String subtitle;
    private List<OverviewDetail> details;

    public Overview(String title, String subtitle, List<OverviewDetail> details) {
        this.title = title;
        this.subtitle = subtitle;
        this.details = details;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public List<OverviewDetail> getDetails() {
        return details;
    }

    public void setDetails(List<OverviewDetail> details) {
        this.details = details;
    }
}
