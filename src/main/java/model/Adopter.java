package model;

/**
 * 사용자 관리를 위해 필요한 도메인 클래스. adopterINFO 테이블과 대응됨
 */
public class Adopter {
	private String user_id;
	private String password;
	private String user_name;
	private String email;
	private String phone;
	
	public Adopter(String user_id, String password, String user_name, String email, String phone) {
		super();
		this.user_id = user_id;
		this.password = password;
		this.user_name = user_name;
		this.email = email;
		this.phone = phone;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Adopter() { }		// 기본 생성자
	
	/*public void update(adopter updateadopter) {
        this.password = updateadopter.password;
        this.name = updateadopter.name;
        this.email = updateadopter.email;
        this.phone = updateadopter.phone;
    }*/

	

	
	/* 비밀번호 검사 */
	public boolean matchPassword(String password) {
		if (password == null) {
			return false;
		}
		return this.password.equals(password);
	}
	
	public boolean isSameadopter(String user_id) {
        return this.user_id.equals(user_id);
    }

	@Override
	public String toString() {
		return "adopter [user_id=" + user_id + ", password=" + password + ", user_name=" + user_name + ", email=" + email + ", phone="
				+ phone +  "]";
	}	
}
