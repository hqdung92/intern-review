package intership.dev.contact;

import android.widget.ImageView;

import java.io.Serializable;

public class Contacts implements Serializable {
    private String mName;
    private String mDecription;
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

    public Contacts(String mName,String mDecription, int mAvata) {
        this.mName = mName;
        this.mDecription = mDecription;
        this.mAvata = mAvata;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDecription() {
        return mDecription;
    }

    public void setmDecription(String mDecription) {
        this.mDecription = mDecription;
    }

    public int getmAvata() {
        return mAvata;
    }

    public void setmAvata(int mAvata) {
        this.mAvata = mAvata;
    }
}
