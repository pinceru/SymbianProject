package com.example.symbianproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EnderecoActivity extends AppCompatActivity {

    private EditText txtCEP;
    private EditText txtNumero;
    private EditText txtComplemento;
    private Button btnCadastrarEndereco;
    private int codUsuario;
    private String sComplemento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endereco);

        txtCEP = findViewById(R.id.registerAdressCEP);
        txtNumero = findViewById(R.id.registerAdressNumber);
        txtComplemento = findViewById(R.id.registerAdressComplement);
        btnCadastrarEndereco = findViewById(R.id.registerButton);
        Validate validate = new Validate();

        btnCadastrarEndereco.setOnClickListener(view -> {
            if (!validate.cepValidate(txtCEP)) {
                Toast.makeText(this, "Preencha o campo do CEP!",
                        Toast.LENGTH_SHORT).show();
                if(validate.adressLengthValidate(txtNumero, txtCEP, txtComplemento)) {
                    return;
                } else {
                    Toast.makeText(this, "Algum campo excedeu o limite de caracteres",
                            Toast.LENGTH_SHORT).show();
                }
            }

            AlertDialog dialog = new AlertDialog.Builder(this).setTitle(getString(R.string.cadastrar))
                    .setMessage("CERTEZA QUE DESEJA CADASTRAR?")
                    .setPositiveButton("SALVAR", (dialog1, wich) -> {
                        String sCEP = txtCEP.getText().toString();
                        String sNumero = validate.numberValidate(txtNumero);
                        if(txtComplemento.getText().toString().isEmpty()){
                            sComplemento = "Não tem complemento.";
                        } else {
                            sComplemento = txtComplemento.getText().toString();
                        }
                        if(getIntent().hasExtra("COD_USUARIO")){
                            Bundle extras = getIntent().getExtras();
                            this.codUsuario = extras.getInt("COD_USUARIO");
                        }
                        boolean cadastroEndereco = SQLHelper.getInstance(this).addAdress(codUsuario, sCEP, sNumero, sComplemento);

                        if (cadastroEndereco) {
                            Toast.makeText(this,
                                    R.string.cadastro_ok,
                                    Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(this,
                                    R.string.cadastro_erro,
                                    Toast.LENGTH_LONG).show();
                        }
                    })
                    .setNegativeButton(R.string.cancelar, (dialog1, wich)->{})
                    .create();
            dialog.show();
        });
    }
}