# Binary Search Tree (BST) 구현 체크리스트

## 기본 연산 구현 여부
- [ ] `insert(value)` — BST 규칙에 따라 삽입 (left < root < right)
- [ ] `contains(value)` — 값 탐색
- [ ] `remove(value)` — 삭제 (리프 / 자식 1개 / 자식 2개 세 경우)
- [ ] `inorder()` — 중위 순회 → 정렬된 결과 반환
- [ ] `min()` — 최솟값 (가장 왼쪽)
- [ ] `max()` — 최댓값 (가장 오른쪽)
- [ ] `height()` — 트리 높이
- [ ] `size` — 전체 노드 수

---

## 테스트 케이스

### insert / 구조 검증
```
insert 순서: 5, 3, 7, 1, 4, 6, 8

         5
        / \
       3   7
      / \ / \
     1  4 6  8

inorder → [1, 3, 4, 5, 6, 7, 8]  (정렬 보장)
중복 insert(5) → size 변화 없음 (또는 정책에 따라)
```

### contains
```
contains(4) → true
contains(9) → false
빈 트리에서 contains(1) → false
```

### remove — 리프 노드
```
[5,3,7,1,4,6,8]에서 remove(1)
→ inorder: [3, 4, 5, 6, 7, 8], size == 6
```

### remove — 자식 1개
```
[5,3,7,1,6,8]에서 remove(3)  (3은 left=1만 존재)
→ 5의 left가 1로 교체
→ inorder: [1, 5, 6, 7, 8]
```

### remove — 자식 2개 (핵심)
```
[5,3,7,1,4,6,8]에서 remove(3)  (3은 left=1, right=4)
→ 오른쪽 서브트리의 최솟값(4, in-order successor)으로 교체
→ inorder: [1, 4, 5, 6, 7, 8]
```

### remove — root 삭제
```
[5,3,7]에서 remove(5)
→ root가 7(successor) 또는 3(predecessor)으로 교체
→ 트리 구조 유효성 유지
```

### remove — 존재하지 않는 값
```
remove(99) → 예외 없이 트리 변화 없음
```

### min / max
```
[5,3,7,1,4,6,8]에서 min() → 1
[5,3,7,1,4,6,8]에서 max() → 8
빈 트리에서 min() / max() → null
```

### height
```
빈 트리 → 0
단일 노드 → 1
[5,3,7,1,4,6,8] → 3
우편향 트리 insert(1,2,3,4) → 4
```

---

## 자주 놓치는 버그 포인트

| 상황 | 놓치기 쉬운 것 |
|---|---|
| remove — 자식 2개 | successor(오른쪽 서브트리 최솟값) 찾은 뒤 successor 자신도 트리에서 제거 필요 |
| remove — root 삭제 | parent 없으므로 root 포인터 직접 갱신 |
| remove — 자식 1개 | left/right 중 어느 쪽인지 모두 처리 |
| inorder 정렬 보장 | BST 불변식이 깨지면 inorder가 정렬되지 않음 → 삽입/삭제 후 확인 |
| 중복 값 정책 | 무시 / 덮어쓰기 / 오른쪽 삽입 중 일관되게 선택 |
