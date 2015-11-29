package com.example.openfragment.Activity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.openfragment.ManageBean.FragmentRegisterBean;
//import com.github.pwittchen.networkevents.library.event.ConnectivityChanged;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.LinearLayout;

public abstract class XActivity extends Activity {

	public Map<String, List<FragmentRegisterBean>> Register = new HashMap<String, List<FragmentRegisterBean>>();

	public Bundle mBundle = new Bundle();

	/**
	 * ���ð���openfragment�ĸ�����(��ģ�岼��)<br>
	 * 
	 * @return int���͵ģ�����Id(�磺R.layout.main)
	 */
	protected abstract int setMainLayout();

	/**
	 * ���ñ�Activity�Ƿ���Ҫ��������仯<br>
	 * ע�⣺ʹ������ʱ��ע�����嵥�ļ��е�������ʷ���Ȩ�ޣ����Ȩ�޴��󣬽�����Ӧ���쳣.
	 * 
	 * @return boolean���͵ģ�mIsNeedNetwork
	 */
	protected abstract boolean setIsNeedNetwork();

	/**
	 * ���ñ�Activity�Ƿ���Ҫʹ��handler<br>
	 * 
	 * @return boolean���͵ģ�IsNeedHandler
	 */
	protected abstract boolean setIsNeedHandler();

	/**
	 * ������ʼ������仯�������ķ���<br>
	 * ��дʱ�����initNetworkEvent(String url,int timeout)����
	 */
	protected abstract void initNetworkEvent();

	/**
	 * ������ʼ������仯�������ķ���<br>
	 * 
	 * @url �����ж��Ƿ���������ķ������ӣ��������������ʾ���������������ʾ����������
	 * @timeout ��ʱʱ��
	 */
	protected abstract void initNetworkEvent(String url, int timeout);

	/**
	 * ����仯ʱ�ͻ���õķ���<br>
	 * ����(���µ�״̬֮һ(����ʵ�ʻ�������)):<br>
	 * UNKNOWN("unknown")<br>
	 * WIFI_CONNECTED("connected to WiFi")<br>
	 * WIFI_CONNECTED_HAS_INTERNET( "connected to WiFi (Internet available)")
	 * <br>
	 * WIFI_CONNECTED_HAS_NO_INTERNET(
	 * "connected to WiFi (Internet not available)")<br>
	 * MOBILE_CONNECTED("connected to mobile network")<br>
	 * OFFLINE("offline")
	 * 
	 */
//	protected abstract void onConnectivityChanged(ConnectivityChanged event);

	/**
	 * �������findViewByID�ĵط�<br>
	 * 
	 * ���棺 ���ڿ��ܻὫ�˴��޸�Ϊ�����ȡID
	 */
	protected abstract void findView();

	/**
	 * ������ʼ��view,�ṩAddView,SetViewGone,SetViewInVisibility�ȷ���<br>
	 * 
	 */
	protected abstract void initView();

	/**
	 * �����ݽ��в�������mIsNeedHandlerΪ��ʱ��ʹ��handler����Ϣ���ݽ��ж�view�ĸ���<br>
	 * 
	 */
	protected abstract void initData();

	/**
	 * ���������е�mhHandler������Ϣ,������initData������ʹ��<br>
	 * ���棺������ֻ����mIsNeedHandlerΪ���ʱ��ſ���ʹ��.
	 */
	protected abstract void setHandlerMessage(int MessageWhat, Bundle mbBundle);;

	/**
	 * ���̵߳Ľ�����Ϣ���գ��������½���<br>
	 * ע�⣺������ֻ����mIsNeedNetwork()��������Ϊ���ʱ��Ż����.
	 */
	protected abstract void GetHandlerMessage();

	/**
	 * ������ת��ָ����activity,�ɴ����ݺͲ�������<br>
	 * ���棺������ֻ����mIsNeedHandlerΪ���ʱ��ſ���ʹ��.(δȷ��)
	 */
	public abstract void setDataToActivity(Class<?> ActivityClass, Bundle mbBundle);

	/**
	 * ������fragment�������ݵ���Activity��<br>
	 * ���棺������ֻ����mIsNeedHandlerΪ���ʱ��ſ���ʹ��.(δȷ��)
	 */
	public abstract void setDataToParentActivity(Bundle mbBundle);

	/**
	 * ������Fragment���͸��½�����Ϣ,������GetHandlerMessage������ʹ��<br>
	 * ���棺������ֻ����mIsNeedHandlerΪ���ʱ��ſ���ʹ��.(δȷ��)
	 */
	public abstract void setDataToFragment(int FragmentID, Bundle mbBundle);

	/**
	 * �ṩ��ViewǶ�뵽ģ�岼���еķ���<br>
	 * 
	 * @ParentView ���ؼ�view
	 * @ChildViewLayoutID ҪǶ��Ĳ���ID
	 */
	public abstract void AddView(LinearLayout ParentView, int ChildViewLayoutID);

	/**
	 * �����ؼ�����GONE״̬<br>
	 * �ṩ����fragment�Ĺ��ܣ�fragment�����ε�ʱ�� ����������fragment��onPause����<br>
	 * 
	 * @ParentView ���ؼ�view
	 * @ChildViewLayoutID ҪǶ��Ĳ���ID
	 */
	public abstract void SetViewGone(LinearLayout ParentView, String ParentLayoutViewTAG);

	/**
	 * �����ؼ�����InVisibility״̬<br>
	 * �ṩ����fragment�Ĺ��ܣ�fragment�����ε�ʱ�� ����������fragment��onPause����<br>
	 * 
	 * @ParentView ���ؼ�view
	 * @ChildViewLayoutID ҪǶ��Ĳ���ID
	 */
	public abstract void SetViewInVisibility(LinearLayout ParentView, String ParentLayoutViewTAG);

	/**
	 * �����ؼ�����Visibility״̬<br>
	 * �ṩ��ʾ������fragment�Ĺ��ܣ�fragment����ʾ��ʱ�� ����������fragment��onResume����<br>
	 * 
	 * @ParentView ���ؼ�view
	 * @ChildViewLayoutID ҪǶ��Ĳ���ID
	 */
	public abstract void SetViewVisibility(LinearLayout ParentView, String ParentLayoutViewTAG);

	/**
	 * �ṩ��fragment��ģ�岼���е��Ƴ�����<br>
	 * 
	 * @ChildViewLayoutID ҪǶ��Ĳ���ID
	 */
	public abstract void RemoveView(LinearLayout ParentView, String ParentLayoutViewTAG);

	/**
	 * ͨ��Activity�����Բ���Id,�ҵ�����Activity�����Բ���View;
	 * 
	 * @ParentId Activity�����Բ���Id
	 */
	public abstract LinearLayout getView(int ParentId);
	
	/**
	 * �������Բ��ָ߶�,�߶�Ĭ��Ϊ�����е�����,���Ĭ��Ϊ��������
	 * 
	 * @ParentView ���ؼ�view
	 * @height Ҫ���õĸ߶ȣ���λ��Px��(���ڿ��ܸ�Ϊdp)
	 */
	public abstract void setHeight(LinearLayout ParentView, int height);

	/**
	 * �������Բ��ֿ��,���Ĭ��Ϊ�����е�����,�߶�Ĭ��Ϊ��������
	 * 
	 * @ParentView ���ؼ�view
	 * @width Ҫ���õĿ�ȣ���λ��Px��(���ڿ��ܸ�Ϊdp)
	 */
	public abstract void SetWidth(LinearLayout ParentView, int width);

	/**
	 * �������Բ��ֿ��,Ĭ��ֵΪ�����е�����
	 * 
	 * @ParentView ���ؼ�view
	 * @height Ҫ���õĸ߶ȣ���λ��Px��(���ڿ��ܸ�Ϊdp)
	 * @width Ҫ���õĿ�ȣ���λ��Px��(���ڿ��ܸ�Ϊdp)
	 */
	// �µ��뷨��ͨ��activityȥgetView���ҵ����ص����Բ���view�Ժ󣬵��ô˷���
	// �˴�ʩ��
	public abstract void SetHeightAndWidth(LinearLayout ParentView, int height, int width);

	/**
	 * �������Բ��ֵı���
	 * 
	 * @ParentView ���ؼ�view
	 * @background Ҫ���õı���ͼƬ
	 */
	// �µ��뷨��ͨ��activityȥgetView���ҵ����ص����Բ���view�Ժ󣬵��ô˷���
	// �˴�ʩ��
	public abstract void setBackground(LinearLayout ParentView, Drawable background);

	/**
	 * �������Բ��ֵı�����ɫ
	 * 
	 * @ParentView ���ؼ�view
	 * @color Ҫ���õ���ɫ
	 */
	public abstract void setBackgroundColor(LinearLayout ParentView, int color);
}
