package tr.com.kolaysoft.manav.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

public class StockDTO implements Serializable {
    public Long id;

    public long productid;

    public BigDecimal totalcount;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public long getProductid() {
        return productid;
    }

    public void setProductid(long productid) {
        this.productid = productid;
    }

    public BigDecimal getTotalcount() {
        return totalcount;
    }

    public void setTotalcount(BigDecimal totalcount) {
        this.totalcount = totalcount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StockDTO)) {
            return false;
        }

        StockDTO stockDTO = (StockDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, stockDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public String toString() {
        return "StockDTO{" +
                "id=" + id +
                ", productid=" + productid +
                ", totalcount=" + totalcount +
                '}';
    }
}
