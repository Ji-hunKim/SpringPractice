package first.hellospring.repository;

import first.hellospring.domain.Member;

import java.util.List;

import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); // 저장소에 회원이 저장
    Optional<Member> findById(Long id);   // 저장소에서 찾아오는 것
    Optional<Member> findByName(String name);   // find by null 반환할때 optional로 감싸서 반환하는 것이 optional의 기능

    List<Member> findAll();  // 지금까지 저장된 모든 회원 리스트 반환
}
