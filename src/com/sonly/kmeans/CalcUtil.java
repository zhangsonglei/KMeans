package com.sonly.kmeans;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * provide practical calculate methods used in K-Means
 * Description: 
 * @author: Sonly
 * Company: HUST 
 * @date: 2017年5月13日上午9:00:04
 */
public class CalcUtil {
	private static DecimalFormat df = new DecimalFormat("#0.000000");
	/**
	 * 
	 * @Title: preDataSet
	 * Description: translate List<String[]>  to double[][] array
	 * @param dataSet
	 * @return double[][]
	 * @throws
	 */
	public static double[][] listToArray(List<String[]> dataSet) {
		if(0 == dataSet.size()) {
			System.err.println("null data set");
			return null;
		}else {
			int column = dataSet.get(0).length;
			int row = dataSet.size();
			double[][] simple = new double[row][column];
			
			for(int i = 0; i < dataSet.size(); i++) {
				String[] line = dataSet.get(i);
			
				for(int j = 0; j < line.length; j++) {
					simple[i][j] = Double.parseDouble(line[j]);
//					System.out.println(line[j]);
				}
			}
			return simple;
		}
	}

	/**
	 * 
	 * @Title: arrayToList
	 * Description: translate double[][] to list(each double[] to string)
	 * @param data
	 * @return List<String>
	 * @throws
	 */
	public static List<String> arrayToList(double[][] data) {
		List<String> list = new ArrayList<>();
		
		for(int i = 0; i < data.length; i++) {
			String string = new String();
			for(int j = 0; j < data[i].length; j++) 
				string +=  String.valueOf(data[i][j]) +" ";
			
			list.add(string);
		}
		return list;
	}
	
	/**
	 * 
	 * @Title: calcDistance
	 * Description: to calculate the Euclidean distance of two data
	 * @param a
	 * @param b
	 * @return double
	 * @throws
	 */
	public static double calcDistance(double[] a, double[] b) {
		if((a.length != b.length) || (0 == a.length) ||(0 == b.length)) {
			System.err.println("error count of features between to data");
			return -1;
		}else {
			double distance = -1.0;
			
			for(int i = 0; i < a.length; i++)
				distance += (a[i] - b[i]) * (a[i] - b[i]);
			return Math.sqrt(Double.parseDouble(df.format(distance)));
		}
	}
	
	/**
	 * 
	 * @Title: calcCenterPoint
	 * Description: to calculate the center point of data set
	 * @param dataSet
	 * @return double[]
	 * @throws
	 */
	public static double[] calcCenterPoint(double[][] dataSet) {
		double[] center = new double[dataSet[0].length];
		
		for(int column = 0; column < dataSet[0].length; column++){
			for(int row = 0; row < dataSet.length; row++) 
				center[column] += dataSet[column][row];
			center[column] = Double.parseDouble(df.format(center[column]/dataSet.length)) ;
		}
		
		return center;
	}

	
}
