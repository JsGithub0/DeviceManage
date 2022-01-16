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
	// 咨询标题
	private String informationTitle;
	// 咨询标题图片名称
	// 注意：File变量informationImage的后面增加FileName，作为File的文件名变量
	private String informationImageFileName;
	// 咨询标题图片
	private File informationImage;
	// 咨询简介
	private String informationBrief;
	// 咨询富文本内容
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
		// 用<x>和</x>对，将标题informationTitle、简介informationBrief和正文informationMainBody这3个内容，拼接成字符串
		String informationContent = "<x>" + informationTitle + "</x>" + "<x>"
				+ informationBrief + "</x>" + "<x>" + informationMainBody
				+ "</x>";
		information.setInformationContent(informationContent);
		SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		information.setInformationCreateTime(dFormat.format(new Date()));
		// informationImageFileName是本地标题图片文件名(如C:\demo.png)，将把该图片上传到web服务器上
		if (informationImageFileName != null) {
			// realPath是Tomcat的webapps\DeviceManage\image目录
			String realPath = ServletActionContext.getRequest().getRealPath("/image");
			String hz = informationImageFileName
					.substring(informationImageFileName.lastIndexOf("."));
			// newFileName是web服务器上新建的标题图片文件名，如4874d660-33e4-4816-a6b5-36b76fdc049f.png
			String newFileName = UUID.randomUUID().toString() + hz;
			// 在webapps\DeviceManage\image目录下新建1个文件4874d660-33e4-4816-a6b5-36b76fdc049f.png
			OutputStream os = new FileOutputStream(new File(realPath,
					newFileName));
			// titleImageFullName是存放到数据库表中的标题图片文件的相对路径名
			String titleImageFullName = "/DeviceManage/image/" + newFileName;
			// 设置简介图片，只有1张图片
			information.setInformationImage(titleImageFullName);
			// 打开本地标题图片文件对象informationImage，informationImage是File类型
			InputStream is = new FileInputStream(informationImage);
			// 通过字节流方式，每次1K读取本地图片，然后写到web服务器webapps\DeviceManage\image下的标题图片文件4874d660-33e4-4816-a6b5-36b76fdc049f.png
			byte flush[] = new byte[1024];
			int len = 0;
			while (0 <= (len = is.read(flush))) {
				os.write(flush, 0, len);
			}
			is.close();
			os.close();
		} else {
			System.out.println("未上传图片");
		}
		informationDao.addInformation(information);
		return "success";
	}

}
