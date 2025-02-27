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
		this.iplAnalyser = new IplAnalyser();
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
			Assert.assertEquals("MS Dhoni", topAverages.get(0).name);
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
			Assert.assertEquals("Ishant Sharma", topStrikeRates.get(0).name);
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
			Assert.assertEquals("Andre Russell", topNumSixesAndFours.get(0).name);
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
			Assert.assertEquals("Andre Russell", topStrikeRatesWithNumSixesAndFours.get(0).name);
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
			Assert.assertEquals("Andre Russell", topAveragesWithBestStrikeRates.get(0).name);
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
			Assert.assertEquals("David Warner", topRunsWithBestAverages.get(0).name);
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
			Assert.assertEquals("Anukul Roy", topAverages.get(0).name);
		} catch (CsvException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenBowlersCsvShouldReturnTopTenStrikeRates() {
		List<Bowler> topStrikeRates;
		try {
			topStrikeRates = iplAnalyser.getTopTenBowlingStrikeRates();
			Assert.assertEquals(10, topStrikeRates.size());
			Assert.assertEquals("Alzarri Joseph", topStrikeRates.get(0).name);
		} catch (CsvException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenBowlersCsvShouldReturnTopTenEconomyRates() {
		List<Bowler> topEconomyRates;
		try {
			topEconomyRates = iplAnalyser.getTopTenEconomyRates();
			Assert.assertEquals(10, topEconomyRates.size());
			Assert.assertEquals("Shivam Dube", topEconomyRates.get(0).name);
		} catch (CsvException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenBowlersCsvShouldReturnTopThreeStrikeRatesWith5wAnd4w() {
		List<Bowler> topStrikeRatesWith5wor4w;
		topStrikeRatesWith5wor4w = iplAnalyser.getTopThreeStrikeRatesWith5wAnd4w();
		Assert.assertEquals(3, topStrikeRatesWith5wor4w.size());
		Assert.assertEquals("Alzarri Joseph", topStrikeRatesWith5wor4w.get(0).name);
	}

	@Test
	public void givenBowlersCsvShouldReturnTopThreeBestBowlingAveragesWithBestStrikeRates() {
		List<Bowler> topAveragesWithBestStrikeRates;
		try {
			topAveragesWithBestStrikeRates = iplAnalyser.getTopThreeBowlingAveragesWithBestBowlingStrikeRates();
			Assert.assertEquals(3, topAveragesWithBestStrikeRates.size());
			Assert.assertEquals("Anukul Roy", topAveragesWithBestStrikeRates.get(0).name);
		} catch (CsvException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenBowlersCsvShouldReturnTopThreeMaxWicketsWithBestBowlingAverages() {
		List<Bowler> topWicketsWithBestAverages;
		try {
			topWicketsWithBestAverages = iplAnalyser.getTopThreeMaxWicketsWithBestBowlingAverages();
			Assert.assertEquals(3, topWicketsWithBestAverages.size());
			Assert.assertEquals("Imran Tahir", topWicketsWithBestAverages.get(0).name);
		} catch (CsvException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenBatsmanAndBowlersCsvShouldReturnBestBattingAndBowlingAverage() {
		List<Bowler> topBattingAndBowlingAverages;
		topBattingAndBowlingAverages = iplAnalyser.getTopTenBestBowlingAndBattingAverages();
		Assert.assertEquals(10, topBattingAndBowlingAverages.size());
		Assert.assertEquals("Andre Russell", topBattingAndBowlingAverages.get(0).name);
	}

	@Test
	public void givenBatsmanAndBowlersCsvShouldMostRunsAndWickets() {
		List<Bowler> mostRunsAndWickets;
		mostRunsAndWickets = iplAnalyser.getTopTenAllRounders();
		Assert.assertEquals(10, mostRunsAndWickets.size());
		Assert.assertEquals("Kagiso Rabada", mostRunsAndWickets.get(0).name);
	}

	@Test
	public void givenBatsmanCsvShouldReturnTopThreeBatsmenWithMostNumHundredsRunsWithBestAverages() {
		List<Batsman> topNumHundredsWithBestAverages;
		try {
			topNumHundredsWithBestAverages = iplAnalyser.getTopThreeMostNumHundredsWithBestAverages();
			Assert.assertEquals(3, topNumHundredsWithBestAverages.size());
			Assert.assertEquals("David Warner", topNumHundredsWithBestAverages.get(0).name);
		} catch (CsvException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenBatsmanCsvShouldReturnTopTenBatsmenWithNoHundredsOrFiftiesWithBestAverages() {
		List<Batsman> bestAveragesWithoutHundredOrFifty;
		bestAveragesWithoutHundredOrFifty = iplAnalyser.getTopThreeBestAveragesWithoutHundredOrFifty();
		Assert.assertEquals(10, bestAveragesWithoutHundredOrFifty.size());
		Assert.assertEquals("Marcus Stoinis", bestAveragesWithoutHundredOrFifty.get(0).name);
	}
}
