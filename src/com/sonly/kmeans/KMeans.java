package com.sonly.kmeans;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

/**
 * 
 * Description: the entrance of K-Means
 * @author: Sonly
 * Company: HUST 
 * @date: 2017年5月13日上午8:54:50
 */
public class KMeans {
	
	private static DecimalFormat df = new DecimalFormat("#0.000000");
	
	/**
	 * 
	 * @Title: initCenters
	 * Description: initialize the center of each cluster 
	 * @param min
	 * @param max
	 * @param k
	 * @param count
	 * @return double[][]
	 * @throws
	 */
	public static double[][] initCenters(double min, double max, int k, int count) {
		double[][]	init = new double[k][count];
		Random random = new Random();
		
		for(int i = 0; i < k; i++)
			for(int j = 0; j < count; j++)
				init[i][j] = Double.parseDouble(df.format(random.nextDouble() * (max - min) + min));
		
		return init;
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
	public static void KMeansIterator(double[][] dataSet, double min, double max, int k, String result) throws IOException {
		double[][] centers = initCenters(min, max, k, dataSet[0].length);
		
		
		
		FileOperator.writeFile(CalcUtil.arrayToList(centers), result);
	}
	
	public static void main(String[] args) throws IOException {
		
		String readFilePath = "File\\DataSet.utf8";
		String writeFilePath = "File\\result.utf8";
		List<String[]> list = FileOperator.readFile(readFilePath);

		double[][] dataSet = CalcUtil.listToArray(list);
		KMeansIterator(dataSet, -5, 5, 4, writeFilePath);

	}

}
