package util;

import java.util.ArrayList;
import java.util.List;

/**
 * ��ʱ����������¼�㷨�ĺ�ʱ
 * @author zyy
 *
 */
public class Timer {

	List<Long> times;

	static Timer timer = new Timer();

	private Timer() {

	}

	public static Timer getInstance() {
		return timer;
	}

	/**
	 * ��ʼ��ʱ
	 */
	public void start() {
		times = new ArrayList<Long>();
		times.add(getMilionTime());
	}

	/**
	 * ���¼�ʱ������Ϊһ���ָ��
	 */
	public void click() {
		times.add(getMilionTime());
	}

	/**
	 * ������ϴ�click��ʱ����
	 * @param describe �ó�������
	 * @return ��ʱ���ַ�����ʾ
	 */
	public void printTimeInterval(String describe) {
		System.out.println(getTimeInterval(describe));
	}

	/**
	 * ��ȡ���һ�����ϴ�click��ʱ����
	 * @param describe �ó�������
	 * @return ��ʱ���ַ�����ʾ
	 */
	public String getTimeInterval(String describe) {
		long t = times.get(times.size() - 1) - times.get(times.size() - 2);
		StringBuilder sb = new StringBuilder("cost time :");
		sb.append(formatTime(t));
		sb.append(" " + (describe == null ? "" : describe));
		return sb.toString();
	}

	/**
	 * ���¼�ʱ�����зָ�����������ϴ�clickʱ��ʱ����
	 * @param describe �ó�������
	 * @return ��ʱ���ַ�����ʾ
	 */
	public void clickAndPrintInterval(String describe) {
		click();
		System.out.println(getTimeInterval(describe));
	}

	public void clickAndPrintTotalTimeCost(String describe) {
		click();
		long t = times.get(times.size() - 1) - times.get(0);
		System.out
				.println("total cost time :" + formatTime(t) + "," + describe);
	}

	/**
	 * �ܹ���ʱ
	 * @return �ܺ�ʱ���ַ�����ʾ
	 */
	public String getCostTime() {
		long t = times.get(times.size() - 1) - times.get(0);
		return formatTime(t);
	}

	private String formatTime(long time) {
		long[] tf = new long[3];
		String[] tunit = { "m", "s", "ms" };
		tf[2] = time % 1000;
		time /= 1000;
		tf[1] = time % 60;
		time /= 60;
		tf[0] = time;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 3; i++) {
			sb.append(tf[i]);
			sb.append(tunit[i]);
			sb.append(" ");
		}
		return sb.toString();
	}

	public long getMilionTime() {
		return System.currentTimeMillis();
	}

	public static void main(String[] args) {
		Timer t = new Timer();
		t.start();
		t.clickAndPrintInterval(null);
		t.clickAndPrintInterval(null);
		t.clickAndPrintInterval(null);
		System.out.println(t.formatTime(60000));
	}

}
