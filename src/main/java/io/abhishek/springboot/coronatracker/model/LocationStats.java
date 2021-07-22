package io.abhishek.springboot.coronatracker.model;

public class LocationStats  {

    private String state;
    private String country;
    private Integer totalCasesReported;
    private Integer CaseReportedInLastDay;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getTotalCasesReported() {
        return totalCasesReported;
    }

    public void setTotalCasesReported(Integer totalCasesReported) {
        this.totalCasesReported = totalCasesReported;
    }

    public Integer getCaseReportedInLastDay() {
        return CaseReportedInLastDay;
    }

    public void setCaseReportedInLastDay(Integer caseReportedInLastDay) {
        CaseReportedInLastDay = caseReportedInLastDay;
    }

   /*@Override
    public int compareTo(LocationStats locationStats) {
        return this.getCaseReportedInLastDay().compareTo(locationStats.getCaseReportedInLastDay());
    }*/

    @Override
    public String toString() {
        return "LocationStats{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", totalCasesReported=" + totalCasesReported +
                ", CaseReportedFromLastDay=" + CaseReportedInLastDay +
                '}';
    }
}
