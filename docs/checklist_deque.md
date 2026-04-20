# Deque 구현 체크리스트

## 기본 연산 구현 여부
- [ ] `addFirst(value)` — 맨 앞에 삽입
- [ ] `addLast(value)` — 맨 뒤에 삽입
- [ ] `removeFirst()` — 맨 앞 값 제거 후 반환 (빈 경우 null)
- [ ] `removeLast()` — 맨 뒤 값 제거 후 반환 (빈 경우 null)
- [ ] `peekFirst()` — 맨 앞 값 조회 (제거 없이)
- [ ] `peekLast()` — 맨 뒤 값 조회 (제거 없이)
- [ ] `isEmpty()` — 비어있는지 확인
- [ ] `size` — 현재 요소 수

---

## 테스트 케이스

### addFirst / addLast
```
addFirst(1)                       → [1]
addFirst(2), addFirst(3)          → [3, 2, 1]  (앞에 계속 추가)
addLast(1), addLast(2)            → [1, 2]     (뒤에 계속 추가)
addFirst(1), addLast(2)           → [1, 2]
addLast(1), addFirst(2)           → [2, 1]
```

### removeFirst / removeLast
```
[1,2,3]에서 removeFirst()         → 반환 1, 리스트 [2,3]
[1,2,3]에서 removeLast()          → 반환 3, 리스트 [1,2]
단일 노드 [1]에서 removeFirst()    → 반환 1, isEmpty() == true
단일 노드 [1]에서 removeLast()     → 반환 1, isEmpty() == true
빈 덱에서 removeFirst()            → null 반환
빈 덱에서 removeLast()             → null 반환
```

### peekFirst / peekLast
```
[1,2,3]에서 peekFirst()           → 반환 1, size 변화 없음
[1,2,3]에서 peekLast()            → 반환 3, size 변화 없음
빈 덱에서 peekFirst()              → null
빈 덱에서 peekLast()               → null
```

### 원형 배열 wrap-around
```
addFirst를 반복해 head가 앞으로 wrap-around 되는지
addLast를 반복해 tail이 wrap-around 되는지
removeFirst → addFirst 반복 후 재사용 가능한지
```

### isEmpty / size
```
초기 상태                          → isEmpty() == true, size == 0
addFirst / addLast 후             → isEmpty() == false
모두 제거 후                       → isEmpty() == true, size == 0
```

---

## 자주 놓치는 버그 포인트

| 상황 | 놓치기 쉬운 것 |
|---|---|
| addFirst 인덱스 계산 | `(head - 1 + capacity) % capacity` — 음수 방지 |
| removeLast 인덱스 계산 | `(tail - 1 + capacity) % capacity` — 음수 방지 |
| 단일 노드 제거 | head == tail 상태 복원 |
| size와 포화 판별 분리 | `head == tail`만으로 빈/꽉참 구분 불가 → size로 판별 |
