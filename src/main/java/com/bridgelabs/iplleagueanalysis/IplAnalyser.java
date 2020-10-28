package com.bridgelabs.iplleagueanalysis;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import com.bridgeLabs.csvHandler.CsvBuilderFactory;
import com.bridgeLabs.csvHandler.CsvException;
import com.bridgeLabs.csvHandler.CsvExceptionType;
import com.bridgeLabs.csvHandler.ICsvBuilder;

public class IplAnalyser {
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

	public List<Batsman> getTopTenAverages(String csvFilePath) throws CsvException {
		List<Batsman> batsmenList=loadBatsmenData(csvFilePath);
		List<Batsman> topAverages=batsmenList.stream()
				.sorted((batsman1,batsman2)->batsman2.average.compareTo(batsman1.average))
				.limit(10)
				.collect(Collectors.toList());
		return topAverages;
	}
}
