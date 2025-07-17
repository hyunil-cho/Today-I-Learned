# 스케줄러

## 역할

1. 파드를 어떤 노드에 올릴 지 결정하는 역할
  1.1 주의할 점은, 스케줄러는 단순히 지정만 하는 역할이며, 실제로 파드를 생성하거나 하지는 않음. -> 이는 각 노드의 kubelet이 담당


## 작동 방식

1. Filter Nodes
  -> 노드 중, 파드를 띄울 수 없는 노드를 제외(예를 들어 리소스가 부족하거나, taint가 걸리는 등)
2. Rank Nodes -> 
  -> Filtering 후 남은 노드 중, 현재 리소스 상황에 기반하여 0~10까지의 우선순위를 비교하고, 가장 높은 우선순위를 가지는 노드에 파드를 배치

## More

1. Resource Requirements and Limits
2. Taints and Tolerations
3. Node Selectors/Affinity
