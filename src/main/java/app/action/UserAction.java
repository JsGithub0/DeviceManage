package app.action;

import java.io.IOException;

public interface UserAction {
	public void findAllUser() throws IOException;
	public void findUserById() throws IOException;
	public void loginValidate() throws IOException;
}
