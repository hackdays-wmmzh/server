package com.wmmzh.backend.controller;

import com.wmmzh.backend.model.Ereignis;
import com.wmmzh.backend.service.EreignisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/activities")
public class EreignissController {

    private EreignisService ereignisService;

    public EreignissController(EreignisService ereignisService) {
        this.ereignisService = ereignisService;
    }

    @GetMapping
    public List<Ereignis> getAll() {
        return ereignisService.getAll();
    }
}
