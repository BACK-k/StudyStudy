package myDispatcher;

// ViewResolver
// ViewName을 완성
public class MyViewResolver {
	private String prefix;
	private String suffix;

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	// ViewName을 리턴
	public String getViewName(String viewName) {
		viewName = prefix + viewName + suffix;
		System.out.println(" viewName => " + viewName);
		return viewName;
	}

} // class
