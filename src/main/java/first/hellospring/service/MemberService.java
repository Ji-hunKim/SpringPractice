package first.hellospring.service;

import first.hellospring.domain.Member;
import first.hellospring.repository.MemberRepository;
import first.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
//    private final MemberRepository memberRepository= new MemoryMemberRepository();
    // 멤버 서비스의 new MemoryMemberRepository와 Testcase의 그것이 다른 인스턴스임. (static이기 때문에 문제가 안되지만 아니라면 문제가 생길 수 있음)
    // 그리고 같은 repository로 테스트하려고 하는건데 지금 다른 레파지토리로 테스트 되고 있는 것

    // 의존성 주입
    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }




    /** 회원가임 **/
    public Long join(Member member) {
        //같은 이름이 있는 중복 회원 안됨
        validateDuplicateMemeber(member); // 중복회원검증
        /* 로직이 나오는 경우는 메서드로 뽑는 것이 좋음 */
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMemeber(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(member1 -> { // 멤버 값이 있으면 로직 동작 (Optional이 있기 때문에 가능) 과거 : if null
                    // get 권장하진 않음, orElseGet도 있음 (있으면 꺼내)
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                 });

        /* 좀더 쉬운 코드
        memberRepository.findByName(member.getName())
           .ifPresent(m -> {
               throw new IllegalStateException("이미 존재하는 회원입니다.")
         }) */
    }

    /** 전체 회원 조회 **/
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
