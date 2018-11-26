import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseContextHandler {
	public static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String, Object>>();

	
	public static void main(String args[]){
		Thread.yield();
	}
	
	
	public static void set(String key, Object value) {
		Map<String, Object> map = threadLocal.get();
		if (map == null) {
			map = new HashMap<String, Object>();
			threadLocal.set(map);
		}
		map.put(key, value);
	}

	public static Object get(String key) {
		Map<String, Object> map = threadLocal.get();
		if (map == null) {
			map = new HashMap<String, Object>();
			threadLocal.set(map);
		}
		return map.get(key);
	}

	private static String returnObjectValue(Object value) {
		return value == null ? null : value.toString();
	}

	public static void remove() {
		threadLocal.remove();
	}

	@RunWith(MockitoJUnitRunner.class)
	public static class UnitTest {
		private Logger logger = LoggerFactory.getLogger(UnitTest.class);

		@Test
		public void testSetContextVariable() throws InterruptedException {
			BaseContextHandler.set("test", "main");
			new Thread(() -> {
				BaseContextHandler.set("test", "moo");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				assertEquals(BaseContextHandler.get("test"), "moo");
				logger.info("thread one done!");
			}).start();
			new Thread(() -> {
				BaseContextHandler.set("test", "moo2");
				assertEquals(BaseContextHandler.get("test"), "moo2");
				logger.info("thread two done!");
			}).start();

			Thread.sleep(5000);
			assertEquals(BaseContextHandler.get("test"), "main");
			logger.info("main one done!");
		}
		
	}
}
