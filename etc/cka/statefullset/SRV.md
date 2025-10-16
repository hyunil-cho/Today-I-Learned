# SRV (Service) DNS 레코드 — 완전 정복

> SRV 레코드(Service record)는 도메인 네임 시스템(DNS)에서 특정 서비스가 제공되는 호스트와 포트 정보를 제공하는 레코드입니다. 서비스 기반의 서비스 디스커버리에 널리 사용되며, SIP, XMPP, LDAP, Minecraft, Kubernetes 등 다양한 프로토콜과 시스템에서 중요한 역할을 합니다.

---

## 목차

1. SRV 레코드란?
2. 레코드 형식과 필드 설명
3. 우선순위(Priority)와 가중치(Weight)의 동작 원리
4. 일반적인 사용 사례
5. DNS 존 파일(BIND) 예시
6. `dig`/`nslookup`로 SRV 조회하기
7. TLS/HTTPS와 SRV의 관계
8. CNAME과의 상호작용
9. 와일드카드 및 기타 주의사항
10. 보안 및 운영상의 고려사항
11. 요약

---

## 1. SRV 레코드란?

SRV(Sender/Service) 레코드는 특정 서비스(예: `sip`, `xmpp`)가 어느 호스트의 어떤 포트에서 동작하는지를 DNS에 기술하는 레코드입니다. 기존의 A/AAAA 레코드(호스트명 → IP)와 달리, SRV는 **서비스 이름**과 **프로토콜**(TCP/UDP)을 기반으로 대상 호스트와 포트를 알려줍니다.

SRV 레코드는 RFC 2782에서 정의되어 있습니다.

**대표적 장점**

* 서비스 엔드포인트(호스트/포트)를 DNS만으로 동적으로 배포 가능
* 여러 엔드포인트에 대한 부하분산 및 장애 전환(우선순위/가중치) 지원
* 서비스 디스커버리에 적합 (클라이언트는 DNS만 조회하면 됨)

---

## 2. 레코드 형식과 필드 설명

SRV 레코드의 레코드형(레코드 데이터)은 다음 4개의 숫자/값을 가집니다:

```
_priority_ _weight_ _port_ _target_
```

* **Priority (우선순위)**: 숫자가 낮을수록 우선적으로 선택됩니다. 같은 우선순위 그룹 내에서 가중치에 따라 선택됩니다.
* **Weight (가중치)**: 동일 우선순위 내에서 여러 레코드의 트래픽을 분산하는 비율(정수). 0 이상의 정수.
* **Port (포트)**: 서비스가 실제로 리스닝하는 TCP/UDP 포트 번호.
* **Target (목적지 호스트)**: 연결할 호스트명(A/AAAA 레코드로 IP를 해석).

### 전체 레코드 예시

```
_sip._tcp.example.com. 3600 IN SRV 10 60 5060 sip1.example.com.
_sip._tcp.example.com. 3600 IN SRV 10 20 5060 sip2.example.com.
_sip._tcp.example.com. 3600 IN SRV 10 20 5060 sip3.example.com.
```

위 예시에서 클라이언트는 `sip._tcp.example.com`을 조회하여 우선순위 10 그룹(모두 동일)에서 가중치 합(60+20+20=100)에 따라 확률적으로 `sip1`(60%) 또는 `sip2`(20%)/`sip3`(20%)로 트래픽을 보냅니다.

---

## 3. 우선순위(Priority)와 가중치(Weight)의 동작 원리

1. 클라이언트는 조회 결과의 **가장 낮은 Priority 값**을 가진 레코드들만 고려합니다.
2. 그 그룹 내에서 Weight 값에 따라 **비례 분배**를 합니다.

   * Weight가 모두 0이면, 모두 동일한 확률로 선택됩니다.
   * Weight가 다르면, 각 엔트리의 Weight를 합한 값에서 비율로 선택합니다.

**예시 동작**

* 레코드 A: priority 5, weight 10
* 레코드 B: priority 5, weight 90
  클라이언트는 같은 우선순위 그룹이므로 B를 약 90%의 확률로 선택합니다.

**주의:** RFC에서는 클라이언트가 선택한 레코드를 시도하고 실패할 경우, 같은 그룹 내 다른 레코드로 시도하도록 권장합니다.

---

## 4. 일반적인 사용 사례

* **Voice over IP (SIP)**: `_sip._tcp` 또는 `_sip._udp`로 서버 디스커버리
* **XMPP (Jabber)**: `_xmpp-server._tcp`, `_xmpp-client._tcp`
* **LDAP**: `_ldap._tcp` — LDAP 서버를 찾을 때 사용
* **Minecraft 서버**: `_minecraft._tcp` (일부 서버에서 사용)
* **서비스 디스커버리 전반**: 마이크로서비스 환경에서 특정 포트로 프로토콜을 지정해 서비스 디스커버리에 사용

---

## 5. DNS 존 파일(BIND) 예시

```
$ORIGIN example.com.

; SRV for SIP over TCP
_sip._tcp      3600 IN SRV 10 60 5060 sip1.example.com.
_sip._tcp      3600 IN SRV 10 20 5060 sip2.example.com.
_sip._tcp      3600 IN SRV 10 20 5060 sip3.example.com.

; A records for targets
sip1    3600 IN A 192.0.2.11
sip2    3600 IN A 192.0.2.12
sip3    3600 IN A 192.0.2.13
```

**참고:** Target은 반드시 FQDN이어야 하며, 최종적으로 A/AAAA로 해석될 수 있어야 합니다. 또한 target에 `.`(루트)로 끝내는 것이 안전합니다.

---

## 6. `dig` / `nslookup`로 SRV 조회하기

* `dig` 예시

```bash
# 특정 서비스/프로토콜 조회
dig +short SRV _sip._tcp.example.com

# 출력 예시
10 60 5060 sip1.example.com.
10 20 5060 sip2.example.com.
10 20 5060 sip3.example.com.
```

* `nslookup` 예시

```bash
nslookup -type=SRV _sip._tcp.example.com
```

클라이언트는 SRV 레코드를 받아서 각 Target에 대해 A/AAAA 레코드를 추가 조회해야 합니다.

---

## 7. TLS/HTTPS와 SRV의 관계

SRV는 서비스와 포트를 명시하지만, **HTTPS (웹 브라우징)** 표준에서는 일반적으로 사용되지 않습니다. 웹 브라우저는 기본적으로 SRV 레코드를 조회하지 않기 때문입니다.

그러나 특정 애플리케이션(또는 라이브러리)은 SRV를 사용해 TLS 서비스 위치를 찾을 수 있습니다. 예:

* 일부 XMPP/SIP 클라이언트

또한, ***autodiscover*** 같은 메커니즘에서는 SRV를 통해 서비스 엔드포인트를 찾도록 설계되기도 합니다.

---

## 8. CNAME과의 상호작용

SRV 레코드의 `target`은 **CNAME을 가리킬 수 있지만 주의가 필요**합니다.

* RFC 권장사항: SRV target이 CNAME으로 점프하면 최종적으로 A/AAAA로 해석이 가능해야 합니다.
* 일부 DNS 서버나 클라이언트에서 CNAME을 통한 추가 조회가 문제를 일으킬 수 있으니, 가능하면 target을 직접 A/AAAA로 설정하는 것이 안전합니다.

---

## 9. 와일드카드 및 기타 주의사항

* SRV 레코드는 와일드카드(`*`)를 지원하지만, 서비스/프로토콜 레이블(`_sip._tcp`)은 고정 형식이므로 와일드카드 사용은 제한적입니다.
* TTL 및 캐시: SRV 레코드의 TTL은 클라이언트가 레코드를 얼마나 오래 캐시할지 결정합니다. 가중치/우선순위 변경을 빠르게 반영해야 한다면 TTL을 짧게 설정하세요.
* 일부 클라이언트는 SRV 레코드의 Weight를 무시하거나 비정상적으로 처리하므로, 실서비스에서 동작 검증이 필요합니다.

---

## 10. 보안 및 운영상의 고려사항

* **DNSSEC**: SRV 레코드도 DNSSEC로 서명될 수 있으므로 데이터 위조를 방지할 수 있습니다.
* **부하분산과 헬스체크**: SRV는 DNS 레벨에서 분산을 도와주지만, 실제 서버 헬스 상태는 별도 헬스체크/오케스트레이션이 필요합니다. DNS는 실패를 즉시 감지해내지 못합니다.
* **포트 노출**: SRV는 포트를 명시하므로, 공개 DNS에 민감한 내부 포트 정보를 노출할 수 있습니다. 고려해서 공개/비공개 존을 관리하세요.

---

## 11. 요약

* SRV는 서비스 이름과 프로토콜에 대해 호스트와 포트를 제공하는 DNS 레코드입니다.
* 핵심 필드는 `priority`, `weight`, `port`, `target`입니다.
* 우선순위 → 가중치 순으로 선택 로직이 동작합니다.
* SIP, XMPP, LDAP 등 서비스 디스커버리에서 널리 사용됩니다.
* 운영 시 클라이언트 구현 차이(가중치 처리 등)와 보안(포트 노출, DNSSEC)을 고려해야 합니다.

---

### 부록: 빠른 레퍼런스

* RFC 2782 — A DNS RR for specifying the location of services (SRV RRs)

---

*원하시면 이 문서를 기반으로 블로그 포스트용 HTML 변환, 이미지(다이어그램) 추가, 혹은 예제(zone 파일과 클라이언트 동작 시퀀스) 시각화도 도와드릴게요.*
