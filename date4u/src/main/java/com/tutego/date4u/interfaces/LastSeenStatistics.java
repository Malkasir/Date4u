package com.tutego.date4u.interfaces;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.YearMonth;
import java.util.List;

public record LastSeenStatistics(List<Data> data) {
    public record Data(
            @JsonProperty("x") YearMonth yearMonth,
            @JsonProperty("y") int count
    ) { }
}
