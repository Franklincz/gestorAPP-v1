package com.example.gestorapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gestorapp.Entities.PuntoVenta;
import com.example.gestorapp.UI.MapsActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements RecyclerViewInterface {


    private DBManager dbManager;

    Context mcontext;
    List<PuntoVenta> myList;
    RecyclerView mRecyclerview;
    RecyclerViewAdapter mRecyclerAdapter;

    FragmentManager fragmentManager;
    View rootview;
    FloatingActionButton fab;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mcontext = getActivity().getApplicationContext(); //obtengo mi context

        mRecyclerAdapter.notifyDataSetChanged();




    }//se llama despues del OncreateView

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fragment_home, container, false);
        mRecyclerview = rootview.findViewById(R.id.recyclerView1);
        fab = rootview.findViewById(R.id.fab);

        dbManager = new DBManager(getContext());






        this.updateData();
















        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                View view = getLayoutInflater().inflate(R.layout.creat_pv, null);
                final EditText txtDireccion = view.findViewById(R.id.edtdireeccion);
                final EditText txtDescripcion = view.findViewById(R.id.edtdescripcion);
                final EditText txtCodigo = view.findViewById(R.id.edtCodigo);
                alert.setView(view);
                AlertDialog dialog = alert.create();
                dialog.show();
                Button btnAgregar = view.findViewById(R.id.btnAgregar);
                Button btnCancelar = view.findViewById(R.id.btncancelar);
                btnAgregar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String direccion = txtDireccion.getText().toString();
                        String descripcion = txtDescripcion.getText().toString();
                        String namecodigo = txtCodigo.getText().toString();
                        try {
                            //dbManager.open();

                            dbManager.insertPv(direccion, null, descripcion, namecodigo);
                            //dbManager.close();
                            Toast.makeText(mcontext, "Agregado exitosamente!!", Toast.LENGTH_SHORT).show();
                            txtDireccion.setText("");
                            txtDescripcion.setText("");
                            txtCodigo.setText("");

                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                        updateData();
                    }

                });

                btnCancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Toast.makeText(getContext(),
                                "opcion cancelar", Toast.LENGTH_SHORT).show();
                       ;
                       dialog.dismiss();

                    }
                });



            }
        });


        return rootview;
    }






    public void updateData(){
        myList = new ArrayList<>();
        myList = dbManager.fetch();

        mRecyclerview.setLayoutManager(new GridLayoutManager(getContext(), 1));
        mRecyclerAdapter = new RecyclerViewAdapter(getContext(), myList,this::onItemClick);//adapter
        mRecyclerview.setAdapter(mRecyclerAdapter);
        mRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int position) {

        // Intent intent = new Intent(mcontext, MapsActivity.class);
        //intent.putExtra("name",myList.get(position).getName().toString());
        //startActivity(intent);
        openMap(myList.get(position).getDireccion());
    }


    private void openMap(String direccion) {
//Uri uri = Uri.parse("geo:0, 0?q=Government Polytechnic Aadmpur Trabganj Gond");
        Uri uri = Uri.parse("geo:0, 0?q="+direccion);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);

    }




}