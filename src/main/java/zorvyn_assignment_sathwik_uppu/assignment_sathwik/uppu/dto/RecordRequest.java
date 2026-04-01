package zorvyn_assignment_sathwik_uppu.assignment_sathwik.uppu.dto;

import jakarta.validation.constraints.*;


import zorvyn_assignment_sathwik_uppu.assignment_sathwik.uppu.entity.RecordType;

import java.time.LocalDate;


public class RecordRequest {

    @NotNull
    private Double amount;

    @NotNull
    private RecordType type;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public RecordType getType() {
        return type;
    }

    public void setType(RecordType type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @NotBlank
    private String category;

    @NotNull
    private LocalDate date;

    private String notes;
    
}