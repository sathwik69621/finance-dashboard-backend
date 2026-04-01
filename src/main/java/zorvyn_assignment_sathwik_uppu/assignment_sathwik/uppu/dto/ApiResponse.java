package zorvyn_assignment_sathwik_uppu.assignment_sathwik.uppu.dto;

public class ApiResponse<T> {

    private String message;
    private T data;

    public ApiResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() { return message; }
    public T getData() { return data; }
}
