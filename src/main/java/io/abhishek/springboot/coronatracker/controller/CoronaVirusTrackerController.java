package io.abhishek.springboot.coronatracker.controller;

import io.abhishek.springboot.coronatracker.model.LocationStats;
import io.abhishek.springboot.coronatracker.service.CorornaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@Controller
public class CoronaVirusTrackerController {

    @Autowired
    CorornaVirusDataService corornaVirusDataService;

    @GetMapping("/coronaTracker")
    public String getCoronaVirusInfectedPeopleData(Model model) throws IOException, InterruptedException {
        List<LocationStats> coronaInfectedPeopleData = corornaVirusDataService.fetchCoronaReportedCaseData();
        Integer totalCases = coronaInfectedPeopleData.stream().mapToInt(ls -> ls.getTotalCasesReported()).sum();
        Integer totalCasesInLastDay = coronaInfectedPeopleData.stream().mapToInt(ls -> ls.getCaseReportedInLastDay()).sum();
        coronaInfectedPeopleData.sort(new Comparator<LocationStats>() {
            @Override
            public int compare(LocationStats locationStats1, LocationStats locationStats2) {
                return locationStats2.getCaseReportedInLastDay().compareTo(locationStats1.getCaseReportedInLastDay());
            }
        });
        model.addAttribute("coronaInfectedPeopleData",coronaInfectedPeopleData);
        model.addAttribute("totalCases",totalCases);
        model.addAttribute("totalCasesInLastDay",totalCasesInLastDay);

        return "coronaInfectantTracker";
    }


}
