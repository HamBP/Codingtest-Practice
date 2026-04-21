# Binary Tree 구현 체크리스트

## 기본 연산 구현 여부
- [ ] `insert(value)` — 레벨 순서(BFS)로 삽입 (완전 이진 트리 유지)
- [ ] `contains(value)` — 값 존재 여부 탐색
- [ ] `preorder()` — 전위 순회 (Root → Left → Right)
- [ ] `inorder()` — 중위 순회 (Left → Root → Right)
- [ ] `postorder()` — 후위 순회 (Left → Right → Root)
- [ ] `levelorder()` — 레벨 순회 (BFS)
- [ ] `height()` — 트리 높이
- [ ] `size` — 전체 노드 수

---

## 테스트 케이스

### 트리 구조 (공통 픽스처)
```
         1
        / \
       2   3
      / \ / \
     4  5 6  7
```

### insert
```
빈 트리에 insert(1)              → root == 1
insert(1,2,3)                   → 레벨 순서로 배치
insert(1,2,3,4,5,6,7)           → 완전 이진 트리 형태
```

### 전위 순회 (preorder: Root→L→R)
```
위 트리 → [1, 2, 4, 5, 3, 6, 7]
단일 노드 [1] → [1]
빈 트리 → []
```

### 중위 순회 (inorder: L→Root→R)
```
위 트리 → [4, 2, 5, 1, 6, 3, 7]
단일 노드 [1] → [1]
빈 트리 → []
```

### 후위 순회 (postorder: L→R→Root)
```
위 트리 → [4, 5, 2, 6, 7, 3, 1]
단일 노드 [1] → [1]
빈 트리 → []
```

### 레벨 순회 (levelorder: BFS)
```
위 트리 → [1, 2, 3, 4, 5, 6, 7]
단일 노드 [1] → [1]
빈 트리 → []
```

### contains
```
위 트리에서 contains(4) → true
위 트리에서 contains(9) → false
빈 트리에서 contains(1) → false
```

### height
```
빈 트리         → 0
단일 노드 [1]   → 1
위 트리 (3레벨) → 3
좌편향 트리     → 노드 수와 동일
```

### size
```
빈 트리         → 0
insert 1회      → 1
위 트리         → 7
```

---

## 자주 놓치는 버그 포인트

| 상황 | 놓치기 쉬운 것 |
|---|---|
| 빈 트리 순회 | null root 처리 없으면 NPE |
| 재귀 순회 | left/right 순서 실수 (특히 후위) |
| 레벨 순회 | Queue 사용 필수, 자식 null 체크 |
| height 계산 | `max(left, right) + 1`, 빈 트리는 0 |
| insert BFS 방식 | left 없으면 left에, left 있고 right 없으면 right에 삽입 |
