package weka;

import java.io.FileReader;

import weka.clusterers.SimpleKMeans;
import weka.core.*;
import weka.filters.unsupervised.attribute.*;

public class ExemploKMeans {
	
	public static void main(String[] args) throws Exception{
		FileReader reader = new FileReader("/home/leo/workspace2/DataMining/arquivo.arff");
		Instances instancias = new Instances(reader);
		
		//Ignorando os 10 primeiros atributos (considerar apenas os atributos espaciais)
	    instancias.deleteAttributeAt(0);
	    instancias.deleteAttributeAt(1);
	    instancias.deleteAttributeAt(2);
	    instancias.deleteAttributeAt(3);
	    instancias.deleteAttributeAt(4);
	    instancias.deleteAttributeAt(5);
	   // instancias.deleteAttributeAt(6);
	   // instancias.deleteAttributeAt(7);
	   // instancias.deleteAttributeAt(8);
	   // instancias.deleteAttributeAt(9);



		SimpleKMeans agrupamento = new SimpleKMeans();
		
		agrupamento.setPreserveInstancesOrder(true);
		agrupamento.setNumClusters(100);
		agrupamento.setDisplayStdDevs(true);
		agrupamento.buildClusterer(instancias);
		
		Instances clusterCenters = agrupamento.getClusterCentroids();
		Instances clusterSTDDEVs = agrupamento.getClusterStandardDevs();
		
		int[] clusterSizes = agrupamento.getClusterSizes();
		
		for(int cluster=0; cluster < clusterCenters.numInstances(); cluster++) {
			System.out.println("Cluster #"+(cluster+1)+": "+clusterSizes[cluster]+" dados.");
			//System.out.println("	Centroide: "+clusterCenters.instance(cluster));
			//System.out.println("	STDDEV: "+clusterSTDDEVs.instance(cluster));
		}
		
		int[] assignments = agrupamento.getAssignments();
		
		int i=0;
		for(int clusterNum : assignments) {
		    System.out.printf("Instance %d -> Cluster %d", i, clusterNum);
		    System.out.println("");
		    i++;
		}
		
		
		
	}

}
