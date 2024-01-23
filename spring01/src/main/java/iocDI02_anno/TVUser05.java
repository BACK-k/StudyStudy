package iocDI02_anno;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

//** Annotation, @, 애너테이션
//=> 컴파일러에게 알려줌 (지시어)

//** @ Annotation 애너테이션 활용방법 *********************

//1. xml 에 context 네임스페이스 추가 [NameSpaces] 이용
//=> Component-scan 설정  : 
//<context:component-scan base-package="d0714.iocEx05" />
//  
//2. 소스코드 
//=> import 확인  : org.springframework.context.support.*;
//=> 생성을 원하는 TV 클래스 위에 @Component("tv") 설정

//=> @Component("tv") 
//<bean ....> </bean> -> xml
//new 생성자() -> Java Code

//3.Test : 오류 메시지 확인 하기
//=> @ Test1 생성 -> <bean ... />
//=> @ Test2 id명 미 지정(둘다 beanname 없이) 
//=> ...NoSuchBeanDefinitionException: 
//           No bean named 'tv' is defined ....
//=> @ Test3 id명 중복되는 경우 (둘다 beanname  ("tv") 지정 )  
//=> ...annotation.ConflictingBeanDefinitionException: ....
//  ...non-compatible bean definition of same name and class ...

interface TV {
	void powerOn();

	void powerOff();

	void volumeUp();

	void volumeDown();
}

//bean을 등록, id가 getBean(호출)된다, id가 없으면 그냥 생성
@Component("tv")
class SsTVi implements TV {
//	생성자를 통해 누가 생성되었는지 확인
	public SsTVi() {
		System.out.println(" SsTVi 기본생성자 ");
	}

	@Override
	public void powerOn() {
		System.out.println(" SsTVi powerOn ");
	}

	@Override
	public void powerOff() {
		System.out.println(" SsTVi powerOff ");
	}

	@Override
	public void volumeUp() {
		System.out.println(" SsTVi volumeUp ");
	}

	@Override
	public void volumeDown() {
		System.out.println(" SsTVi volumeUp ");
	}

}

//bean을 등록
@Component("ltv")
class LgTVi implements TV {
//	생성자를 통해 누가 생성되었는지 확인
	public LgTVi() {
		System.out.println(" LgTVi 기본생성자 ");
	}

	@Override
	public void powerOn() {
		System.out.println(" LgTV powerOn ");
	}

	@Override
	public void powerOff() {
		System.out.println(" LgTV powerOff ");
	}

	@Override
	public void volumeUp() {
		System.out.println(" LgTV volumeUp ");
	}

	@Override
	public void volumeDown() {
		System.out.println(" LgTV volumeUp ");
	}

}

public class TVUser05 {

	public static void main(String[] args) {
		// 1 스프링 컨테이너 생성
		AbstractApplicationContext sc = new GenericXmlApplicationContext("iocDI02_anno/app05.xml");

		// 2 필요한 객체를 전달받고 서비스 실행
		TV tv = (TV) sc.getBean("tv");
		if (tv != null) {
			tv.powerOn();
			tv.volumeDown();
			tv.volumeUp();
			tv.powerOff();
		} else {
			System.out.println(" TV를 선택하지않음 ");
		}
		System.out.println(" 정상 종료 ");

		sc.close();
	} // main

} // class
