package com.example.dllo.volleydemo;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    /**
     * Volley 的意思是齐射,他的优点是:
     * Volley 网络请求框架 轻量级 用于购物 游戏 新闻 更快的App
     * Volley 内部封装了Http和Connection和Thread简化开发代码
     * Volley 内部封装了线程池 帮助我们管理网络请求线程
     * 体现给我们的是一个网络请求队列 会将我们发出的网络请求在队列中排队 根据CPU是否空闲 来安排执行的时间
     * 同步和异步的区别 你不答应我 你不跟我去 我就一直等着 直到你跟着我一起去
     * 异步 我叫你去吃饭 我喊了一嗓子去吃饭了,我就去了 不管你去不去 什么时候去
     * ANR Application Not Responsed 应用没有响应 时间是3-5秒
     * @param savedInstanceState
     */

    // 1定义请求队列
            private ImageView iv;
    private RequestQueue queue;
    private String url = "http://api.liwushuo.com/v2/channels/131/items?limit=20&offset=0&gender=2&generation=1";
    private String imgUrl = "http://www2.autoimg.cn/newsdfs/g20/M08/AD/B5/640x320_0_autohomecar__wKgFVFeESL-Aaw3iAAdNNkkYEoU460.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  iv = (ImageView) findViewById(R.id.iv);

        // 2初始化请求队列
     //   queue = Volley.newRequestQueue(MainActivity.this);
//
//        // Volley提供了3种请求方式
//        // JSONObjectRequest StringRequset和ImageRequest
//        // json数据请求 字符串数据请求 图片数据请求
//
//        //创建网络请求对象
//
//        // 参数1:请求的URL的网址
//        // 参数2:请求成功的回调方法
//        // 参数3:请求失败的回调方法
//        StringRequest sr = new StringRequest(url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
//                // 解析网络数据
//                Gson gson = new Gson();
//                // 解析
//                MyBean dataBean = gson.fromJson(response,MyBean.class);
//                List<MyBean.DataBean.ItemsBean> datas = dataBean.getData().getItems();
//                for (int i = 0; i < datas.size(); i++) {
//                    Log.d("MainActivity", datas.get(i).getShort_title());
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        //4 将请求加入请求队列
//        queue.add(sr);
//

//        // 二 JSONObjectRequest的使用
//        JsonObjectRequest jor = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                String str = response.toString();
//                Toast.makeText(MainActivity.this,str, Toast.LENGTH_SHORT).show();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        // 加入请求
//        queue.add(jor);
        // 三 图片请求 ImageRequest


        // 使用单例后的volley
//        VolleyInstance.getInstance(this).startRequest(url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });


       VolleyInstance.getInstance(this).stratRequest(url, new VolleyResult() {
           @Override
           public void success(String str) {
               Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
           }

           @Override
           public void failure() {

           }
       });

   }
}
