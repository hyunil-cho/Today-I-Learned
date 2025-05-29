# What is Pandas?

> 관계형 또는 레이블이 된 데이터로 쉽고 직관적 으로 작업할 수 있도록 설계되었고, 빠르고, 유연한 데이터 구조를 제공하는 Python 패키지입니다.
>> 1. SQL 테이블 또는 Excel 스프레드 시트에서와 같은 열과 행으로 이루어진 테이블 형식 데이터
>> 2. 정렬되고 정렬되지 않은 시계열 데이터
>> 3. 다른 형태의 관찰 / 통계 데이터 세트

## How to use?

```
import pandas as pd # 관례상, pd로 많이 사용함
```

# Pandas 기본 자료구조

## Series

1. 데이터를 담는 차원 배열 구조를 가집니다.
2. 인덱스(index)를 사용 가능합니다.
3. 데이터 타입을 가집니다. (dtype)
4. 2차원 배열의 행, 혹은 열을 나타낼 수 있다. 하지만 주로 열(column)으로 이용됨

**주의! 가능하면 동일한 데이터 타입을 사용하는 것이 좋음**
**소수점을 처리해야 하는 경우, 혹은 반대의 경우, Series 선언 시, 사용하고자 하는 타입 명시 가능**

```
import pandas as pd

se = pd.Series([1,2,3])
print(se[0])
print(se[1])

s = pd.Series([1,2,3], dtype='float64')
```

## Series 내의 여러 내장 기능

1. fancy indexing
```
se = pd.Series([1,2,3])
s[[0,1]]
# Series의 경우, 파이썬의 배열과 마찬가지로 슬라이싱도 가능
```
2. boolean indexing
```
np.random.seed(0)
s = pd.Series(np.random.randint(10000, 20000, size=(10,)))
s[s > 10000] # 이외, 여러 boolean 표현식 사용 가능
```
3. Index 지정
```
s = pd.Series(['마케팅', '경영', '개발', '기획', '인사'], index=['a', 'b', 'c', 'd', 'e'])
print(s)
print(s.index)
```
4. 그외
isnull(), isna() 결측치(Nan) 값을 찾는 함수
notnull() 등

## DataFrame

1. 2차원 데이터 구조 (Excel 데이터 시트를 생각하시면 됩니다)
2. 행(row), 열(column)으로 구성되어 있습니다.
3. 각 열(column)은 각각의 데이터 타입 (dtype)을 가집니다.

### How to create?

```
df = pd.DataFrame([[1, 2, 3], 
              [4, 5, 6], 
              [7, 8, 9]])

print(df)

pd.DataFrame([[1, 2, 3], 
              [4, 5, 6], 
              [7, 8, 9]], columns=['가', '나', '다'])

print(df)

# 딕셔너리로 생성 가능, 이 경우, 칼럼은 키 값으로 자동 매핑

data = {
    'name': ['Kim', 'Lee', 'Park'], 
    'age': [24, 27, 34], 
    'children': [2, 1, 3]
}

pd.DataFrame(data)

```

### 속성

- index: index (기본 값으로 RangeIndex)
- columns: column 명
- values: numpy array형식의 데이터 값
- dtypes: column 별 데이터 타입
- T: DataFrame을 전치(Transpose)
