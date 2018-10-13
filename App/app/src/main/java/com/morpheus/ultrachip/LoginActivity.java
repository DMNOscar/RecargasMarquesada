package com.morpheus.ultrachip;

import android.app.ProgressDialog;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.morpheus.ultrachip.Controlador.LoginDAO;
import com.morpheus.ultrachip.Herramientas.Constantes;
import com.morpheus.ultrachip.Herramientas.Peticion;
import com.morpheus.ultrachip.Herramientas.PreferencesLogin;
import com.morpheus.ultrachip.Modelo.Permiso;

import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener
{
    private EditText edtUsuario, edtPass;
    private TextView txtVersion;
    private AppCompatButton btAceptar;
    private CheckBox chkDatos;
    private ProgressDialog progressDialog;
    private PreferencesLogin preferencesLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        edtUsuario = findViewById(R.id.edtUser);
        edtPass = findViewById(R.id.edtPass);
        txtVersion = findViewById(R.id.txtVersion);
        chkDatos = findViewById(R.id.chkDatos);
        btAceptar = findViewById(R.id.btAceptar);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
        preferencesLogin = PreferencesLogin.getInstance(this);

        String version = getIntent().getStringExtra(Constantes.VERSION);
        txtVersion.setText("Versión: " + version != null ? version : "Desconocida");

        //Checa si hay datos guardados por default
        if(preferencesLogin.getUser() != null && preferencesLogin.getPass() != null)
        {
            edtUsuario.setText(preferencesLogin.getUser());
            edtPass.setText(preferencesLogin.getPass());

            //Selecciona el texto introducido
            edtUsuario.setSelection(edtUsuario.length());
            edtPass.setSelection(edtPass.length());

            chkDatos.setChecked(preferencesLogin.getUser().length() > 0 && preferencesLogin.getPass().length() > 0);
        }

        btAceptar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view)
    {

    }

    //Metodo que obtiene la lista de permisos registrados
    private synchronized void getPermisos()
    {
        LoginDAO.getInstance().getPermisos(this, new Peticion.OnResultListListener<Permiso>()
        {
            @Override
            public void onSuccess(List<Permiso> result)
            {
                for(Permiso permiso : result)
                    Log.i("permiso", permiso.toString());
            }

            @Override
            public void onFailed(String message, int code)
            {
                Toast.makeText(LoginActivity.this, message + " " + code, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
