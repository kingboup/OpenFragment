package com.example.openfragment.ManageBean;

public class FragmentRegisterBean {

	public String ParentLayoutTAG;
	public int FragmentID;

	public FragmentRegisterBean(String ParentLayoutTAG, int FragmentID) {
		this.FragmentID = FragmentID;
		this.ParentLayoutTAG = ParentLayoutTAG;
	}

	/**
	 * ����Fragment��ID��ȡ����صĲ���TAG<br>
	 * 
	 * @return String���͵ģ�ParentLayoutTAG ����: null
	 */
	public String getParentLayoutTAG(int FragmentID) {
		if (this.FragmentID == FragmentID) {
			return this.ParentLayoutTAG;
		} else {
			return null;
		}
	}

	/**
	 * ����Fragment�ĸ��ؼ�TAG��ȡ����Fragment��ID
	 * 
	 * @return int���͵�:FragmentID ���� int���͵����֣� -1;<br>
	 *         -1��ʾû���ҵ���ص�id
	 */
	public int getFragmentID(String ParentLayoutTAG) {
		if (this.ParentLayoutTAG.equals(ParentLayoutTAG)) {
			return FragmentID;
		} else {
			return -1;
		}
	}
}
