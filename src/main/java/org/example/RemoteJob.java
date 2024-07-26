package org.example;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


final class RemoteJob {
    public Integer id;
    public String url;
    public String jobTitle;
    public String companyName;
    public String companyLogo;
    public List<String> jobIndustry;
    public List<String> jobType;
    public String jobGeo;
    public String jobLevel;
    public String jobSlug;
    public String jobExcerpt;
    public String jobDescription;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime pubDate;
    public BigDecimal annualSalaryMin;
    public BigDecimal annualSalaryMax;
    public String salaryCurrency; //ISO 4217 salary currency code (if applicable)

    public RemoteJob() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RemoteJob remoteJob = (RemoteJob) o;
        return Objects.equals(id, remoteJob.id) && Objects.equals(url, remoteJob.url) && Objects.equals(jobTitle, remoteJob.jobTitle) && Objects.equals(companyName, remoteJob.companyName) && Objects.equals(companyLogo, remoteJob.companyLogo) && Objects.equals(jobIndustry, remoteJob.jobIndustry) && Objects.equals(jobType, remoteJob.jobType) && Objects.equals(jobGeo, remoteJob.jobGeo) && Objects.equals(jobLevel, remoteJob.jobLevel) && Objects.equals(jobSlug, remoteJob.jobSlug) && Objects.equals(jobExcerpt, remoteJob.jobExcerpt) && Objects.equals(jobDescription, remoteJob.jobDescription) && Objects.equals(pubDate, remoteJob.pubDate) && Objects.equals(annualSalaryMin, remoteJob.annualSalaryMin) && Objects.equals(annualSalaryMax, remoteJob.annualSalaryMax) && Objects.equals(salaryCurrency, remoteJob.salaryCurrency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, jobTitle, companyName, companyLogo, jobIndustry, jobType, jobGeo, jobLevel, jobSlug, jobExcerpt, jobDescription, pubDate, annualSalaryMin, annualSalaryMax, salaryCurrency);
    }

    @Override
    public String toString() {
        return "RemoteJob{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", companyName='" + companyName + '\'' +
                ", companyLogo='" + companyLogo + '\'' +
                ", jobIndustry=" + jobIndustry +
                ", jobType=" + jobType +
                ", jobGeo='" + jobGeo + '\'' +
                ", jobLevel='" + jobLevel + '\'' +
                ", jobSlug='" + jobSlug + '\'' +
                ", jobExcerpt='" + jobExcerpt + '\'' +
                ", jobDescription='" + jobDescription + '\'' +
                ", pubDate=" + pubDate +
                ", annualSalaryMin=" + annualSalaryMin +
                ", annualSalaryMax=" + annualSalaryMax +
                ", salaryCurrency='" + salaryCurrency + '\'' +
                '}';
    }
}
