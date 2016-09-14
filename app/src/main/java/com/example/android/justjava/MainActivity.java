// ola

package com.example.android.justjava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    private String nome;
    private Boolean status=true;
    private EditText nomeProduto;
    private Button botao_listar;

    DatabaseReference dataBase = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myRef = dataBase.child("ProdutosNovo");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nomeProduto = (EditText) findViewById(R.id.nome_produto);
        botao_listar= (Button) findViewById(R.id.botao_listar);

        botao_listar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(MainActivity.this,
                        listarProdutos.class);

                startActivity(intent);

                finish();
            }
        });


    }


    public void onRadioButtonClicked(View view) {
        // Obtem o botao q esta ativo
        boolean checked = ((RadioButton) view).isChecked();

        // Verifica qual botï¿½o foi pego, pelo id
        switch (view.getId()) {
            case R.id.Tem:
                if (checked)
                    // Altera o status do ingrediente para 1/tem;
                    status = true;
                break;
            case R.id.NaoTem:
                if (checked)
                    // Altera o status do ingrediente para 0/nï¿½o tem;
                    status = false;
                break;
        }
    }


    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        nome = nomeProduto.getText().toString();
        Produto p = new Produto(nome,status);
        // Push the chat message to the database
        myRef.push().setValue(p);



    }




}