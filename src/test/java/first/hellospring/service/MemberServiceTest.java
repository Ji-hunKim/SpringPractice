package first.hellospring.service;

import first.hellospring.domain.Member;
import first.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;


    // 테스트 실행하기 전에 테스트 마다 메모리멤버 repo 만들고 그거를 멤버 repo에 넣고
    // 멤버 repo를 memberservice에 넣어주면 같은 메모리 멤버 repo 사용
    // = DI (dependency injection) 의존성 주입
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void signUp() {
        // given - when - then 문법
        // given
        Member member = new Member();
        member.setName("hello");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }


    /** 중복 회원 예외 검증이 더 중요 **/
    @Test
    public void duplicatedCheck() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when
//        try {
//            memberService.join(member2);
//            // 이게 멀쩡히 넘어가면 fail 인것임
//            fail();
//        } catch (IllegalStateException e) {
//            // exception 터져서 정상적으로 실행한 것
//            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }
        // 좀 더 좋은 문법
        memberService.join(member1);
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        // 뒤에 있는 member~ 로직 실행할건데 앞에 예외가 터져야한다.

        // 메세지 검증 -> cmd option v -> e값에 저장하고 확인
//        memberService.join(member1);
//        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
//        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}