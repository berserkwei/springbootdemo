package SpringBootDemo.Entities;

public class ApiResult {
	Object result;
	boolean succeed;
	String message;
	
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	
	public boolean isSucceed() {
		return succeed;
	}
	public void setSucceed(boolean succeed) {
		this.succeed = succeed;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public ApiResult(boolean succeed, String message) {
		this.succeed = succeed;
		this.message = message;
	}
	
	public ApiResult(boolean succeed, String message, Object result) {
		this.succeed = succeed;
		this.message = message;
		this.result = result;
	}
}
