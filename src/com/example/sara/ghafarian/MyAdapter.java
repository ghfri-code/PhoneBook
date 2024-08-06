package com.example.sara.ghafarian;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<Contact> contactList;

    public MyAdapter(Context context, List<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public Object getItem(int position) {
        return contactList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.contactlistview,parent, false);
        }

        TextView contactName = (TextView) convertView.findViewById(R.id.name);
        TextView contactPhone = (TextView) convertView.findViewById(R.id.phone);
        TextView contactEmail = (TextView) convertView.findViewById(R.id.email);
        ImageView contactImage = (ImageView) convertView.findViewById(R.id.image);

        contactName.setText(contactList.get(position).getName());
        contactPhone.setText(contactList.get(position).getPhoneNumber());
        contactEmail.setText(contactList.get(position).toString());
        contactImage.setImageResource(contactList.get(position).getImage());

        return convertView;

    }
}

