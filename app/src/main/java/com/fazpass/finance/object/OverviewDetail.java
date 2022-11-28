package com.fazpass.finance.object;

public class OverviewDetail {
    private String title;
    private String subtitle;
    private String value;

    public OverviewDetail(String title, String subtitle, String value) {
        this.title = title;
        this.subtitle = subtitle;
        this.value = value;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
