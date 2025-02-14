package pojos;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import jakarta.persistence.*;
import util.HibernateUtil;

@Entity
@Table(name = "OrderDetails")
public class OrderDetail {
    @EmbeddedId
    private OrderDetailId id;
    
    @ManyToOne
    @MapsId("orderID")
    @JoinColumn(name = "OrderID")
    private Order order;
    
    @ManyToOne
    @MapsId("productID")
    @JoinColumn(name = "ProductID")
    private Product product;
    
    @Column(nullable = false)
    private Double unitPrice;
    
    @Column(nullable = false)
    private Short quantity;
    
    @Column(nullable = false)
    private Double discount;
    
    @Column(nullable = false)
    private Double total;

    // Getters and Setters
	public OrderDetailId getId() {
		return id;
	}

	public void setId(OrderDetailId id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Short getQuantity() {
		return quantity;
	}

	public void setQuantity(Short quantity) {
		this.quantity = quantity;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "OrderDetail [id=" + id + ", product=" + product + ", unitPrice=" + unitPrice
				+ ", quantity=" + quantity + ", discount=" + discount + ", total=" + total + "]";
	}
	
	public static OrderDetail get(Integer orderID, Integer productId) {
		OrderDetail orderDetail = null;
		try (SessionFactory factory = HibernateUtil.getSessionFactory();
				Session session = factory.openSession()) {
			
			orderDetail = session.get(OrderDetail.class, new OrderDetailId(orderID, productId));
			
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
		}
		return orderDetail;
	}
}