package com.example.maxixi.yuanqu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;


public class diancan extends AppCompatActivity {



    private StickyListHeadersListView lv;
    private HeadAdapter adapter;
    private List<ItemBeam> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ectivity_diancan);

        Toolbar toolbar=(Toolbar)findViewById(R.id.diancan_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        lv = (StickyListHeadersListView) findViewById(R.id.lv);
        //添加数据


        list = new ArrayList<ItemBeam>();
        for (int i = 0; i < 10; i++)
        {
            ItemBeam bean = new ItemBeam();
            bean.setValue("bb");
            list.add(bean);
        }
        for (int i = 0; i < 10; i++)
        {
            ItemBeam bean = new ItemBeam();
            bean.setValue("aa");
            list.add(bean);
        }
        for (int i = 0; i < 13; i++)
        {
            ItemBeam bean = new ItemBeam();
            bean.setValue("dd");
            list.add(bean);
        }
        for (int i = 0; i < 17; i++)
        {
            ItemBeam bean = new ItemBeam();
            bean.setValue("cc");
            list.add(bean);
        }


        //设置id，为了能够获取head的id
        int headId = 0;
        Map<String, Integer> map = new HashMap<>();
        for (ItemBeam bean2 : list)
        {
            String value = bean2.getValue();
            if (!map.containsKey(value))//如果这个实体对象在map里面没有这个value对应的key，就给实体对象
            {                           //设置id，并存到map中
                bean2.setId(headId);
                map.put(value, headId);
                headId++;
            } else
            {//如果有就直接设置id，这样不同的实体对象就对应不同的id
                bean2.setId(map.get(value));
            }
        }
        //排序
        Collections.sort(list, new Comparator<ItemBeam>()
        {
            @Override
            public int compare(ItemBeam lhs, ItemBeam rhs)
            {
                return lhs.getValue().compareTo(rhs.getValue());
            }
        });
        adapter = new HeadAdapter(this, list);
        lv.setAdapter(adapter);


        ArrayAdapter<String>adapter=new ArrayAdapter<>(
                diancan.this,android.R.layout.simple_list_item_1,data
        );
        ListView listView=(ListView) findViewById(R.id.lvlv);
        listView.setAdapter(adapter);





    }

    private String[] data={"apple","banana","orange"};

}
