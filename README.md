# 스프링 영속성 Best Practice

## 연관관계

### 단방향 @OneToMany 연관관계를 피하는 이유

- `@ManyToOne` 누락되면 부모-자식 연관관계를 관리하기 위한 연결 테이블이 추가됨
  - ex) `author` - `author_books` - `book`
  - 3개의 조인 필요
  - 2개의 외래키로 불필요한 메모리 낭비
