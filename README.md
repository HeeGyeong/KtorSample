# KtorSample

## Description
Ktor을 사용하여 API 데이터를 가져와본 기본적인 Sample Code입니다.

Module Class와 ApiService Class를 통해 기존 사용하던 Retrofit과 다른 부분을 확인할 수 있습니다.

## DI
Hilt를 기본적으로 사용하도록 구현해 두었고, Koin을 사용할 수 있게 모듈을 나누어 두었습니다.

현재 구조에서는 주석처리를 하지 않아 두 가지의 DI를 바로 사용할 수 있으며, MainActivity에서 사용되는 VM의 의존성 주입 부분만 변경해주면 됩니다.

Gradle에 라이브러리 추가까지 다 해두었기 때문에, 해당 액티비티에서 Hilt버전을 주석처리하고, Koin 버전의 주석을 제거하여 사용할 수 있습니다.

## Blog
Ktor에 대한 설명은 다음 블로그에 작성해 두었습니다.

[Tistory Blog](https://heegs.tistory.com/133 "Ktor Example")
