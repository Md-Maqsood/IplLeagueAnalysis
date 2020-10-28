package com.bridgelabs.iplleagueanalysis;

import com.opencsv.bean.CsvBindByName;

public class Bowler {

	@CsvBindByName(column = "PLAYER", required = true)
	public String name;
	
	@CsvBindByName(column = "Mat", required = true)
	public Integer matches;
	
	@CsvBindByName(column = "Inns", required = true)
	public Integer innings;
	
	@CsvBindByName(column = "Runs", required = true)
	public Integer runs;
	
	@CsvBindByName(column = "Ov", required = true)
	public Double overs;
	
	@CsvBindByName(column = "Avg", required = true)
	public Double bowlingAverage;
	
	@CsvBindByName(column = "SR", required = true)
	public Double bowlingStrikeRate;
	
	@CsvBindByName(column = "WKts", required = true)
	public Integer wickets;
	
	@CsvBindByName(column = "Econ", required = true)
	public Double economy;
	
	@CsvBindByName(column = "4w", required = true)
	public Integer num4w;
	
	@CsvBindByName(column = "5w", required = true)
	public Integer num5w;

	@Override
	public String toString() {
		return "Bowler [name=" + name + ", matches=" + matches + ", innings=" + innings + ", runs=" + runs + ", overs="
				+ overs + ", bowlingAverage=" + bowlingAverage + ", bowlingStrikeRate=" + bowlingStrikeRate + ", wickets=" + wickets + ", economy="
				+ economy + ", num4w=" + num4w + ", num5w=" + num5w + "]";
	}
	
}
