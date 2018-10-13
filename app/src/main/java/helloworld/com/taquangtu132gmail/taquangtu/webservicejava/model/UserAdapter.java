package helloworld.com.taquangtu132gmail.taquangtu.webservicejava.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import helloworld.com.taquangtu132gmail.taquangtu.webservicejava.R;

public class UserAdapter extends BaseAdapter {
    private Context mContext;
    private int mIdResourceLayout;
    private List<User> userList;

    public UserAdapter(Context mContext, int mIdResourceLayout, List<User> userList)
    {
        this.mContext = mContext;
        this.mIdResourceLayout = mIdResourceLayout;
        this.userList = userList;
    }
    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int i) {
        return userList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return userList.get(i).getmId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        ViewHolder viewHolder = null;
        if(view==null)
        {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(mIdResourceLayout,null);
            viewHolder = new ViewHolder();
            viewHolder.mTextViewName = view.findViewById(R.id.tv_name);
            viewHolder.mTextViewId   = view.findViewById(R.id.tv_id);
            view.setTag(viewHolder);
        }
        else
        {
                viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.mTextViewId.setText("Id: "+userList.get(i).getmId());
        viewHolder.mTextViewName.setText("Name: "+userList.get(i).getmName());
        return view;
    }
    private class ViewHolder
    {
        TextView mTextViewName,mTextViewId;
    }
}
