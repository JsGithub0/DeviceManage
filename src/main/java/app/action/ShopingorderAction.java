package app.action;
import java.io.IOException;

public interface ShopingorderAction {
	public void findAllShopingorder()  throws IOException;
	public void findShopingorderById() throws IOException;
	public void findShopingorderByUserId() throws IOException;
	public String addShopingorder() throws IOException;
	public String deleteShopingorder() throws IOException;
}
