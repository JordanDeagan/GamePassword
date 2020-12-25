package com.example.gamepassword;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import java.util.List;

public class RumorArray extends ArrayAdapter {
    Context Ctx;
    private Rumor rumor;
    List<Rumor> rumorList;
    static final String TAG = "lifecycle";

    public RumorArray(@NonNull Context context, int resource, @NonNull List<Rumor> objects) {
        super(context, resource, objects);
        Ctx = context;
        rumorList = objects;
    }

    private class ViewHolder {
        ImageView Weapon;
        ImageView Room;
        ImageView Suspect;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        rumor = rumorList.get(position);

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(convertView == null){
            convertView = inflater.inflate(R.layout.rumor, null);
            holder = new ViewHolder();
            holder.Room = (ImageView) convertView.findViewById(R.id.Room);
            holder.Weapon = (ImageView) convertView.findViewById(R.id.Weapon);
            holder.Suspect = (ImageView) convertView.findViewById(R.id.Suspect);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.Room.setImageResource(rumor.getRoom());
        holder.Weapon.setImageResource(rumor.getWeapon());
        holder.Suspect.setImageResource(rumor.getSuspect());

        return convertView;
    }
}
