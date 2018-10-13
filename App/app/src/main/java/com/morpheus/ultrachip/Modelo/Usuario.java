package com.morpheus.ultrachip.Modelo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Locale;

public class Usuario implements Parcelable
{
    private Integer id;
    private String nombre;
    private String telefono;
    private boolean activo;
    private Permiso permiso;
    private Credencial credencial;

    public Usuario(Integer id, String nombre, String telefono, boolean activo, Permiso permiso, Credencial credencial)
    {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.activo = activo;
        this.permiso = permiso;
        this.credencial = credencial;
    }

    public Usuario(String nombre, String telefono, boolean activo, Permiso permiso, Credencial credencial)
    {
        this.nombre = nombre;
        this.telefono = telefono;
        this.activo = activo;
        this.permiso = permiso;
        this.credencial = credencial;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getTelefono()
    {
        return telefono;
    }

    public void setTelefono(String telefono)
    {
        this.telefono = telefono;
    }

    public boolean isActivo()
    {
        return activo;
    }

    public void setActivo(boolean activo)
    {
        this.activo = activo;
    }

    public Permiso getPermiso()
    {
        return permiso;
    }

    public void setPermiso(Permiso permiso)
    {
        this.permiso = permiso;
    }

    public Credencial getCredencial()
    {
        return credencial;
    }

    public void setCredencial(Credencial credencial)
    {
        this.credencial = credencial;
    }

    @Override
    public String toString()
    {
        return String.format(Locale.getDefault(), "ID: %d, Nombre: %s, Telefono: %s, Activo: %s, Permiso: %s, Credencial: %s %s",
                getId() != null ? getId() : 0, getNombre(), getTelefono() != null ? getTelefono() : "S/R", isActivo() ? "Activo" : "Inactivo",
                getPermiso().getNombre(), getCredencial().getNick(), getCredencial().getPass());
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeValue(this.id);
        dest.writeString(this.nombre);
        dest.writeString(this.telefono);
        dest.writeByte(this.activo ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.permiso, flags);
        dest.writeParcelable(this.credencial, flags);
    }

    protected Usuario(Parcel in)
    {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.nombre = in.readString();
        this.telefono = in.readString();
        this.activo = in.readByte() != 0;
        this.permiso = in.readParcelable(Permiso.class.getClassLoader());
        this.credencial = in.readParcelable(Credencial.class.getClassLoader());
    }

    public static final Parcelable.Creator<Usuario> CREATOR = new Parcelable.Creator<Usuario>()
    {
        @Override
        public Usuario createFromParcel(Parcel source)
        {
            return new Usuario(source);
        }

        @Override
        public Usuario[] newArray(int size)
        {
            return new Usuario[size];
        }
    };
}
