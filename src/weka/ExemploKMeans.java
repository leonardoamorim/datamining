package weka;

import java.io.FileReader;

import weka.clusterers.SimpleKMeans;
import weka.core.*;
import weka.clusterers.Clusterer;
import weka.filters.unsupervised.attribute.AddCluster;

public class ExemploKMeans {
	
	public static void main(String[] args) throws Exception{
		
		//Lendo o arquivo arquivo.arff que contem 100.000 instancias
		FileReader reader = new FileReader("/home/leo/workspace2/DataMining/arquivo.arff");
		Instances instancias = new Instances(reader);
		
		//Deletando os 10 primeiros atributos (considerar apenas os atributos espaciais)
	    //instancias.deleteAttributeAt(0);
	    //instancias.deleteAttributeAt(1);
	    //instancias.deleteAttributeAt(2);
	    //instancias.deleteAttributeAt(3);
	    //instancias.deleteAttributeAt(4);
	    //instancias.deleteAttributeAt(5);
	    //instancias.deleteAttributeAt(6);
	    //instancias.deleteAttributeAt(7);
	   // instancias.deleteAttributeAt(8);
	   // instancias.deleteAttributeAt(9);


		/*
		 * Definindo metodo de clusterizacao usando o algoritmo SimpleKMeans
		 * Criando 100 clusters usando a tecnica de Distancia Euclidiana
		 * 
		 */
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
		
		AddCluster teste = new AddCluster();
		teste.setIgnoredAttributeIndices("0,1,2,3,4,5,6,7,8,9");
		teste.getClusterer();
		//Obtendo as centroides		
		Instances clusterCenters = agrupamento.getClusterCentroids();
		Instances clusterSTDDEVs = agrupamento.getClusterStandardDevs();
		
		
		//Obtendo informacoes sobre os clusters
		int[] clusterSizes = agrupamento.getClusterSizes();
		
		//for(int cluster=0; cluster < clusterCenters.numInstances(); cluster++) {
		//	System.out.println("Cluster #"+(cluster+1)+": "+clusterSizes[cluster]+" dados.");
		//	System.out.println("	Centroide: "+clusterCenters.instance(cluster));
		//	System.out.println("	STDDEV: "+clusterSTDDEVs.instance(cluster));
		//}
		
		
		//Obtendo as intancias do arquivo arquivo.arff
		for(int x=0; x < agrupamento.getNumClusters(); x++) {
			System.out.println(instancias.instance(x));
		}
	
		
		
	}

}
