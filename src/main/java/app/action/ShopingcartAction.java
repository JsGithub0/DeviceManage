package app.action;
import java.io.IOException;

public interface ShopingcartAction {
	public void findAllShopingcart() throws IOException;
	public void findShopingcartById() throws IOException;
	public String addShopingcart() throws IOException;
	public String deleteShopingcart() throws IOException;
	public void findAllShopingcartByUserId() throws IOException;
	public String jiesuanShopingcart() throws IOException;
}
