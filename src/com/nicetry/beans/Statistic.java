package com.nicetry.beans;

public class Statistic {

	private int nbSessions;

	/*
	 * C. Utilisé un bean pour indiquer (sur votre page principale du site)
	 * combien il y a de session en cours (c'est à dire: combien d'utilisateur
	 * sont en train de magasiner actuellement).
	 */
	public int getNbSessions() {
		return nbSessions;
	}
	
	public void setNbSessions(int nbSessions) {
		this.nbSessions = nbSessions;
	}

}
