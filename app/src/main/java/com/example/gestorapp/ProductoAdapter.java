package com.example.gestorapp;

import android.content.Context;
import android.nfc.Tag;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestorapp.Entities.Producto;

import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.viewHolder> {

    Context contex;
    List<Producto> list_products;
DBManager DBManager;
    public ProductoAdapter(Context context, List<Producto> list_products) {
        this.contex = context;
        this.list_products = list_products;

    }


    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(contex).inflate(R.layout.item_layout_p, parent, false);
        return new viewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {


        try{
            if (list_products != null && list_products.size() > 0) {

                Producto producto = list_products.get(position);
                holder.precioRvTaMayor.setText((int) producto.getPrecioRvtaMayor());
                holder.descripcion.setText(producto.getDescripcion());
                holder.costo.setText((int) producto.getCosto());
                holder.stock.setText(producto.getStock());

            }else{
                return ;
            }

        }catch (Exception e){

        }



    }


    @Override
    public int getItemCount() {
        return list_products.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView descripcion, costo, precioRvTaMayor, stock;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
            descripcion = itemView.findViewById(R.id.idNamerProd);

            costo = itemView.findViewById(R.id.idcosto);
            precioRvTaMayor = itemView.findViewById(R.id.idRvptaMayor);
            stock = itemView.findViewById(R.id.idstock);

        }
    }
}
