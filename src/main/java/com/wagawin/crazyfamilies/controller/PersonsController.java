package com.wagawin.crazyfamilies.controller;

import com.wagawin.crazyfamilies.model.ParentSummary;
import com.wagawin.crazyfamilies.service.ParentSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("persons/")
public class PersonsController {

    @Autowired
    private ParentSummaryService parentSummaryService;

    @GetMapping("/children")
    public List<ParentSummary> getPersonChildStatistics() {
        return this.parentSummaryService.getPersonChildStatisticSummary();
    }

}
