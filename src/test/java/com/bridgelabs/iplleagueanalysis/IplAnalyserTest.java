package com.bridgelabs.iplleagueanalysis;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.bridgeLabs.csvHandler.CsvException;

public class IplAnalyserTest {
	private static final String BATSMEN_CSV = "src/main/resources/batsmen.csv";
	private static final String BOWLERS_CSV = "src/main/resources/bowlers.csv";
	private IplAnalyser iplAnalyser;
	
	@Before
	public void setUp() {
		this.iplAnalyser=new IplAnalyser();
		try {
			this.iplAnalyser.loadBatsmenData(BATSMEN_CSV);
			this.iplAnalyser.loadBowlersData(BOWLERS_CSV);
		} catch (CsvException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenBatsmenCsvWhenLoadedShouldReturnTheList() {
		List<Batsman> batsmenList;
		try {
			batsmenList = iplAnalyser.loadBatsmenData(BATSMEN_CSV);
			Assert.assertEquals(101, batsmenList.size());
		} catch (CsvException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenBowlersCsvWhenLoadedShouldReturnTheList() {
		List<Bowler> bowlersList;
		try {
			bowlersList = iplAnalyser.loadBowlersData(BOWLERS_CSV);
			Assert.assertEquals(99, bowlersList.size());
		} catch (CsvException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenBatsmanCsvShouldReturnTopTenAverages() {
		List<Batsman> topAverages;
		try {
			topAverages = iplAnalyser.getTopTenAverages();
			Assert.assertEquals(10, topAverages.size());
			Assert.assertEquals("MS Dhoni" , topAverages.get(0).name);
		} catch (CsvException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenBatsmanCsvShouldReturnTopTenStrikeRates() {
		List<Batsman> topStrikeRates;
		try {
			topStrikeRates = iplAnalyser.getTopTenStrikeRates();
			Assert.assertEquals(10, topStrikeRates.size());
			Assert.assertEquals("Ishant Sharma" , topStrikeRates.get(0).name);
		} catch (CsvException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenBatsmanCsvShouldReturnTopTenBatsmenWithMaximumSixesAndFours() {
		List<Batsman> topNumSixesAndFours;
		try {
			topNumSixesAndFours = iplAnalyser.getTopTenNumSixesAndFours();
			Assert.assertEquals(10, topNumSixesAndFours.size());
			Assert.assertEquals("Andre Russell" , topNumSixesAndFours.get(0).name);
		} catch (CsvException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenBatsmanCsvShouldReturnTopThreeBatsmenWithBestStrikingRatesWithMaximumSixesAndFours() {
		List<Batsman> topStrikeRatesWithNumSixesAndFours;
		try {
			topStrikeRatesWithNumSixesAndFours = iplAnalyser.getTopThreeStrikeRatesWithMaxNumSixesAndFours();
			Assert.assertEquals(3, topStrikeRatesWithNumSixesAndFours.size());
			Assert.assertEquals("Andre Russell" , topStrikeRatesWithNumSixesAndFours.get(0).name);
		} catch (CsvException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenBatsmanCsvShouldReturnTopThreeBatsmenWithBestAveragesWithBestStrikingRates() {
		List<Batsman> topAveragesWithBestStrikeRates;
		try {
			topAveragesWithBestStrikeRates = iplAnalyser.getTopThreeAveragesWithBestStrikeRates();
			Assert.assertEquals(3, topAveragesWithBestStrikeRates.size());
			Assert.assertEquals("Andre Russell" , topAveragesWithBestStrikeRates.get(0).name);
		} catch (CsvException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenBatsmanCsvShouldReturnTopThreeBatsmenWithMostRunsWithBestAverages() {
		List<Batsman> topRunsWithBestAverages;
		try {
			topRunsWithBestAverages = iplAnalyser.getTopThreeMostRunsWithBestAverages();
			Assert.assertEquals(3, topRunsWithBestAverages.size());
			Assert.assertEquals("David Warner" , topRunsWithBestAverages.get(0).name);
		} catch (CsvException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenBowlersCsvShouldReturnTopTenAverages() {
		List<Bowler> topAverages;
		try {
			topAverages = iplAnalyser.getTopTenBowlingAverages();
			Assert.assertEquals(10, topAverages.size());
			Assert.assertEquals("Anukul Roy" , topAverages.get(0).name);
		} catch (CsvException e) {
			e.printStackTrace();
		}
	}
	
}
