package jpabook.jpashop.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Member extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String name;
    @Embedded
    Address address;
//    @OneToMany(mappedBy = "member")
//    private List<Order> orders = new ArrayList<>();


    public Member() {
    }

    public Member(String name, String city, String street, String zipCode) {
        this.name = name;
        this.address = new Address(city, street, zipCode);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
