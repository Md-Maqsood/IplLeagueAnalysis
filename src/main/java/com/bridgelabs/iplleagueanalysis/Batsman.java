package com.bridgelabs.iplleagueanalysis;

import com.opencsv.bean.CsvBindByName;

public class Batsman {

	@CsvBindByName(column = "PLAYER", required = true)
	public String name;
	
	@CsvBindByName(column = "Mat", required = true)
	public Integer matches;
	
	@CsvBindByName(column = "Inns", required = true)
	public Integer innings;
	
	@CsvBindByName(column = "Runs", required = true)
	public Integer runs;
	
	@CsvBindByName(column = "HS", required = true)
	public Integer highest;
	
	@CsvBindByName(column = "Avg", required = true)
	public Double average;
	
	@CsvBindByName(column = "SR", required = true)
	public Double strikeRate;
	
	@CsvBindByName(column = "100", required = true)
	public Integer numHundreds;
	
	@CsvBindByName(column = "50", required = true)
	public Integer numFifties;
	
	@CsvBindByName(column = "4s", required = true)
	public Integer numFours;
	
	@CsvBindByName(column = "6s", required = true)
	public Integer numSixes;

	@Override
	public String toString() {
		return "Batsman [name=" + name + ", matches=" + matches + ", innings=" + innings + ", runs=" + runs
				+ ", highest=" + highest + ", average=" + average + ", strikeRate=" + strikeRate + ", numHundreds="
				+ numHundreds + ", numFifties=" + numFifties + ", numFours=" + numFours + ", numSixes=" + numSixes
				+ "]";
	}

}
