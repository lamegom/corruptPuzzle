package game.div.cookiecounter;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import com.android.fontlibrary.CookieFont;
import com.divneg.storage.Config;
import com.facebook.android.friendsmash.FriendSmashApplication;

import java.util.ArrayList;
import java.util.List;

import game.div.corruptpuzzle.R;
import game.div.foradilma.util.Common;

public class ShopList extends AppCompatActivity  {
//	private static ShopList _instance;
	ListView shopList;
	ItemAdapter adapter;
	ArrayList<ShopItem> shopData;
	
//	public static ShopList getInstance(){
//		return _instance;
//	}

	@Override
	  public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main, menu);
	    return true;
	  }
	
	@Override
	  public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    // action with ID action_refresh was selected
//	    case R.id.action_refresh:
//	      Toast.makeText(this, "To be implemented. Please support us to new enhancements !!", Toast.LENGTH_SHORT)
//	          .show();
//	      break;
	    case android.R.id.home:
            NavUtils.navigateUpFromSameTask(this);
            return true;
	    default:
	      break;
	    }

	    return true;
	  } 
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		requestWindowFeature(Window.FEATURE_ACTION_BAR);
		super.onCreate(savedInstanceState);

		setContentView(R.layout.shop_layout);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME
		        | ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_SHOW_CUSTOM);
		
		
		shopList = (ListView) findViewById(R.id.shopList);

		shopData = new ArrayList<ShopItem>();
		

		Config config = new Config();

		fillArray(config);
		
		adapter = new ItemAdapter(this, shopData);
		shopList.setAdapter(adapter);
		

		actionBar.setDisplayHomeAsUpEnabled(true);
		
			
//		_instance = this;
	}
	
	public void showToast(String msg){
		 LayoutInflater inflater = getLayoutInflater();
		 View layout = inflater.inflate(R.layout.toast_layout,
		                           (ViewGroup) findViewById(R.id.toast_layout_root));


		 ImageView image = (ImageView) layout.findViewById(R.id.image);
		 image.setImageResource(R.drawable.ic_launcher);
		 TextView text = (TextView) layout.findViewById(R.id.text);
		 text.setText(msg);

		 Toast toast = new Toast(getApplicationContext());
		 toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		 toast.setDuration(Toast.LENGTH_SHORT);
		 toast.setView(layout);
		 toast.show();
	}



	private static class ViewHolder {
		CookieFont itemName;
		ImageView itemImage;
		LinearLayout itemBox;
		TextView itemCount;
		TextView itemCost;
		RelativeLayout container;
	}

	class ItemAdapter extends BaseAdapter {

		private List<ShopItem> shopItem;

		public ItemAdapter(Context context, List<ShopItem> shopItems) {

			this.shopItem = shopItems;
		}

		@Override
		public int getCount() {
			return shopItem.size();
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {

			View view = convertView;
			final ViewHolder holder;
			if (convertView == null) {
				view = getLayoutInflater().inflate(R.layout.element_list,
						parent, false);
				holder = new ViewHolder();
				holder.itemCost = (TextView) view.findViewById(R.id.itemCost);
				holder.itemCount = (TextView) view
						.findViewById(R.id.ItemNumber);
				holder.itemName = (CookieFont) view.findViewById(R.id.ItemName);
				
				holder.itemImage = (ImageView) view
						.findViewById(R.id.ItemImage);
				holder.itemBox = (LinearLayout) view.findViewById(R.id.itemBox);
				holder.container = (RelativeLayout) view
						.findViewById(R.id.container);

				view.setTag(holder);
			} else {
				holder = (ViewHolder) view.getTag();
			}
			if(isTablet(GameActivity.getInstance())) ((CookieFont) holder.itemName).setTextSize(24);
			holder.itemCost.setText(shopItem.get(position).getItemCost());
			holder.itemCount.setText(String.valueOf(shopItem.get(position)
					.getItemNumber()));
			holder.itemName.setText(shopItem.get(position).getItemName());

			holder.itemImage.setImageBitmap(shopItem.get(position)
					.getItemImage());

			holder.itemBox
					.setOnClickListener(new OnItemClickListener(position));

			holder.itemBox
					.setOnTouchListener(new OnItemTouchListener(position));

			return view;

		}
		
		public boolean isTablet(Context context) {
	        boolean xlarge = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == 4);
	        boolean large = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
	        return (xlarge || large);
	    }

	}

	private class OnItemTouchListener implements OnTouchListener {
		private int mposition;

		OnItemTouchListener(int position) {
			mposition = position;
		}

		@Override
		public boolean onTouch(View arg0, MotionEvent arg1) {
			// TODO Auto-generated method stub
			return false;
		}

	}

	private class OnItemClickListener implements OnClickListener {
		private int mPosition;
		private static final String NOT_ENOUGH = "It's better more #StopCorruption to a thing like that!";

		OnItemClickListener(int position) {
			mPosition = position;
		}

		@Override
		public void onClick(View view) {

			switch (mPosition) {
			case 0:
				if(increaseCPS(mPosition)){
				Config.fillData("Cursor", String.valueOf(Float
						.parseFloat(Config.getData("Cursor")) + 1));
				increaseCPS(mPosition);
				fillArray(new Config());

				adapter.notifyDataSetChanged();
				}else
				{
//					Toast.makeText(ShopList.this,NOT_ENOUGH , Toast.LENGTH_SHORT).show();
					showToast(NOT_ENOUGH);
					
				}
				break;
			case 1:
				if(increaseCPS(mPosition)){
				Config.fillData("Grandma", String.valueOf(Float
						.parseFloat(Config.getData("Grandma")) + 1));
				fillArray(new Config());
				increaseCPS(mPosition);
				adapter.notifyDataSetChanged();
				}else
				{
//					Toast.makeText(ShopList.this, NOT_ENOUGH, Toast.LENGTH_SHORT).show();
					showToast(NOT_ENOUGH);
				}
				break;
			case 2:
				if(increaseCPS(mPosition)){
				Config.fillData("Farm", String.valueOf(Float
						.parseFloat(Config.getData("Farm")) + 1));
				fillArray(new Config());
				increaseCPS(mPosition);
				adapter.notifyDataSetChanged();
				}else
				{
//					Toast.makeText(ShopList.this, NOT_ENOUGH, Toast.LENGTH_SHORT).show();
					showToast(NOT_ENOUGH);
				}
				break;
			case 3:
				if(increaseCPS(mPosition)){
				Config.fillData("Factory", String.valueOf(Float
						.parseFloat(Config.getData("Factory")) + 1));
				fillArray(new Config());
				increaseCPS(mPosition);
				adapter.notifyDataSetChanged();
				}else
				{
//					Toast.makeText(ShopList.this, NOT_ENOUGH, Toast.LENGTH_SHORT).show();
					showToast(NOT_ENOUGH);
				}
				break;
			case 4:
				if(increaseCPS(mPosition)){
				Config.fillData("Mine", String.valueOf(Float
						.parseFloat(Config.getData("Mine")) + 1));
				fillArray(new Config());
				increaseCPS(mPosition);
				adapter.notifyDataSetChanged();
				}else
				{
//					Toast.makeText(ShopList.this, NOT_ENOUGH, Toast.LENGTH_SHORT).show();
					showToast(NOT_ENOUGH);
				}
				break;
			case 5:
				if(increaseCPS(mPosition)){
				Config.fillData("ShipMent", String.valueOf(Float
						.parseFloat(Config.getData("ShipMent")) + 1));
				fillArray(new Config());
				increaseCPS(mPosition);
				adapter.notifyDataSetChanged();
				}else
				{
//					Toast.makeText(ShopList.this, NOT_ENOUGH, Toast.LENGTH_SHORT).show();
					showToast(NOT_ENOUGH);
				}
				break;
			case 6:
				if(increaseCPS(mPosition)){
				Config.fillData("Alchemy Lab", String.valueOf(Float
						.parseFloat(Config.getData("Alchemy Lab")) + 1));
				fillArray(new Config());
				increaseCPS(mPosition);
				adapter.notifyDataSetChanged();
				}else
				{
//					Toast.makeText(ShopList.this, NOT_ENOUGH, Toast.LENGTH_SHORT).show();
					showToast(NOT_ENOUGH);
				}
				break;
			case 7:
				if(increaseCPS(mPosition)){
				Config.fillData("Portal", String.valueOf(Float
						.parseFloat(Config.getData("Portal")) + 1));
				fillArray(new Config());
				increaseCPS(mPosition);
				adapter.notifyDataSetChanged();
				}else
				{
//					Toast.makeText(ShopList.this, NOT_ENOUGH, Toast.LENGTH_SHORT).show();
					showToast(NOT_ENOUGH);
				}
				break;
			case 8:
				if(increaseCPS(mPosition)){
				Config.fillData("Time Machine", String.valueOf(Float
						.parseFloat(Config.getData("Time Machine")) + 1));
				fillArray(new Config());
				increaseCPS(mPosition);
				adapter.notifyDataSetChanged();
				}else
				{
//					Toast.makeText(ShopList.this, NOT_ENOUGH, Toast.LENGTH_SHORT).show();
					showToast(NOT_ENOUGH);
				}
				break;

				/*
				case 9:
				if(increaseCPS(mPosition)){
				Config.fillData("Antimatter Condenser", String.valueOf(Float
						.parseFloat(Config.getData("Antimatter Condenser")) + 1));
				fillArray(new Config());
				increaseCPS(mPosition);
				adapter.notifyDataSetChanged();
				}else
				{
//					Toast.makeText(ShopList.this, NOT_ENOUGH, Toast.LENGTH_SHORT).show();
					showToast(NOT_ENOUGH);
				}
				break;
			case 10:
				if(increaseCPS(mPosition)){
				Config.fillData("Prism", String.valueOf(Float
						.parseFloat(Config.getData("Prism")) + 1));
				fillArray(new Config());
				adapter.notifyDataSetChanged();
				}else
				{
//					Toast.makeText(ShopList.this, NOT_ENOUGH, Toast.LENGTH_SHORT).show();
					showToast(NOT_ENOUGH);
				}
				break;
				*/
			}
			// on click the buy button
			
			application.saveInventory();

		}

	}

	public void fillArray(Config config) {

		shopData.clear();

		shopData.add(new ShopItem(BitmapFactory.decodeResource(getResources(),
				R.drawable.cursoricon), Config.ITEM1,
				Common.milTrilConverter(Double.valueOf(Config.ITEM1COST), true), Config.ITEM1NUM));
		
		shopData.add(new ShopItem(BitmapFactory.decodeResource(getResources(),
				R.drawable.grandma_icon), Config.ITEM2,
				Common.milTrilConverter(Double.valueOf(Config.ITEM2COST), true), Config.ITEM2NUM));
		
		shopData.add(new ShopItem(BitmapFactory.decodeResource(getResources(),
				R.drawable.farm), Config.ITEM3,
				Common.milTrilConverter(Double.valueOf(Config.ITEM3COST), true), Config.ITEM3NUM));
		shopData.add(new ShopItem(BitmapFactory.decodeResource(getResources(),
				R.drawable.factory_icon), Config.ITEM4,
				Common.milTrilConverter(Double.valueOf(Config.ITEM4COST), true), Config.ITEM4NUM));
		shopData.add(new ShopItem(BitmapFactory.decodeResource(getResources(),
				R.drawable.mine), Config.ITEM5,
				Common.milTrilConverter(Double.valueOf(Config.ITEM5COST), true), Config.ITEM5NUM));
		shopData.add(new ShopItem(BitmapFactory.decodeResource(getResources(),
				R.drawable.shipment_icon), Config.ITEM6,
				Common.milTrilConverter(Double.valueOf(Config.ITEM6COST), true), Config.ITEM6NUM));
		shopData.add(new ShopItem(BitmapFactory.decodeResource(getResources(),
				R.drawable.alchemylab), Config.ITEM7,
				Common.milTrilConverter(Double.valueOf(Config.ITEM7COST), true), Config.ITEM7NUM));
		shopData.add(new ShopItem(BitmapFactory.decodeResource(getResources(),
				R.drawable.portal), Config.ITEM8,
				Common.milTrilConverter(Double.valueOf(Config.ITEM8COST), true), Config.ITEM8NUM));
		shopData.add(new ShopItem(BitmapFactory.decodeResource(getResources(),
				R.drawable.timemachine), Config.ITEM9,
				Common.milTrilConverter(Double.valueOf(Config.ITEM9COST), true), Config.ITEM9NUM));
		shopData.add(new ShopItem(BitmapFactory.decodeResource(getResources(),
				R.drawable.antimattercondenser), Config.ITEM10,
				Common.milTrilConverter(Double.valueOf(Config.ITEM10COST), true), Config.ITEM10NUM));
		shopData.add(new ShopItem(BitmapFactory.decodeResource(getResources(),
				R.drawable.prism), Config.ITEM11,
				Common.milTrilConverter(Double.valueOf(Config.ITEM11COST), true), Config.ITEM11NUM));
	}


	FriendSmashApplication application = (FriendSmashApplication) GameActivity.getInstance().getApplication();
	
	public boolean increaseCPS(int pos){
		switch (pos){
		case 0:
			
			if(application.getBombs() >= Double.parseDouble(Config.ITEM1COST)){
			double cookie = application.getBombs();
			double cost = Double.parseDouble(Config.ITEM1COST);
			application.setBombs(cookie-cost);
			// \text {CpS} = \text {amount of Cursors} \times 0.3+0.1 
			double Newrate = application.getCoins() + 2;//1000.1;
			application.setCoins(Newrate);

				return true;
				
			}else
			{
				return false;
			}
		case 1:
			
			if(application.getBombs() >= Double.parseDouble(Config.ITEM2COST)){
				double cookie = application.getBombs();
				double cost = Double.parseDouble(Config.ITEM2COST);
				application.setBombs(cookie-cost);
				//(0.8 + amount of grandmas / 25 + amount of portals / 20) x 262144
//				double Newrate = application.getCoins() + ((0.8 + cost) / 25) + (cookie / 20);// + 1000;// * 262144;
				double Newrate = application.getCoins() + 2.5;
				application.setCoins(Newrate);
				
					return true;
				}else
				{
					return false;
				}
			
		case 2:

			if(application.getBombs() >= Double.parseDouble(Config.ITEM3COST)){
				double cookie = application.getBombs();
				double cost = Double.parseDouble(Config.ITEM3COST);
				application.setBombs(cookie-cost);
				double Newrate = application.getCoins()+160;// + 1000;
				application.setCoins(Newrate);
				
					return true;
				}else
				{
					return false;
				}

		case 3:
			
			if(application.getBombs() >= Double.parseDouble(Config.ITEM4COST)){
				double cookie = application.getBombs();
				double cost = Double.parseDouble(Config.ITEM4COST);
				application.setBombs(cookie-cost);
				double Newrate =application.getCoins()+448;// + 1000;
				application.setCoins(Newrate);
				
					return true;
				}else
				{
					return false;
				}
			
		case 4:
			
			if(application.getBombs() >= Double.parseDouble(Config.ITEM5COST)){
				double cookie = application.getBombs();
				double cost = Double.parseDouble(Config.ITEM5COST);
				application.setBombs(cookie-cost);
				double Newrate = application.getCoins()+1600;// + 1000;
				application.setCoins(Newrate);
				
					return true;
				}else
				{
					return false;
				}
			
		case 5:

			if(application.getBombs() >= Double.parseDouble(Config.ITEM6COST)){
				double cookie = application.getBombs();
				double cost = Double.parseDouble(Config.ITEM6COST);
				application.setBombs(cookie-cost);
				double Newrate = application.getCoins()+4160;// + 1000;
				application.setCoins(Newrate);
				
					return true;
				}else
				{
					return false;
				}

		case 6:
			
			if(application.getBombs() >= Double.parseDouble(Config.ITEM7COST)){
				double cookie = application.getBombs();
				double cost = Double.parseDouble(Config.ITEM7COST);
				application.setBombs(cookie-cost);
				double Newrate = application.getCoins()+16000 ;//+ 1000;
				application.setCoins(Newrate);
				
					return true;
				}else
				{
					return false;
				}
			
		case 7:
			
			
			if(application.getBombs() >= Double.parseDouble(Config.ITEM8COST)){
				double cookie = application.getBombs();
				double cost = Double.parseDouble(Config.ITEM8COST);
				application.setBombs(cookie-cost);
				double Newrate = application.getCoins()+266624;// + 1000;
				application.setCoins(Newrate);
				
					return true;
				}else
				{
					return false;
				}
			
		case 8:
			
			if(application.getBombs() >= Double.parseDouble(Config.ITEM9COST)){
				double cookie = application.getBombs();
				double cost = Double.parseDouble(Config.ITEM9COST);
				application.setBombs(cookie-cost);
				double Newrate = application.getCoins()+3476512;// + 1000;
				application.setCoins(Newrate);
				
					return true;
				}else
				{
					return false;
				}
			
		case 9:
			
			if(application.getBombs() >= Double.parseDouble(Config.ITEM10COST)){
				double cookie = application.getBombs();
				double cost = Double.parseDouble(Config.ITEM10COST);
				application.setBombs(cookie-cost);
				double Newrate = application.getCoins()+35199936;// + 1000;
				application.setCoins(Newrate);
				
					return true;
				}else
				{
					return false;
				}
			
		case 10:
			
			if(application.getBombs() >= Double.parseDouble(Config.ITEM11COST)){
				double cookie = application.getBombs();
				double cost = Double.parseDouble(Config.ITEM11COST);
				application.setBombs(cookie-cost);
				double Newrate = application.getCoins()+352000000;// + 1000;
				application.setCoins(Newrate);
				
					return true;
				}else
				{
					return false;
				}
			
		}
		
		return true;	
	}
}
