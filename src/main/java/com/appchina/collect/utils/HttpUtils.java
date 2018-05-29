package com.appchina.collect.utils;

import com.appchina.collect.domain.CategoryDownloadRank;
import net.sf.json.JSONArray;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liujianmeng on 2017/12/29.
 */
public class HttpUtils {

    public static void main(String[] args) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String url = "http://chassis.appchina.com:8080/chassis/api/category_rank/add";
        HttpPost httpPost = new HttpPost(url);
        List<CategoryDownloadRank> list = new ArrayList<CategoryDownloadRank>();
        for (int i = 0;i<800;i++){
            CategoryDownloadRank categoryDownloadRank = new CategoryDownloadRank();
            categoryDownloadRank.setCategoryId(411);
            categoryDownloadRank.setPackageName("com.SoloDev.Maybe");
            categoryDownloadRank.setRank(i);
            list.add(categoryDownloadRank);
        }

        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        String json =  JSONArray.fromObject(list).toString();
        System.out.println(json);
        nvps.add(new BasicNameValuePair("params",  json));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(httpPost);
            // 5.解析接口返回值
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "UTF-8");
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
