package pojos;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import jakarta.persistence.*;
import util.HibernateUtil;

@Entity
@Table(name = "Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productID;
    
    @Column(nullable = false, length = 40)
    private String productName;
    
    @ManyToOne
    @JoinColumn(name = "SupplierID")
    private Supplier supplier;
    
    @ManyToOne
    @JoinColumn(name = "CategoryID")
    private Category category;
    
    @Column
    private Double unitPrice;
    
    @Column
    private Short unitsInStock;
    
    @Column(nullable = false)
    private Boolean discontinued;
    
    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetails;

    // Getters and Setters
	public Integer getProductID() {
		return productID;
	}

	public void setProductID(Integer productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Short getUnitsInStock() {
		return unitsInStock;
	}

	public void setUnitsInStock(Short unitsInStock) {
		this.unitsInStock = unitsInStock;
	}

	public Boolean getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(Boolean discontinued) {
		this.discontinued = discontinued;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	@Override
	public String toString() {
		return   "Product => " + productID + "\n\t" + "productName => " + productName + "\n\t" + "unitPrice => " + unitPrice + " €"
				+ "\n\t" + "unitsInStock => " + unitsInStock;
	}
	
	public static Product get(String nombre) {
		Product product = null;
		
		try (SessionFactory factory = HibernateUtil.getSessionFactory(); 
				Session session = factory.openSession()) {
			
			product = (Product) session
					.createQuery("from Product where productName = :valor")
					.setParameter("valor", nombre)
					.uniqueResult();
			
		} catch (HibernateException e) {
			
		}
		
		
		return product;
		
	}
}
