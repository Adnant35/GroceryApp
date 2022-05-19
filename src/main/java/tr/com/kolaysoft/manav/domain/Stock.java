package tr.com.kolaysoft.manav.domain;

import tr.com.kolaysoft.manav.service.dto.StockDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="stock")
public class Stock implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "id")
    private Long id;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Stock id(Long id) {
        this.setId(id);
        return this;
    }

    @Column(name="product_id")
    private long productid;

    public long getProductid() {
        return productid;
    }

    public void setProductid(long productid) {
        this.productid = productid;
    }

    public Stock productid(long productid) {
        this.setProductid(productid);
        return this;
    }



    @NotNull
    @Column(name = "totalcount", precision = 21, scale = 2, nullable = false)
    private BigDecimal totalcount;

    public BigDecimal getTotalcount() {
        return totalcount;
    }

    public void setTotalcount(BigDecimal totalcount) {
        this.totalcount = totalcount;
    }

    public Stock totalcount(BigDecimal totalcount) {
        this.setTotalcount(totalcount);
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Stock)) {
            return false;
        }
        return id != null && id.equals(((Stock) o).id);
    }
    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", productid=" + productid +
                ", totalcount=" + totalcount +
                '}';
    }
}
