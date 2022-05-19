package tr.com.kolaysoft.manav.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class PurchaseDTO implements Serializable {
    private Long id;

    private long groceryid;

    private long productid;

    private BigDecimal count;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getGroceryid() {
        return groceryid;
    }

    public void setGroceryid(long groceryid) {
        this.groceryid = groceryid;
    }

    public long getProductid() {
        return productid;
    }

    public void setProductid(long productid) {
        this.productid = productid;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductDTO)) {
            return false;
        }

        PurchaseDTO purchaseDTO = (PurchaseDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, purchaseDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public String toString() {
        return "PurchaseDTO{" +
                "id=" + id +
                ", groceryid=" + groceryid +
                ", productid=" + productid +
                ", count=" + count +
                '}';
    }
}
