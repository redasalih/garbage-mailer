package classes;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Cette classe permet de traiter les observations du fichier csv
 * 
 * Ces observations peuvent être:
 * 1) observation de la prestation de collecte 
 * qui nous dit quels numéros d'adresse sont traités par la ligne du fichier
 * 
 * 2) observation du jour de la collecte 
 * qui met en relation les numéros d'adresse avec le ou les jours de la collecte
 * @author Justine
 *
 */

public class ObservationsParsage {
	
	private String observation; //l'observation a parser
	
	
	/**
	 * Constructeur de l'objet qui prend en compte l'expression à parser
	 * @param observation
	 */
	public ObservationsParsage(String observation){
		this.observation = StringOperation.sansAccent(observation);
	}
	
	
	/**
	 * Fonction qui parse les observations de la prestation de collecte
	 * et qui renvoie un objet PlageNum contenant les différentes plages
	 * de numéros d'adresse traitées par la ligne du fichier csv
	 * 
	 * @return objet PlageNum contenant les differentes plages de numero
	 */
	public PlageNum determinePlageNum(){
		PlageNum plageNum = new PlageNum();
		
		/**
		 * Nous avons répertoriés les différents motifs possibles présents dans
		 * l'observation.
		 * Ces motifs sont les suivants, une fois trouvé ils engendrent des modifications sur
		 * la plage de numéros d'adresse qui sera renvoyée par cete méthode.
		 */
		
		//Les numeros supplementaires
		Pattern expSup1 = Pattern.compile("^les numeros \\d+-\\d+-\\d+");
		Matcher matcher = expSup1.matcher(observation);
		if(matcher.find()){
			expSup1 = Pattern.compile("\\d+-\\d+-\\d+");
			matcher = expSup1.matcher(observation);
			while(matcher.find()){
				String[] numSup = matcher.group().split("-");
				for(String num : numSup){
					plageNum.numerosSupplementaires.add(Integer.parseInt(num));
				}
			}
		}
		
		Pattern expSup2 = Pattern.compile("^les numeros \\d+ et \\d+");
		matcher = expSup2.matcher(observation);
		if(matcher.find()){
			expSup2 = Pattern.compile("\\d+ et \\d+");
			matcher = expSup2.matcher(observation);
			while(matcher.find()){
				String[] numSup = matcher.group().split(" et ");
				for(String num : numSup){
					plageNum.numerosSupplementaires.add(Integer.parseInt(num));
				}
			}
		}
		
		Pattern expSup3 = Pattern.compile("^le numero \\d+");
		matcher = expSup3.matcher(observation);
		if(matcher.find()){
			plageNum.numerosSupplementaires.add(Integer.parseInt(matcher.group().split("le numero ")[1].split(" ")[0]));
		}
		
		Pattern expSup4 = Pattern.compile("^les numeros \\d+ et \\d+");
		matcher = expSup4.matcher(observation);
		if(matcher.find()){
			expSup4 = Pattern.compile("\\d+ et \\d+");
			matcher = expSup4.matcher(observation);
			while(matcher.find()){
				String[] numSup = matcher.group().split(" et ");
				for(String num : numSup){
					plageNum.numerosSupplementaires.add(Integer.parseInt(num));
				}
			}
		}
		
		
		//Les numeros exclus
		Pattern expExc1 = Pattern.compile("sauf le numero \\d+");
		matcher = expExc1.matcher(observation);
		if(matcher.find()){
			plageNum.numerosExclus.add(Integer.parseInt(matcher.group().split("sauf le numero ")[1].split(" ")[0]));
			plageNum.finPair = 2000;
			plageNum.finImpair = 1999;
		}
		
		Pattern expExc2 = Pattern.compile("sauf les numeros \\d+-\\d+-\\d+");
		matcher = expExc2.matcher(observation);
		if(matcher.find()){
			expExc2 = Pattern.compile("\\d+-\\d+-\\d+");
			matcher = expExc2.matcher(observation);
			while(matcher.find()){
				String[] numExc = matcher.group().split("-");
				for(String num : numExc){
					plageNum.numerosExclus.add(Integer.parseInt(num));
					plageNum.finPair = 2000;
					plageNum.finImpair = 1999;
				}
			}
		}
		
		Pattern expExc3 = Pattern.compile("sauf \\d+");
		matcher = expExc3.matcher(observation);
		if(matcher.find()){
			expExc3 = Pattern.compile("\\d+");
			matcher = expExc3.matcher(observation);
			while(matcher.find()){
					plageNum.numerosExclus.add(Integer.parseInt(matcher.group()));
					plageNum.finPair = 2000;
					plageNum.finImpair = 1999;
			}
		}
		
		Pattern expExc4 = Pattern.compile("sauf \\d+-\\d+-\\d+");
		matcher = expExc4.matcher(observation);
		if(matcher.find()){
			expExc4 = Pattern.compile("\\d+-\\d+-\\d+");
			matcher = expExc4.matcher(observation);
			while(matcher.find()){
				String[] numExc = matcher.group().split("-");
				for(String num : numExc){
					plageNum.numerosExclus.add(Integer.parseInt(num));
					plageNum.finPair = 2000;
					plageNum.finImpair = 1999;
				}
			}
		}
		Pattern expExc5 = Pattern.compile("sauf \\d+ et \\d+");
		matcher = expExc5.matcher(observation);
		if(matcher.find()){
			expExc5 = Pattern.compile("\\d+ et \\d+");
			matcher = expExc5.matcher(observation);
			while(matcher.find()){
				String[] numExc = matcher.group().split(" et ");
				for(String num : numExc){
					plageNum.numerosExclus.add(Integer.parseInt(num));
					plageNum.finPair = 2000;
					plageNum.finImpair = 1999;
				}
			}
		}
		
		Pattern expExc6 = Pattern.compile("sauf le \\d+");
		matcher = expExc6.matcher(observation);
		if(matcher.find()){
			expExc6 = Pattern.compile("\\d+");
			matcher = expExc6.matcher(observation);
			while(matcher.find()){
					plageNum.numerosExclus.add(Integer.parseInt(matcher.group()));
					plageNum.finPair = 2000;
					plageNum.finImpair = 1999;
			}
		}
		
		Pattern expExc7 = Pattern.compile("sauf les numeros \\d+ et \\d+");
		matcher = expExc7.matcher(observation);
		if(matcher.find()){
			expExc7 = Pattern.compile("\\d+ et \\d+");
			matcher = expExc7.matcher(observation);
			while(matcher.find()){
				String[] numExc = matcher.group().split(" et ");
				for(String num : numExc){
					plageNum.numerosExclus.add(Integer.parseInt(num));
					plageNum.finPair = 2000;
					plageNum.finImpair = 1999;
				}
			}
		}
		
		Pattern expExc8 = Pattern.compile("sauf les numeros \\d+ a \\d+");
		matcher = expExc8.matcher(observation);
		if(matcher.find()){
			expExc8 = Pattern.compile("\\d+ a \\d+");
			matcher = expExc8.matcher(observation);
			while(matcher.find()){
				String[] numExc = matcher.group().split(" a ");
				for(int j = Integer.parseInt(numExc[0]) ; j==Integer.parseInt(numExc[1]) ;j++){
					plageNum.numerosExclus.add(j);
					plageNum.finPair = 2000;
					plageNum.finImpair = 1999;
				}
			}
		}
		
		Pattern expExc9 = Pattern.compile("sauf les \\d+ et \\d+");
		matcher = expExc9.matcher(observation);
		if(matcher.find()){
			expExc9 = Pattern.compile("\\d+ et \\d+");
			matcher = expExc9.matcher(observation);
			while(matcher.find()){
				String[] numExc = matcher.group().split(" et ");
				for(String num : numExc){
					plageNum.numerosExclus.add(Integer.parseInt(num));
					plageNum.finPair = 2000;
					plageNum.finImpair = 1999;
				}
			}
		}
		
		
		//Les intervalles
		Pattern expInt1 = Pattern.compile("^des numeros \\d+ a \\d+ et \\d+ a \\d+");
		matcher = expInt1.matcher(observation);
		if(matcher.find()){
			expInt1 = Pattern.compile("\\d+ a \\d+ et \\d+ a \\d+");
			matcher = expInt1.matcher(observation);
			while(matcher.find()){
				String IntImp = matcher.group().split(" et ")[0];
				String IntP = matcher.group().split(" et ")[1];
				plageNum.debutImpair = Integer.parseInt(IntImp.split(" a ")[0]);
				plageNum.finImpair = Integer.parseInt(IntImp.split(" a ")[1]);
				plageNum.debutPair = Integer.parseInt(IntP.split(" a ")[0]);
				plageNum.finPair = Integer.parseInt(IntP.split(" a ")[1]);
			}
		}
		
		Pattern expInt2 = Pattern.compile("^a partir des numeros \\d+ et \\d+");
		matcher = expInt2.matcher(observation);
		if(matcher.find()){
			expInt2 = Pattern.compile("\\d+ et \\d+");
			matcher = expInt2.matcher(observation);
			while(matcher.find()){
				plageNum.debutImpair = Integer.parseInt(matcher.group().split(" et ")[0]);
				plageNum.debutPair = Integer.parseInt(matcher.group().split(" et ")[1]);
				plageNum.finPair = 2000;
				plageNum.finImpair = 1999;
			}
		}
		
		Pattern expInt3 = Pattern.compile("^jusqu'aux numeros \\d+ et \\d+");
		matcher = expInt3.matcher(observation);
		if(matcher.find()){
			expInt3 = Pattern.compile("\\d+ et \\d+");
			matcher = expInt3.matcher(observation);
			while(matcher.find()){
				plageNum.finImpair = Integer.parseInt(matcher.group().split(" et ")[0]);
				plageNum.finPair = Integer.parseInt(matcher.group().split(" et ")[1]);
			}
		}
		
		Pattern expInt4 = Pattern.compile("[a-zA-Z0-9]*a partir du numero \\d+");
		matcher = expInt4.matcher(observation);
		if(matcher.find()){
			plageNum.debutImpair = Integer.parseInt(matcher.group().split("a partir du numero ")[1]);
			plageNum.debutPair = Integer.parseInt(matcher.group().split("a partir du numero ")[1]);
			plageNum.finPair = 2000;
			plageNum.finImpair = 1999;
		}
		
		Pattern expInt5 = Pattern.compile("^jusqu'au numero \\d+");
		matcher = expInt5.matcher(observation);
		if(matcher.find()){
			expInt5 = Pattern.compile("\\d+");
			matcher = expInt5.matcher(observation);
			while(matcher.find()){
				plageNum.finImpair = Integer.parseInt(matcher.group());
				plageNum.finPair = Integer.parseInt(matcher.group());
			}
		}
		
		Pattern expInt6 = Pattern.compile("^tous les numeros");
		matcher = expInt6.matcher(observation);
		if(matcher.find()){
			plageNum.finImpair = 1999;
			plageNum.finPair = 2000;
		}
		
		
		//Les plages de numeros impairs
		Pattern expImp1 = Pattern.compile("^numeros impairs$");
		matcher = expImp1.matcher(observation);
		if(matcher.find()){
			plageNum.debutImpair = 1;
			plageNum.finImpair = 1999;
		}
		
		Pattern expImp2 = Pattern.compile("^numeros impairs a partir du numero \\d+");
		matcher = expImp2.matcher(observation);
		if(matcher.find()){
			expImp2 = Pattern.compile("\\d+");
			matcher = expImp2.matcher(observation);
			while(matcher.find()){
				plageNum.debutImpair = Integer.parseInt(matcher.group());
				plageNum.finImpair = 1999;
			}
		}
		
		Pattern expImp3 = Pattern.compile("^numeros impairs jusqu'au numero \\d+");
		matcher = expImp3.matcher(observation);
		if(matcher.find()){
			expImp3 = Pattern.compile("\\d+");
			matcher = expImp3.matcher(observation);
			while(matcher.find()){
				plageNum.debutImpair = 1;
				plageNum.finImpair = Integer.parseInt(matcher.group());
			}
		}
		
		Pattern expImp4 = Pattern.compile("^numeros impairs du \\d+ au \\d+");
		matcher = expImp4.matcher(observation);
		if(matcher.find()){
			expImp4 = Pattern.compile("\\d+ au \\d+");
			matcher = expImp4.matcher(observation);
			while(matcher.find()){
				String[] numImp = matcher.group().split(" au ");
				 plageNum.debutImpair = Integer.parseInt(numImp[0]); 
				 plageNum.finImpair = Integer.parseInt(numImp[1]);
			}
		}
		
		
		//Les plages de numeros pairs
		Pattern expP1 = Pattern.compile("^numeros pairs$");
		matcher = expP1.matcher(observation);
		if(matcher.find()){
			plageNum.debutPair = 0;
			plageNum.finPair = 2000;
		}
		
		Pattern expP2 = Pattern.compile("^numeros pairs a partir du numero \\d+");
		matcher = expP2.matcher(observation);
		if(matcher.find()){
			expP2 = Pattern.compile("\\d+");
			matcher = expP2.matcher(observation);
			while(matcher.find()){
				plageNum.debutPair = Integer.parseInt(matcher.group());
				plageNum.finPair = 1999;
			}
		}
		
		Pattern expP3 = Pattern.compile("^numeros pairs jusqu'au numero \\d+");
		matcher = expP3.matcher(observation);
		if(matcher.find()){
			expP3 = Pattern.compile("\\d+");
			matcher = expP3.matcher(observation);
			while(matcher.find()){
				plageNum.debutPair = 0;
				plageNum.finPair = Integer.parseInt(matcher.group());
			}
		}
		
		Pattern expP4 = Pattern.compile("^numeros pairs du \\d+ au \\d+");
		matcher = expP4.matcher(observation);
		if(matcher.find()){
			expP4 = Pattern.compile("\\d+ au \\d+");
			matcher = expP4.matcher(observation);
			while(matcher.find()){
				String[] numP = matcher.group().split(" au ");
				 plageNum.debutPair = Integer.parseInt(numP[0]); 
				 plageNum.finPair = Integer.parseInt(numP[1]);
			}
		}
		
		
		return plageNum;
	}
	
	/**
	 * Fonction qui parse les observations du jour de la collecte
	 * et qui renvoie un objet PlageJour contenant les différentes plages
	 * de numéros d'adresse traitées par la ligne du fichier csv associées
	 * à un ou plusieurs jours de collecte des poubelles bleues
	 * @return Plagejour contenant les differentes plages de numeros
	 */
	public PlageJour determinePlageJour(){
		PlageJour plageJour = new PlageJour();
		/**
		 * la chaine est de type : 
		 * jusqu'aux numeros [numImpair] et [numPair] : [jour1/jour2] ; A partir des numéros : [numImpair] et [numPair] : [jour1/jour2]
		 * ou
		 * jusqu'aux numeros [numImpair] et [numPair] : [jour1/jour2] ; numéros pairs a partir du : [numPair] : [jour1/jour2]
		 * ou
		 * jusqu'aux numeros [numImpair] et [numPair] : [jour1/jour2] ; numéros impairs a partir du : [numImPair] : [jour1/jour2]
		
		 */
		
		//on sépare la chaine par le ";"
		String[] observationSplittee = this.observation.split(";");
		
		/**
		 * splitt du "jusqu'au"
		 */
		String [] jusquau = observationSplittee[0].split(" ");
		//recuperation du ou des jours de collecte
		String[] jours1 = observationSplittee[0].split(":")[1].split(" ")[1].split("/");
		for(String jour : jours1){
			plageJour.Jours1.add(jour);
		}
		
		//recuperation des numeros d'adresse
		Boolean numImpairTrouve = false;
		for(String mot : jusquau){
			try {
				Integer.parseInt(mot);
				if(!numImpairTrouve){
					numImpairTrouve = true;
					plageJour.finImpair1 = Integer.parseInt(mot);
				}
				else{
					plageJour.finPair1 = Integer.parseInt(mot);
				}
			} catch (NumberFormatException e){
			}
		}
		
		/**
		 * splitt du "à partir de"
		 */
		String [] apartirde = observationSplittee[1].split(" ");
		//recuperation du ou des jours
		String[] jours2 = observationSplittee[1].split(":")[1].split(" ")[1].split("/");
		for(String jour : jours2){
			plageJour.Jours2.add(jour);
		}
		
		Boolean pair = false;
		Boolean impair = false;
		//si "numéros pairs à partir de"
		for(String mot : apartirde){
			if(mot.equals("pairs")){
				pair = true;
			}
			if(pair){
				try {
					Integer.parseInt(mot);
					plageJour.debutPair2 = Integer.parseInt(mot);
					plageJour.finPair2 = 1000;
					plageJour.debutImpair2 = 0;
					plageJour.finImpair2 = 0;
				} catch (NumberFormatException e){
				}
			}
		}

		//si "numéros impairs à partir de"
		if(!pair){
			for(String mot : apartirde){
				if(mot.equals("impairs")){
					impair = true;
				}
				if(impair){
					try {
						Integer.parseInt(mot);
						plageJour.debutImpair2 = Integer.parseInt(mot);
						plageJour.finImpair2 = 999;
						plageJour.debutPair2 = 0;
						plageJour.finPair2 = 0;
					} catch (NumberFormatException e){
					}
				}
			}
			
		}
		//si "à partir de"
		if(!pair && !impair){
			Boolean pairTrouve = false;
			for(String mot : apartirde){
				if(!pairTrouve){
					try {
						Integer.parseInt(mot);
						pairTrouve = true;
						plageJour.debutPair2 = Integer.parseInt(mot);
						plageJour.finPair2 = 1000;
					} catch (NumberFormatException e){
					}
				}
				else{
					try {
						Integer.parseInt(mot);
						pairTrouve = true;
						plageJour.debutImpair2 = Integer.parseInt(mot);
						plageJour.finImpair2 = 999;
					} catch (NumberFormatException e){
					}
				}
			}
			
		}
		
		return plageJour;
	}
	
	/**
	 * Fonction test de ObservationsParsage
	 * @param args
	 */
	public static void main(String[] args) {
		Integer numAdresse = 31;
		Boolean numAdresse_est_pair = false; //est-il pair?
		if(numAdresse%2 == 0){
			numAdresse_est_pair = true;
		}
		
		Boolean ligne_trouvee = false;
		ObservationsParsage obs = new ObservationsParsage("sauf le numero 30 seulement");
		PlageNum plageNum = obs.determinePlageNum();
		
		if(numAdresse_est_pair){
			if( ( (numAdresse >= plageNum.debutPair) && 
					(numAdresse <= plageNum.finPair) &&
					(!plageNum.numerosExclus.contains(numAdresse)) ) || 
					( plageNum.numerosSupplementaires.contains(numAdresse) ) ){
				ligne_trouvee = true;
			}
		}
		else{
			if( ( (numAdresse >= plageNum.debutImpair) && 
					(numAdresse <= plageNum.finImpair) &&
					(!plageNum.numerosExclus.contains(numAdresse)) ) ||
					( plageNum.numerosSupplementaires.contains(numAdresse) ) ){
				ligne_trouvee = true;
			}
		}
		
		if(ligne_trouvee){ System.out.println("trouve!");}
	}

}