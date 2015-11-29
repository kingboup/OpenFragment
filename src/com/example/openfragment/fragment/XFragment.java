package com.example.openfragment.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class XFragment extends Fragment {

	/**
	 * ����ע�ᾲ̬Fragment��Avtivity��������<br>
	 * ��дʱ�����SetLayoutTAG(String ParentLayoutViewTAG)<br>
	 * <br>
	 * ����: �����ڱ������н�����������.
	 */
	protected abstract void SetTAG();

	/**
	 * ������Ҫͨ����view��Idȥ���Ҹ���������ע��Ĵ��ڵ�����view��id�飬Ȼ��������ע���ϱ�fragment��id<br>
	 * 
	 * @ParentLayoutViewTAG ���ڵĸ����ֵ�TAG��ǩ
	 */
	protected void SetLayoutTAG(String ParentLayoutViewTAG) {
	}

	@Override
	public abstract View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
	
	/**
	 * �����Ǳ�¶����Ĳ���Fragment��view�ķ�����ͨ���Ի�ȡ�����ݵ�ȡֵ���жϣ�����������
	 * 
	 * @ParentLayoutViewTAG ���ڵĸ����ֵ�TAG��ǩ
	 */
	public abstract void getData(Bundle mBundle);
}
