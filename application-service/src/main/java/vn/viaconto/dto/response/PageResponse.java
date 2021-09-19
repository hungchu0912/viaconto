package vn.viaconto.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T> {

    private int  totalPages;
    private long totalItems;
    private int  currentPage;
    private int  itemsPerPage;
    private int  pageSize;

    private List<T> items;

    public static <T> PageResponse<T> of(Page<T> pg) {
        PageResponse<T> result = new PageResponse<>();
        result.setCurrentPage(pg.getNumber() + 1);
        result.setPageSize(pg.getSize());
        result.setTotalPages(pg.getTotalPages());
        result.setTotalItems(pg.getTotalElements());
        result.setItemsPerPage(pg.getNumberOfElements());
        result.setItems(pg.getContent());
        return result;
    }
}
