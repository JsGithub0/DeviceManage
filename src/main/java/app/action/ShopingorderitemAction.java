package app.action;
import java.io.IOException;

public interface ShopingorderitemAction {
	public void findAllShopingorderitem() throws IOException;
	public void findShopingorderitemById() throws IOException;
	public void findShopingorderitemListByShopingorderId() throws IOException;
	public String addShopingorderitem() throws IOException;
	public String deleteShopingorderitem() throws IOException;
}
