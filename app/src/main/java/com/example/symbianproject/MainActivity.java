package com.example.symbianproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {

    private EditText txtNome;
    private EditText txtSobrenome;
    private Button btnCadastrarUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNome = findViewById(R.id.registerUserName);
        txtSobrenome = findViewById(R.id.registerUserLastName);
        btnCadastrarUsuario = findViewById(R.id.registerButtonUser);
        Validate validate = new Validate();

        btnCadastrarUsuario.setOnClickListener(view -> {
            if (!validate.nameValidate(txtNome, txtSobrenome)) {
                Toast.makeText(this, "Preencha todos os campos!",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.cadastrar))
                    .setMessage("Certeza que deseja cadstrar?")
                    .setPositiveButton(R.string.cadUsuario, (dialog1, wich) -> {

                        String sNome = txtNome.getText().toString();
                        String sSobrenome = txtSobrenome.getText().toString();

                        int cadastroUsuario = (int) SQLHelper.getInstance(this).addUser(sNome, sSobrenome);

                        if (cadastroUsuario > 0) {

                            Intent intent = new Intent(this, EnderecoActivity.class);
                            intent.putExtra("COD_USUARIO", cadastroUsuario);
                            startActivity(intent);
                            Toast.makeText(this,
                            R.string.cadastro_ok,
                            Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(this,
                            R.string.cadastro_erro,
                            Toast.LENGTH_LONG).show();
                        }
                    }).setNegativeButton(R.string.cancelar, (dialog1, wich) -> {}).create();
            dialog.show();
        });
    }
}