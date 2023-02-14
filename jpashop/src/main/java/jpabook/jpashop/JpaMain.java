package jpabook.jpashop;

import java.time.LocalDateTime;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import jpabook.jpashop.domain.Delivery;
import jpabook.jpashop.domain.DeliveryStatus;
import jpabook.jpashop.domain.Item;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.OrderStatus;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            // 아이템 생성
            Item item = new Item("사과", 1000, 10);
            em.persist(item);

            // 회원 등록
            Member member = new Member("Yuwonwoo", "Gunpo", "328 Beonyeong-ro", "15870");
            em.persist(member);

            // 주문
            Order order = new Order(member, LocalDateTime.now(), OrderStatus.ORDER);
            em.persist(order);

            // 배송 등록
            Delivery delivery = new Delivery("Sejong", "Gwangjin-ro", "12345",
                    DeliveryStatus.READY);
            delivery.changeOrder(order);
            em.persist(delivery);

            // 주문 목록 담기
            OrderItem orderItem = new OrderItem(item, item.getPrice(), 1);
            order.addOrderItem(orderItem);
            em.persist(orderItem);

            tx.commit();
        } catch (Exception exception) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
