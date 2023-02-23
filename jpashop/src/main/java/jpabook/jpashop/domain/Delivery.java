package jpabook.jpashop.domain;

import static javax.persistence.FetchType.LAZY;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Delivery extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;
    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    private Order order;
    @Embedded
    Address address;
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    public Delivery() {
    }

    public Delivery(String city, String street, String zipCode, DeliveryStatus status) {
        this.address = new Address(city, street, zipCode);
        this.status = status;
    }

    public void changeOrder(Order order) {
        setOrder(order);
        order.setDelivery(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }
}
