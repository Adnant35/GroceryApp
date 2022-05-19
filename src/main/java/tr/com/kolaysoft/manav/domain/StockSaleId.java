package tr.com.kolaysoft.manav.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StockSaleId implements Serializable {

    @Column(name = "sale_id")
    private Long saleId;
    public StockSaleId(){}

    public StockSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        StockSaleId that = (StockSaleId) o;
        return Objects.equals(saleId, that.saleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(saleId);
    }

    @Override
    public String toString() {
        return "StockSaleId{" +
                "saleId=" + saleId +
                '}';
    }
}
