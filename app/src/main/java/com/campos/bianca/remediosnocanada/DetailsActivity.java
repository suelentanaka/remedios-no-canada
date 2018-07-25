package com.campos.bianca.remediosnocanada;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity{
    Medicine medicine;
    DataBaseAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        dbAdapter = new DataBaseAdapter(this);

        Intent intent = getIntent();
        int medicine_id = intent.getIntExtra("MEDICINE", 0);
        medicine = dbAdapter.getDetailPage(medicine_id);

        TextView name_pt_TV = findViewById(R.id.name_pt_TV);
        name_pt_TV.setText(medicine.getName_pt());

        TextView name_en_TV = findViewById(R.id.name_en_TV);
        name_en_TV.setText(medicine.getName());

        TextView brand_name_pt_TV = findViewById(R.id.brand_name_pt_TV);
        brand_name_pt_TV.setText(medicine.getBrand_name_pt());

        TextView brand_name_en_TV = findViewById(R.id.brand_name_en_TV);
        brand_name_en_TV.setText(medicine.getBrand_name());

        TextView clinical_use_pt_TV = findViewById(R.id.clinical_use_pt_TV);
        clinical_use_pt_TV.setText(medicine.getClinical_use_pt());

        TextView clinical_use_en_TV = findViewById(R.id.clinical_use_en_TV);
        clinical_use_en_TV.setText(medicine.getClinical_use());

        TextView need_prescription_pt_TV = findViewById(R.id.need_prescription_pt_TV);
        need_prescription_pt_TV.setText(medicine.getPrescription());

    }

    public DetailsActivity() {
    }

    public DetailsActivity(Medicine medicine) {
        this.medicine = medicine;
    }
}
