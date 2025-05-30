# 수업 목표

파이썬을 활용하여, 포맷이 정의되어 있는 엑셀파일을 만들고, 이를 하드디스크에 저장할 수 있다.

# 요구사항

`
data = [
    ["이름", "나이", "도시"],
    ["홍길동", 30, "서울"],
    ["김철수", 25, "부산"],
    ["이영희", 28, "대전"],
]

위와 같은 2차원 배열을 활용하여 아래와 같은 형태의 엑셀 파일로 생성`

![image](https://github.com/user-attachments/assets/0cb02462-7f02-4c7c-b0c5-c73278f1ab01)

# 심화

1. 위와 같이 데이터를 Sheet1에 저장하고, 평균나이를 계산하여, Sheet2에 다음과 같이 작성한다.
2. 모든 데이터는 가운데 정렬을 수행해야 한다.
3. 생성된 파일은 read-only이어야 한다
4. 파일의 제목은 "학생 관리 파일 - {현재 날짜}"이어야 한다.

   ![image](https://github.com/user-attachments/assets/f3cc010c-9b5e-4eee-880a-5ef8d85cacd6)

# 본문

## pip 설치 및 개요 설명

pip은 외부 라이브러리를 관리하는 패키지 매니저이자 의존성 관리 도구이다.
개발자는 pip을 통해 필요한 라이브러리뿐만 아니라, 해당 라이브러리가 의존하는 다른 라이브러리들도 함께 설치할 수 있어, 복잡한 의존성 관리를 손쉽게 할 수 있다.

https://www.geeksforgeeks.org/how-to-install-pip-in-macos/

### pandas　개요 및 설치

#### 설치
`
pip install pandas openpyxl
`

### pandas란?

> pandas는 엑셀처럼 표(테이블) 형태로 데이터를 쉽게 다루게 해주는 파이썬 라이브러리로
데이터를 읽고, 고치고, 계산하고, 정리하는 걸 빠르고 간단하게 만들 수 있다.

pandas를 활용하면, 행렬 형태의 데이터를 간단하고, 효율적으로 처리할 수 있으며, 다양한 형태의 연산, 예를 들어 집계 등을 간단하게 처리할 수 있다.

## 코드

```
import pandas as pd

data = [
    ["Alice", 25, "New York"],
    ["Bob", 30, "Los Angeles"],
    ["Charlie", 28, "Chicago"]
]

# 데이터프레임 생성
df = pd.DataFrame(data, columns=["이름", "나이", "도시"])

# 엑셀 파일로 저장 (index=False는 인덱스 컬럼을 저장하지 않겠다는 뜻이야)
# df.to_excel("/home/output.xlsx", index=False)

avg_age = df['나이'].mean()

df_avg = pd.DataFrame({'평균 나이': [avg_age]})

with pd.ExcelWriter("/home/output.xlsx", engine='openpyxl') as writer:
    # 첫 번째 시트에 데이터 저장
    df = pd.DataFrame(data, columns=["이름", "나이", "도시"]).to_excel(writer, sheet_name='Sheet1', index=False)

    # 두 번째 시트에 다른 데이터 저장
    df_avg.to_excel(writer, sheet_name='Sheet2', index=False)
```



