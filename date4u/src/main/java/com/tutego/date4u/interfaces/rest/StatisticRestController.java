package com.tutego.date4u.interfaces.rest;

import com.tutego.date4u.interfaces.LastSeenStatistics;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;


@RestController
public class StatisticRestController {

    @GetMapping( "/api/stat/last-seen" )
    public LastSeenStatistics lastSeenStatistics(
            @RequestParam(value = "start", required = false) Optional<String> start,
            @RequestParam(value = "end", required = false) Optional<String> end) {

        YearMonth startDate = start.map(YearMonth::parse).orElseGet(() -> YearMonth.now().minusYears(2));
        YearMonth endDate = end.map(YearMonth::parse).orElseGet(YearMonth::now);

        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        List<LastSeenStatistics.Data> data =
                Stream.iterate( startDate, o -> o.plusMonths( 1 ) )
                        .limit( startDate.until( endDate, ChronoUnit.MONTHS ) + 1 )
                        .map( yearMonth -> new LastSeenStatistics.Data(
                                yearMonth, rnd.nextInt(1000,10000) ) )
                        .toList();

        return new LastSeenStatistics( data );
    }
}
