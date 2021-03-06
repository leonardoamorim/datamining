package weka;

import java.io.FileReader;

import weka.clusterers.SimpleKMeans;
import weka.core.*;
import weka.clusterers.Clusterer;
import weka.filters.unsupervised.attribute.AddCluster;

public class ExemploKMeans {
	
	public static void main(String[] args) throws Exception{
		
		FileReader reader = new FileReader("/home/leo/workspace2/DataMining/arquivo.arff");
		Instances instancias = new Instances(reader);
		
		SimpleKMeans agrupamento = new SimpleKMeans();
		EuclideanDistance ed = new EuclideanDistance();
		
		
		String[] options = new String[2];
		options[0] = "-R";
		options[1] = "first-last";
		
		agrupamento.setPreserveInstancesOrder(false);
		agrupamento.setNumClusters(100);
		agrupamento.setDisplayStdDevs(false);
		agrupamento.buildClusterer(instancias);
		agrupamento.setDistanceFunction(ed);
		agrupamento.setMaxIterations(500);
		agrupamento.setSeed(10);
		agrupamento.setDontReplaceMissingValues(false);
		agrupamento.setOptions(options);
	
		Instances clusterCenters = agrupamento.getClusterCentroids();
		Instances clusterSTDDEVs = agrupamento.getClusterStandardDevs();
		
		
		int[] clusterSizes = agrupamento.getClusterSizes();
		
		for(int cluster=0; cluster < clusterCenters.numInstances(); cluster++) {
			System.out.println("Cluster #"+(cluster+1)+": "+clusterSizes[cluster]+" dados.");
			System.out.println("	Centroide: "+clusterCenters.instance(cluster));
			System.out.println("	STDDEV: "+clusterSTDDEVs.instance(cluster));
		}
		
	}
}

