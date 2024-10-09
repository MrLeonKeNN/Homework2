package com.homework2.controller;

import com.homework2.dto.LocalityDto;
import com.homework2.service.impl.LocalityServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing locality operations.
 */
@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/locality")
public class LocalityController {

    private final LocalityServiceImpl localityService;

    /**
     * Saves a new locality.
     *
     * @param localityDto the LocalityDto containing the details of the locality to be saved
     * @return a ResponseEntity indicating the result of the save operation
     */
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody LocalityDto localityDto) {
        localityService.save(localityDto);
        return ResponseEntity.ok().build();
    }
}
