package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * API Query Parameters (optional):
 * <ul>
 * <li>count - Number of listings to return (default: 50, range: 1-50)</li>
 * <li>geo - Filter by job region (default: all regions)</li>
 * <li>industry - Filter by job category (default: all categories)</li>
 * <li>tag - Search by job title and description (default: all jobs)</li>
 * </ul>
 * Eg: https://jobicy.com/api/v2/remote-jobs?count=20&geo=usa&industry=marketing&tag=seo
 */
final class ApiRequestParams {
    private Map<String, String> params;

    public ApiRequestParams() {
        params = new HashMap<>();
    }
    ApiRequestParams withCount(int value) {
        if (value < 1 || value > 50) return this;
        params.put("count", String.valueOf(value));
        return this;
    }
    ApiRequestParams withGeo(ApiParamGeo value) {
        params.put("geo", getName(value.name()));
        return this;
    }
    ApiRequestParams withIndustry(ApiParamIndustry value) {
        params.put("industry", getName(value.name()));
        return this;
    }
    ApiRequestParams withTag(String value) {
        if (value == null || value.isBlank()) return this;
        params.put("tag", value.strip());
        return this;
    }
    String toStringParams() {
        return params.entrySet()
                .stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));
    }
    String getName(String value) {
        return value
                .toLowerCase()
                .replace("_", "-");
    }
}
