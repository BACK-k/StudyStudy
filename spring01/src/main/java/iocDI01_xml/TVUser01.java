package iocDI01_xml;

// Test2. 객체지향 - 다형성 적용
// interface 구현을 강제, 메서드명이 동일해진다
interface TV {
	void powerOn();

	void powerOff();

	void volumeUp();

	void volumeDown();
}

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

// Test3. Factory패턴 (IOC/DI 입문)
// getBean 메서드의 매개변수로 요청을 전달
class BeanFactory {
	public TV getBean(String tv) {
		if (tv != null && tv.equals("ss"))
			return new SsTVi();
		else if (tv != null && tv.equals("lg"))
			return new LgTVi();
		else
			return null;
	} // getBean
} // BeanFactory

public class TVUser01 {

	public static void main(String[] args) {
//		Test3. 소스코드 수정없이 실시간으로 교체, Factory 필요
//		 객체를 생성해서 교체해줄 역할자가 필요 : Factory 패턴 (IOC/DI 입문)
//		 user 클래스의 요구사항(필요한 클래스, 의존성_Dependency) 을 Factory 에게 전달하는방법
//		 3가지 : xml, @, JavaConfig (JavaCode)
		System.out.println(" Test3. Factory 패턴 (IOC/DI 입문) ");
		BeanFactory bf = new BeanFactory();
		// 소스코드 수정없이 실시간으로 요청을 전달받음
		// main의 매개변수 활용
		TV tvf = bf.getBean(args[0]);

		if (tvf != null) {
			tvf.powerOn();
			tvf.volumeDown();
			tvf.volumeUp();
			tvf.powerOff();
		} else {
			System.out.println(" TV를 선택하지않음 ");
		}

	} // main

} // class
