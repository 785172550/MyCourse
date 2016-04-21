package com.mycourse;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.example.mycourse.R;
import com.mycourse.base.BaseActivity;
import com.mycourse.data.CourseInfo;
import com.mycourse.util.CourseDataHelper;
import com.mycourse.util.CourseSQLiteUtils;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ImportCourseActivity extends BaseActivity {

	String htmlpath;
	DefaultHttpClient httpClient;
	ArrayList<CourseInfo> courseInfos = new ArrayList<CourseInfo>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_import);
		
		htmlpath = "http://202.115.47.141/loginAction.do";
		httpClient = new DefaultHttpClient();
		initView();
	}
	
	public void initView() {
		initTopBarForLeft("导入课表");		
		Button importbButton = (Button) findViewById(R.id.importB);
		importbButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				login(httpClient);									
			}
		});
	
	/*	Intent mIntent = new Intent();		
	    mIntent.putExtra("key",(Serializable)courseInfos);	
		//resultCode
		this.setResult(2, mIntent);*/
			
	}
	
	//获取登录的账号密码，抓取页面信息
	public void login(final HttpClient httpClient)
	{
		EditText uername = (EditText) findViewById(R.id.editText1);
		EditText password = (EditText) findViewById(R.id.editText2);
		
		final String nameString = uername.getText().toString();
		final String passString = password.getText().toString();
		
		new Thread(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				
				params.add(new BasicNameValuePair("zjh", nameString));
				params.add(new BasicNameValuePair("mm", passString));		
								
				try {
												
					HttpPost post = new HttpPost(htmlpath);
					post.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));
					
					/*
					 * 用post请求得到返回的 response
					 * post 中含有 账号密码信息  GBK格式
					 */
					
					HttpResponse mHttpResponse = httpClient.execute(post);
									
					if (mHttpResponse.getStatusLine().getStatusCode() == 200)								
					{
						
						HttpEntity entity = mHttpResponse.getEntity();
						
						String msg = EntityUtils.toString(entity,HTTP.UTF_8);
											
						System.out.println(msg);	
						System.out.println("----------------------2");
												
						//System.out.println(htmlpath);
						//学校教务处  本学期课表的地址
						HttpGet get = new HttpGet("http://202.115.47.141//xkAction.do?actionType=6");						
						HttpResponse r2 = httpClient.execute(get);
						HttpEntity entity2 = r2.getEntity();
						//获取课程网页信息
						String msg2 = EntityUtils.toString(entity2,HTTP.UTF_8);																								
						jiexi(msg2);
						//System.out.println(jiexi(msg2));
						
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally {  
			        // 关闭请求  
			   //     httpClient.getConnectionManager().shutdown();  
			    }  
			}
				
		}.start();
	}
	
	// 解析页面信息
	public String jiexi (String html) throws IOException {
		
		org.jsoup.nodes.Document doc = Jsoup.parse(html);		
		//System.out.println(doc.html());					
		Elements links = doc.select("tr.odd"); // 具有href 属性的链接		
		String name = null;
		//String teacher = null;
		int week = 0;
		int time[] =new int [3];
		String adress = null;
					
		for (Element link : links) {		
			try {				
				//period = link.childNode(27).toString();	
				name = getTrueValue(link.childNode(5).toString());
				//teacher = getTrueValue(link.childNode(15).toString());
				adress = getTrueValue(link.childNode(29).toString())+getTrueValue(link.childNode(31).toString())
						+getTrueValue(link.childNode(33).toString());					
				week = Integer.parseInt(getTrueValue(link.childNode(25).toString()));			
				time = findperiod(getTrueValue2(link.childNode(27).toString()));							

				CourseInfo courseInfo = new CourseInfo(name,adress,week,time[2],time[0]);
				courseInfos.add(courseInfo);
				System.out.println(courseInfo.name+courseInfo.start+"#####"+courseInfo.Period);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}	
		
		if(!courseInfos.isEmpty())
		{
			System.out.println("课表导入成功");
			ShowToast("课表导入成功");
			//建立数据库添加数据 , 第二次导入时清空以前数据
			CourseSQLiteUtils sql = new CourseSQLiteUtils();
			CourseDataHelper dbHelper = sql.createCourseDataHelper(ImportCourseActivity.this);
			sql.deleteall(dbHelper);				
			for(CourseInfo courseInfo : courseInfos)
			{
				sql.insert(dbHelper, courseInfo);
			}
		}
		System.out.println("-------------");
		return name;
	}
	 	   
	/* 去除标签</td>，获取标签里的值  */
	public String getTrueValue(String str) {
		
		Pattern p = Pattern.compile("&nbsp; (.+?)</td>");
		Matcher m = p.matcher(str); 
		String res = null;
		  if(m.find())
		  {
			  res = m.group(1);
		  }
		return res;
	}
	
    public String getTrueValue2(String str) {
		
		Pattern p = Pattern.compile("&nbsp;(.+?)</td>");
		Matcher m = p.matcher(str); 
		String res = null;
		  if(m.find())
		  {
			  res = m.group(1);
		  }
		return res;
	}
	
    
   /* 起始时间   终止时间   持续时间
	 */
	public int[] findperiod(String str)
	{
		String start=null,end=null;
		// 起始时间 、终止时间 、持续时间
		int time[] = new int[3];
		
		//（.+）是贪婪，尽可能多的匹配 (.+?)是非贪婪，尽可能少的匹配
		Pattern p1 = Pattern.compile("(.+)~");
		Matcher m1 = p1.matcher(str); 
		
		if(m1.find())
		{
			start = m1.group(1);
		}
		Pattern p2 = Pattern.compile("~(.+)");
		Matcher m2 = p2.matcher(str);
		if(m2.find())
		{
			end = m2.group(1);
		}
		time[0] = Integer.parseInt(start);
		time[1] = Integer.parseInt(end);
		time[2] = time[1] - time[0]+1;
		
		return time;				
	}
	
	
}
