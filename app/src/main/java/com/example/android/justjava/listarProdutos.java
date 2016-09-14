package com.example.android.justjava;

import android.app.Activity;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Hellow on 13/09/2016.
 */
public class listarProdutos extends Activity {
    private String nome_produto;
    private Boolean status_produto;
    private Produto p;
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> lista_de_produtos = new ArrayList<>();
    DatabaseReference dataBase = FirebaseDatabase.getInstance().getReference().child("ProdutosNovo");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar_produtos);
        listView = (ListView) findViewById(R.id.listView);

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista_de_produtos);
        listView.setAdapter(arrayAdapter);


        dataBase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Iterator i = dataSnapshot.getChildren().iterator();

                while (i.hasNext()){

                    nome_produto = (String) ((DataSnapshot)i.next()).getValue();
                    status_produto= (Boolean)((DataSnapshot)i.next()).getValue();

                    lista_de_produtos.add(nome_produto+" : "+status_produto);

                }



                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    }

/*
    Iterator i = dataSnapshot.getChildren().iterator();
    list_produtos.clear();
    while(i.hasNext()){
        nome_produto = (String) ((DataSnapshot)i.next()).getValue();
        list_produtos.add(nome_produto);
        status_produto = (Boolean) ((DataSnapshot)i.next()).getValue();
    }

    arrayAdapter.notifyDataSetChanged();
*/