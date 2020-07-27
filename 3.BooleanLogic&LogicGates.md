>[CrashCourse Computer Science](https://www.youtube.com/watch?v=gI-qXk7XojA&list=PL8dPuuaLjXtNlUrzyH5r6jN9ulIgZBpdo&t=0s) 강의를 듣고 정리했습니다. 정확하지 않은 내용이 있을 수 있습니다.

2가지의 전기적 상태만으로 우리는 중요한 정보를 표현할 수 있다. 우리는 이것을 두 가지 상태를 일컫는 이진Binary이라고 부른다. 이 두 가지는 정확히 참이거나 거짓을 나타낼 때 필요하다. 

초기 전자 컴퓨터 중에는 3 개의 상태를 가진 3진법 또는 5진법을 채택한 컴퓨터도 있었다. 그러나 중간 상태가 많을 수록 그것을 나누기가 힘들었다. 트랜지스터가 1초에 수백만 번 상태를 바꾼다면 상황은 더 안 좋다. 그래서 단지 on과 off만을 사용하고 두 개의 신호를 멀리 배치하게 되었다. 

![](https://images.velog.io/images/cocodori/post/15229c33-a224-4443-8e32-e10e7f0b97b4/1.png)


 이진법을 채택한 또 다른 이유는 참과 거짓을 다루는 학문이 독점적으로 있었기 때문이다. 그 수학 분야는 참과 거짓을 조작하는데 필요한 규칙과 연산방법을 모두 알아냈다. 이것을 **부울대수학**이라고 부른다.

부울 대수학은 1800년대 영국 수학자 조지 부울George Boole의 이름을 따서 만들었다. 그는 "아래에, 걸쳐서, 기준 밖의" 상태를 나타내는 논리적 표현에 관심이 있었다. 부울의 접근은 논리 방적식을 통해 공식, 체계적으로 증명 되었다.

일반적으로 대수학에서 변수의 값은 숫자이며 연산은 덧셈과 곱셈 같은 것이다. 부울 대수학에서는 변수의 값은 참과 거짓이고 연산은 논리적이다. 

- NOT
- AND
- OR

이 세가지가 부울 대수학의 핵심 연산이다. 

# NOT

NOT은 참이든 거짓이든 그것 중 하나의 값을 취하고 부정한다. 참은 거짓으로, 거짓은 참으로 뒤집는다. 

![](https://images.velog.io/images/cocodori/post/a78fd7a6-1dd4-4b25-8d08-e3dab9abf323/2.png)
여기서 트랜지스터에 있는 부울 논리를 배울 수 있다.

지난 번에 말했듯이 트랜지스터는 전기를 제어하는 작은 스위치일 뿐이다. 트랜지스터는 제어선과 두 개의 전극을 가지고 있다. 제어선에 전기를 제공하면 하나의 전극에 전기가 흐르게 할 수 있고 트랜지스터를 통해 다른 전극에도 전류가 흐를 수 있게 된다.

수도꼭지와 비슷하다. 수도를 열면 물이 흐르고, 수도를 닫으면 물은 멈춘다.

제어선을 입력으로 생각하고 하부에 있는 전극을 출력으로 생각할 수도 있다
![](https://images.velog.io/images/cocodori/post/fde7f3aa-4dbb-46d7-8230-201818d3c566/Untitled.png)
하나의 트랜지스터로 하나의 입력과 하나의 출력이 가능하다. 입력을 켜면, 전류가 흐를 수 있고 출력도 역시 켜진다. 입력을 끄면 출력도 역시 꺼지고 전류는 흐르지 않는다. 부울용어에서, 입력이 참이면 출력도 참이다. 입력이 거짓이면 출력도 거짓이다. 이것은 그리 흥미로운 회로는 아니다.

 그러나 이 회로를 약간만 수정해서 NOT을 만들 수 있다.

 트랜지스터 끝부분에 출력 선을 가지는 대신, 출력선을 조금 앞으로 움직인다고 가정한다.

입력을 켜면 트랜지스터는 전류를 통과시켜 접지 상태가 된다. 그리고 출력선은 전류를 받지 않고 꺼진다.

접지상태를 물에 비유하자면, 집에 모든 물이 흘러 바닥으로 닿고 거대한 호스에서 물이 모두 빠져나와 샤워할 물이 남아있지 않은 상태다. 그래서 이 경우, 입력은 켜지고 출력은 꺼진 상태가 된다. 우리가 트랜지스터를 끄면 전류는 접지상태가 되는 바닥으로 흐르지 못하게 되고 대신 전류는 출력선을 통해 흐른다. 그래서 입력은 꺼지고 출력은 켜진다.

![](https://images.velog.io/images/cocodori/post/1733ae94-09e5-4f91-bda8-fa9654d418a1/4.png)![](https://images.velog.io/images/cocodori/post/7d64c342-aa92-4d6d-96d8-30d09eabf2b8/5.png)

이것이 NOT 게이트다.

전류의 흐름을 통제하기 때문에 게이트라는 이름이 붙여졌다.

# AND

AND연산은 두 개의 입력이 필요하지만 마찬가지로 하나의 출력을 가진다. 두 개의 INPUT이 모두 참이어야만 OUTPUT도 참이 된다. 둘 중 하나라도 거짓이라면 OUTPUT은 거짓이다.

AND게이트를 만들기 위해 두 개의 트랜지스터를 연결해서 두 개의 입력과 하나의 출력을 만들어야 한다. 트랜지스터A와 B가 같이 켜져 있을 때만 전류가 흐른다.
![](https://images.velog.io/images/cocodori/post/d483919f-8109-4597-8969-bab63b74b337/6.png)
# OR

마지막 부울 연산은 OR이다. 하나의 입력만 참이어도 출력이 참이 되는 연산이다. OR연산은 모든 입력이 거짓일 때만 거짓이다. 

![](https://images.velog.io/images/cocodori/post/445c2813-fba9-4bf1-b637-8c1f67a4a30c/8.png)

하나만 참일 때
![](https://images.velog.io/images/cocodori/post/097cbd88-51ef-4a46-a4b9-39c72b8ecc67/9.png)
둘 다 참일 때

모두 참이다.
![](https://images.velog.io/images/cocodori/post/5e04bca5-89ca-4ec7-ba14-14a991f4abc3/media-1061594-max-bb-02.gif)

출처 : [https://www.edn.com/how-to-invert-three-signals-with-only-two-not-gates-and-no-xor-gates-part-1/](https://www.edn.com/how-to-invert-three-signals-with-only-two-not-gates-and-no-xor-gates-part-1/)

위 사진은 각각의 게이트를 표현하는 기호다.

# XOR

지금까지 살펴본 NOT, AND, OR 게이트를 이용해 또다른 유용한 연산을 할 수 있다. Exclusive OR(XOR)이다.

XOR은 OR연산과 같지만 모든 입력이 참일 때 XOR의 출력은 거짓이다. XOR은 둘 중 하나가 참일 때만 출력이 참이다. 

![](https://images.velog.io/images/cocodori/post/d08203d6-55c8-4278-acd5-fdb0abf661cc/xor_.png)

AND, OR, NOT을 이용해서 XOR게이트를 만드는 과정이다.

이 연산에 대해서는 다음 강의에서 좀 더 살펴볼 예정이다.

오늘 배운 논리 회로만으로 우리는 복잡한 논리문을 평가하는 시스템을 만들 수 있다.

 *이름이 김코코이고AND 평일 또는OR 주말이고AND 종로구에 있는 경우 김코코는 커피를 원할 것이다.*

위의 (나름) 복잡한 연산을 우리는 오늘 배운 것들로만 가지고 할 수 있다.