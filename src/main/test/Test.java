import java.nio.charset.Charset;
import java.util.Base64;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.Application;
import com.alibaba.fastjson.JSONObject;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class Test
{
	@Autowired
	LoadBalancerClient	loadBalancerClient;
	@Autowired
	RestTemplate		restTemplate;

	@org.junit.Test
	public void todo()
	{
		// System.out.println("hi");
		// ServiceInstance serviceInstance = loadBalancerClient.choose("eureka-client");
		// String url = "http://" + serviceInstance.getHost() + ":" +
		// serviceInstance.getPort() + "/user/getByUsername";
		// System.out.println(url);
		// System.out.println(restTemplate.getForObject(url, String.class));
		JSONObject menuJson = null;
//		restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("admin", "admin"));
		// menuJson = restTemplate.postForObject("http://localhost:8881/menu", new
		// HashMap() {{
		// put("appid", "3");
		// }}, JSONObject.class);

		String auth = "admin:admin";
		byte[] encodeAuth = Base64.getEncoder().encode(auth.getBytes(Charset.forName("US-ASCII")));
		String authHeader = "Basic " + new String(encodeAuth);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", authHeader);
		// 表单提交
//		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
//		params.add("appid", "3");
//		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
		// JSON提交
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		String requestJson = "{\"appid\":\"3\"}";
		HttpEntity<String> requestEntity = new HttpEntity<String>(requestJson, headers);
		
		ResponseEntity<JSONObject> respJson = restTemplate.exchange("http://localhost:8881/menu", HttpMethod.POST, requestEntity, JSONObject.class);
		System.out.println("=========================================");
		System.out.println(respJson.getStatusCode());
		System.out.println(respJson.getBody());
		System.out.println("=========================================");
	}
}
