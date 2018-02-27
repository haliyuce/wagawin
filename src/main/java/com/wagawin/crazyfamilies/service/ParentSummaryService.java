package com.wagawin.crazyfamilies.service;

import com.google.common.collect.Lists;
import com.wagawin.crazyfamilies.model.ParentSummary;
import com.wagawin.crazyfamilies.repository.ParentSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParentSummaryService {

    @Autowired
    private ParentSummaryRepository parentSummaryRepository;

    @Scheduled(cron = "0 0/15 * * * ?")
    public void refreshData() {
        this.parentSummaryRepository.save(this.parentSummaryRepository.getStatisticsOfParentChildCount());
    }

    public List<ParentSummary> getPersonChildStatisticSummary() {
        return Lists.newArrayList(this.parentSummaryRepository.findAll());
    }

}
