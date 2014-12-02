package com.farsunset.gmb.net;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.farsunset.gmb.bean.Comment;
import com.farsunset.gmb.bean.Product;
import com.farsunset.gmb.bean.Page;
import com.farsunset.gmb.bean.Response;
import com.farsunset.gmb.bean.UpdateInfo;
import com.farsunset.gmb.bean.User;
import com.farsunset.gmb.env.Constant;
import com.google.gson.Gson;
public class API {
	
	private final static String API_URL=Constant.SERVER_URL+"/api/";
    public static Page  getCommentList(String matterId,int pagenum) throws Exception
    {
    	ArrayList<Comment> commentList = new  ArrayList<Comment>();
    	Map<String,String> map = new HashMap<String,String>();
    	map.put("currentPage", String.valueOf(pagenum));
    	map.put("comment.matterId", matterId);
    	String json = httpPost(API_URL+"comment_queryJsonList.php",map);
    	 
    	Response response = new Gson().fromJson(json, Response.class);
    	 
    	if(response.getKey() !=0)
    	{
    		throw new IOException();
    	}
    	if(response.getPage().getDataList() != null)
    	{
    		for(Object o :response.getPage().getDataList())
        	{
        		HashMap<String,?> m = (HashMap<String,?>) o;
        		Comment c = new Comment();
        		c.alias = m.get("alias").toString();
        		c.commentId = m.get("commentId").toString();
        		c.content =m.get("content").toString();
        		c.userId = m.get("userId").toString();
        		c.matterId =m.get("matterId").toString();
        		c.rank = 	(int)Double.parseDouble(m.get("rank").toString())+"";
        		c.timestamp =m.get("timestamp").toString();
        		commentList.add(c);
        	} 
    	}
    	response.getPage().setDataList(commentList);
     
    	return response.getPage();
		 
    }
     
	public static String publishComment(Comment comment) throws ClientProtocolException, IOException, JSONException {
		Map<String,String> map = new HashMap<String,String>();
		comment.timestamp=String.valueOf(System.currentTimeMillis());
    	map.put("comment.userId", comment.userId);
    	map.put("comment.content", comment.content);
    	map.put("comment.alias", comment.alias);
    	map.put("comment.matterId", comment.matterId);
    	String json = httpPost(API_URL+"comment_publish.php",map);
    	JSONObject jsonObj = new JSONObject(json);
    	String  key = jsonObj.getString("key");
		return key;
	}
	public static User getUserByIMEI(String imei) throws JSONException, ClientProtocolException, IOException {
		Map<String,String> map = new HashMap<String,String>();
    	map.put("user.imei", imei);
    	String json = httpPost(API_URL+"user_getByIMEI.php",map);
    	/*JSONObject jsonObj = new JSONObject(json);
    	if(jsonObj.has("user")){
    		 User user = new User();
    		 user.alias=jsonObj.getJSONObject("user").getString("alias");
    		 user.userId=jsonObj.getJSONObject("user").getString("userId");
    		 user.imei=jsonObj.getJSONObject("user").getString("imei");
    		 return user;
		}*/
    	Response response = new Gson().fromJson(json, Response.class);
    	return response.getUser();
    	
     
	}
	 
	 
	
	 
	
	public static void sendFadeBack(String msg,String userId) throws ClientProtocolException, IOException, JSONException {
		Map<String,String> map = new HashMap<String,String>();
    	map.put("matter.userId", userId);
    	map.put("matter.content", msg);
    	String json = httpPost(API_URL+"matter_fadeBack.php",map);
    	JSONObject jsonObj = new JSONObject(json);
    	String  key = jsonObj.getString("key");
    	if(!key.equals("0"))
    	{
    		throw new IOException();
    	}
		
	}
	public static void vote(String matterId, String f) throws ClientProtocolException, IOException, JSONException {
		Map<String,String> map = new HashMap<String,String>();
    	map.put("matter.matterId", matterId);
    	map.put("key", f);
    	String json = httpPost(API_URL+"matter_doVote.php",map);
    	JSONObject jsonObj = new JSONObject(json);
    	String  key = jsonObj.getString("key");
    	if(!key.equals("0"))
    	{
    		throw new IOException();
    	}
	}
	
	public static UpdateInfo updateInfo() throws IOException, JSONException {
		
		UpdateInfo updateInfo = new UpdateInfo();
		Map<String,String> map = new HashMap<String,String>();
    	map.put("config.domain", "gmb");
    	String json = httpPost(API_URL+"config_queryConfig.php",map);
    	JSONObject jsonObj = new JSONObject(json);
    	String  key = jsonObj.getString("key");
    	if(!key.equals("0"))
    	{
    		throw new IOException();
    	}
		JSONArray array = jsonObj.getJSONArray("dataList");
		for(int i=0;i<array.length();i++)
		{
			JSONObject obj  = array.getJSONObject(i);
			if(obj.getString("key").equals("app_level"))
			{
				updateInfo.setNewlevel(Integer.parseInt(obj.getString("value")));
			}
			if(obj.getString("key").equals("app_url"))
			{
				updateInfo.setAppUrl(obj.getString("value"));
			}
			if(obj.getString("key").equals("app_version"))
			{
				updateInfo.setNewVersion(obj.getString("value"));
			}
			if(obj.getString("key").equals("updateMsg"))
			{
				updateInfo.setUpdateMsg(obj.getString("value"));
			}
		}
	 
		return updateInfo;
	}
	
	public static String httpPost(String url,Map<String,String> map) throws ClientProtocolException, IOException
	{
		
		 HttpPost httpPost = new HttpPost(url);  
         // 设置字符集  
		 List<NameValuePair> params = new ArrayList<NameValuePair>();
		 for(String key:map.keySet())
		 {
			 params.add(new BasicNameValuePair(key,map.get(key)));
		 }
         HttpEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);  
         // 设置参数实体  
         httpPost.setEntity(entity);  
         // 获取HttpClient对象  
         HttpClient httpClient = new DefaultHttpClient();  
         //连接超时  
         httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 30000);  
         //请求超时  
         httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 30000);  
 
         HttpResponse httpResp = httpClient.execute(httpPost);  
         String json = EntityUtils.toString(httpResp.getEntity(), "UTF-8");
         System.out.println(json);
         return json;
	}
    
	 
}
