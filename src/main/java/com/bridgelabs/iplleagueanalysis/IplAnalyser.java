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

@SuppressWarnings(value = { "unchecked", "rawtypes" })
public class IplAnalyser {
	enum SortByParameter {
		AVERAGE, STRIKE_RATE, NUM_SIXES_AND_FOURS, MOST_RUNS, BOWLING_AVERAGE, BOWLING_STRIKE_RATE,
		ECONOMY		
	}

	private List<Batsman> batsmenList;
	private List<Bowler> bowlersList;

	public List<Batsman> loadBatsmenData(String csvFilePath) throws CsvException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			ICsvBuilder csvBuilder = CsvBuilderFactory.createBuilderOpen();
			this.batsmenList = csvBuilder.getListFromCsv(reader, Batsman.class);
			return batsmenList;
		} catch (IOException e) {
			throw new CsvException("Incorrect CSV File", CsvExceptionType.CENSUS_FILE_PROBLEM);
		}
	}

	public List<Bowler> loadBowlersData(String csvFilePath) throws CsvException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			ICsvBuilder csvBuilder = CsvBuilderFactory.createBuilderOpen();
			this.bowlersList = csvBuilder.getListFromCsv(reader, Bowler.class);
			return bowlersList;
		} catch (IOException e) {
			throw new CsvException("Incorrect CSV File", CsvExceptionType.CENSUS_FILE_PROBLEM);
		}
	}

	private <T> List<T> getTopTen(List<T> playersList, SortByParameter sortByParameter) throws CsvException {
		List<T> topTen = (List<T>) playersList.stream().sorted(getComparator(sortByParameter)).limit(10)
				.collect(Collectors.toList());
		return topTen;
	}

	private Comparator getComparator(SortByParameter sortByParameter) {

		Comparator<Batsman> comparatorBatsman = null;
		Comparator<Bowler> comparatorBowler = null;
		switch (sortByParameter) {
		case AVERAGE:
			comparatorBatsman = (player1, player2) -> player2.average.compareTo(player1.average);
			break;
		case STRIKE_RATE:
			comparatorBatsman = (player1, player2) -> player2.strikeRate.compareTo(player1.strikeRate);
			break;
		case NUM_SIXES_AND_FOURS:
			comparatorBatsman = (player1, player2) -> ((Integer) (player2.numSixes + player2.numFours))
					.compareTo(player1.numSixes + player1.numFours);
			break;
		case MOST_RUNS:
			comparatorBatsman = (player1, player2) -> player2.runs.compareTo(player1.runs);
			break;
		case BOWLING_AVERAGE:
			comparatorBowler = (player1, player2) -> player1.bowlingAverage.compareTo(player2.bowlingAverage);
			break;
		case BOWLING_STRIKE_RATE:
			comparatorBowler = (player1, player2) -> player1.bowlingStrikeRate.compareTo(player2.bowlingStrikeRate);
			break;
		case ECONOMY:
			comparatorBowler = (player1, player2) -> player1.economy.compareTo(player2.economy);
			break;
		default:
			comparatorBowler = (player1, player2) -> player1.name.compareTo(player2.name);
		}
		return comparatorBatsman == null ? comparatorBowler : comparatorBatsman;
	}

	public List<Batsman> getTopTenAverages() throws CsvException {
		return getTopTen(batsmenList, SortByParameter.AVERAGE);
	}

	public List<Batsman> getTopTenStrikeRates() throws CsvException {
		return getTopTen(batsmenList, SortByParameter.STRIKE_RATE);
	}

	public List<Batsman> getTopTenNumSixesAndFours() throws CsvException {
		return getTopTen(batsmenList, SortByParameter.NUM_SIXES_AND_FOURS);
	}

	public List<Batsman> getTopThreeStrikeRatesWithMaxNumSixesAndFours() throws CsvException {
		List<Batsman> topTenNumSixesandFours = getTopTenNumSixesAndFours();
		return (List<Batsman>) topTenNumSixesandFours.stream().sorted(getComparator(SortByParameter.STRIKE_RATE))
				.limit(3).collect(Collectors.toList());
	}

	public List<Batsman> getTopThreeAveragesWithBestStrikeRates() throws CsvException {
		List<Batsman> topTenStrikeRates = getTopTenStrikeRates();
		return (List<Batsman>) topTenStrikeRates.stream().sorted(getComparator(SortByParameter.AVERAGE)).limit(3)
				.collect(Collectors.toList());
	}

	public List<Batsman> getTopThreeMostRunsWithBestAverages() throws CsvException {
		List<Batsman> topTenAverages = getTopTenAverages();
		return (List<Batsman>) topTenAverages.stream().sorted(getComparator(SortByParameter.MOST_RUNS)).limit(3)
				.collect(Collectors.toList());
	}

	public List<Bowler> getTopTenBowlingAverages() throws CsvException {
		List<Bowler> nonZeroAverageBowlers = this.bowlersList.stream()
				.filter(bowler -> !(bowler.bowlingAverage.equals(0.0))).collect(Collectors.toList());
		return getTopTen(nonZeroAverageBowlers, SortByParameter.BOWLING_AVERAGE);
	}

	public List<Bowler> getTopTenBowlingStrikeRates() throws CsvException {
		List<Bowler> nonZeroStrikeRateBowlers = this.bowlersList.stream()
				.filter(bowler -> !(bowler.bowlingStrikeRate.equals(0.0))).collect(Collectors.toList());
		return getTopTen(nonZeroStrikeRateBowlers, SortByParameter.BOWLING_STRIKE_RATE);
	}

	public List<Bowler> getTopTenEconomyRates() throws CsvException {
		return getTopTen(bowlersList, SortByParameter.ECONOMY);
	}

	public List<Bowler> getTopThreeStrikeRatesWith5wAnd4w() {
		return (List<Bowler>) bowlersList.stream().filter(bowler -> (bowler.num4w != 0) || (bowler.num5w != 0))
				.sorted(getComparator(SortByParameter.BOWLING_STRIKE_RATE)).limit(3).collect(Collectors.toList());
	}
}
