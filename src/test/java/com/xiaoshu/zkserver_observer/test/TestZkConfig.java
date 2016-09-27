package com.xiaoshu.zkserver_observer.test;

import org.I0Itec.zkclient.ZkClient;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xiaoshu.zkserver_observer.ConfigChangeListener;
import com.xiaoshu.zkserver_observer.ConfigChangeSubscriber;
import com.xiaoshu.zkserver_observer.ZkUtils;



public class TestZkConfig {

	private static ZkClient zkClient;
	private static ConfigChangeSubscriber zkConfig;
	
	public static void init() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
				"test-spring-config.xml");
		zkClient = ((ZkClient) ctx.getBean("zkClient"));
		zkConfig = ((ConfigChangeSubscriber) ctx
				.getBean("configChangeSubscriber"));
		ZkUtils.mkPaths(zkClient, "/zkSample/conf");
		if (!zkClient.exists("/zkSample/conf/test1.properties"))
			zkClient.createPersistent("/zkSample/conf/test1.properties");

		if (!zkClient.exists("/zkSample/conf/test2.properties"))
			zkClient.createPersistent("/zkSample/conf/test2.properties");
	}
	public static void main(String[] args) {
		 init();
		 Thread t = new Thread(new TestThead(zkConfig));
		 t.start();
	}
}

class TestThead implements Runnable{
	private  ConfigChangeSubscriber zkConfig;
	private static volatile boolean running = true;
	
	
	public TestThead(ConfigChangeSubscriber zkConfig) {
		this.zkConfig = zkConfig;
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.zkConfig.subscribe("test1.properties", new ConfigChangeListener() {
			public void configChanged(String key, String value) {
				System.out.println("test1接收到数据变更通知: key=" + key + ", value="
						+ value);
			}
		});
		synchronized(TestThead.class){
			while(running){
				try {
					TestThead.class.wait();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}
	
}
