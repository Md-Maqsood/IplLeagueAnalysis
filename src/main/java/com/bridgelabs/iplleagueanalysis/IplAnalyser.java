package com.bridgelabs.iplleagueanalysis;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.bridgeLabs.csvHandler.CsvBuilderFactory;
import com.bridgeLabs.csvHandler.CsvException;
import com.bridgeLabs.csvHandler.CsvExceptionType;
import com.bridgeLabs.csvHandler.ICsvBuilder;

public class IplAnalyser {
	enum SortByParameter {
		AVERAGE, STRIKE_RATE, NUM_SIXES_AND_FOURS
	}

	public List<Batsman> loadBatsmenData(String csvFilePath) throws CsvException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			ICsvBuilder csvBuilder = CsvBuilderFactory.createBuilderOpen();
			List<Batsman> batsmenList = csvBuilder.getListFromCsv(reader, Batsman.class);
			return batsmenList;
		} catch (IOException e) {
			throw new CsvException("Incorrect CSV File", CsvExceptionType.CENSUS_FILE_PROBLEM);
		}
	}

	public List<Bowler> loadBowlersData(String csvFilePath) throws CsvException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			ICsvBuilder csvBuilder = CsvBuilderFactory.createBuilderOpen();
			List<Bowler> bowlersList = csvBuilder.getListFromCsv(reader, Bowler.class);
			return bowlersList;
		} catch (IOException e) {
			throw new CsvException("Incorrect CSV File", CsvExceptionType.CENSUS_FILE_PROBLEM);
		}
	}
	
	private List<Batsman> getTopTen(String csvFilePath, SortByParameter sortByParameter) throws CsvException {
		List<Batsman> batsmenList = loadBatsmenData(csvFilePath);
		List<Batsman> topTen = batsmenList.stream().sorted(getComparatorForBatsman(sortByParameter)).limit(10)
				.collect(Collectors.toList());
		return topTen;
	}

	private Comparator<Batsman> getComparatorForBatsman(SortByParameter sortByParameter) {
		Comparator<Batsman> comparator = null;
		switch (sortByParameter) {
		case AVERAGE:
			comparator = (player1, player2) -> player2.average.compareTo(player1.average);
			break;
		case STRIKE_RATE:
			comparator = (player1, player2) -> player2.strikeRate.compareTo(player1.strikeRate);
			break;
		case NUM_SIXES_AND_FOURS:
			comparator = (player1, player2) ->((Integer) (player2.numSixes+player2.numFours)).compareTo(player1.numSixes+player1.numFours);
			break;
		default:
			comparator = (player1, player2) -> player2.name.compareTo(player1.name);
		}
		return comparator;
	}

	public List<Batsman> getTopTenAverages(String csvFilePath) throws CsvException {
		return getTopTen(csvFilePath, SortByParameter.AVERAGE);
	}

	public List<Batsman> getTopTenStrikeRates(String csvFilePath) throws CsvException {
		return getTopTen(csvFilePath, SortByParameter.STRIKE_RATE);
	}

	public List<Batsman> getTopTenNumSixesAndFours(String csvFilePath) throws CsvException {
		return getTopTen(csvFilePath, SortByParameter.NUM_SIXES_AND_FOURS);
	}
}
