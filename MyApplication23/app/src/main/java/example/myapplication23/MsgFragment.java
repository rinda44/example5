package example.myapplication23;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MsgFragment extends Fragment
{
    View contentView;
    MyListAdapter listAdapter;
    ArrayList<MyListItem> listData = new ArrayList();

    public MsgFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // 创建界面
        contentView = inflater.inflate(R.layout.fragment_msg, container, false);

        // 初始化ListView
        listAdapter = new MyListAdapter();
        ListView listView = (ListView) contentView.findViewById(R.id.id_listview);
        listView.setAdapter(listAdapter); // 设置适配器

        return contentView;
    }

    @Override
    public void onDestroyView()
    {
        listData.clear();
        super.onDestroyView();
    }

    @Override
    public void onStart()
    {
        super.onStart();
        demo();
    }

    // 演示数据
    private void demo()
    {
        MyListItem it;
        it = new MyListItem();
        it.text = "坚持练习，保持热爱，时间会报答你的努力。";
        listData.add(it);

        it = new MyListItem();
        it.text = "编程是一门职业技术，不要以混过考试的目的来学习编程。";
        listData.add(it);

        it = new MyListItem();
        it.text = "必须坚持每天都学都练，三天打渔两天晒网是学不会的。";
        listData.add(it);

        listAdapter.notifyDataSetChanged();


    }

    private static class MyListItem
    {
        public String text;
    }

    private class MyListAdapter extends BaseAdapter
    {
        LayoutInflater inflater;   //注意

        public MyListAdapter()
        {
            // MsgFragment.this.getContext()获取Context（上下文对象）
            inflater = LayoutInflater.from(getContext());
        }

        @Override
        public int getCount()
        {
            return listData.size();
        }

        @Override
        public Object getItem(int position)
        {
            return listData.get(position);
        }

        @Override
        public long getItemId(int position)
        {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            // 创建控件
            if (convertView == null)
            {
                convertView = inflater.inflate(R.layout.list_item_msg, parent, false);
            }

            // 获取/显示数据
            MyListItem it = (MyListItem) getItem(position);
            ((TextView) convertView.findViewById(R.id.id_item_text)).setText(it.text);

            return convertView;
        }
    }
}
