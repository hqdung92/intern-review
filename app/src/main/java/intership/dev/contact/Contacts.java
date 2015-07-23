package intership.dev.contact;

import android.widget.ImageView;

import java.io.Serializable;

public class Contacts implements Serializable {
    private String mName;
    private int mAvata;
    private ImageView mImageEdit;
    private ImageView mIamgeDelete;

    public ImageView getmImageEdit() {
        return mImageEdit;
    }

    public void setmImageEdit(ImageView mImageEdit) {
        this.mImageEdit = mImageEdit;
    }

    public ImageView getmIamgeDelete() {
        return mIamgeDelete;
    }

    public void setmIamgeDelete(ImageView mIamgeDelete) {
        this.mIamgeDelete = mIamgeDelete;
    }

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
