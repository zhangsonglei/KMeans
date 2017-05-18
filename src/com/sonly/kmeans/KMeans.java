package com.sonly.kmeans;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Description: the entrance of K-Means
 * @author: Sonly
 * Company: HUST 
 * @date: 2017年5月13日上午8:54:50
 */
public class KMeans {
	private static DecimalFormat df = new DecimalFormat("#0.000000");
	
	/**
	 * @Title: initCenters
	 * Description: initialize the center of each cluster 
	 * @param min 	minimum size
	 * @param max 	maximum size
	 * @param k		count of center
	 * @param count	count of feature
	 * @return double[][]
	 * @throws
	 */
	public static double[][] initCenters(double min, double max, int k, int count) {
		if(count < 1 || min >= max || k < 1) {
			System.err.println("error in method initCenters");
			return null;
		}
			
		double[][]	init = new double[k][count];
		Random random = new Random();
		
		for(int i = 0; i < k; i++)
			for(int j = 0; j < count; j++)
				init[i][j] = Double.parseDouble(df.format(random.nextDouble() * (max - min) + min));
		
		return init;
	}
	
	/**
	 * @Title: Iterator
	 * Description: 
	 * @param dataSet
	 * @param centers
	 * @return double[][]
	 * @throws
	 */
	public static double[][] iterator(double[][] dataSet, double[][] centers) {
		Map<Integer, double[][]> map = new HashMap<>();
		
		for(int i = 0; i < dataSet.length; i++) {
			int index = CalcUtil.indexOfNearestData(centers, dataSet[i]);
			
			if(-1 == index) {
				System.out.println("error in iterator");
				return null;
			}
			
			if(map.containsKey(index)){
				int size_map = map.get(index).length;
				double[][] subSet = new double[size_map + 1][dataSet[0].length];
				
				for(int j = 0; j < size_map; j++)
					subSet[j] = map.get(index)[j];
				subSet[size_map] = dataSet[i];
				
				map.put(index, subSet);
			}else {
				double[][] data = new double[1][dataSet[0].length];
				data[0] = dataSet[i];
				map.put(index, data);
			}
		}
		
		for(Integer key : map.keySet()) {
			double center[][] = map.get(key);
			centers[key] = CalcUtil.calcCenterPoint(center);
		}
		
		return centers;
	}
		
	/**
	 * @throws IOException 
	 * @Title: KMeansIterator
	 * Description: to create cluster
	 * @param dataSet
	 * @param min
	 * @param max
	 * @param k
	 * @param result
	 * @return double[][]
	 * @throws
	 */
	public static void kMeans(double[][] dataSet, double[][] centers, String result) throws IOException {
		int count = 1;
		double[][] thisCenters = centers;
		double[][] newCenters = iterator(dataSet, thisCenters);
		
		while(isChanged(thisCenters, newCenters)) {
			show(newCenters);
			thisCenters = newCenters;
			newCenters = iterator(dataSet, thisCenters);
			count++;
		}
		
		show(newCenters);
		FileOperator.writeFile(CalcUtil.arrayToList(newCenters), result);
		System.out.println(count+"次迭代后收敛");
	}
	
	public static void main(String[] args) throws IOException {
		String readFilePath = "File\\DataSet.utf8";
		String writeFilePath = "File\\result.utf8";
		
		double[][] dataSet = CalcUtil.stringListToArray(FileOperator.readFile(readFilePath));
		double[][] centers = initCenters(-5, 5, 4, dataSet[0].length);
//		double[][] centers ={
//				{-3.6608,2.3086},
//				{3.2437,3.04700}, 
//				{2.5257,-3.1248}, 
//				{-2.7967,3.1920}
//		};
		kMeans(dataSet, centers, writeFilePath);
	}
	
	/**
	 * @Title: isChanged
	 * Description: judge whether center points is changed
	 * @param a
	 * @param b
	 * @return boolean
	 * @throws
	 */
	public static boolean isChanged(double[][] a, double[][] b) {
		int size_a = a.length;
		int size_b = b.length;
		
		if(size_a != size_b || size_a == 0 ||size_b == 0) {
			System.err.println("error size in method isChanged");
			return false;
		}
		
		for(int i = 0; i < size_a; i++){
			if(CalcUtil.calcDistance(a[i], b[i]) > 0.0)
				return true;
		}
		
		return false;
	}
	
	/**
	 * @Title: show
	 * Description: print data
	 * @param datas
	 * @return void
	 * @throws
	 */
	public static void show(double[][] datas) {
		for(double[] data : datas) {
			for(int i = 0; i < data.length; i++)
				System.out.print(data[i] + " ");
			System.out.println();
		}
		System.out.println("-------------------------------------------------");
	}
}
