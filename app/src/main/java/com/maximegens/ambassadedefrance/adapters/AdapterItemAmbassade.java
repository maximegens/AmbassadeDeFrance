package com.maximegens.ambassadedefrance.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.maximegens.ambassadedefrance.beans.Ambassade;
import com.maximegens.ambassadedefrance.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterItemAmbassade extends BaseAdapter {
    List<Ambassade> lesAmbassades = new ArrayList<>();
    Context ctx;
    LayoutInflater inflater;

    /**
     * Constructeur de l'Adapter.
     * @param context Le contexte de l'activity contenant l'adapter.
     * @param lesAmbassades La liste des ambassades
     */
    public AdapterItemAmbassade(Context context, List<Ambassade> lesAmbassades) {
        inflater = LayoutInflater.from(context);
        this.lesAmbassades = lesAmbassades;
        ctx = context;
    }
    @Override
    public int getCount() {
        return lesAmbassades.size();
    }

    @Override
    public Object getItem(int position) {
        return lesAmbassades.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    private class ViewHolder {
        TextView LeonNom;
        TextView Distance;
        TextView Adresse;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_ambassade, null);
            holder.LeonNom = (TextView)convertView.findViewById(R.id.adapter_ambassade_nom);
            holder.Distance = (TextView)convertView.findViewById(R.id.adapter_ambassade_distance);
            holder.Adresse = (TextView)convertView.findViewById(R.id.adapter_ambassade_adresse);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.LeonNom.setText(lesAmbassades.get(position).getNom());
        //holder.Adresse.setText(lesAmbassades.get(position).getAdresse());
        return convertView;
    }
}
