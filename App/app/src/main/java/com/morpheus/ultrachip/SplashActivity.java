package com.morpheus.ultrachip;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.morpheus.ultrachip.Herramientas.Constantes;

public class SplashActivity extends AppCompatActivity
{
    private ImageView imgLogo;
    private TextView txtCorreo, txtDerechos, txtVersion;
    private LinearLayout contenedorSplash;
    private String version;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Cambia el color de la barra de acciones
        getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimary));

        imgLogo = findViewById(R.id.imgLogo);
        txtCorreo = findViewById(R.id.txtCorreo);
        txtDerechos = findViewById(R.id.txtDerechos);
        txtVersion = findViewById(R.id.txtVersion);
        contenedorSplash = findViewById(R.id.contenedorSplash);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animacion_splash);
        contenedorSplash.startAnimation(animation);

        //Carga la versión de la app
        cargaVersion();

        //Hilo que muestra la pantalla de inicio
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                iniciarActivity();
            }
        }, 3000);
    }

    private void cargaVersion()
    {
        try
        {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), 0);
            version = info.versionName;
            txtVersion.setText("Versión: " + version);
        }catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    private void iniciarActivity()
    {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        intent.putExtra(Constantes.VERSION, version);

        //Arranca la actividad de login
        startActivity(intent);
        finish();
    }
}
