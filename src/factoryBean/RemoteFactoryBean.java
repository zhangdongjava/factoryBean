package factoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.springframework.beans.factory.FactoryBean;

/**
 * 用于创创建借口的代理bean
 * 用于远程服务调用等
 */
public class RemoteFactoryBean implements FactoryBean<Object> {

	private Class<?> claObj;

	@Override
	public Object getObject() throws Exception {
		return Proxy.newProxyInstance(this.getClass().getClassLoader(),
				new Class[]{claObj}, new InvocationHandler() {
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						System.out.println("代理执行的方法:"+method.getName());
						return null;
					}
				});
	}
	@Override
	public Class<?> getObjectType() {
		return claObj;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	private String className;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
		try {
			claObj = Class.forName(className);
		} catch (ClassNotFoundException e) {
			new RuntimeException(className+ " not found !");
		}
	}
	
	
}
