package tr.com.kolaysoft.manav.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="purchase")
public class Purchase  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name= "id")

    private Long id;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Purchase id(Long id) {
        this.setId(id);
        return this;
    }
    @Column(name="grocery_id")
    private long groceryid;

    public long getGroceryid() {
        return groceryid;
    }

    public void setGroceryid(long groceryid) {
        this.groceryid = groceryid;
    }

    public Purchase groceryid(Long groceryid) {
        this.setGroceryid(groceryid);
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

    public Purchase productid(Long productid) {
        this.setProductid(productid);
        return this;
    }
    @Column(name = "count", precision = 21, scale = 2, nullable = false)
    private BigDecimal count;

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public Purchase count(BigDecimal count) {
        this.setCount(count);
        return this;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Purchase)) {
            return false;
        }
        return id != null && id.equals(((Purchase) o).id);
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", groceryid=" + groceryid +
                ", productid=" + productid +
                ", count=" + count +
                '}';
    }
}
