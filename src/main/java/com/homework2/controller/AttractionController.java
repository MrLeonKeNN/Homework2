package com.homework2.controller;

import com.homework2.dto.AttractionDto;
import com.homework2.enums.AttractionNames;
import com.homework2.enums.AttractionsEnum;
import com.homework2.service.api.AttractionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing attraction operations.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/attractions")
@CrossOrigin
public class AttractionController {

    private final AttractionService attractionService;

    /**
     * Saves a new attraction.
     *
     * @param attractionDto the AttractionDto containing the details of the attraction to be saved
     * @return a ResponseEntity indicating the result of the save operation
     */
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody AttractionDto attractionDto) {
        attractionService.save(attractionDto);
        return ResponseEntity.ok().build();
    }

    /**
     * Deletes an attraction by its identifier.
     *
     * @param id the identifier of the attraction to be deleted
     * @return a ResponseEntity indicating the result of the delete operation
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        attractionService.delete(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Retrieves a list of attractions by locality identifier.
     *
     * @param id the identifier of the locality for which attractions are to be retrieved
     * @return a ResponseEntity containing a list of AttractionDto for the specified locality
     */
    @GetMapping("/locality/{id}")
    public ResponseEntity<List<AttractionDto>> getByLocality(@PathVariable Long id) {
        return ResponseEntity.ok(attractionService.getByLocality(id));
    }

    /**
     * Edits an existing attraction.
     *
     * @param id the identifier of the attraction to be edited
     * @param attractionDto the AttractionDto containing the updated details of the attraction
     * @return a ResponseEntity indicating the result of the edit operation
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> edit(@PathVariable Long id, @RequestBody AttractionDto attractionDto) {
        attractionService.edit(id, attractionDto);
        return ResponseEntity.ok().build();
    }

    /**
     * Retrieves a list of attractions based on sorting and filtering criteria.
     *
     * @param attractionName the sorting criteria
     * @param filter the filtering criteria, represented as an AttractionsEnum
     * @return a ResponseEntity containing a list of AttractionDto matching the criteria
     */
    @GetMapping("/sort/{attractionName}/{filter}")
    public ResponseEntity<List<AttractionDto>> getByFilter(@PathVariable AttractionNames attractionName,
                                                           @PathVariable AttractionsEnum filter) {
        System.out.println(attractionName);
        return ResponseEntity.ok(attractionService.getBySort(attractionName, filter));
    }
}
