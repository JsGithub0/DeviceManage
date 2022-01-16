package app.action;
import java.io.IOException;

// app端涉及DeviceClass操作的接口
public interface DeviceClassAction {
	// 获得所有设备分类对象的列表
	void findAllDeviceClass() throws IOException;
	void findDeviceClass() throws IOException;	
}
