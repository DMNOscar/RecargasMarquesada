package com.morpheus.ultrachip;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
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

import com.android.volley.Request;
import com.morpheus.ultrachip.Controlador.LoginDAO;
import com.morpheus.ultrachip.Herramientas.Constantes;
import com.morpheus.ultrachip.Herramientas.Peticion;
import com.morpheus.ultrachip.Herramientas.PreferencesLogin;
import com.morpheus.ultrachip.Modelo.Permiso;
import com.morpheus.ultrachip.Modelo.Usuario;

import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener
{
    private EditText edtUsuario, edtPass;
    private TextView txtVersion;
    private AppCompatButton btAceptar;
    private CheckBox chkDatos;
    private ProgressDialog progressDialog;
    private PreferencesLogin preferencesLogin;
    private List<Permiso> permisos;
    private Usuario usuario;
    private boolean hiloUsuario, hiloPermisos;

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
        progressDialog.setTitle("Cargando");
        progressDialog.setMessage("Un momento...");
        progressDialog.setCanceledOnTouchOutside(false);
        preferencesLogin = PreferencesLogin.getInstance(this);

        String version = getIntent().getStringExtra(Constantes.VERSION);
        txtVersion.setText("Versión: " + version != null ? version : "Desconocida");

        //Checa si hay datos guardados por default
        if(preferencesLogin.getUser() != null && preferencesLogin.getPass() != null)
        {
            edtUsuario.setText(preferencesLogin.getUser());
            edtPass.setText(preferencesLogin.getPass());

            chkDatos.setChecked(preferencesLogin.getUser().length() > 0 && preferencesLogin.getPass().length() > 0);
        }

        btAceptar.setOnClickListener(this);

        chequeoInternet();
    }

    @Override
    public void onClick(View view)
    {
        hiloUsuario = false;
        hiloPermisos = false;
        progressDialog.show();

        getPermisos();
        getUsuario();
    }

    //Metodo que obtiene la lista de permisos registrados
    private void getPermisos()
    {
        LoginDAO.getInstance().getPermisos(this, new Peticion.OnResultListListener<Permiso>()
        {
            @Override
            public void onSuccess(List<Permiso> result)
            {
                if(result != null)
                {
                    permisos = result;
                    hiloPermisos = true;
                    conexionHilos();
                }
                else
                {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "No existen los permisos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailed(String message, int code)
            {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, message + " " + code, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Metodo que obtiene los datos de usuario que se logea
    private void getUsuario()
    {
        Request request = LoginDAO.getInstance().getUsuario(this, edtUsuario.getText().toString().trim(), edtPass.getText().toString().trim(), new Peticion.OnResultElementListener<Usuario>()
        {
            @Override
            public void onSuccess(Usuario result)
            {
                if(result != null)
                {
                    usuario = result;
                    hiloUsuario = true;
                    conexionHilos();
                }
                else
                {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Verifique su usuario y contraseña", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailed(String message, int code)
            {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, message + " " + code, Toast.LENGTH_SHORT).show();
            }
        });

        //Cancela la peticion
        request.cancel();
        if(request.isCanceled())
        {
            Toast.makeText(this, "Peticion cancelada", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }
    }

    //Metodo que controla la entrada de los hilos de ejecucion
    private void conexionHilos()
    {
        if(hiloPermisos && hiloUsuario)
        {
            progressDialog.dismiss();
            guardarCredenciales(usuario);
            Intent intent = new Intent(this, InicialActivity.class);
            intent.putExtra(Constantes.USUARIO, usuario);
            startActivity(intent);
        }
    }

    //Metodo que permite guardar las credenciales por default
    private void guardarCredenciales(Usuario usuario)
    {
        if(chkDatos.isChecked())
            preferencesLogin.setValues(usuario.getCredencial().getNick(), usuario.getCredencial().getPass());
        else
            preferencesLogin.setValues("", "");
    }

    //Metodo que dispara la intencion del chequeo de internet
    private void chequeoInternet()
    {
        IntentFilter intentFilter = new IntentFilter(BroadcastNetwork.NETWORK_AVAILABLE_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(new BroadcastReceiver()
        {
            @Override
            public void onReceive(Context context, Intent intent)
            {
                boolean isNetworkAvailable = intent.getBooleanExtra(BroadcastNetwork.IS_NETWORK_AVAILABLE, false);
                String networkStatus = isNetworkAvailable ? "Conectado" : "Desconectado";

                edtUsuario.setText(networkStatus);

                Snackbar.make(findViewById(R.id.login), "Estado Conexión: " + networkStatus, Snackbar.LENGTH_LONG).show();
            }
        }, intentFilter);
    }
}
