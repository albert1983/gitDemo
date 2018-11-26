import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

public class Test {

	private final String appKey = "571e21dfc7cff5172a2ccec23bce3add";
	
	private final String appSecret = "fb8fb09c9d2c";
	
	
	private final String nonce = "12345";
	
	
	private final String createUrl = "https://api.netease.im/nimserver/user/create.action";
	
	
	private final String updateUserInfoUrl = "https://api.netease.im/nimserver/user/updateUinfo.action";
	
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		 
		Test test = new Test();
		test.updateUserInfo() ; 
		
	}

	public   boolean register() throws ClientProtocolException, IOException{
		
		DefaultHttpClient httpClient = new DefaultHttpClient();
		 
		HttpPost httpPost = new HttpPost(createUrl);
 
		String curTime = String.valueOf((new Date()).getTime() / 1000L);
		String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);// 参考
																					// 计算CheckSum的java代码

		// 设置请求的header
		httpPost.addHeader("AppKey", appKey);
		httpPost.addHeader("Nonce", nonce);
		httpPost.addHeader("CurTime", curTime);
		httpPost.addHeader("CheckSum", checkSum);
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

		// 设置请求的参数
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("accid", "zhudh"));
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));

		// 执行请求
		HttpResponse response = httpClient.execute(httpPost);

		// 打印执行结果
		//System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
		JSONObject obj = new JSONObject().parseObject(EntityUtils.toString(response.getEntity(), "utf-8"));  //将json字符串转换为json对象
		
		
		
		System.out.println(obj.get("code"));
		
		 
		
		return false;
	}
	
	public   boolean updateUserInfo() throws ClientProtocolException, IOException{
		
		DefaultHttpClient httpClient = new DefaultHttpClient();
		 
		HttpPost httpPost = new HttpPost(updateUserInfoUrl);
 
		String curTime = String.valueOf((new Date()).getTime() / 1000L);
		String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);// 参考
																					// 计算CheckSum的java代码

		// 设置请求的header
		httpPost.addHeader("AppKey", appKey);
		httpPost.addHeader("Nonce", nonce);
		httpPost.addHeader("CurTime", curTime);
		httpPost.addHeader("CheckSum", checkSum);
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

		// 设置请求的参数
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("accid", "zhudh"));
		
		nvps.add(new BasicNameValuePair("name", "朱东海"));
		
		
		 
		//BufferedImage image_buffer = ImageIO.read(new File("G:\\bb.jpg"));
        //String string_image=imgToBase64String(image_buffer);
		
        //image_buffer.toString();
		
		String imageUrl = "https://imgsrc.baidu.com/baike/pic/item/a8ec8a13632762d0d50d6e6cabec08fa513dc636.jpg";
		
		String imageUrl2 = "https://imgsrc.baidu.com/baike/pic/item/35a85edf8db1cb13dc1af6c0d654564e93584bf6.jpg";
		
		nvps.add(new BasicNameValuePair("icon", imageUrl  ));
		
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));

		// 执行请求
		HttpResponse response = httpClient.execute(httpPost);

		System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
		return false; 
	}
	
	public void postBroadcast(){
		
		DefaultHttpClient httpClient = new DefaultHttpClient();
		 
		HttpPost httpPost = new HttpPost(updateUserInfoUrl);
 
		String curTime = String.valueOf((new Date()).getTime() / 1000L);
		String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);// 参考
																					// 计算CheckSum的java代码

		// 设置请求的header
		httpPost.addHeader("AppKey", appKey);
		httpPost.addHeader("Nonce", nonce);
		httpPost.addHeader("CurTime", curTime);
		httpPost.addHeader("CheckSum", checkSum);
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

		// 设置请求的参数
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("accid", "zhudh")); 
		nvps.add(new BasicNameValuePair("name", "朱东海"));
		
		
		
		
	}
	
	
	
	
	
    //把图片转换成string类型
    public   String imgToBase64String(final RenderedImage img) {
        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ImageIO.write(img, "jpg", Base64.getEncoder().wrap(os));
            return os.toString(StandardCharsets.ISO_8859_1.name());
        } catch (final IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
    }	
    
    
    
    
    
}
