package zorvyn_assignment_sathwik_uppu.assignment_sathwik.uppu.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import zorvyn_assignment_sathwik_uppu.assignment_sathwik.uppu.dto.*;
import zorvyn_assignment_sathwik_uppu.assignment_sathwik.uppu.service.FinancialService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/records")
public class FinancialController {

    private final FinancialService service;

    public FinancialController(FinancialService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<ApiResponse<RecordResponse>> create(@RequestBody @Valid RecordRequest request) {
        return ResponseEntity.ok(new ApiResponse<>("Record created", service.create(request)));
    }

    // GET ALL (normal)
    @GetMapping
    public ResponseEntity<List<RecordResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    // FILTER
    @GetMapping("/filter")
    public ResponseEntity<List<RecordResponse>> filter(@RequestParam String category) {
        return ResponseEntity.ok(service.filterByCategory(category));
    }

    // PAGINATION (different endpoint!)
    @GetMapping("/page")
    public ResponseEntity<Page<RecordResponse>> getAllWithPagination(Pageable pageable) {
        return ResponseEntity.ok(service.getAllWithPagination(pageable));
    }
   @PutMapping("/{id}")
public ResponseEntity<ApiResponse<RecordResponse>> update(
        @PathVariable Long id,
        @RequestBody @Valid RecordRequest request) {

    return ResponseEntity.ok(
        new ApiResponse<>("Record updated successfully", service.update(id, request))
    );
}
@GetMapping("/summary")
public ResponseEntity<Map<String, Double>> getSummary() {
    return ResponseEntity.ok(service.getSummary());
}
    // DELETE
  @DeleteMapping("/{id}")
public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id) {

    service.delete(id);

    return ResponseEntity.ok(
        new ApiResponse<>("Record deleted successfully", null)
    );
}
}
