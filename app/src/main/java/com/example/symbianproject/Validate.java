package com.example.symbianproject;

import android.widget.EditText;

public class Validate {
    public boolean nameValidate(EditText txtNome, EditText txtSobrenome) {
        return (
                !txtNome.getText().toString().isEmpty() &&
                !txtSobrenome.getText().toString().isEmpty()
        );
    }

    public boolean adressLengthValidate(EditText txtNumero, EditText txtCEP, EditText txtComplemento) {
        if(txtNumero.getText().toString().length() <= 10 && txtCEP.getText().toString().length() <= 10 && txtComplemento.getText().toString().length() > 500) {
            return false;
        } else {
            return true;
        }
    }

    public boolean cepValidate(EditText txtCEP) {
        return (
                !txtCEP.getText().toString().isEmpty()
        );
    }

    public String numberValidate(EditText txtNumber) {
        if (txtNumber.getText().toString().isEmpty() || txtNumber.getText().toString().equals(" ")) {
            return "SN";
        } else {
            return txtNumber.getText().toString();
        }
    }
}
