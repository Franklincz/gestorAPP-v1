package com.example.gestorapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestorapp.Entities.PuntoVenta;
import com.example.gestorapp.UTILITIES.Helper;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    Context mContext;
    ArrayList<PuntoVenta> mLystPunto;
    private final RecyclerViewInterface recyclerViewInterface;


    public RecyclerViewAdapter(Context mcontext, List<PuntoVenta> mLystPunto, RecyclerViewInterface recyclerViewInterface) {
        this.mContext = mcontext;
        this.mLystPunto = (ArrayList<PuntoVenta>) mLystPunto;
        this.recyclerViewInterface = recyclerViewInterface;
    }


    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view, recyclerViewInterface);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.txtpuntoVentaname.setText(mLystPunto.get(position).getDescripcion());
        holder.Direccion.setText(mLystPunto.get(position).getDireccion());
        holder.codigo.setText(mLystPunto.get(position).getName());
        byte[] my =mLystPunto.get(position).getFoto();
        if(my==null){

            holder.img.setImageResource(R.drawable.ic_baseline_explore_24);
        }
        else{
            holder.img.setImageBitmap(Helper.getImage(my));
        }




    }

    @Override
    public int getItemCount() {
        return mLystPunto.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtpuntoVentaname;
        TextView codigo,Descripcion,Direccion;
        ImageView img;

        private CardView maincard;


        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            itemView.setOnClickListener(this);
            txtpuntoVentaname = itemView.findViewById(R.id.txtzona);
            maincard = itemView.findViewById(R.id.maincard);
            Descripcion= itemView.findViewById(R.id.txtzona);
            codigo=itemView.findViewById(R.id.txtz);
            Direccion=itemView.findViewById(R.id.txtdescription);
            img = itemView.findViewById(R.id.imgBtnMaps);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerViewInterface != null) {
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });

        }

        @Override
        public void onClick(View v) {
//pen detail
            int position = getAdapterPosition();

            PuntoVenta pv = mLystPunto.get(position);
            Intent details = new Intent(mContext, DetailPuntoVenta.class);
            details.putExtra("name", pv.getName());
            //material
            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(mContext);
            builder.setTitle(pv.getDescripcion());
            builder.setMessage("Atenderá  el PDV?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {


                    Intent intent = new Intent(mContext, DetailPuntoVenta.class);
                    intent.putExtra("puntoventa", pv);
                    mContext.startActivity(intent);


                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            builder.show();


            // mContext.startActivity(details);
        }
    }
}
