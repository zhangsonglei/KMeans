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
	 * @Title: stringListToArray
	 * Description: translate List<String[]>  to double[][] array
	 * @param dataSet
	 * @return double[][]
	 * @throws
	 */
	public static double[][] stringListToArray(List<String[]> dataSet) {
		int column = dataSet.get(0).length;
		int row = dataSet.size();
		double[][] simple = new double[row][column];
		
		for(int i = 0; i < dataSet.size(); i++) {
			String[] line = dataSet.get(i);
			
			for(int j = 0; j < line.length; j++) 
				simple[i][j] = Double.parseDouble(line[j]);
		}
		
		return simple;
	}
	
	/**
	 * @Title: doubleListToArray
	 * Description: translate List<double[]>  to double[][] array
	 * @param dataSet
	 * @return double[][]
	 * @throws
	 */
	public static double[][] doubleListToArray(List<double[]> dataSet) {
		
		int column = dataSet.get(0).length;
		int row = dataSet.size();
		double[][] simple = new double[row][column];
			
		for(int i = 0; i < dataSet.size(); i++) {
			double[] line = dataSet.get(i);
			
			for(int j = 0; j < line.length; j++) 
				simple[i][j] = line[j];
		}
		
		return simple;
	}
	
	/**
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
	 * @Title: calcDistance
	 * Description: to calculate the Euclidean distance of two data
	 * @param a
	 * @param b
	 * @return double
	 * @throws
	 */
	public static double calcDistance(double[] a, double[] b) {
		double distance = 0.0;
		if(a.length != b.length)
			return -1;
		
		for(int i = 0; i < a.length; i++)
			distance += (a[i] - b[i]) * (a[i] - b[i]);
		
		return Double.parseDouble(df.format(Math.sqrt(distance)));
	}
	
	/**
	 * @Title: calcCenterPoint
	 * Description: to calculate the center point of data set
	 * @param dataSet
	 * @return double[]
	 * @throws
	 */
	public static double[] calcCenterPoint(double[][] dataSet) {
		double[] center = new double[dataSet[0].length];
		int column	= dataSet[0].length;
		int row		= dataSet.length;
		
		if(column == 0 || row == 0) {
			System.err.println("error size in method calcCenterPoint");
			return null;
		}
		
		for(int i = 0; i < column; i++){
			for(int j = 0; j < row; j++) 
				center[i] += dataSet[j][i];
			
			center[i] = Double.parseDouble(df.format(center[i] / row));
		}
		
		return center;
	}
	
	/**
	 * @Title: calcNearestData
	 * Description: get the nearest center to simple
	 * @param a
	 * @param b
	 * @return double[]
	 * @throws
	 */
	public static int indexOfNearestData(double[][] a, double[] b) {
		int size_a = a.length;
		int size_b = b.length;
		
		if(size_a ==0 || size_b == 0) {
			System.err.println("error size in method indexOfNearestData");
			return -1;
		}
					
		double[] dist = new double[size_a];
		for(int i = 0; i < size_a; i++)
			dist[i] = calcDistance(a[i], b);
		
		return indexOfMin(dist);
	}
	
	/**
	 * @Title: indexOfMin
	 * Description: get the index of minimum in array 
	 * @param data
	 * @return int
	 * @throws
	 */
	private static int indexOfMin(double[] data) {
		int index = 0;
		int size = data.length;
		
		if(0 == size) {
			System.err.println("error size in method indexOfMin");
			return -1;
		}else if(1 == size)
			return 0;
		
		double min = data[0];
		for(int i = 1; i < size; i++)
			if(data[i] < min) {
				min = data[i];
				index = i;
			}
		
		return index;
	}
	
	
}