package app.action;
import java.io.IOException;

public interface InformationAction {
	public void findAllInformation() throws IOException;
	public void findInformationById() throws IOException;
	// 在web网页端上图文混排显示指定编号的咨询
	public void showInformationByIdFromWebPortol() throws IOException;
	// 在移动app端上图文混排显示指定编号的咨询
	public void showInformationByIdFromAppPortol() throws IOException;
}
