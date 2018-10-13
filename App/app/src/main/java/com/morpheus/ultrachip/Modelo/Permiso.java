package com.morpheus.ultrachip.Modelo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Locale;

public class Permiso implements Parcelable
{
    private Integer id;
    private String nombre;

    public Permiso(Integer id, String nombre)
    {
        this.id = id;
        this.nombre = nombre;
    }

    public Permiso(String nombre)
    {
        this.nombre = nombre;
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

    @Override
    public String toString()
    {
        return String.format(Locale.getDefault(), "ID: %d, Nombre: %s", getId() != null ? getId() : 0, getNombre());
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
    }

    protected Permiso(Parcel in)
    {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.nombre = in.readString();
    }

    public static final Parcelable.Creator<Permiso> CREATOR = new Parcelable.Creator<Permiso>()
    {
        @Override
        public Permiso createFromParcel(Parcel source)
        {
            return new Permiso(source);
        }

        @Override
        public Permiso[] newArray(int size)
        {
            return new Permiso[size];
        }
    };
}
