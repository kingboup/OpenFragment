package com.example.openfragment.Activity;

import java.util.List;
import com.example.openfragment.ManageBean.FragmentRegisterBean;
import com.example.openfragment.fragment.BaseFragment;
//import com.github.pwittchen.networkevents.library.NetworkEvents;
//import com.squareup.otto.Bus;

import android.R;
import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

/**
 * ���齫���ݻ�ȡ����д�ɳ����࣬Ȼ����洦���������Ϊ�̳����ݻ�ȡ�ĳ����࣬�����Ϳ��Խ����ݺͽ�����ȫ���롣<br>
 * ͨ��DataActivity�е�setHandlerMessage���͸�handler��
 * ��viewActivity��GetHandlerMessage����Ϣ��Ȼ�󣬽���view����
 */
@SuppressLint("NewApi")
public abstract class BaseActivity extends XActivity {

//	private Bus mBus;
//	private NetworkEvents mNetworkEvents;
	private boolean mIsNeedNetwork = false;

	private boolean mIsNeedHandler = false;
	private Handler mhHandler;

	public FragmentManager manager;
	public FragmentTransaction transaction;

	public Context mContext;

	private LinearLayout returnLinearLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(setMainLayout());

		mContext = this;

		manager = getFragmentManager();

		mIsNeedNetwork = setIsNeedNetwork();
		mIsNeedHandler = setIsNeedHandler();
		initNetworkEvent();

		if (mIsNeedHandler) {
			mhHandler = new Handler() {
				public void handleMessage(android.os.Message msg) {
					GetHandlerMessage();
				};
			};
		} else {
			Log.e("Handler", "��activity��δʹ��handler�����̼߳�Ľ���");
		}

		findView();

		initView();
		initData();

	}

	@Override
	protected void initNetworkEvent(String url, int timeout) {
//		if (mIsNeedNetwork) {
//			mBus = new Bus();
//			mNetworkEvents = new NetworkEvents(mContext, mBus).withPingUrl(url).withPingTimeout(timeout)
//					.withoutWifiAccessPointsScan();
//		} else {
//			Log.e("NetWork", "��activity��δʹ������");
//		}
	}

	@Override
	protected void setHandlerMessage(int MessageWhat, Bundle mBundle) {
		Message message = new Message();
		message.what = MessageWhat;
		message.setData(mBundle);
		mhHandler.sendMessage(message);
	};

	@Override
	public void setDataToFragment(int FragmentID, Bundle mbBundle) {
		BaseFragment mFragment = (BaseFragment) manager.findFragmentById(FragmentID);
		mFragment.getData(mbBundle);
	};

	@Override
	public void setDataToActivity(Class<?> ActivityClass, Bundle mbBundle) {
		// ���ô˷����ܴ�fragment��ת��������actvity
		// �˴�ʩ��
	}

	public void setDataToParentActivity(Bundle mbBundle) {
		// ���ô˷����ܴ�fragment�������ݵ���activity
		// �˴�ʩ��
	}

	@Override
	public void AddView(LinearLayout ParentView, int ChildViewID) {

		if (ParentView == null || ChildViewID <= 0) {
			Log.e("com.example.openfragment.Activity", "AddView���������������");
			return;
		}

		View view = LayoutInflater.from(mContext).inflate(ChildViewID, null);
		view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT));
		ParentView.addView(view);
		ParentView.setVisibility(View.VISIBLE);
	}

	@Override
	public void SetViewGone(LinearLayout ParentView, String ParentLayoutViewTAG) {
		boolean IsHaveRegisterError = false;

		if (ParentView == null || ParentLayoutViewTAG == null || ParentLayoutViewTAG.trim().equals("")) {
			Log.e("com.example.openfragment.Activity", "RemoveView���������������");
			return;
		}

		ParentView.setVisibility(View.GONE);

		List<FragmentRegisterBean> mRegisterList = Register.get(ParentLayoutViewTAG);

		for (int i = 0; i < mRegisterList.size(); i++) {
			FragmentRegisterBean mfragmentRegisterBean = mRegisterList.get(i);

			if (mfragmentRegisterBean != null) {
				int mFragmentID = mfragmentRegisterBean.getFragmentID(ParentLayoutViewTAG);
				if (mFragmentID != -1) {
					BaseFragment mFragment = (BaseFragment) manager.findFragmentById(mFragmentID);
					mFragment.onPause();

					IsHaveRegisterError = true;
				}
			}

			if (!IsHaveRegisterError) {
				Log.e("com.example.openfragment.Activity", "����ע���fragment����Ϣ��ע�����Ϣ���ܴ��ڴ���");
			}
		}
	}

	@Override
	public void SetViewInVisibility(LinearLayout ParentView, String ParentLayoutViewTAG) {
		boolean IsHaveRegisterError = false;

		if (ParentView == null || ParentLayoutViewTAG == null || ParentLayoutViewTAG.trim().equals("")) {
			Log.e("com.example.openfragment.Activity", "RemoveView���������������");
			return;
		}

		ParentView.setVisibility(View.INVISIBLE);

		List<FragmentRegisterBean> mRegisterList = Register.get(ParentLayoutViewTAG);

		for (int i = 0; i < mRegisterList.size(); i++) {
			FragmentRegisterBean mfragmentRegisterBean = mRegisterList.get(i);

			if (mRegisterList != null) {
				int mFragmentID = mfragmentRegisterBean.getFragmentID(ParentLayoutViewTAG);
				if (mFragmentID != -1) {
					BaseFragment mFragment = (BaseFragment) manager.findFragmentById(mFragmentID);
					mFragment.onPause();

					IsHaveRegisterError = true;
				}
			}

			if (!IsHaveRegisterError) {
				Log.e("com.example.openfragment.Activity", "����ע���fragment����Ϣ��ע�����Ϣ���ܴ��ڴ���");
			}

		}
	}

	@Override
	public void SetViewVisibility(LinearLayout ParentView, String ParentLayoutViewTAG) {
		boolean IsHaveRegisterError = false;

		if (ParentView == null || ParentLayoutViewTAG == null || ParentLayoutViewTAG.trim().equals("")) {
			Log.e("com.example.openfragment.Activity", "RemoveView���������������");
			return;
		}

		ParentView.setVisibility(View.VISIBLE);

		List<FragmentRegisterBean> mRegisterList = Register.get(ParentLayoutViewTAG);

		for (int i = 0; i < mRegisterList.size(); i++) {
			FragmentRegisterBean mfragmentRegisterBean = mRegisterList.get(i);

			if (mRegisterList != null) {

				int mFragmentID = mfragmentRegisterBean.getFragmentID(ParentLayoutViewTAG);
				if (mFragmentID != -1) {
					BaseFragment mFragment = (BaseFragment) manager.findFragmentById(mFragmentID);
					mFragment.onResume();

					IsHaveRegisterError = true;
				}
			}

			if (!IsHaveRegisterError) {
				Log.e("com.example.openfragment.Activity", "����ע���fragment����Ϣ��ע�����Ϣ���ܴ��ڴ���");
			}
		}
	}

	@Override
	public void RemoveView(LinearLayout ParentView, String ParentLayoutViewTAG) {
		boolean IsHaveRegisterError = false;

		if (ParentView == null || ParentLayoutViewTAG == null || ParentLayoutViewTAG.trim().equals("")) {
			Log.e("com.example.openfragment.Activity", "RemoveView���������������");
			return;
		}

		ParentView.removeAllViews();
		List<FragmentRegisterBean> mRegisterList = Register.get(ParentLayoutViewTAG);

		if (mRegisterList != null) {

			for (int i = 0; i < mRegisterList.size(); i++) {
				FragmentRegisterBean mfragmentRegisterBean = mRegisterList.get(i);
				int mFragmentID = mfragmentRegisterBean.getFragmentID(ParentLayoutViewTAG);
				if (mFragmentID != -1) {
					BaseFragment mFragment = (BaseFragment) manager.findFragmentById(mFragmentID);
					
					transaction = manager.beginTransaction();
					transaction.remove(mFragment);

					IsHaveRegisterError = true;
				}
			}
			
			if (!IsHaveRegisterError) {
				Log.e("com.example.openfragment.Activity", "����ע���fragment����Ϣ��ע�����Ϣ���ܴ��ڴ���");
			}
		}

		transaction.commit();
		transaction = null;
		Register.remove(ParentLayoutViewTAG);
	}

	@Override
	public LinearLayout getView(int ParentId) {
		returnLinearLayout = (LinearLayout) findViewById(ParentId);
		return returnLinearLayout;
	}

	@Override
	public void setHeight(LinearLayout ParentView, int height) {
		ParentView.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, height));
	}

	@Override
	public void SetWidth(LinearLayout ParentView, int width) {
		ParentView.setLayoutParams(new LinearLayout.LayoutParams(width, LayoutParams.WRAP_CONTENT));
	}

	@Override
	public void SetHeightAndWidth(LinearLayout ParentView, int height, int width) {
		ParentView.setLayoutParams(new LinearLayout.LayoutParams(width, height));
	}

	@Override
	public void setBackground(LinearLayout ParentView, Drawable background) {
		ParentView.setBackground(background);
	}

	@Override
	public void setBackgroundColor(LinearLayout ParentView, int color) {
		ParentView.setBackgroundColor(color);
	}

	@Override
	public void onResume() {
		super.onResume();
		if (mIsNeedNetwork) {
//			mBus.register(mContext);
//			mNetworkEvents.register();
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		if (mIsNeedNetwork) {
//			mBus.unregister(mContext);
//			mNetworkEvents.unregister();
		}
	}

	@Override
	protected void onStop() {
		// ��Ҫ��stop����onDestroyʱ�����actvity��fragment
		// �˴�ʩ��
		super.onStop();
	}

}
