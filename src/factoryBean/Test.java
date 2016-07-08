package factoryBean;

import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		FileSystemXmlApplicationContext applicationContext = new FileSystemXmlApplicationContext("application.xml");
		applicationContext.getBean(UserInter.class).test();
		applicationContext.close();

	}

}
