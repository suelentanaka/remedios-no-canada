package com.campos.bianca.remediosnocanada;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TextviewAdapter extends ArrayAdapter<Medicine>{
    private int layoutResource;
    public ArrayList<Medicine> medicineArrayList;
    public ArrayList<Medicine> orig;
    public static final int MEDICINE = 0;

    public TextviewAdapter(Context context, int layoutResource, List<Medicine> medicineList) {
        super(context, layoutResource, medicineList);
        this.layoutResource = layoutResource;
    }


    // search
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(layoutResource, null);
        }

        final Medicine medicine = getItem(position);

        if (medicine != null) {
//            ImageButton imgButton = (ImageButton) view.findViewById(R.id.favoriteButton);
            TextView title = (TextView) view.findViewById(R.id.medicine_title_TV);
            TextView content = (TextView) view.findViewById(R.id.medicine_content_TV);

            if (title != null) {
                title.setText(medicine.getName_pt());
            }
            if (content != null) {
                content.setText(medicine.getBrand_name_pt());
            }
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int medicineId = medicine.getId();
                Intent intent = new Intent(getContext(), DetailsActivity.class);
                intent.putExtra("MEDICINE", medicineId);
                getContext().startActivity(intent);
            }
        });

        return view;
    }


}
