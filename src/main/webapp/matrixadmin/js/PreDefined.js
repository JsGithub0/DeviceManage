 /**
 * 
 */
// 提取jsp传入的get型参数
function getUrlParam(url, name) {
	var pattern = new RegExp("[?&]" + name + "\=([^&]+)", "g"); // 获取页面的url参数
	var matcher = pattern.exec(url);
	var items = null;
	if (matcher != null) {
		try {
			items = decodeURIComponent(decodeURIComponent(matcher[1]));
		} catch (e) {
			try {
				items = decodeURIComponent(matcher[1]);
			} catch (e) {
				items = matcher[1];
			}
		}
	}
	return items;
}

// 之江学院主网站，正式上线后要改成https://sdsy.zzjc.edu.cn
var HostExcelFilePathUrl = "http://127.0.0.1:8080/SDSYwa/excleFile/";
