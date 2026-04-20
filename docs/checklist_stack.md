# Stack 구현 체크리스트

## 기본 연산 구현 여부
- [ ] `push(value)` — 스택 최상단에 삽입
- [ ] `pop()` — 최상단 값 제거 후 반환 (빈 경우 null 또는 예외)
- [ ] `peek()` — 최상단 값 조회 (제거 없이)
- [ ] `isEmpty()` — 비어있는지 확인
- [ ] `size` — 현재 요소 수

---

## 테스트 케이스

### push
```
빈 스택에 push(1)        → size == 1, peek() == 1
push(1), push(2)        → peek() == 2  (LIFO)
연속 push 후 size 확인
```

### pop
```
push(1), push(2), pop() → 반환 2, size == 1
push(1), pop()          → 반환 1, isEmpty() == true
빈 스택에서 pop()        → null 반환 또는 예외
연속 pop 후 size == 0
```

### peek
```
push(1), push(2), peek() → 반환 2, size 변화 없음 (== 2)
빈 스택에서 peek()        → null 반환 또는 예외
```

### isEmpty / size
```
초기 상태              → isEmpty() == true, size == 0
push 후               → isEmpty() == false
push → pop 반복 후    → isEmpty() == true, size == 0
```

---

## 자주 놓치는 버그 포인트

| 상황 | 놓치기 쉬운 것 |
|---|---|
| 빈 스택에서 pop/peek | null 반환 vs 예외 중 일관된 정책 선택 |
| pop 후 size 감소 | size-- 누락 |
| 배열 기반 구현 | top 포인터 초기값 (-1 vs 0) 혼동 |
| 연결리스트 기반 구현 | head(top) 갱신 누락 |
