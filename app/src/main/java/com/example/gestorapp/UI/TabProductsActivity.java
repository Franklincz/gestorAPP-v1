package com.example.gestorapp.UI;

import static com.example.gestorapp.R.layout.activity_tab_products;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.gestorapp.Entities.Producto;
import com.example.gestorapp.ProductoAdapter;
import com.example.gestorapp.R;

import java.util.ArrayList;
import java.util.List;

public class TabProductsActivity extends AppCompatActivity {


    RecyclerView recycler_View;
    ProductoAdapter productoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_tab_products);

        recycler_View = findViewById(R.id.recyclerProductos);

        setRecyclerView();


    }

    private void setRecyclerView() {

        recycler_View.setHasFixedSize(true);
        recycler_View.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        productoAdapter = new ProductoAdapter(getApplicationContext(), getList());

        recycler_View.setAdapter(productoAdapter);
    }

    private List<Producto> getList() {
        List<Producto> products_list = new ArrayList<>();
        products_list.add(new Producto("aceite 12L", 25.0, 25.0, 2));
        products_list.add(new Producto("Leche 1L", 8.0, 7.0, 20));
        products_list.add(new Producto("Cerveza Pilser x caja", 100, 98.0, 20));

        Log.d("M", "ERROR + =" + products_list.size());

        return products_list;


    }


}