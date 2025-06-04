package com.hmm.test.infraestructure.adapter.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/{a}")
    public ResponseEntity<Integer> index(@PathVariable Integer a){
        return ResponseEntity.ok(a);
    }
}
