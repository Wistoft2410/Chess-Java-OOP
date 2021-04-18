package com.company;
import com.company.brikker.Brik;


public class Traek {
    public Felt fra, til;
    public Brik brik, tagetBrik;
    public Traek(Felt fra, Felt til, Brik brik, Brik tagetBrik) {
        this.fra = fra;
        this.til = til;
        this.brik = brik;
        this.tagetBrik = tagetBrik;
    }
}
