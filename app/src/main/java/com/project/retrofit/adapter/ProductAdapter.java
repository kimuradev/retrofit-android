package com.project.retrofit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.project.retrofit.R;
import com.project.retrofit.model.Product;

import java.util.List;

/**
 * Created by Leandro on 02/01/2017.
 */

public class ProductAdapter extends ArrayAdapter<Product> {

    public ProductAdapter(Context context, int resource, List<Product> objects) {
        super(context, resource, objects);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Product product = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.txt_nome);
        TextView tvDescricao = (TextView) convertView.findViewById(R.id.txt_descricao);
        // Populate the data into the template view using the data object
        tvName.setText(product.getName());
        tvDescricao.setText(product.getDescription());
        // Return the completed view to render on screen
        return convertView;
    }


}

