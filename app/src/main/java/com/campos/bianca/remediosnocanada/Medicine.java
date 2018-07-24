package com.campos.bianca.remediosnocanada;

public class Medicine {
    private int id;
    private String name_pt;
    private String name;
    private String brand_name_pt;
    private String brand_name;
    private String clinical_use_pt;
    private String clinical_use;
    private String prescription;

    // general constructor
    public Medicine(int id, String name_pt, String name, String brand_name_pt, String brand_name, String clinical_use_pt, String clinical_use, String prescription) {
        this.id = id;
        this.name_pt = name_pt;
        this.name = name;
        this.brand_name_pt = brand_name_pt;
        this.brand_name = brand_name;
        this.clinical_use_pt = clinical_use_pt;
        this.clinical_use = clinical_use;
        this.prescription = prescription;
    }

    // constructor main page
    public Medicine(int id, String name_pt, String brand_name_pt){
        this.id = id;
        this.brand_name_pt = brand_name_pt;
        this.name_pt = name_pt;
    }

    // default constructor
    public Medicine(){

    }

    // getters
    public int getId() {
        return id;
    }

    public String getName_pt() {
        return name_pt;
    }

    public String getName() {
        return name;
    }

    public String getBrand_name_pt() {
        return brand_name_pt;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public String getClinical_use_pt() {
        return clinical_use_pt;
    }

    public String getClinical_use() {
        return clinical_use;
    }

    public String getPrescription() {
        return prescription;
    }

    @Override
    public String toString() {
        return  name_pt;
    }
}
