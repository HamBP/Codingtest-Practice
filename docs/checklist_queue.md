# Queue 구현 체크리스트

## 기본 연산 구현 여부
- [ ] `enqueue(value)` — 맨 뒤에 삽입
- [ ] `dequeue()` — 맨 앞 값 제거 후 반환 (빈 경우 null)
- [ ] `peek()` — 맨 앞 값 조회 (제거 없이)
- [ ] `isEmpty()` — 비어있는지 확인
- [ ] `size` — 현재 요소 수

---

## 테스트 케이스

### enqueue
```
빈 큐에 enqueue(1)              → size == 1, peek() == 1
enqueue(1), enqueue(2)         → peek() == 1  (FIFO)
연속 enqueue 후 size 확인
```

### dequeue
```
enqueue(1), enqueue(2), dequeue() → 반환 1, size == 1
enqueue(1), dequeue()             → 반환 1, isEmpty() == true
빈 큐에서 dequeue()                → null 반환
연속 dequeue 후 size == 0
```

### peek
```
enqueue(1), enqueue(2), peek()    → 반환 1, size 변화 없음 (== 2)
빈 큐에서 peek()                   → null 반환
```

### 원형 큐 (배열 기반 구현 시 추가 확인)
```
capacity 꽉 찬 상태에서 enqueue   → 예외 또는 동적 확장
dequeue 후 front 포인터 wrap-around 확인
enqueue → dequeue 반복 후 재사용 가능한지
```

### isEmpty / size
```
초기 상태                         → isEmpty() == true, size == 0
enqueue 후                        → isEmpty() == false
enqueue → dequeue 반복 후         → isEmpty() == true, size == 0
```

---

## 자주 놓치는 버그 포인트

| 상황 | 놓치기 쉬운 것 |
|---|---|
| 빈 큐에서 dequeue/peek | null 반환 vs 예외 중 일관된 정책 |
| 원형 큐 인덱스 | `(index + 1) % capacity` wrap-around 누락 |
| 원형 큐 포화 판별 | `front == rear` 가 빈 큐인지 꽉 찬 큐인지 구분 |
| dequeue 후 size 감소 | size-- 누락 |
| 연결리스트 기반 | tail 갱신 누락 (단일 노드 삭제 시 tail = null) |
