package com.morpheus.ultrachip.Controlador;

import android.content.Context;
import android.util.Log;

import com.morpheus.ultrachip.Herramientas.Constantes;
import com.morpheus.ultrachip.Herramientas.Peticion;
import com.morpheus.ultrachip.Modelo.Credencial;
import com.morpheus.ultrachip.Modelo.Permiso;
import com.morpheus.ultrachip.Modelo.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginDAO
{
    private static LoginDAO dao;

    private LoginDAO()
    {
    }

    public static LoginDAO getInstance()
    {
        if(dao == null)
            dao = new LoginDAO();

        return dao;
    }

    //Permite obtener una lista de Permisos
    public void getPermisos(Context context, final Peticion.OnResultListListener<Permiso> listener)
    {
        String url = Constantes.HOST + "login/permisosService.php";

        Peticion.GET get = new Peticion.GET(context, url);
        get.getResponse(new Peticion.OnPeticionListener<String>()
        {
            @Override
            public void onSuccess(String respuesta)
            {
                List<Permiso> permisos = new ArrayList<>();

                try
                {
                    JSONArray jsonArray = new JSONArray(respuesta);
                    for(int i = 0; i < jsonArray.length(); i++)
                    {
                        JSONObject object = jsonArray.getJSONObject(i);
                        Permiso permiso = new Permiso(Integer.parseInt(object.getString("id")), object.getString("nombre"));
                        permisos.add(permiso);
                    }

                    listener.onSuccess(permisos);
                }catch (JSONException e)
                {
                    e.printStackTrace();
                    listener.onSuccess(null);
                }
            }

            @Override
            public void onFailed(String error, int respuestaHTTP)
            {
                listener.onFailed(error, respuestaHTTP);
            }
        });
    }

    //Permite obtener el usuario en caso de logeo exitoso
    public void getUsuario(Context context, final String nick, final String pass, final Peticion.OnResultElementListener<Usuario> listener)
    {
        String url = Constantes.HOST + "login/usuarioService.php";

        Map<String, String> params = new HashMap<>();
        params.put("nick", nick);
        params.put("pass", pass);

        Peticion.POST post = new Peticion.POST(context, url, params);
        post.getResponse(new Peticion.OnPeticionListener<String>()
        {
            @Override
            public void onSuccess(String respuesta)
            {
                try
                {
                    JSONObject object = new JSONObject(respuesta);
                    Credencial credencial = new Credencial(Integer.parseInt(object.getString("credencial_id")), nick, pass);
                    Permiso permiso = new Permiso(Integer.parseInt(object.getString("permiso_id")), object.getString("permiso"));
                    Usuario usuario = new Usuario(Integer.parseInt(object.getString("usuario_id")), object.getString("nombre"),
                            object.getString("telefono"), true, permiso, credencial);

                    listener.onSuccess(usuario);
                }catch (JSONException e)
                {
                    e.printStackTrace();
                    listener.onSuccess(null);
                }
            }

            @Override
            public void onFailed(String error, int respuestaHTTP)
            {
                listener.onFailed(error, respuestaHTTP);
            }
        });
    }
}
