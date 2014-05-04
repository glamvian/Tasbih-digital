package com.glmvn.tasbihdigital;

import android.os.Bundle;
import android.os.Vibrator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class mainActivity extends Activity {
	private ImageView count;
	private ImageView res;
	private TextView h;
	private EditText su;
	int angka=0;
	int max=7;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        angakho();
    }

    public void angakho()
    {
    	 count=(ImageView)findViewById (R.id.c);
         res=(ImageView)findViewById(R.id.r);
         h=(TextView)findViewById(R.id.hum);
         count.setOnClickListener(cListener);
         res.setOnClickListener(rListener);
         
    }
    
  //method tombol count
 private View.OnClickListener cListener = new View.OnClickListener() {
		
		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			h.setText(String.valueOf(++angka));
			
			if (angka==max)
			{
				stopActivity();
				Vibrator v1= (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
				 v1.vibrate(300);
				Toast.makeText(mainActivity.this, "alhamdulilah sudah "+max+" kali", Toast.LENGTH_LONG).show();
				
			}
		}
	};
	
	//method tombol reset
	private View.OnClickListener rListener = new View.OnClickListener() {
		
		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			h.setText(String.valueOf(angka=0));
			count.setOnClickListener(cListener);
		}
	};
	
	//stop count listener
	public void stopActivity()
	{
		count.setOnClickListener(null);
	}

	//menu
	public boolean onCreateOptionsMenu(Menu menu)
	   {
		SubMenu subMenu = menu.addSubMenu("Repeat");
		subMenu.add(1,1,0, "7times").setChecked (true); 
		subMenu.add(1,2,0,"33times");
		subMenu.add(1,3,0, "100times");
		subMenu.add(1,4,0,"others ");
		menu.add(0,5,0,"info");
		menu.add(0,6,0, "exit").setIcon(android.R.drawable.btn_minus);
		return true;
		
	   }
	
	//options menu
	 public boolean onOptionsItemSelected(MenuItem item)
	   {
		   switch (item.getItemId()){
		   case 1:
			   max=7;
			   return (true);
		   case 2:
			   max=33;
			   return (true);
		   case 3:
			   max=100;
			   return (true);
		   case 4:
			  others();  
			   return (true);
		   case 5:
			   info();
			   return (true);
		   case 6:
			   onBackPressed();
			   return (true);
		   }
		   return false;
	   }
	 
	 //layout others
	 public void others()
	 {
		 this.setContentView(R.layout.others);
		 su=(EditText)findViewById(R.id.huum);
		 su.setText(String.valueOf(max));
	 }
	 
	 //method tombol oke di layout others
	 public void om_mput(View view)
		{
			max=Integer.valueOf(su.getText().toString()).intValue();
			this.setContentView(R.layout.activity_main);
			angakho();
		}
	 
	//method tombol back	
	 public void onBackPressed()
	  {
		    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
		    localBuilder.setTitle("konfirmasi");
		    localBuilder.setMessage("apakah anda benar ingin keluar?");
		    localBuilder.setPositiveButton("ya", new OnClickListener(){
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					exit();
				}});
		    localBuilder.setNegativeButton("tidak", new OnClickListener(){
		    	public void onClick(DialogInterface dialog, int which){	
		    	}});
		    localBuilder.show();
	  }
	 
	 public void info()
	 {
		 startActivity(new Intent(this,AboutActivity.class));
	 }
	 
	 public void exit()
		{
			this.finish();
		}
}
