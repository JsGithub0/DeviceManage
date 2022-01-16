package web.action.imp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;
import org.dao.InformationDao;
import org.model.Information;

import web.action.InformationAction;

import com.opensymphony.xwork2.ActionSupport;

public class InformationActionImp extends ActionSupport implements
		InformationAction {
	// ��ѯ����
	private String informationTitle;
	// ��ѯ����ͼƬ����
	// ע�⣺File����informationImage�ĺ�������FileName����ΪFile���ļ�������
	private String informationImageFileName;
	// ��ѯ����ͼƬ
	private File informationImage;
	// ��ѯ���
	private String informationBrief;
	// ��ѯ���ı�����
	private String informationMainBody;

	public String getInformationTitle() {
		return informationTitle;
	}

	public void setInformationTitle(String informationTitle) {
		this.informationTitle = informationTitle;
	}

	public String getInformationImageFileName() {
		return informationImageFileName;
	}

	public void setInformationImageFileName(String informationImageFileName) {
		this.informationImageFileName = informationImageFileName;
	}

	public File getInformationImage() {
		return informationImage;
	}

	public void setInformationImage(File informationImage) {
		this.informationImage = informationImage;
	}

	public String getInformationBrief() {
		return informationBrief;
	}

	public void setInformationBrief(String informationBrief) {
		this.informationBrief = informationBrief;
	}

	public String getInformationMainBody() {
		return informationMainBody;
	}

	public void setInformationMainBody(String informationMainBody) {
		this.informationMainBody = informationMainBody;
	}

	InformationDao informationDao;

	public void setInformationDao(InformationDao informationDao) {
		this.informationDao = informationDao;
	}

	public String addInformation() throws IOException {
		Information information = new Information();
		// ��<x>��</x>�ԣ�������informationTitle�����informationBrief������informationMainBody��3�����ݣ�ƴ�ӳ��ַ���
		String informationContent = "<x>" + informationTitle + "</x>" + "<x>"
				+ informationBrief + "</x>" + "<x>" + informationMainBody
				+ "</x>";
		information.setInformationContent(informationContent);
		SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		information.setInformationCreateTime(dFormat.format(new Date()));
		// informationImageFileName�Ǳ��ر���ͼƬ�ļ���(��C:\demo.png)�����Ѹ�ͼƬ�ϴ���web��������
		if (informationImageFileName != null) {
			// realPath��Tomcat��webapps\DeviceManage\imageĿ¼
			String realPath = ServletActionContext.getRequest().getRealPath("/image");
			String hz = informationImageFileName
					.substring(informationImageFileName.lastIndexOf("."));
			// newFileName��web���������½��ı���ͼƬ�ļ�������4874d660-33e4-4816-a6b5-36b76fdc049f.png
			String newFileName = UUID.randomUUID().toString() + hz;
			// ��webapps\DeviceManage\imageĿ¼���½�1���ļ�4874d660-33e4-4816-a6b5-36b76fdc049f.png
			OutputStream os = new FileOutputStream(new File(realPath,
					newFileName));
			// titleImageFullName�Ǵ�ŵ����ݿ���еı���ͼƬ�ļ������·����
			String titleImageFullName = "/DeviceManage/image/" + newFileName;
			// ���ü��ͼƬ��ֻ��1��ͼƬ
			information.setInformationImage(titleImageFullName);
			// �򿪱��ر���ͼƬ�ļ�����informationImage��informationImage��File����
			InputStream is = new FileInputStream(informationImage);
			// ͨ���ֽ�����ʽ��ÿ��1K��ȡ����ͼƬ��Ȼ��д��web������webapps\DeviceManage\image�µı���ͼƬ�ļ�4874d660-33e4-4816-a6b5-36b76fdc049f.png
			byte flush[] = new byte[1024];
			int len = 0;
			while (0 <= (len = is.read(flush))) {
				os.write(flush, 0, len);
			}
			is.close();
			os.close();
		} else {
			System.out.println("δ�ϴ�ͼƬ");
		}
		informationDao.addInformation(information);
		return "success";
	}

}
