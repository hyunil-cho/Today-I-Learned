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
```
2. boolean indexing
```
np.random.seed(0)
s = pd.Series(np.random.randint(10000, 20000, size=(10,)))
s[s > 10000] # 이외, 여러 boolean 표현식 사용 가능
```
