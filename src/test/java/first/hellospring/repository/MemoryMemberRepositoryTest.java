package first.hellospring.repository;

import first.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 메서드 실행 끝날때마다 동작 (콜백 메서드)
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {   // test로 메서드 실행됨
        Member member = new Member();  // 메인 메서드 세팅해주는거랑 비슷
        member.setName("JoonieKim");

        repository.save(member);  // 저장할때 아이디 세팅됨

        Member result = repository.findById(member.getId()).get();  // 옵셔널에서 값 꺼냄, get으로 꺼내는게 좋은건 아님
        // Assertions.assertEquals(member, result); // 저장한 멤버와 결과값이 같은지 비교 -> 녹색불이 뜸(성공)
        // 요즘엔 아래껄 더 많이 씀 (assert J)
        Assertions.assertThat(member).isEqualTo(result); // 멤버가 result랑 똑같은지 비교해주는 것
        // Assertions을 static import로 해서 (옵션 + 엔터) assertThat 사용가능
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        Assertions.assertThat(result).isEqualTo(member2);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}
