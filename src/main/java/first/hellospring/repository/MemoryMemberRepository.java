package first.hellospring.repository;

import first.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    // 저장할 곳
    private static Map<Long, Member> store = new HashMap<>(); // 실무에서는 동시성 문제로 concurrent hash 써야함
    private static long sequence = 0L; // 시퀀스는 key 값 생성해주는 것, 실무에서는 long보다는 atomic long

    @Override
    public Member save(Member member) {
        member.setId(++sequence);            // store 넣기 전에 member 아이디 값 세팅
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));    //null 반환 가능성 있어서 optional로 감싸줌
                                                      // 이렇게 하면 클라이언트로 조작가능
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() // 루프 돌림
                .filter(member -> member.getName().equals(name))  //같은지 확인
                .findAny(); // 하나라도 찾으면 반환, 끝까지 없으면 optional null
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
