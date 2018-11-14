import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class Test
{
//	@Autowired
//	LoadBalancerClient	loadBalancerClient;
//	@Autowired
//	RestTemplate		restTemplate;
//
//	@org.junit.Test
//	public void todo()
//	{
//		System.out.println("hi");
//		ServiceInstance serviceInstance = loadBalancerClient.choose("eureka-client");
//		String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/user/getByUsername";
//		System.out.println(url);
//		System.out.println(restTemplate.getForObject(url, String.class));
//	}
}
