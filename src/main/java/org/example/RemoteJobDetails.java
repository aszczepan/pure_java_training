package org.example;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

final class RemoteJobDetails {
    public String apiVersion;
    public String documentationUrl;
    public String friendlyNotice;
    public Integer jobCount;
    public String xRayHash;
    public String clientKey;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime lastUpdate;
    public Collection<RemoteJob> jobs;

    public RemoteJobDetails() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RemoteJobDetails that = (RemoteJobDetails) o;
        return Objects.equals(apiVersion, that.apiVersion) && Objects.equals(documentationUrl, that.documentationUrl) && Objects.equals(friendlyNotice, that.friendlyNotice) && Objects.equals(jobCount, that.jobCount) && Objects.equals(xRayHash, that.xRayHash) && Objects.equals(clientKey, that.clientKey) && Objects.equals(lastUpdate, that.lastUpdate) && Objects.equals(jobs, that.jobs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apiVersion, documentationUrl, friendlyNotice, jobCount, xRayHash, clientKey, lastUpdate, jobs);
    }

    @Override
    public String toString() {
        return "RemoteJobDetails{" +
                "apiVersion='" + apiVersion + '\'' +
                ", documentationUrl='" + documentationUrl + '\'' +
                ", friendlyNotice='" + friendlyNotice + '\'' +
                ", jobCount=" + jobCount +
                ", xRayHash='" + xRayHash + '\'' +
                ", clientKey='" + clientKey + '\'' +
                ", lastUpdate=" + lastUpdate +
                ", jobs=" + jobs +
                '}';
    }
}
