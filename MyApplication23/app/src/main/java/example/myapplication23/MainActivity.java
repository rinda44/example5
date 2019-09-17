package example.myapplication23;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity
{
    Fragment[] pages = new Fragment[3];
    RdTabBarAdapter rdTabBarAdapter;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pages[0] = new MsgFragment();
        pages[1] = new CCFragment();
        pages[2] = new UserFragment();

        //给ViewPager设置Adapter
        FragmentPagerAdapter pagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager)findViewById(R.id.id_viewpager);
        viewPager.setAdapter(pagerAdapter);

        //标签栏内容
        RdTabBarAdapter.Item[] labels = new RdTabBarAdapter.Item[3];
        labels[0] = new RdTabBarAdapter.Item("微信","msg");
        labels[0].iconNormal = getDrawable(R.drawable.ic_msg_normal);
        labels[0].iconActive = getDrawable(R.drawable.ic_msg_active);
        labels[1] = new RdTabBarAdapter.Item("发现","cc");
        labels[1].iconNormal = getDrawable(R.drawable.ic_find_normal);
        labels[1].iconActive = getDrawable(R.drawable.ic_find_active);
        labels[2] = new RdTabBarAdapter.Item("我","user");
        labels[2].iconNormal = getDrawable(R.drawable.ic_user_normal);
        labels[2].iconActive = getDrawable(R.drawable.ic_user_active);
        rdTabBarAdapter = new RdTabBarAdapter(this);
        rdTabBarAdapter.addItems(labels);

        GridView gridView = (GridView)findViewById(R.id.id_gridview);
        gridView.setAdapter(rdTabBarAdapter);
        gridView.setNumColumns(labels.length);  //列
        rdTabBarAdapter.setActive(0, true);  //默认选中第一页

        //监听器，当点击标签页时，显示对应的页
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                viewPager.setCurrentItem(position);
                rdTabBarAdapter.setActive(position, true);
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {

            }

            @Override
            public void onPageSelected(int position)
            {
                rdTabBarAdapter.setActive(position, true);
            }

            @Override
            public void onPageScrollStateChanged(int state)
            {

            }
        });

    }

    private class MyViewPagerAdapter extends FragmentPagerAdapter
    {
        public MyViewPagerAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            return pages[position];
        }

        @Override
        public int getCount()
        {
            return pages.length;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        //分发给子fragment处理
        for(Fragment f: pages)
        {
            f.onActivityResult(requestCode, resultCode, data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
