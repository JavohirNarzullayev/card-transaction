package uz.narzullaev.cardtrx.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@NoArgsConstructor
public class SuccessDataIterable<T> {
    private Integer status;
    private String message;
    private List<T> data;
    private int totalPages;
    private long totalCount;
    private int size;
    private int page;

    public SuccessDataIterable(Page<T> objects) {
        this.status = 0;
        this.message = "success";
        this.data = objects.getContent();
        this.totalPages = objects.getTotalPages();
        this.totalCount = objects.getTotalElements();
        this.size = objects.getSize();
        this.page = objects.getNumber();
    }


}
