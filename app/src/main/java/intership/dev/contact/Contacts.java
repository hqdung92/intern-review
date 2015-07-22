package intership.dev.contact;

import java.io.Serializable;

public class Contacts implements Serializable{
    private String mName;
    private int mAvata;

    public Contacts(String mName, int mAvata) {
        this.mName = mName;
        this.mAvata = mAvata;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getmAvata() {
        return mAvata;
    }

    public void setmAvata(int mAvata) {
        this.mAvata = mAvata;
    }
}
