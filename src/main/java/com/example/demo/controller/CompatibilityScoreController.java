// package com.example.demo.controller;

// import org.springframework.web.bind.annotation.*;

// import com.example.demo.model.CompatibilityScoreRecord;
// import com.example.demo.service.CompatibilityScoreService;

// @RestController
// @RequestMapping("/compatibility")
// public class CompatibilityScoreController {

//     private final CompatibilityScoreService service;

//     public CompatibilityScoreController(
//             CompatibilityScoreService service) {
//         this.service = service;
//     }

//     @PostMapping
//     public CompatibilityScoreRecord compute(
//             @RequestBody CompatibilityScoreRecord record) {
//         return service.computeScore(record);
//     }
// }
