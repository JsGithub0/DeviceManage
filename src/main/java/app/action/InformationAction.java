package app.action;
import java.io.IOException;

public interface InformationAction {
	public void findAllInformation() throws IOException;
	public void findInformationById() throws IOException;
	// ��web��ҳ����ͼ�Ļ�����ʾָ����ŵ���ѯ
	public void showInformationByIdFromWebPortol() throws IOException;
	// ���ƶ�app����ͼ�Ļ�����ʾָ����ŵ���ѯ
	public void showInformationByIdFromAppPortol() throws IOException;
}
