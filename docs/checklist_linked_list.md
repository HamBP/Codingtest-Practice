# LinkedList 구현 체크리스트

## 기본 연산 구현 여부
- [ ] `addFirst(value)` — 맨 앞에 삽입
- [ ] `addLast(value)` — 맨 뒤에 삽입
- [ ] `add(index, value)` — 특정 인덱스에 삽입
- [ ] `removeFirst()` — 맨 앞 삭제 후 반환
- [ ] `removeLast()` — 맨 뒤 삭제 후 반환
- [ ] `remove(index)` — 특정 인덱스 삭제 후 반환
- [ ] `get(index)` — 특정 인덱스 값 조회
- [ ] `size` / `isEmpty()`

---

## 테스트 케이스

### 삽입
```
빈 리스트에 addFirst(1)          → [1]
[1]에 addFirst(2)               → [2, 1]
[2,1]에 addLast(3)              → [2, 1, 3]
[2,1,3]에 add(1, 99)            → [2, 99, 1, 3]
add(0, x) == addFirst(x)
add(size, x) == addLast(x)
```

### 삭제
```
[1,2,3]에서 removeFirst()       → 반환 1,  리스트 [2,3]
[1,2,3]에서 removeLast()        → 반환 3,  리스트 [1,2]
[1,2,3]에서 remove(1)           → 반환 2,  리스트 [1,3]
단일 노드 [1]에서 removeFirst()  → 반환 1,  리스트 []  (head/tail null 처리)
```

### 경계값 (꼭 확인)
```
빈 리스트에서 removeFirst()      → 예외 또는 null
빈 리스트에서 get(0)             → 예외 또는 null
index < 0 또는 index >= size    → 예외
size == 1일 때 삭제 후 head == tail == null
```

### 순회 / 조회
```
[1,2,3]에서 get(0) == 1
[1,2,3]에서 get(2) == 3
toString() 또는 toList()로 전체 출력 확인
```

### 양방향(Doubly) 추가 확인 사항
```
삽입 후 prev 포인터도 올바르게 연결됐는지
삭제 후 앞/뒤 노드의 prev·next 모두 갱신됐는지
head.prev == null, tail.next == null
```

---

## 자주 놓치는 버그 포인트

| 상황 | 놓치기 쉬운 것 |
|---|---|
| 빈 리스트에 첫 삽입 | head와 tail 동시에 갱신 |
| 마지막 노드 삭제 | tail 갱신 + 이전 노드 next = null |
| 첫 번째 노드 삭제 | head 갱신 + 이전 head의 prev 정리 |
| 단일 노드 삭제 | head = null, tail = null 동시 처리 |
| index 경계 | `0..size-1` 범위 체크 |
