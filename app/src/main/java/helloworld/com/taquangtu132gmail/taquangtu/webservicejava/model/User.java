package helloworld.com.taquangtu132gmail.taquangtu.webservicejava.model;

public class User
{
    private int mId;
    private String mName;

    public User(int mId, String mName) {
        this.mId = mId;
        this.mName = mName;
    }
    public int getmId() {
        return mId;
    }
    public void setmId(int mId) {
        this.mId = mId;
    }
    public String getmName() {
        return mName;
    }
    public void setmName(String mName) {
        this.mName = mName;
    }
}
