package com.webex.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

/*
*   MyBatis에서 DAO라고 불리는 데이터 레이어 접근자를 JPA에서는 Repository로 표기한다.
*   Entitiy클래스와 Entity Repository는 함께 위치해야 한다.
*   둘은 아주 밀접한 관계고, Entity는 Repository 없이 제대로 된 역할을 할 수 없다.
*   나중에 유지보수할 때도 용이하므로 엔티티와 레포지토리는 같은 패키지에서 관리한다.
* */
public interface PostsRepository extends JpaRepository<Posts, Long> {
}
