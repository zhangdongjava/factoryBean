package factoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.springframework.beans.factory.FactoryBean;

public class UserFactoryBean implements FactoryBean<Object> {

	@Override
	public Object getObject() throws Exception {
		return Proxy.newProxyInstance(this.getClass().getClassLoader(),
				new Class[]{Class.forName(className)}, new InvocationHandler() {
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						System.out.println("代理执行的方法:"+method.getName());
						return null;
					}
				});
	}
	@Override
	public Class<?> getObjectType() {
		try {
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.toString());
		}
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
		System.out.println(className);
		this.className = className;
	}
	
	
}
