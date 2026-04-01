package zorvyn_assignment_sathwik_uppu.assignment_sathwik.uppu.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import zorvyn_assignment_sathwik_uppu.assignment_sathwik.uppu.dto.RecordRequest;
import zorvyn_assignment_sathwik_uppu.assignment_sathwik.uppu.dto.RecordResponse;
import zorvyn_assignment_sathwik_uppu.assignment_sathwik.uppu.entity.FinancialRecord;
import zorvyn_assignment_sathwik_uppu.assignment_sathwik.uppu.repository.FinancialRecordRepository;

import java.util.*;

@Service
public class FinancialService {

    private final FinancialRecordRepository repository;

    public FinancialService(FinancialRecordRepository repository) {
        this.repository = repository;
    }

    // ✅ CREATE (DTO BASED)
    public RecordResponse create(RecordRequest request) {

        FinancialRecord record = new FinancialRecord();
        record.setAmount(request.getAmount());
        record.setType(request.getType());
        record.setCategory(request.getCategory());
        record.setDate(request.getDate());
        record.setNotes(request.getNotes());

        FinancialRecord saved = repository.save(record);

        RecordResponse response = new RecordResponse();
        response.setId(saved.getId());
        response.setAmount(saved.getAmount());
        response.setType(saved.getType());
        response.setCategory(saved.getCategory());
        response.setDate(saved.getDate());
        response.setNotes(saved.getNotes());

        return response;
    }

    // ✅ GET ALL
    public List<RecordResponse> getAll() {
        return repository.findAll().stream().map(record -> {
            RecordResponse res = new RecordResponse();
            res.setId(record.getId());
            res.setAmount(record.getAmount());
            res.setType(record.getType());
            res.setCategory(record.getCategory());
            res.setDate(record.getDate());
            res.setNotes(record.getNotes());
            return res;
        }).toList();
    }

    // ✅ PAGINATION
    public Page<RecordResponse> getAllWithPagination(Pageable pageable) {
        return repository.findAll(pageable).map(record -> {
            RecordResponse res = new RecordResponse();
            res.setId(record.getId());
            res.setAmount(record.getAmount());
            res.setType(record.getType());
            res.setCategory(record.getCategory());
            res.setDate(record.getDate());
            res.setNotes(record.getNotes());
            return res;
        });
    }

    // ✅ FILTER
    public List<RecordResponse> filterByCategory(String category) {
        return repository.findByCategory(category).stream().map(record -> {
            RecordResponse res = new RecordResponse();
            res.setId(record.getId());
            res.setAmount(record.getAmount());
            res.setType(record.getType());
            res.setCategory(record.getCategory());
            res.setDate(record.getDate());
            res.setNotes(record.getNotes());
            return res;
        }).toList();
    }

    // ✅ DASHBOARD
    public Map<String, Double> getSummary() {

        Double income = repository.getTotalIncome();
        Double expense = repository.getTotalExpense();

        Map<String, Double> map = new HashMap<>();
        map.put("income", income != null ? income : 0);
        map.put("expense", expense != null ? expense : 0);
        map.put("balance", (income != null ? income : 0) - (expense != null ? expense : 0));

        return map;
    }
    // update an exixting record
    public RecordResponse update(Long id, RecordRequest request) {

    FinancialRecord record = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Record not found with id: " + id));

    record.setAmount(request.getAmount());
    record.setType(request.getType());
    record.setCategory(request.getCategory());
    record.setDate(request.getDate());
    record.setNotes(request.getNotes());

    FinancialRecord updated = repository.save(record);

    RecordResponse response = new RecordResponse();
    response.setId(updated.getId());
    response.setAmount(updated.getAmount());
    response.setType(updated.getType());
    response.setCategory(updated.getCategory());
    response.setDate(updated.getDate());
    response.setNotes(updated.getNotes());

    return response;
}
// for deleting a record
    public void delete(Long id) {

    FinancialRecord record = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Record not found with id: " + id));

    repository.delete(record);
}
}
