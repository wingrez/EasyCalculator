package site.wingrez.easy_calculator;

import java.text.DecimalFormat;

import android.annotation.TargetApi;
import android.app.Activity;
import android.icu.math.BigDecimal;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import site.wingrez.R;

public class MainActivity extends Activity implements OnClickListener{
	Button btn_0;
	Button btn_1;
	Button btn_2;
	Button btn_3;
	Button btn_4;
	Button btn_5;
	Button btn_6;
	Button btn_7;
	Button btn_8;
	Button btn_9;
	Button btn_point;
	Button btn_plus;
	Button btn_minus;
	Button btn_multiply;
	Button btn_divide;
	Button btn_equal;
	Button btn_del;
	Button btn_clear;
	EditText et_input;
	
	boolean clearFlag=true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn_0=(Button)findViewById(R.id.btn_0);
		btn_1=(Button)findViewById(R.id.btn_1);
		btn_2=(Button)findViewById(R.id.btn_2);
		btn_3=(Button)findViewById(R.id.btn_3);
		btn_4=(Button)findViewById(R.id.btn_4);
		btn_5=(Button)findViewById(R.id.btn_5);
		btn_6=(Button)findViewById(R.id.btn_6);
		btn_7=(Button)findViewById(R.id.btn_7);
		btn_8=(Button)findViewById(R.id.btn_8);
		btn_9=(Button)findViewById(R.id.btn_9);
		btn_point=(Button)findViewById(R.id.btn_point);
		btn_plus=(Button)findViewById(R.id.btn_plus);
		btn_minus=(Button)findViewById(R.id.btn_minus);
		btn_multiply=(Button)findViewById(R.id.btn_multiply);
		btn_divide=(Button)findViewById(R.id.btn_divide);
		btn_equal=(Button)findViewById(R.id.btn_equal);
		btn_del=(Button)findViewById(R.id.btn_del);
		btn_clear=(Button)findViewById(R.id.btn_clear);
		et_input=(EditText)findViewById(R.id.et_input);
		
		btn_0.setOnClickListener(this);
		btn_1.setOnClickListener(this);
		btn_2.setOnClickListener(this);
		btn_3.setOnClickListener(this);
		btn_4.setOnClickListener(this);
		btn_5.setOnClickListener(this);
		btn_6.setOnClickListener(this);
		btn_7.setOnClickListener(this);
		btn_8.setOnClickListener(this);
		btn_9.setOnClickListener(this);
		btn_point.setOnClickListener(this);
		btn_plus.setOnClickListener(this);
		btn_minus.setOnClickListener(this);
		btn_multiply.setOnClickListener(this);
		btn_divide.setOnClickListener(this);
		btn_equal.setOnClickListener(this);
		btn_del.setOnClickListener(this);
		btn_clear.setOnClickListener(this);

	}
	
	public void onClick(View v) {
		String str=et_input.getText().toString();
		switch(v.getId()) {
		case R.id.btn_0:
		case R.id.btn_1:
		case R.id.btn_2:
		case R.id.btn_3:
		case R.id.btn_4:
		case R.id.btn_5:
		case R.id.btn_6:
		case R.id.btn_7:
		case R.id.btn_8:
		case R.id.btn_9:
		case R.id.btn_point:
			if(clearFlag) {
				str="";
				et_input.setText("");
				clearFlag=false;
			}
			et_input.setText(str+((Button)v).getText());
			break;
		case R.id.btn_plus:
		case R.id.btn_minus:
		case R.id.btn_multiply:
		case R.id.btn_divide:
			if(str.endsWith(" ")) {
				str=str.substring(0,str.length()-3);
			}
			et_input.setText(str+" "+((Button)v).getText()+" ");
			clearFlag=false;
			break;
		case R.id.btn_del:
			if(!str.equals("")) {
				if(str.endsWith(" ")) {
					str=str.substring(0,str.length()-3);
					et_input.setText("test");
				}
				else str=str.substring(0,str.length()-1);
				et_input.setText(str);
			}
			break;
		case R.id.btn_clear:
			et_input.setText("");
			break;
		case R.id.btn_equal:
			getResult();
			break;
		}
	}
	
	private void getResult() {
		clearFlag=true;
		String exp=et_input.getText().toString();
		int spacePos=exp.indexOf(' ');
		if(spacePos==-1) {
			return;
		}
		String s1=exp.substring(0,spacePos);
		String op=exp.substring(spacePos+1,spacePos+2);
		String s2=exp.substring(spacePos+3);
		DecimalFormat dcmFmt = new DecimalFormat("0.000000");
		double v1=Double.parseDouble(s1);
		double v2=Double.parseDouble(s2);
		double res=0;
		
		if(op.equals("+")) {
			res=v1+v2;
		}
		else if(op.equals("-")) {
			res=v1-v2;
		}
		else if(op.equals("*")) {
			res=v1*v2;
		}
		else if(op.equals("/")) {
			res=v1/v2;
		}
		res=Double.parseDouble(dcmFmt.format(res));
		//对于结果是否为小数的处理
		if(res-(int)res==0) {
			et_input.setText((int)res+"");
		}
		else {
			et_input.setText(res+"");
		}
	}
}
