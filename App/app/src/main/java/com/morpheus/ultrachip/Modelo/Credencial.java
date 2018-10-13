package com.morpheus.ultrachip.Modelo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Locale;

public class Credencial implements Parcelable
{
    private Integer id;
    private String nick;
    private String pass;

    public Credencial(Integer id, String nick, String pass)
    {
        this.id = id;
        this.nick = nick;
        this.pass = pass;
    }

    public Credencial(String nick, String pass)
    {
        this.nick = nick;
        this.pass = pass;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getNick()
    {
        return nick;
    }

    public void setNick(String nick)
    {
        this.nick = nick;
    }

    public String getPass()
    {
        return pass;
    }

    public void setPass(String pass)
    {
        this.pass = pass;
    }

    @Override
    public String toString()
    {
        return String.format(Locale.getDefault(), "ID: %d, Nick: %s, Pass: %s", getId() != null ? getId() : 0,
                getNick(), getPass());
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
        dest.writeString(this.nick);
        dest.writeString(this.pass);
    }

    protected Credencial(Parcel in)
    {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.nick = in.readString();
        this.pass = in.readString();
    }

    public static final Parcelable.Creator<Credencial> CREATOR = new Parcelable.Creator<Credencial>()
    {
        @Override
        public Credencial createFromParcel(Parcel source)
        {
            return new Credencial(source);
        }

        @Override
        public Credencial[] newArray(int size)
        {
            return new Credencial[size];
        }
    };
}
