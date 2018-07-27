package com.campos.bianca.remediosnocanada;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.like.LikeButton;
import com.like.OnLikeListener;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

public class DetailsActivity extends AppCompatActivity{
    Medicine medicine;
    DataBaseAdapter dbAdapter;
    int medicine_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        dbAdapter = new DataBaseAdapter(this);

        Intent intent = getIntent();
        medicine_id = intent.getIntExtra("MEDICINE", 0);
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
        need_prescription_pt_TV.setText(medicine.getPrescription_pt());

        TextView need_prescription_TV = findViewById(R.id.need_prescription_TV);
        need_prescription_TV.setText(medicine.getPrescription());


        //Favorites button
        LikeButton starButton = findViewById(R.id.star_button);
        starButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton starButton) {
                StyleableToast.makeText(DetailsActivity.this, "Added to Favorites List!", Toast.LENGTH_LONG, R.style.likeToast).show();
                dbAdapter.updateFavorites(medicine_id);
            }

            @Override
            public void unLiked(LikeButton starButton) {

                StyleableToast.makeText(DetailsActivity.this, "Removed from Favorites List!", Toast.LENGTH_LONG, R.style.dislikeToast).show();
            }
        });

    }

    // default constructor
    public DetailsActivity() {
    }


}
