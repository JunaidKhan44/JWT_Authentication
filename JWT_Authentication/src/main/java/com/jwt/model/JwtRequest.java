/**
 * 
 */
package com.jwt.model;

/**
 * @author Junaid.Khan
 *
 */

public class JwtRequest {

	String userName;
	String password;

	public JwtRequest() {
		super();

	}

	public JwtRequest(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "JwtRequest [userName=" + userName + ", password=" + password + "]";
	}

}
