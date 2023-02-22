package jpabook.jpashop;

import java.time.LocalDateTime;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import jpabook.jpashop.domain.Album;
import jpabook.jpashop.domain.Book;
import jpabook.jpashop.domain.Category;
import jpabook.jpashop.domain.Delivery;
import jpabook.jpashop.domain.DeliveryStatus;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Movie;
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
            Album album = new Album("좋은 밤 좋은 꿈", 10000, 1, "너드커넥션", "");
            em.persist(album);

            Book book = new Book("12가지 인생의 법칙", 16000, 1, "조던 피터슨", "12345");
            em.persist(book);

            Movie movie = new Movie("쇼생크 탈출", 12000, 1, "프랭크 다라본트", "모건 프리먼");
            em.persist(movie);

            // 카테고리 설정
            Category albumChildCategory = new Category("락");
            albumChildCategory.addItem(album);
            em.persist(albumChildCategory);

            Category albumParentCategory = new Category("노래");
            albumParentCategory.addChildCategory(albumChildCategory);
            em.persist(albumParentCategory);

            Category bookChildCategory = new Category("인문학");
            bookChildCategory.addItem(album);
            em.persist(bookChildCategory);

            Category bookParentCategory = new Category("종이책");
            bookParentCategory.addChildCategory(bookChildCategory);
            em.persist(bookParentCategory);

            Category movieChildCategory = new Category("미국");
            movieChildCategory.addItem(album);
            em.persist(movieChildCategory);

            Category movieParentCategory = new Category("외국 영화");
            movieParentCategory.addChildCategory(movieChildCategory);
            em.persist(movieParentCategory);

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
            OrderItem orderItem1 = new OrderItem(album, album.getPrice(), 1);
            order.addOrderItem(orderItem1);
            em.persist(orderItem1);

            OrderItem orderItem2 = new OrderItem(book, book.getPrice(), 1);
            order.addOrderItem(orderItem2);
            em.persist(orderItem2);

            OrderItem orderItem3 = new OrderItem(movie, movie.getPrice(), 1);
            order.addOrderItem(orderItem3);
            em.persist(orderItem3);

            em.flush();
            Order findOrder = em.find(Order.class, order.getId());

            em.clear();
            // orphanRemoval
            findOrder.getOrderItems().remove(0);
            // cascade
            em.remove(findOrder);

            tx.commit();
        } catch (Exception exception) {
            tx.rollback();
            exception.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }
}
