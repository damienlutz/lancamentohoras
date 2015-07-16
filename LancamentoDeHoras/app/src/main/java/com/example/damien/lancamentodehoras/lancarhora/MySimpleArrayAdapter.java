package com.example.damien.lancamentodehoras.lancarhora;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.damien.lancamentodehoras.R;

public class MySimpleArrayAdapter extends ArrayAdapter<String> {
  private final Context context;
  private final String[] values;

  public MySimpleArrayAdapter(Context context, String[] values) {
    super(context, -1, values);
    this.context = context;
    this.values = values;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    LayoutInflater inflater = (LayoutInflater) context
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View rowView = inflater.inflate(R.layout.apontar_hora_hista_item, parent, false);
    TextView textView = (TextView) rowView.findViewById(R.id.dateLine);
    
    textView.setText(values[position]);
    // change the icon for Windows and iPhone
    String s = values[position];

    return rowView;
  }
} 
