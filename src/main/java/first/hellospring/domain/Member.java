package first.hellospring.domain;

public class Member {
    private Long id;   // system 등록할 때 id
    private String name;   // 가입할때 이름

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
}
