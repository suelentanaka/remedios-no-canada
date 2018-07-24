package com.campos.bianca.remediosnocanada;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class TextviewAdapter extends ArrayAdapter<Medicine>{
    private int layoutResource;

    public TextviewAdapter(Context context, int layoutResource, List<Medicine> medicineList) {
        super(context, layoutResource, medicineList);
        this.layoutResource = layoutResource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(layoutResource, null);
        }

        Medicine medicine = getItem(position);

        if (medicine != null) {
            ImageButton imgButton = (ImageButton) view.findViewById(R.id.favoriteButton);
            TextView title = (TextView) view.findViewById(R.id.medicine_title_TV);
            TextView content = (TextView) view.findViewById(R.id.medicine_content_TV);

//            if (imgButton != null) {
//                imgButton.setImageDrawable(R.drawable.ic_favorite);
//            }
            if (title != null) {
                title.setText(medicine.getBrand_name_pt());
            }
            if (content != null) {
                content.setText(medicine.getName_pt());
            }
        }

        return view;
    }
}
