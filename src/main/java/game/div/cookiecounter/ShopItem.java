package game.div.cookiecounter;

import android.graphics.Bitmap;

public class ShopItem {
	
	Bitmap itemImage;
	String itemName;
	String itemCost;
	int itemNumber;
	
	public ShopItem(Bitmap itemImage, String itemName, String itemCost,
			int itemNumber) {
		super();
		this.itemImage = itemImage;
		this.itemName = itemName;
		this.itemCost = itemCost;
		this.itemNumber = itemNumber;
	}
	
	public Bitmap getItemImage() {
		return itemImage;
	}
	public void setItemImage(Bitmap itemImage) {
		this.itemImage = itemImage;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemCost() {
		return itemCost;
	}
	public void setItemCost(String itemCost) {
		this.itemCost = itemCost;
	}
	public int getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}

}
