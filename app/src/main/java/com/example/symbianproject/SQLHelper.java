package com.example.symbianproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SQLHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "db_symbian";
    private static final int DB_VERSION = 1;
    private static  SQLHelper INSTANCE;


    public static SQLHelper getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new SQLHelper(context);
        }
        return INSTANCE;
    }

    public SQLHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(@NonNull SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table tbl_usuario " +
                "(cod_usuario INTEGER NOT NULL primary key, " +
                "nome varchar(500), " +
                "sobrenome varchar(500));");

        sqLiteDatabase.execSQL("create table tbl_endereco " +
                "(cod_endereco INTEGER not null primary key, " +
                "cod_usuario INTEGER, " +
                "cep varchar(10), " +
                "numero varchar(10), " +
                "complemento varchar(500), " +
                "foreign key (cod_usuario) references tbl_usuario(cod_usuario));");
    }

    @Override
    public void onUpgrade(@NonNull SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public int addUser(String nome, String sobrenome){
        System.out.println("Pq não tá dando certo?");
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        try{

            sqLiteDatabase.beginTransaction();

            ContentValues values = new ContentValues();

            values.put("nome", nome);
            values.put("sobrenome", sobrenome);

            int codUsuario = (int) sqLiteDatabase.insertOrThrow("tbl_usuario",
                    null,
                    values);
            sqLiteDatabase.setTransactionSuccessful();

            return codUsuario;


        }catch (Exception e){

            Log.d("SQLERRO", e.getMessage());
            return 0;

        }finally {

            if(sqLiteDatabase.isOpen()){
                sqLiteDatabase.endTransaction();
            }

        }

    }

    public boolean addAdress(int cod_usuario, String cep, String numero, String complemento){

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        try{

            sqLiteDatabase.beginTransaction();

            ContentValues values = new ContentValues();

            values.put("cod_usuario", cod_usuario);
            values.put("cep", cep);
            values.put("numero", numero);
            values.put("complemento", complemento);

            sqLiteDatabase.insertOrThrow("tbl_endereco",
                    null,
                    values);
            sqLiteDatabase.setTransactionSuccessful();

            return true;

        }catch (Exception e){

            Log.d("SQLERRO", e.getMessage());
            return false;

        }finally {

            if(sqLiteDatabase.isOpen()){
                sqLiteDatabase.endTransaction();
            }

        }

    }

}
