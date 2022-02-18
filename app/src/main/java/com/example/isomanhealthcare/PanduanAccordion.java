package com.example.isomanhealthcare;

public class PanduanAccordion {

    private String tPanduan, dPanduan;
    private boolean expandable;

    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }

    public PanduanAccordion(String tPanduan, String dPanduan) {
        this.tPanduan = tPanduan;
        this.dPanduan = dPanduan;
        this.expandable = false;
    }

    public String gettPanduan() {
        return tPanduan;
    }

    public void settPanduan(String tPanduan) {
        this.tPanduan = tPanduan;
    }

    public String getdPanduan() {
        return dPanduan;
    }

    public void setdPanduan(String dPanduan) {
        this.dPanduan = dPanduan;
    }

    @Override
    public String toString() {
        return "PanduanAccordion{" +
                "tPanduan='" + tPanduan + '\'' +
                ", dPanduan='" + dPanduan + '\'' +
                '}';
    }


}
