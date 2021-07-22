package io.abhishek.springboot.coronatracker.service;

import io.abhishek.springboot.coronatracker.model.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CorornaVirusDataService {

    private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    private List<LocationStats> allStats = new ArrayList<>();

     @PostConstruct
    public List<LocationStats> fetchCoronaReportedCaseData() throws IOException, InterruptedException {

        List<LocationStats> newStats = new ArrayList<>();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(VIRUS_DATA_URL)).build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        StringReader csvBodyReader = new StringReader(httpResponse.body());

        //Reader in = new FileReader("path/to/file.csv");
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        for (CSVRecord record : records) {
            LocationStats  locationStats = new LocationStats();
            locationStats.setState(record.get("Province/State"));
            locationStats.setCountry(record.get("Country/Region"));
            int totalCoronaCasesUptoKnow = Integer.parseInt(record.get(record.size()-1));
            int coronaCasesOneDayBefore = Integer.parseInt(record.get(record.size()-2));
            locationStats.setTotalCasesReported(totalCoronaCasesUptoKnow);
            locationStats.setCaseReportedInLastDay(totalCoronaCasesUptoKnow - coronaCasesOneDayBefore);
            newStats.add(locationStats);
        }
        this.allStats = newStats;

        return allStats;
    }

}
