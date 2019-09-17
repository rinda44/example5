package example.myapplication23;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by shaofa on 2017/12/9.
 */

public class RdTabBarAdapter extends BaseAdapter
{
    Context context;
    LayoutInflater inflater;

    // 颜色也字体
    int colorNormal = Color.argb(0xFF, 0x44, 0x44, 0x44);
    int colorActive = Color.argb(0xFF, 0x00, 0xFF, 0x00);

    // 标签项数据
    ArrayList<Item> listData = new ArrayList();

    public RdTabBarAdapter(Context context)
    {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    // 添加1项
    public void addItem(Item it)
    {
        listData.add(it);
    }
    // 添加多项
    public void addItems(Item[] items)
    {
        for(Item it:items) listData.add(it);
    }
    // 设置选中项
    public void setActive(int position, boolean update)
    {
        for(int i=0; i<listData.size(); i++)
        {
            Item it = listData.get(i);
            if(i == position)
                it.active = true;
            else
                it.active = false;
        }
        if(update) notifyDataSetChanged();
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
            convertView = inflater.inflate(R.layout.rd_tab_bar_item, parent, false);
        }

        // 获取/显示数据
        Item it = (Item) getItem(position);
        TextView label = (TextView) convertView.findViewById(R.id.id_tabbar_label);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.id_tabbar_icon);

        label.setText( it.label);

        if (it.active)
        {
            // 选中状态
            if (it.label.length() > 0)
                label.setTextColor(colorActive);
            if (it.iconActive != null)
                imageView.setImageDrawable( it.iconActive);
            else // iconActive未设置,则显示iconNormal
                imageView.setImageDrawable( it.iconNormal);
        } else
        {
            // 普通状态
            if (it.label.length() > 0)
                label.setTextColor(colorNormal);
            if (it.iconNormal != null)
                imageView.setImageDrawable( it.iconNormal);
        }

        return convertView;
    }

    ///////////////////////////////
    public static class Item
    {
        String label;  // 标签显示
        String value;   // 关联的数据
        public Drawable iconNormal; // 图标
        public Drawable iconActive; // 选中高亮
        public boolean active = false; // 是否选中状态

        public Item()
        {
        }

        public Item(String label, String value)
        {
            this.label = label;
            this.value = value;
        }
        public Item(String label, String value, Drawable iconNormal, Drawable iconActive)
        {
            this.label = label;
            this.value = value;
            this.iconNormal = iconNormal;
            this.iconActive = iconActive;
        }
    }
}
